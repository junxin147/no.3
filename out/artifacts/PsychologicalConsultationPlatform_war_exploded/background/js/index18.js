var nameArr = [];
var valueArr = [];

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
    });
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
    });
    var data1=$("#start").val();
    var data2=$('#end').val();
        myajax("/PsychologicalConsultationPlatform/CountServlet?methodName=channelCount",
        {data1: data1,data2:data2},
        function (msg) {
            var arr = JSON.parse(msg);
            var num = 0;
            nameArr = [];
            valueArr = [];
            for (var i = 0; i < arr.length; i++) {
                // 普通柱状图使用的数据
                nameArr.push(arr[i].name);
                valueArr.push(arr[i].count);
                num = num + arr[i].count;
            }
            $("#mytext").text("共计预约总量为" + num + "名");
            createEchars("渠道统计量");// 创建普通柱状图
        });

});



function dateCount() {
    var data1=$("#start").val();
    var data2=$('#end').val();
    myajax("/PsychologicalConsultationPlatform/CountServlet?methodName=channelCount",
        {data1: data1,data2:data2},
        function (msg) {
            var arr = JSON.parse(msg);
            var num = 0;
            nameArr = [];
            valueArr = [];
            for (var i = 0; i < arr.length; i++) {
                // 普通柱状图使用的数据
                nameArr.push(arr[i].name);
                valueArr.push(arr[i].count);
                num = num + arr[i].count;
            }
            $("#mytext").text("该段时间的统计量为" + num + "名");
            createEchars("渠道统计量");// 创建普通柱状图
        });


}

//普通柱状图
function createEchars(tablename) {
    //基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('echarts_div'), 'dark');//dark为暗黑主题 不要可以去掉

    // 指定图表的配置项和数据
    var option = {
        title: {
            text: tablename
        },
        tooltip: {},
        legend: {
            data: ['柱状数据表']
        },
        xAxis: {
            data: nameArr
        },
        yAxis: {},
        series: [{
            name: '统计量',
            type: 'bar',
            data: valueArr
        }],
    };
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
}
