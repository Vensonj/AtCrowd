// 执行分页，生成页面效果，任何时候调用这个函数都会重新加载页面
function generatePage(){
    // 1、获取分页数据
    const pageInfo = getPageInfoRemote();
    // 2、填充表格
    fillTBody(pageInfo);
}
// 远程访问服务器端程序获取pageInfo数据
function getPageInfoRemote(){
     $.ajax({
        url:"role/get/page/info1",
        type:"post",
        data:{
            pageNum:window.pageNum,
            pageSize:window.pageSize,
            keyword:window.keyword
        },
        dataType:"json",
        success:function (response){
            // console.log(response)
            const pageInfo = response.data;
            fillTBody(pageInfo);
        },
        error:function (response){
            layer.msg("连接失败,URL错误或其他资源错误，请仔细检查")
        }
    })

}
// 填充表格
function fillTBody(pageInfo){

}
// 生成分页页码导航条
function generateNavigator(pageInfo){

}
// 翻页时的回调函数
function paginationCallBack(pageIndex,JQuery){

}