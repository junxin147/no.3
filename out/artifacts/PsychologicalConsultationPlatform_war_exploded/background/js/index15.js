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
    $("#score2").blur(function () {
        var mystar=$('#score1').val();
        var myend =$('#score2').val();
        if (mystar==''){
            $('#score1').val($('#score2').val());
        }
        if (myend < mystar) {
            alert('结束分数不能小于开始分数');
            $('#score2').val($('#score1').val());
        }
    })
    $("#score1").blur(function () {
        var mystar=$('#score1').val();
        var myend=$('#score2').val();
        if (myend==''){
            $('#score2').val($('#score1').val());
        }

        if (myend < mystar) {
            alert('结束分数不能小于开始分数');
            $('#score1').val($('#score2').val());
        }
    });

    $("#score1").change(function () {
        var mystar=$('#score1').val();
        var myend=$('#score2').val();
        if (myend==''){
            $('#score2').val($('#score1').val());
        }

        if (myend < mystar) {
            alert('结束分数不能小于开始分数');
            $('#score1').val($('#score2').val());
        }
    });
    $("#score2").change(function () {
        var mystar=$('#score1').val();
        var myend =$('#score2').val();
        if (mystar==''){
            $('#score1').val($('#score2').val());
        }
        if (myend < mystar) {
            alert('结束分数不能小于开始分数');
            $('#score2').val($('#score1').val());
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

function myreportlist(obj) {
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
                content: '/PsychologicalConsultationPlatform/MySubjectServlet?methodName=myReport&myid='+myid,

            }
        );
    })


}
