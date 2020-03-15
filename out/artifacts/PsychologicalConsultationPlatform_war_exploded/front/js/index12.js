function myreport(obj) {
    var myid=$(obj).val();

    layui.use("layer", function () {
        var layer = layui.layer;
        layer.open({
                id: 'myOpen',
                type: 2,
                title: '查看报告详情',
                shadeClose: false,
                shade: false,
                maxmin: true, //开启最大化最小化按钮
                area: ['653px', '500px'],
                btn: ['关闭'],
                content: '/PsychologicalConsultationPlatform/MyreportServlet?methodName=myReport&myid='+myid,

            }
        );
    })


}
var fromNode;
var path;
var startNode;
window.onload = function () {
    startNode = document.getElementById("pageTurn").value;
    fromNode = document.getElementById("form3");
};

function pageup() {
    startNode = parseInt(startNode) - 1;
    document.getElementById("pageTurn").value = startNode;
    fromNode.submit();//表单提交
}

function pagedown() {
    startNode = parseInt(startNode) + 1;
    document.getElementById("pageTurn").value = startNode;
    fromNode.submit();//表单提交
}