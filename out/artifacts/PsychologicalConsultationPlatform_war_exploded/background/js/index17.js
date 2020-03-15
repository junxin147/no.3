var nameArr = [];
var valueArr = [];
var obArr = [];

$(function () {
    myajax("/PsychologicalConsultationPlatform/CountServlet?methodName=myweek",
        {myweek: "myweek"},
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
            $("#mytext").text("本周用户新增" + num + "名");
            createEchars("本周用户新增量");// 创建普通柱状图
        });
    $("#myweek").click(function () {
        myajax("/PsychologicalConsultationPlatform/CountServlet?methodName=myweek",
            {myweek: "myweek"},
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
                $("#mytext").text("本周用户新增" + num + "名");
                createEchars("本周用户新增量");// 创建普通柱状图
            })
    });

    $("#mymonth").click(function () {
        myajax("/PsychologicalConsultationPlatform/CountServlet?methodName=mymonth",
            {mymonth: "mymonth"},
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
                $("#mytext").text("本月用户新增" + num + "名");
                createEchars("本月用户新增量");// 创建普通柱状图

            });
    });

    $("#myyear").click(function () {
        myajax("/PsychologicalConsultationPlatform/CountServlet?methodName=myyear",
            {myyear: "myyear"},
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
                $("#mytext").text("近半年内用户新增" + num + "名");
                createEchars("近半年内用户新增量");// 创建普通柱状图


            });
        }
    )
});

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
