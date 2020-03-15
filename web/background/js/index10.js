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

function getIfo(obj) {
    var myhour = $(obj).parents("tr").find(".myhour").text();
    var mydate = $(obj).parents("tr").find(".mydate").text();
    var staffname = $(obj).parents("tr").find(".name").text();
    var useraccount = $(obj).parents("tr").find(".useraccount").text();
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
                content: '/PsychologicalConsultationPlatform/AppointmentServlet?methodName=mygetinfo&myhour='+
                    myhour+'&mydate='+mydate+'&staffname='+staffname+
                    '&useraccount='+useraccount,
                success: function (layero, index) {
                    //获取IFRAME窗口
                    var iframe = window['layui-layer-iframe' + index];
                },
            });
        }
    );
}
function myover(obj) {
    var myhour = $(obj).parents("tr").find(".myhour").text();
    var mydate = $(obj).parents("tr").find(".mydate").text();
    var staffname = $(obj).parents("tr").find(".name").text();
    var useraccount = $(obj).parents("tr").find(".useraccount").text();
    myajax("/PsychologicalConsultationPlatform/AppointmentServlet?methodName=cancelAppointment",
        {myhour:myhour, mydate:mydate, staffname:staffname,useraccount:useraccount},
        function (msg) {
            var megob = JSON.parse(msg);
            if (megob.msg == "success") {
                alert("确认终止成功");
                $(obj).parents("tr").find(".mystage").text("已终止");
                var fanode=$(obj).parent();
                $(obj).remove();
            } else if (megob.msg == "false") {
                alert("异常情况，请稍后再试")
            }  else if (megob.msg == "returnlogin") {
                alert("你的账号有异常情况，请联系后台管理员");
                parent.window.location.replace("/PsychologicalConsultationPlatform/background/jsp/index1.jsp");
            }
        })

}