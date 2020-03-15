var calendarEl;
var calendar;
$(function () {


    // 日历插件初始化
    calendarEl = document.getElementById('calendar');
    myajax("/PsychologicalConsultationPlatform/CalendarServlet?methodName=myDate",
        {schedule: "schedule"},
        function (msg) {
            var megob = JSON.parse(msg);
            if (megob.msg === "returnlogin") {
                alert("账号有异常情况，请联系后台");
                parent.window.location.replace("/PsychologicalConsultationPlatform/background/jsp/index1.jsp");
            } else {

                // 创建新的日程最好还是在AJAX回传结果后在来创建
                for (var i = 0; i < megob.mylist.length; i++) {
                    calendar.addEvent({
                            title: megob.mylist[i].staff_name,// 日程标题
                            start: new Date(megob.mylist[i].dataday),// 日程起始时间
                            end: new Date(megob.mylist[i].dataday),// 日程结束时间
                            allDay: true
                            // 日程是否全天
                        }
                    );
                }
            }
        });
    // calendarEl=$("#calendar");
    calendar = new FullCalendar.Calendar(calendarEl, {
        plugins: ['interaction', 'dayGrid'],
        defaultDate: new Date(),
        editable: true,
        eventLimit: true, // allow "more" link when too many events
        navLinks: false, // 开启单击天/周名称导航视图
        selectable: true,
        selectMirror: true,
        select: function (arg) {
            $("#starttime").val(new Date(arg.start).format('yyyy-MM-dd'));// 使用时间格式转换时间填入Dialog
            // $("#endtime").val(new Date(arg.end).format('yyyy-MM-dd'));
            $("#caidan").dialog("open");
            calendar.unselect();
        },
        eventClick: function (info) {
            var myname = $("#groups").val();
            var mydate = new Date(info.event.start).format('yyyy-MM-dd');//获取某一天数据
            $("#label_1").attr("class", "ui-checkboxradio-label ui-corner-all ui-button ui-widget");
            $("#label_2").attr("class", "ui-checkboxradio-label ui-corner-all ui-button ui-widget");
            $("#label_3").attr("class", "ui-checkboxradio-label ui-corner-all ui-button ui-widget");
            $("#label_4").attr("class", "ui-checkboxradio-label ui-corner-all ui-button ui-widget");
            $("#label_5").attr("class", "ui-checkboxradio-label ui-corner-all ui-button ui-widget");
            $("#label_6").attr("class", "ui-checkboxradio-label ui-corner-all ui-button ui-widget");
            $("#label_1").css("color", "white");
            $("#label_2").css("color", "white");
            $("#label_3").css("color", "white");
            $("#label_4").css("color", "white");
            $("#label_5").css("color", "white");
            $("#label_6").css("color", "white");

            myajax("/PsychologicalConsultationPlatform/CalendarServlet?methodName=myDateHour",
                {myname: myname, mydate: mydate},
                function (msg) {
                    var megob = JSON.parse(msg);
                    if (megob.msg === "returnlogin") {
                        alert("账号有异常情况，请联系后台");
                        parent.window.location.replace("/PsychologicalConsultationPlatform/background/jsp/index1.jsp");
                    } else {
                        for (var i = 0; i < megob.mygetlist.length; i++) {
                            var myhour = megob.mygetlist[i].hour;
                            if (megob.mygetlist[i].appointment_Stage === "未预约" ||
                                megob.mygetlist[i].appointment_Stage === "已终止") {
                                if (myhour === "9") {

                                    $("#label_1").attr("class", "ui-checkboxradio-label ui-corner-all ui-button ui-widget ui-checkboxradio-checked ui-state-active");
                                    $("#label_1").attr("onclick", "return true");
                                    $("#label_1").css("color", "white");

                                }
                                if (myhour === "10") {
                                    $("#label_2").attr("class", "ui-checkboxradio-label ui-corner-all ui-button ui-widget ui-checkboxradio-checked ui-state-active");
                                    $("#label_2").attr("onclick", "return true");
                                    $("#label_2").css("color", "white");
                                }
                                if (myhour === "11") {
                                    $("#label_3").attr("class", "ui-checkboxradio-label ui-corner-all ui-button ui-widget ui-checkboxradio-checked ui-state-active");
                                    $("#label_3").attr("onclick", "return true");
                                    $("#label_3").css("color", "white");

                                }
                                if (myhour === "14") {
                                    $("#label_4").attr("class", "ui-checkboxradio-label ui-corner-all ui-button ui-widget ui-checkboxradio-checked ui-state-active");
                                    $("#label_4").attr("onclick", "return true");
                                    $("#label_4").css("color", "white");

                                }
                                if (myhour === "15") {
                                    $("#label_5").attr("class", "ui-checkboxradio-label ui-corner-all ui-button ui-widget ui-checkboxradio-checked ui-state-active");
                                    $("#label_5").attr("onclick", "return true");
                                    $("#label_5").css("color", "white");

                                }
                                if (myhour === "16") {
                                    $("#label_6").attr("class", "ui-checkboxradio-label ui-corner-all ui-button ui-widget ui-checkboxradio-checked ui-state-active");
                                    $("#label_6").attr("onclick", "return true");
                                    $("#label_6").css("color", "white");
                                }
                            } else {
                                if (myhour === "9") {
                                    $("#label_1").attr("class", "ui-checkboxradio-label ui-corner-all ui-button ui-widget ui-checkboxradio-checked ui-state-active");
                                    $("#label_1").attr("onclick", "return false");
                                    $("#label_1").css("color", "red");
                                }
                                if (myhour === "10") {
                                    $("#label_2").attr("class", "ui-checkboxradio-label ui-corner-all ui-button ui-widget ui-checkboxradio-checked ui-state-active");
                                    $("#label_2").attr("onclick", "return false");
                                    $("#label_2").css("color", "red");
                                }
                                if (myhour === "11") {
                                    $("#label_3").attr("class", "ui-checkboxradio-label ui-corner-all ui-button ui-widget ui-checkboxradio-checked ui-state-active");
                                    $("#label_3").attr("onclick", "return false");
                                    $("#label_3").css("color", "red");
                                }
                                if (myhour === "14") {
                                    $("#label_4").attr("class", "ui-checkboxradio-label ui-corner-all ui-button ui-widget ui-checkboxradio-checked ui-state-active");
                                    $("#label_4").attr("onclick", "return false");
                                    $("#label_4").css("color", "red");
                                }
                                if (myhour === "15") {
                                    $("#label_5").attr("class", "ui-checkboxradio-label ui-corner-all ui-button ui-widget ui-checkboxradio-checked ui-state-active");
                                    $("#label_5").attr("onclick", "return false");
                                    $("#label_5").css("color", "red");
                                }
                                if (myhour === "16") {
                                    $("#label_6").attr("class", "ui-checkboxradio-label ui-corner-all ui-button ui-widget ui-checkboxradio-checked ui-state-active");
                                    $("#label_6").attr("onclick", "return false");
                                    $("#label_6").css("color", "red");
                                }

                            }
                        }

                    }
                });

            $("#label_1").click(function () {
                myajax("/PsychologicalConsultationPlatform/CalendarServlet?methodName=myDateHour",
                    {myname: myname, mydate: mydate},
                    function (msg) {
                        var megob = JSON.parse(msg);
                        if (megob.msg === "returnlogin") {
                            alert("账号有异常情况，请联系后台");
                            parent.window.location.replace("/PsychologicalConsultationPlatform/background/jsp/index1.jsp");
                        } else {
                            for (var i = 0; i < megob.mygetlist.length; i++) {
                                var myhour = megob.mygetlist[i].hour;
                                if (myhour === "9") {
                                    if (!(megob.mygetlist[i].appointment_Stage === "未预约" ||
                                        megob.mygetlist[i].appointment_Stage === "已终止")) {
                                        $("#label_1").attr("class", "ui-checkboxradio-label ui-corner-all ui-button ui-widget ui-checkboxradio-checked ui-state-active");
                                        $("#label_1").attr("onclick", "return false");
                                        $("#label_1").css("color", "red");
                                        alert("已经被预约了")
                                    } else {
                                        $("#label_1").attr("onclick", "return true");
                                        $("#label_1").css("color", "white");

                                    }
                                }
                            }
                        }
                    });
            });
            $("#label_2").click(function () {
                myajax("/PsychologicalConsultationPlatform/CalendarServlet?methodName=myDateHour",
                    {myname: myname, mydate: mydate},
                    function (msg) {
                        var megob = JSON.parse(msg);
                        if (megob.msg === "returnlogin") {
                        alert("账号有异常情况，请联系后台");
                        parent.window.location.replace("/PsychologicalConsultationPlatform/background/jsp/index1.jsp");
                    } else {
                        for (var i = 0; i < megob.mygetlist.length; i++) {
                            var myhour = megob.mygetlist[i].hour;
                            if (myhour === "10") {
                                if (!(megob.mygetlist[i].appointment_Stage === "未预约" ||
                                    megob.mygetlist[i].appointment_Stage === "已终止")) {
                                    $("#label_2").attr("class", "ui-checkboxradio-label ui-corner-all ui-button ui-widget ui-checkboxradio-checked ui-state-active");
                                    $("#label_2").attr("onclick", "return false");
                                    $("#label_2").css("color", "red");

                                    alert("已经被预约了")
                                } else {
                                    $("#label_2").attr("onclick", "return true");
                                    $("#label_2").css("color", "white");

                                }
                            }
                        }
                    }
                }
            );
            });
            $("#label_3").click(function () {
                myajax("/PsychologicalConsultationPlatform/CalendarServlet?methodName=myDateHour",
                    {myname: myname, mydate: mydate},
                    function (msg) {
                        var megob = JSON.parse(msg);
                        if (megob.msg === "returnlogin") {
                            alert("账号有异常情况，请联系后台");
                            parent.window.location.replace("/PsychologicalConsultationPlatform/background/jsp/index1.jsp");
                        } else {
                            for (var i = 0; i < megob.mygetlist.length; i++) {
                                var myhour = megob.mygetlist[i].hour;
                                if (myhour === "11") {
                                    if (!(megob.mygetlist[i].appointment_Stage === "未预约" ||
                                        megob.mygetlist[i].appointment_Stage === "已终止")) {
                                        $("#label_3").attr("class", "ui-checkboxradio-label ui-corner-all ui-button ui-widget ui-checkboxradio-checked ui-state-active");
                                        $("#label_3").attr("onclick", "return false");
                                        $("#label_3").css("color", "red");
                                        alert("已经被预约了")
                                    } else {
                                        $("#label_3").attr("onclick", "return true");
                                        $("#label_3").css("color", "white");

                                    }
                                }
                            }
                        }
                    }
                );
            });
            $("#label_4").click(function () {
                myajax("/PsychologicalConsultationPlatform/CalendarServlet?methodName=myDateHour",
                    {myname: myname, mydate: mydate},
                    function (msg) {
                        var megob = JSON.parse(msg);
                        if (megob.msg === "returnlogin") {
                            alert("账号有异常情况，请联系后台");
                            parent.window.location.replace("/PsychologicalConsultationPlatform/background/jsp/index1.jsp");
                        } else {
                            for (var i = 0; i < megob.mygetlist.length; i++) {
                                var myhour = megob.mygetlist[i].hour;
                                if (myhour === "14") {
                                    if (!(megob.mygetlist[i].appointment_Stage === "未预约" ||
                                        megob.mygetlist[i].appointment_Stage === "已终止")) {
                                        $("#label_4").attr("class", "ui-checkboxradio-label ui-corner-all ui-button ui-widget ui-checkboxradio-checked ui-state-active");
                                        $("#label_4").attr("onclick", "return false");
                                        $("#label_4").css("color", "red");

                                        alert("已经被预约了")
                                    } else {
                                        $("#label_4").attr("onclick", "return true");
                                        $("#label_4").css("color", "white");
                                    }
                                }
                            }
                        }
                    }
                );
            });
            $("#label_5").click(function () {
                myajax("/PsychologicalConsultationPlatform/CalendarServlet?methodName=myDateHour",
                    {myname: myname, mydate: mydate},
                    function (msg) {
                        var megob = JSON.parse(msg);
                        if (megob.msg === "returnlogin") {
                            alert("账号有异常情况，请联系后台");
                            parent.window.location.replace("/PsychologicalConsultationPlatform/background/jsp/index1.jsp");
                        } else {
                            for (var i = 0; i < megob.mygetlist.length; i++) {
                                var myhour = megob.mygetlist[i].hour;
                                if (myhour === "15") {
                                    if (!(megob.mygetlist[i].appointment_Stage === "未预约" ||
                                        megob.mygetlist[i].appointment_Stage === "已终止")) {
                                        $("#label_5").attr("class", "ui-checkboxradio-label ui-corner-all ui-button ui-widget ui-checkboxradio-checked ui-state-active");
                                        $("#label_5").attr("onclick", "return false");
                                        $("#label_5").css("color", "red");
                                        alert("已经被预约了")
                                    } else {
                                        $("#label_5").attr("onclick", "return true");
                                        $("#label_5").css("color", "white");

                                    }
                                }
                            }
                        }
                    }
                );
            });
            $("#label_6").click(function () {
                myajax("/PsychologicalConsultationPlatform/CalendarServlet?methodName=myDateHour",
                    {myname: myname, mydate: mydate},
                    function (msg) {
                        var megob = JSON.parse(msg);
                        if (megob.msg === "returnlogin") {
                            alert("账号有异常情况，请联系后台");
                            parent.window.location.replace("/PsychologicalConsultationPlatform/background/jsp/index1.jsp");
                        } else {
                            for (var i = 0; i < megob.mygetlist.length; i++) {
                                var myhour = megob.mygetlist[i].hour;
                                if (myhour === "16") {
                                    if (!(megob.mygetlist[i].appointment_Stage === "未预约" ||
                                        megob.mygetlist[i].appointment_Stage === "已终止")) {
                                        $("#label_6").attr("class", "ui-checkboxradio-label ui-corner-all ui-button ui-widget ui-checkboxradio-checked ui-state-active");
                                        $("#label_6").attr("onclick", "return false");
                                        $("#label_6").css("color", "red");
                                        alert("已经被预约了")
                                    } else {
                                        $("#label_6").attr("onclick", "return true");
                                        $("#label_6").css("color", "white");

                                    }

                                }

                            }
                        }
                    });
            });

            $("#sameday").dialog({
                autoOpen: false,
                modal: true,
                width: 550,
                height: 370,
                draggable: false,
                resizable: false,
                buttons: [{
                    text: "删除",
                    icon: "ui-icon-heart",
                    click: function () {
                        myajax("/PsychologicalConsultationPlatform/CalendarServlet?methodName=myDeleteDate",
                            {myname: myname, mydate: mydate},
                            function (msg) {
                                var megob = JSON.parse(msg);
                                if (megob.msg === "returnlogin") {
                                    alert("账号有异常情况，请联系后台");
                                    parent.window.location.replace("/PsychologicalConsultationPlatform/background/jsp/index1.jsp");
                                } else if (megob.msg === "success") {
                                    alert("删除成功");
                                    info.event.remove();
                                    window.location.reload();
                                } else {
                                    alert("异常情况，无法进行删除操作");
                                    window.location.reload();
                                }

                            });

                        $(this).dialog("close");// dialog关闭

                    }
                }, {
                    text: "确定",
                    icon: "ui-icon-heart",
                    click: function () {
                        var mylable = $('.ui-checkboxradio-checked');
                        var a = new Array();
                        for (var i = 0; i < mylable.length; i++) {
                            var flag = mylable[i].getAttribute("onclick");
                            if (mylable[i].innerText == "09:00-10:00" && flag == "return true") {
                                a.push(9)
                            }
                            if (mylable[i].innerText == "10:00-11:00" && flag == "return true") {
                                a.push(10)
                            }
                            if (mylable[i].innerText == "11:00-12:00" && flag == "return true") {
                                a.push(11)

                            }
                            if (mylable[i].innerText == "14:00-15:00" && flag == "return true") {
                                a.push(14)
                            }
                            if (mylable[i].innerText == "15:00-16:00" && flag == "return true") {
                                a.push(15)
                            }
                            if (mylable[i].innerText == "16:00-17:00" && flag == "return true") {
                                a.push(16)
                            }
                        }
                        var timelist = JSON.stringify(a);

                        myajax("/PsychologicalConsultationPlatform/CalendarServlet?methodName=myLoad",
                            {mydate: mydate, timelist: timelist},
                            function (msg) {
                                var megob = JSON.parse(msg);
                                if (megob.msg === "success") {
                                    // $(this).dialog("close");// dialog关闭
                                    alert(" 保存成功");
                                    info.title = $('#groups option:selected').val(),// 日程标题
                                        info.start = new Date($("#starttime").val()),// 日程起始时间
                                        info.end = new Date($("#endtime").val()),// 日程结束时间
                                        info.allDay = true,// 日程是否全天
                                        window.location.reload();
                                } else if (megob.msg === "deletesuccess") {
                                    // $(this).dialog("close");// dialog关闭
                                    alert(" 保存成功,已清除你所选日期的安排");
                                    info.event.remove();
                                    window.location.reload();
                                } else if (megob.msg === "returnlogin") {
                                    alert("你的账号有异常情况，请联系后台管理员");
                                    parent.window.location.replace("/PsychologicalConsultationPlatform/background/jsp/index1.jsp")
                                } else {
                                    alert("异常情况，操作失败");
                                    window.location.reload();
                                }
                            });
                        $(this).dialog("close");
                    }
                }, {
                    text: "取消",
                    icon: "ui-icon-heart",
                    click: function () {
                        $(this).dialog("close");
                        window.location.reload();
                    }
                },],
                show: {
                    effect: "blind",
                    duration: 1000
                },
                hide: {
                    effect: "explode",
                    duration: 1000
                },
                close: function () {
                    $(this).dialog("close");
                    window.location.reload();
                }
            });
            $("#sameday").dialog("open");

            // if (confirm('是否删除这个日程?')) {
            // info.event.remove();
            // }
        },
        validRange:
            {
                // validRange 为控制锁死某些日期 当前日期的下一天即为明天开始可用 之前的日期全部锁死 官网原版写法 为 start
                // end 设定开始和结束日期
                start: new Date(new Date().getTime() + (1000 * 60 * 60 * 24))
            }
    })
    ;

    calendar.render();

    // 初始化dialog
    $("#caidan").dialog({
        autoOpen: false,
        modal: true,
        width: 500,
        height: 250,
        draggable: false,
        resizable: false,
        buttons: [{
            text: "确定",
            icon: "ui-icon-heart",
            click: function () {
                var mydate = $("#starttime").val();
                var myname = $("#groups").val();
                myajax("/PsychologicalConsultationPlatform/CalendarServlet?methodName=myAddDate",
                    {myname: myname, mydate: mydate},
                    function (msg) {
                        var megob = JSON.parse(msg);
                        if (megob.msg === "success") {
                            alert("创建成功");
                            // 创建新的日程最好还是在AJAX回传结果后在来创建
                            calendar.addEvent({
                                title: $('#groups option:selected').val(),// 日程标题
                                start: new Date($("#starttime").val()),// 日程起始时间
                                end: new Date($("#starttime").val()),// 日程结束时间
                                allDay: true
                                // 日程是否全天
                            });
                        } else if (megob.msg === "returnlogin") {
                            alert("你的账号有异常情况，请联系后台管理员");
                            parent.window.location.replace("/PsychologicalConsultationPlatform/background/jsp/index1.jsp")
                        } else {
                            alert("异常情况，操作失败")
                        }
                    });
                $(this).dialog("close");// dialog关闭
            }
        }, {
            text: "取消",
            icon: "ui-icon-heart",
            click: function () {
                $(this).dialog("close");
            }
        },],
        show: {
            effect: "blind",
            duration: 1000
        },
        hide: {
            effect: "explode",
            duration: 1000
        }
    });

    $("#groups").selectmenu();// 下拉列表使用jquery UI样式

    $("#starttime").datepicker({// 日期选择器使用jquery UI样式
        dateFormat: "yy-mm-dd"
    });
    $("#endtime").datepicker({// 日期选择器使用jquery UI样式
        dateFormat: "yy-mm-dd"
    });

    $("input[type='checkbox']").checkboxradio({
        icon: false
    });
    $("#groups2").selectmenu();
});
// 重构Date对象下的时间格式
Date.prototype.format = function (format) {
    var o = {
        "M+": this.getMonth() + 1,
        "d+": this.getDate(),
        "h+": this.getHours(),
        "m+": this.getMinutes(),
        "s+": this.getSeconds(),
        "q+": Math.floor((this.getMonth() + 3) / 3),
        "S": this.getMilliseconds()
    }
    if (/(y+)/.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear() + "")
            .substr(4 - RegExp.$1.length));
    }
    for (var k in o) {
        if (new RegExp("(" + k + ")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
                : ("00" + o[k]).substr(("" + o[k]).length));
        }
    }
    return format;
}


