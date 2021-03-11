// 初始化树形结构的函数
function generateTree() {
    $.ajax({
        url: "menu/get/whole/tree",
        type: "post",
        dataType: "json",
        success: function (response) {
            var result = response.result;
            if (result === "SUCCESS") {
                // 2.创建JSON对象用于存储对zTree所做的设置
                var settings = {
                    view: {
                        addDiyDom: myAddDiyDom,
                        addHoverDom: myAddHoverDom,
                        removeHoverDom: myRemoveHoverDom,
                    },
                    data: {
                        key: {
                            url: "notexist"
                        }
                    }
                };
                // 3.从响应体中获取用来初始化树形结构的数据
                var zNodes = response.data;
                // 4.初始化树形结构
                $.fn.zTree.init($("#treeDemo"), settings, zNodes);

            }
            if (result === "FAILED") {
                layer.msg(response.message)
            }

        },
        error: function (response) {

        }
    })

}

// 在鼠标移入节点范围时添加按钮组
function myAddHoverDom(treeId, treeNode) {
    // 为了在移出按钮组的时候能精确定位按钮组所在的span标签，需要给按钮组所在的span标签添加id属性
    const btnGroupId = treeNode.tId + "_btnGroup";
    // 判断之前是否已经添加过按钮组
    if ($("#" + btnGroupId).length > 0) {
        return;
    }
    // 准备各个按钮的html标签
    var addBtn = "<a id='" + treeNode.id + "' class='addBtn btn btn-info dropdown-toggle btn-xs' style='margin-left:10px;padding-top:0px;' href='#' title='添加子节点'>&nbsp;&nbsp;<i class='fa fa-fw fa-plus rbg '></i></a>";
    var removeBtn = "<a id='" + treeNode.id + "' class='removeBtn btn btn-info dropdown-toggle btn-xs' style='margin-left:10px;padding-top:0px;' href='#' title='删除节点'>&nbsp;&nbsp;<i class='fa fa-fw fa-times rbg '></i></a>";
    var editBtn = "<a id='" + treeNode.id + "' class='editBtn btn btn-info dropdown-toggle btn-xs' style='margin-left:10px;padding-top:0px;' href='#' title='修改节点'>&nbsp;&nbsp;<i class='fa fa-fw fa-edit rbg '></i></a>";
    // 声明一个变量来存储拼接好的按钮html代码
    var btnHtml = "";
    // 获取当前按钮的级别数据
    var level = treeNode.level;
    // 判断当前节点的级别 0-根节点 1-子节点 2-叶子节点
    if (level === 0) {
        // 如果是根节点：只有添加按钮组
        btnHtml = addBtn;
    }
    if (level === 1) {
        // 如果是子节点但是没有叶子节点：三种按钮都拥有。如果有叶子节点：没有删除按钮组
        btnHtml = addBtn + editBtn;
        // 获取当前节点子节点的数量
        var children = treeNode.children.length;
        if (children === 0) {
            btnHtml = addBtn + removeBtn + editBtn;
        }
    }
    if (level === 2) {
        // 如果是叶子节点：没有添加按钮组
        btnHtml = removeBtn + editBtn;
    }
    // 找到添加按钮组的位置
    const anchorId = treeNode.tId + "_a";
    // 执行在超链接后面附加 span 元素的操作
    $("#" + anchorId).after("<span id='" + btnGroupId + "'>" + btnHtml + "</span>")
}

// 在鼠标移出节点范围时删除按钮组
function myRemoveHoverDom(treeId, treeNode) {
    const btnGroupId = treeNode.tId + "_btnGroup";
    $("#" + btnGroupId).remove();
}

// 修改默认的图标
function myAddDiyDom(treeId, treeNode) {
    // treeId 是整个树形结构附着的 ul标签的 id属性
    // treeNode 是当前树形节点的全部数据（从后端查询到的Menu对象的全部属性）
    // zTree生成id的规则
    // 示例：treeDemo_7_ico
    // 解析：ul标签的id_当前节点的序号_功能
    // 提示："ul标签的id_当前节点的序号"可由treeNode的tid属性得到

    // 根据span标签的 id 生成的规则 拼接出 id值
    const spanId = treeNode.tId + "_ico";
    // 根据控制图标的 span 标签的 id 属性 找到这个span标签

    // 删除旧的class

    // 替换新的class
    $("#" + spanId).removeClass().addClass(treeNode.icon);
}