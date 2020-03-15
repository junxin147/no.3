function myOpen() {
    layui.use("layer", function () {
        var layer = layui.layer;
//layer.msg("大家好，这是最简单的弹层", { time: 9000, type: 1,title:['测试一下','font-size:18px'] });
        layer.open({
            id: 'myOpen',
            offset: 10,
            type: 2,
            title: '我要预约',
            shadeClose: false,
            shade: 0.4,
            btn: ['提交', '取消'],
            maxmin: false, //开启最大化最小化按钮
            area: ['893px', '500px'],
       // /PsychologicalConsultationPlatform/front/jsp/index5.jsp
            content: '/PsychologicalConsultationPlatform/MyLinkServlet?methodName=myapp',
            yes: function (index, layero) {
                // 获取iframe层的body
                var body = layer.getChildFrame('body', index);
                // 找到隐藏的提交按钮模拟点击提交
                body.find('#mysure').click();

                layer.close(index); //关闭弹窗
                layer.msg('正在处理添加信息中', {icon: 1});
                layer.close(index);
                setTimeout(function () {
                    location.reload(true);
                }, 1000);
            }, btn2: function (index, layero) {
                layer.close(index);
            },
        });
    });
}

function myappraise(obj) {
    var mystaffname=$(obj).parents("tr").find(".myname").text();
    var mydate=$(obj).parents("tr").find(".mydate").text();
    var myhour=  $(obj).parents("tr").find(".myhour").text();
    layui.use("layer", function () {
        var layer = layui.layer;
        layer.open({
            id: 'myOpen',
            offset: 10,
            type: 2,
            title: '评价',
            shadeClose: false,
            shade: 0.4,
            btn: ['提交', '取消'],
            maxmin: false, //开启最大化最小化按钮
            area: ['893px', '500px'],
            content: '/PsychologicalConsultationPlatform/MyAppointmentServlet?methodName=myinfo&myhour='+
                myhour+'&mydate='+mydate+'&mystaffname='+mystaffname,
            yes: function (index, layero) {
                // 获取iframe层的body
                var body = layer.getChildFrame('body', index);
                // 找到隐藏的提交按钮模拟点击提交
                body.find('#myappraise').click();

                layer.close(index); //关闭弹窗
                layer.msg('正在处理添加信息中', {icon: 1});
                layer.close(index);
                setTimeout(function(){
                    location.reload(true);
                },1000);
            }, btn2: function (index, layero) {
                layer.close(index);
            },
        });
    })
}

function myinfo(obj){
    var mystaffname=$(obj).parents("tr").find(".myname").text();
    var mydate=$(obj).parents("tr").find(".mydate").text();
    var myhour=  $(obj).parents("tr").find(".myhour").text();
    layui.use("layer", function () {
        var layer = layui.layer;
        layer.open({
            id: 'myOpen',
            offset: 10,
            type: 2,
            title: '评价',
            shadeClose: false,
            shade: 0.4,
            btn: ['返回'],
            maxmin: false, //开启最大化最小化按钮
            area: ['893px', '500px'],
            content: '/PsychologicalConsultationPlatform/MyAppointmentServlet?methodName=myappointmentlist' +
                '&myhour='+
                myhour+'&mydate='+mydate+'&mystaffname='+mystaffname,
        });
    })
}

function staffinfo(obj) {
    var mystaffname=$(obj).parents("tr").find(".myname").text();
    var mydate=$(obj).parents("tr").find(".mydate").text();
    var myhour=  $(obj).parents("tr").find(".myhour").text();
    layui.use("layer", function () {
        var layer = layui.layer;
        layer.open({
            id: 'myOpen',
            offset: 10,
            type: 2,
            title: '评价',
            shadeClose: false,
            shade: 0.4,
            btn: ['返回'],
            maxmin: false, //开启最大化最小化按钮
            area: ['893px', '500px'],
            content: '/PsychologicalConsultationPlatform/MyAppointmentServlet?methodName=staffinfo' +
                '&myhour='+
                myhour+'&mydate='+mydate+'&mystaffname='+mystaffname,

        });
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

