// 创建用于装载auth树形结构的数据函数
function fillAuthTree() {
    // 1、发送 Ajax 请求 Auth 数据
    var ajaxResult = $.ajax({
        "url": "assign/getAllAuth",
        "type": "POST",
        "dataType": "JSON",
        "async": false,
    });

    if (ajaxResult.status !== 200) {
        layer.msg("请求处理出错!响应状态码是：" + ajaxResult.status + "说明是：" + ajaxResult.statusText);
        return;
    }
    // 2、从响应结果中获取 auth 的json数据,并将数据交给 zTree 组装
    var authList = ajaxResult.responseJSON.data;

    // 3、对zTree进行组装的设置，默认是不组装，即为false
    var setting = {
        "data": {
            "simpleData": {
                "enable": true,
                "idKey": "id",
                // 使用 categoryId 关联父节点，取代 默认的 pId
                "pIdKey": "categoryId"
            },
            "key": {
                // 使用 title 属性替换默认的 name 属性
                "name": "title"
            }
        },
        "check": {
            "enable": true,
        }
    };
    // 4、生成树形结构
    $.fn.zTree.init($("#authTreeDemo"), setting, authList);

    // 调用 zTreeObj 对象的方法，展开所有的节点
    var zTreeObj = $.fn.zTree.getZTreeObj("authTreeDemo");
    zTreeObj.expandAll(true);

    // 5、查询已分配的 auth 的 id 组成的数组
    var ajaxReturn = $.ajax({
        "url": "assign/getAssignedAuthIdByRoleId",
        "type": "POST",
        "data": {
            "roleId": window.roleId
        },
        "dataType": "JSON",
        "async": false,
    })

    if (ajaxReturn.status !== 200) {
        layer.msg("请求处理出错!响应状态码是：" + ajaxReturn.status + "说明是：" + ajaxReturn.statusText);
        return;
    }
    var authIdList = ajaxReturn.responseJSON.data;

    // 6、根据数组中的内容将树形结构中相应的节点勾选
    for (let i = 0; i < authIdList.length; i++) {
        var authId = authIdList[i];

        var treeNode = zTreeObj.getNodeByParam("id", authId);
        // checked 设置为 true 表示节点勾选
        // checkTypeFlag 设置为 false 表示 不联动，即是为了避免勾选了不该勾选的
        var checked = true;
        var checkTypeFlag = false;
        zTreeObj.checkNode(treeNode, checked, checkTypeFlag);

    }
}

// 创建用于显示确认删除角色信息时的提示模态框
function confirmDelete(roleArray) {
    // 打开模态框
    $("#confirmModal").modal("show");
    //清空旧数据
    $("#confirmDeleteMessage").empty();

    //全局变量创建数组来处存待删除的角色数组
    window.roleIdArray = [];

    //遍历roleArray数组并将数组中的元素添加到要显示的选择器中
    for (var i = 0; i < roleArray.length; i++) {
        var role = roleArray[i];
        var roleId = role.roleId;
        var roleName = role.roleName;
        $("#confirmDeleteMessage").append(roleName + "<br/>")
        window.roleIdArray.push(roleId);
    }
}


// 执行分页，生成页面效果，任何时候调用这个函数都会重新加载页面
function generatePage() {
    // 1、获取分页数据
    const pageInfo = getPageInfoRemote();
    // 2、填充表格
    fillTBody(pageInfo);
}

// 远程访问服务器端程序获取pageInfo数据
function getPageInfoRemote() {
    $.ajax({
        url: "role/get/page/info",
        type: "post",
        data: {
            pageNum: window.pageNum,
            pageSize: window.pageSize,
            keyword: window.keyword
        },
        dataType: "json",
        success: function (response) {
            // console.log(response)
            const pageInfo = response.data;
            fillTBody(pageInfo);
        },
        error: function (response) {
            layer.msg("连接失败,URL错误或其他资源错误，请仔细检查")
        }
    })

}

// 填充表格
function fillTBody(pageInfo) {
    // 清除tbody中旧的内容
    $("#rolePageBody").empty();
    $("#Pagination").empty();
    // 判断pageInfo是否为空
    if (pageInfo == null || pageInfo.list == null || pageInfo.list.length === 0) {
        $("#rolePageBody").append("<tr><td colspan='4'>抱歉！没有查询到您要搜索的数据！</td></tr>");
        return;
    }
    // 使用 pageInfo.list 属性填充 tbody
    for (var i = 0; i < pageInfo.list.length; i++) {
        var role = pageInfo.list[i];
        var roleId = role.id;
        var roleName = role.name;
        var numberTd = "<td>" + (i + 1) + "</td>";
        var checkboxTd = "<td><input id='" + roleId + "' class='itemBox' type='checkbox'></td>";
        var roleNameTd = "<td>" + roleName + "</td>";
        var checkBtn = "<button id='" + roleId + "' type='button' class='btn btn-success btn-xs checkBtn'><i class='glyphicon glyphicon-check'></i></button>";
        var pencilBtn = "<button id='" + roleId + "' type='button' class='btn btn-primary btn-xs pencilBtn'><i class='glyphicon glyphicon-pencil'></i></button>";
        var removeBtn = "<button id='" + roleId + "' type='button' class='btn btn-danger btn-xs removeBtn'><i class='glyphicon glyphicon-remove'></i></button>";
        var buttonTd = "<td>" + checkBtn + " " + pencilBtn + " " + removeBtn + "</td>";
        var tr = "<tr>" + numberTd + checkboxTd + roleNameTd + buttonTd + "</tr>";
        $("#rolePageBody").append(tr);
    }
    //生成分页导航条
    generateNavigator(pageInfo);
}

// 生成分页页码导航条
function generateNavigator(pageInfo) {
    //获取总记录数
    let totalRecord = pageInfo.total;
    //声明一个JSON对象存储Pagination要设置的属性
    const properties = {
        num_edge_entries: 3,                                     // 边缘页数
        num_display_entries: 5,                                  // 主题页数
        callback: paginationCallBack,                            // 回调函数
        items_per_page: pageInfo.pageSize,                       // 每页显示记录数
        current_page: pageInfo.pageNum - 1,                      // Pagination内部使用pageIndex来管理页码，pageIndex从0开始，而pageNum从1开始
        prev_text: "上一页",                                      // 上一页按钮上要显示的文本
        next_text: "下一页"                                       // 下一页按钮上要显示的文本
    };
    //生成页码导航条
    $("#Pagination").pagination(totalRecord, properties)

}

// 翻页时的回调函数
function paginationCallBack(pageIndex, JQuery) {
    // 修改 window 对象的 pageNum 属性
    window.pageNum = pageIndex + 1;
    // 调用分页函数
    generatePage();
    // 取消页码超链接的默认行为
    return false;
}