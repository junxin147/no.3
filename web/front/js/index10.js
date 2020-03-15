function myassess(obj) {
    var mydomain=$("#domain").val();
    layui.use("layer", function () {
        var layer = layui.layer;
        layer.open({
                id: 'myOpen',
                type: 2,
                title: '评测详情',
                shadeClose: false,
                shade: false,
                maxmin: true, //开启最大化最小化按钮
                area: ['653px', '500px'],
                btn: ['关闭'],
                content: '/PsychologicalConsultationPlatform/UserSubjectServlet?methodName=mysubject&domain='+mydomain,

            }
        );
    })
}