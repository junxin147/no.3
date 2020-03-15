$(function () {

    layui.use('form', function () {
        var form = layui.form;

        form.render();
        form.on('select(domain)', function (data) {

            var val = data.value;
            myajax("/PsychologicalConsultationPlatform/MyAppointmentServlet?methodName=domainStaff",
                {domain: val}, function (msg) {
                    var megob = JSON.parse(msg);
                    if (megob.msg === "success") {
                        var mystaff = $("#staffname");
                        mystaff.empty();
                        mystaff.append(new Option("请选择咨询师"));
                        for (var i = 0; i < megob.mystafflist.length; i++) {
                            var myname = megob.mystafflist[i].staff_Name;
                            mystaff.append(new Option(myname, myname));
                        }
                        form.render();

                    } else if (megob.msg === "returnlogin") {
                        alert("你的账号有异常情况");
                        parent.parent.window.location.replace("/PsychologicalConsultationPlatform/front/jsp/index2.jsp");
                    } else {
                        alert("该领域暂时没有专家，后续再留意")
                    }
                    $("#start").val("");
                    $("#mycheckbox9").attr("hidden", "hidden");
                    $("#mycheckbox10").attr("hidden", "hidden");
                    $("#mycheckbox11").attr("hidden", "hidden");
                    $("#mycheckbox14").attr("hidden", "hidden");
                    $("#mycheckbox15").attr("hidden", "hidden");
                    $("#mycheckbox16").attr("hidden", "hidden");
                    $("#mytable").attr("hidden", "hidden");
                    $("#mydiv").attr("hidden", "hidden");
                })
        });
        form.on('select(staffname)', function (data) {

            var val = data.value;
            var domain = $("#domain").val();
            myajax("/PsychologicalConsultationPlatform/MyAppointmentServlet?methodName=staffIfo",
                {name: val, domain: domain}, function (msg) {
                    var megob = JSON.parse(msg);
                    $("#mytable").removeAttr("hidden");
                    $("#mydiv").removeAttr("hidden");
                    if (megob.msg === "success") {
                        var mysname = megob.staff.staff_Name;
                        var myschool = megob.staff.staff_School;
                        var myAwarded = megob.staff.staff_Awarded;
                        var my_Domain = megob.staff.staff_Domain;
                        var mycost = megob.staff.cost;
                        var myResume = megob.staff.staff_Resume;
                        $("#myname").text(mysname);
                        $("#school").text(myschool);
                        $("#aword").text(myAwarded);
                        $("#mydomain").text(my_Domain);
                        $("#mycost").text(mycost + "元/次");
                        $("#backgroud").text(myResume);
                        form.render();
                    } else if (megob.msg === "returnlogin") {
                        alert("你的账号有异常情况");
                        parent.parent.window.location.replace("/PsychologicalConsultationPlatform/front/jsp/index2.jsp");
                    } else {
                        alert("服务器查无信息")
                    }
                    $("#start").val("");
                    $("#mycheckbox9").attr("hidden", "hidden");
                    $("#mycheckbox10").attr("hidden", "hidden");
                    $("#mycheckbox11").attr("hidden", "hidden");
                    $("#mycheckbox14").attr("hidden", "hidden");
                    $("#mycheckbox15").attr("hidden", "hidden");
                    $("#mycheckbox16").attr("hidden", "hidden");

                })

        });
        form.on('checkbox(myhour)', function (data) {
            if (data.elem.checked == true) {
                var val = data.value;
                var date = $("#start").val();
                var len = $(".myhourclass:checked").length;
                if (len > 1) {
                    $(data.elem).next().attr("class", "layui-unselect layui-form-checkbox");
                    $(data.elem).prop("checked", false);
                    layer.msg('最多只能选1项！', {icon: 5});
                    return false;
                } else {
                    myajax("/PsychologicalConsultationPlatform/MyAppointmentServlet?methodName=myDateHour",
                        {date: date},
                        function (msg) {
                            var megob = JSON.parse(msg);
                            if (megob.msg === "returnlogin") {
                                alert("你的账号有异常情况");
                                parent.parent.window.location.replace("/PsychologicalConsultationPlatform/front/jsp/index2.jsp");
                            } else if (megob.msg === "success") {
                                for (var i = 0; i < megob.mygetlist.length; i++) {
                                    var myhour = megob.mygetlist[i].hour;
                                    if (myhour == val) {
                                        if (!(megob.mygetlist[i].appointment_Stage === "未预约" ||
                                            megob.mygetlist[i].appointment_Stage === "已终止")) {
                                           alert("已经被预约了");
                                            $(data.elem).next().attr("class", "layui-unselect layui-form-checkbox");
                                            $(data.elem).prop("checked", false);
                                            $(data.elem).prop("checked", false).prop('disabled', true);
                                        }else {
                                            $(data.elem).next().attr("class", "layui-unselect layui-form-checkbox layui-form-checkbox");
                                            $(data.elem).prop("checked", true);
                                        }
                                    }
                                    layui.use('form', function () {
                                        var form = layui.form;
                                        form.render();
                                    })
                                }
                            } else {
                                alert("异常情况")
                            }
                        });
                }


            }

        });
        // form.on('submit(*)', function(data){
        //
        //     return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
        // });

    });
    layui.use('laydate', function () {
        var laydate = layui.laydate;
        var start = laydate.render({
            elem: '#start', //选择ID为START的input
            format: 'yyyy-MM-dd',
            min: maxDate(),
            done: function (value, date, endDate) {
                myajax("/PsychologicalConsultationPlatform/MyAppointmentServlet?methodName=stafftime ",
                    {date: value}, function (msg) {
                        var megob = JSON.parse(msg);
                        $("#mycheckbox9").attr("hidden", "hidden");
                        $("#mycheckbox10").attr("hidden", "hidden");
                        $("#mycheckbox11").attr("hidden", "hidden");
                        $("#mycheckbox14").attr("hidden", "hidden");
                        $("#mycheckbox15").attr("hidden", "hidden");
                        $("#mycheckbox16").attr("hidden", "hidden");
                        if (megob.msg === "success") {
                            $("input[type=checkbox]").hide();
                            for (var i = 0; i < megob.mygetlist.length; i++) {
                                var mygethour = megob.mygetlist[i].hour;
                                $("#mycheckbox" + mygethour).find(".myhourclass").prop("checked", false);
                                $("#mycheckbox" + mygethour).find(".myhourclass").removeAttr('disabled', true);
                                $("#mycheckbox" + mygethour).removeAttr("hidden");
                                if (!(megob.mygetlist[i].appointment_Stage == "未预约" ||
                                    megob.mygetlist[i].appointment_Stage == "已终止")) {
                                    $("#mycheckbox" + mygethour).find(".myhourclass").prop("checked", false).prop('disabled', true);
                                }
                                layui.use('form', function () {
                                    var form = layui.form;
                                    form.render();
                                })
                            }
                        } else if (megob.msg === "returnlogin") {
                            alert("你的账号有异常情况");
                            parent.parent.window.location.replace("/PsychologicalConsultationPlatform/front/jsp/index2.jsp");
                        } else {
                            $("#mycheckbox9").attr("hidden", "hidden");
                            $("#mycheckbox10").attr("hidden", "hidden");
                            $("#mycheckbox11").attr("hidden", "hidden");
                            $("#mycheckbox14").attr("hidden", "hidden");
                            $("#mycheckbox15").attr("hidden", "hidden");
                            $("#mycheckbox16").attr("hidden", "hidden");
                            alert("服务器查无该日期相关信息")
                        }

                    })


            }
        });
    })
});
function checkForm() {
   var myhour=$("[type='checkbox']");
   var flag=false;
    var mygethour;
   var mygettext=$("#mytext").val();
    for (var i = 0; i <myhour.length; i++) {
         if (myhour[i].checked==true){
              mygethour=(myhour[i].value);
             flag=true;
         }
    }
    if (flag===true&&mygettext.length>0){
        var mydate=$("#start").val();
        myajax("/PsychologicalConsultationPlatform/MyAppointmentServlet?methodName=myAddAppointment ",
        {mygethour: mygethour,mygettext:mygettext,mydate:mydate}, function (msg) {
                var megob = JSON.parse(msg);
                if (megob.msg === "success") {
                    alert("预约成功,为保证咨询师预约成功，请留下充足的余额")
                } else if (megob.msg === "returnlogin") {
                    alert("你的账号有异常情况");
                    parent.parent.window.location.replace("/PsychologicalConsultationPlatform/front/jsp/index2.jsp");
                }else if (megob.msg === "appointmentfalse") {
                    alert("预约失败");
                }   else {
                    alert("异常情况，请稍后再试")
                }
        });

    }else{
        alert("请选择时刻，并描述你的问题");
        return false;
    }

}
// 设置最大可选的日期
function maxDate() {
    var now = new Date();
    return now.getFullYear() + "-" + (now.getMonth() + 1) + "-" + (now.getDate() + 1);
}

