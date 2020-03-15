$(function () {
    layui.use('laydate', function () {
        var laydate = layui.laydate;
        var start = laydate.render({
            elem: '#start', //选择ID为START的input
            format: 'yyyy-MM-dd',
            done: function (value, date, endDate) {
                var myend=$('#end').val();
                if (myend==''){
                    $('#end').val($('#start').val());
                }
                var startDate = new Date(value).getTime();
                var endTime = new Date($('#end').val()).getTime();
                if (endTime < startDate) {
                    alert('结束时间不能小于开始时间');
                    $('#start').val($('#end').val());
                }
            }
        });
        var end = laydate.render({
            elem: '#end',
            format: 'yyyy-MM-dd',
            done: function (value, date, endDate) {
                var mystar=$('#start').val();
                if (mystar==''){
                    $('#start').val($('#end').val());
                }
                var startDate = new Date($('#start').val()).getTime();
                var endTime = new Date(value).getTime();
                if (endTime < startDate) {
                    alert('结束时间不能小于开始时间');
                    $('#end').val($('#start').val());
                }
            }
        });
        // laydate(start);
        // laydate(end);
    })
    $("#start").blur(function () {
        var mystar=$('#start').val();
        var myend=$('#end').val();
        if (myend==''){
            $('#end').val($('#start').val());
        }
        var startDate = new Date(mystar).getTime();
        var endTime = new Date(myend).getTime();
        if (endTime < startDate) {
            alert('结束时间不能小于开始时间');
            $('#start').val($('#end').val());
        }
    });
    $("#end").blur(function () {
        var mystar=$('#start').val();
        var myend =$('#end').val();
        if (mystar==''){
            $('#start').val($('#end').val());
        }
        var startDate = new Date(mystar).getTime();
        var endTime = new Date(myend).getTime();
        if (endTime < startDate) {
            alert('结束时间不能小于开始时间');
            $('#end').val($('#start').val());
        }
    })

});

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


function mysure(obj) {
    var myhour = $(obj).parents("tr").find(".myhour").text();
    var mydate = $(obj).parents("tr").find(".mydate").text();
    var account = $(obj).parents("tr").find(".name").text();
    myajax("/PsychologicalConsultationPlatform/AppointmentServlet?methodName=sureAppointment",
        {myhour: myhour, mydate: mydate, account: account}, function (msg) {
            var megob = JSON.parse(msg);
            if (megob.msg == "success") {
                 alert("确认预约成功");
                 $(obj).parents("tr").find(".mystage").text("已确认");
                 var fanode=$(obj).parent();
                 $(obj).remove();
                 fanode.append("<input type='button'  value='诊断'  onclick='mydiagnosis(this)' " +
                     "   class='layui-btn layui-btn-normal layui-btn-radius'>")
            } else if (megob.msg == "false") {
                alert("异常情况，请稍后再试")
            } else if (megob.msg == "updatafalse") {
                    alert("该预约可能被后台管理终止了，详情请联系后台管理员")
            } else if (megob.msg == "returnlogin") {
                alert("你的账号有异常情况，请联系后台管理员");
                parent.window.location.replace("/PsychologicalConsultationPlatform/background/jsp/index1.jsp");

            }else {
                alert("用户余额不足，无法进行确认预约了，已终止该预约");
                var fanode= $(obj).parent();
                var ob = $(obj).parent().children();
                for (var i = 0; i < ob.length; i++) {
                    ob[i].remove();
                }
                fanode.append(" <input type=\"button\"  value=\"查看详情\"\n" +
                    "                                       class=\"layui-btn layui-btn-disabled layui-btn-radius\">\n" +
                    "                         ");
                $(obj).parents("tr").find([type='button'])

            }
        });
}


function getIfo(obj) {
        var myhour = $(obj).parents("tr").find(".myhour").text();
        var mydate = $(obj).parents("tr").find(".mydate").text();
        var account = $(obj).parents("tr").find(".name").text();
        layui.use("layer", function () {
                var layer = layui.layer;
                layer.open({
                    id: 'myOpen',
                    type: 2,
                    title: '查看预约详情',
                    shadeClose: false,
                    shade: false,
                    maxmin: true, //开启最大化最小化按钮
                    area: ['653px', '500px'],
                    content: '/PsychologicalConsultationPlatform/AppointmentServlet?methodName=myinfo&myhour='+
                        myhour+'&mydate='+mydate+'&account='+account,
                    success: function (layero, index) {
                        //获取IFRAME窗口
                        var iframe = window['layui-layer-iframe' + index];
                    },
                });
            }
        );

}

function mydiagnosis(obj) {
    var myhour = $(obj).parents("tr").find(".myhour").text();
    var mydate = $(obj).parents("tr").find(".mydate").text();
    var account = $(obj).parents("tr").find(".name").text();

    layui.use("layer", function () {
        var layer = layui.layer;
        layer.open({
            id: 'mydiagnosis',
            type: 2,
            title: '诊断详情',
            btn: ['保存并添加', '取消并关闭'],
            shadeClose: false,
            shade: false,
            maxmin: true, //开启最大化最小化按钮
            area: ['653px', '520px'],
            content: '/PsychologicalConsultationPlatform/AppointmentServlet?methodName=mydiagnosis&myhour='+
                myhour+'&mydate='+mydate+'&account='+account,
            yes: function (index, layero) {
                // 获取iframe层的body
                var body = layer.getChildFrame('body', index);
                // 找到隐藏的提交按钮模拟点击提交
                body.find('#myform1').submit();
                layer.close(index); //关闭弹窗
                layer.msg('正在处理添加信息中', {icon: 1});
                setTimeout(function(){
                    location.reload(true);
                },1000);
                layer.close(index);

            },
            success: function (layero, index) {
                //获取IFRAME窗口
                var iframe = window['layui-layer-iframe' + index];
            },
        });
    });
}