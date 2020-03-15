function f1() {
    var myclick = $("#0").find("option:selected").text();
    $('#rpwr').attr('hidden', 'hidden');
    $('#rpri').attr('hidden', 'hidden');
    $('#rpwr0').attr('hidden', 'hidden');
    $('#rpri0').attr('hidden', 'hidden');
    $('#rpwr1').attr('hidden', 'hidden');
    $('#rpri1').attr('hidden', 'hidden');
    $('#rpwr2').attr('hidden', 'hidden');
    $('#rpri2').attr('hidden', 'hidden');
    $('#rpwr3').attr('hidden', 'hidden');
    $('#rpri3').attr('hidden', 'hidden');
    $('#rpwr4').attr('hidden', 'hidden');
    $('#rpri4').attr('hidden', 'hidden');
    $('#rpwr5').attr('hidden', 'hidden');
    $('#rpri5').attr('hidden', 'hidden');
    $('#rpwr6').attr('hidden', 'hidden');
    $('#rpri6').attr('hidden', 'hidden');
    $('#rpwr7').attr('hidden', 'hidden');
    $('#rpri7').attr('hidden', 'hidden');
    if (myclick === "咨询师") {
        $("#1").removeAttr("hidden");
        $("#1").val("");
        $("#2").removeAttr("hidden");
        $("#3").removeAttr("hidden");
        $("#4").removeAttr("hidden");
        $("#5").removeAttr("hidden");
        $("#6").removeAttr("hidden");
        $("#7").removeAttr("hidden");
        $("#8").removeAttr("hidden");
        $("#9").removeAttr("hidden");
        $("#10").removeAttr("hidden");
        $("#1").val("");
        $("#2").val("");
        $("#3").val("");
        $("#4").val("");
        $("#5").val("");
        $("#6").val("");
        $("#7").val("");
        $("#8").val("");
        $("#9").val("");
        $("#10").val("");

    } else if (myclick === "普通管理员") {
        $("#1").removeAttr("hidden");
        $("#1").val("");
        $("#2").removeAttr("hidden");
        $("#3").removeAttr("hidden");
        $("#4").removeAttr("hidden");
        $("#5").removeAttr("hidden");
        $("#6").attr("hidden", "hidden");
        $("#7").attr("hidden", "hidden");
        $("#8").attr("hidden", "hidden");
        $("#9").attr("hidden", "hidden");
        $("#10").removeAttr("hidden");
        $("#1").val("");
        $("#2").val("");
        $("#3").val("");
        $("#4").val("");
        $("#5").val("");
        $("#6").val("");
        $("#7").val("");
        $("#8").val("");
        $("#9").val("");
        $("#10").val("");
    } else {
        $("#1").attr("hidden", "hidden");
        $("#2").attr("hidden", "hidden");
        $("#3").attr("hidden", "hidden");
        $("#4").attr("hidden", "hidden");
        $("#5").attr("hidden", "hidden");
        $("#6").attr("hidden", "hidden");
        $("#7").attr("hidden", "hidden");
        $("#8").attr("hidden", "hidden");
        $("#9").attr("hidden", "hidden");
        $("#10").attr("hidden", "hidden");
        $("#1").val("");
        $("#2").val("");
        $("#3").val("");
        $("#4").val("");
        $("#5").val("");
        $("#6").val("");
        $("#7").val("");
        $("#8").val("");
        $("#9").val("");
        $("#10").val("");


    }
};
var myname = /^(([a-zA-Z+\.?\·?a-zA-Z+]{2,30}$)|([\u4e00-\u9fa5+\·?\u4e00-\u9fa5+]{2,30}$))/;
var reg = /^[\w]{6,12}$/;
function myadd() {
    var a = $("#0").val();//请选择角色
    var b = $("#1").val();//请输入用户账号
    var c = $("#2").val();//请输入名字
    var d = $("#3").val();//请输入密码
    var e = $("#4").val();//请输入毕业院校
    var f = $("#5").val();//请输入职称
    var g = $("#6").val();//请选择领域
    var h = $("#7").val();//请输入专业背景
    var i = $("#8").val();//请输入简介
    var j = $("#9").val();//请选择该咨询师的价格
    var k = document.getElementsByName('sexy');
    var sex;
    for (var s = 0; s < k.length; s++) {
        if (k[s].checked) {
            sex = k[s].value;
            break;
        }
    }
    if (a.length > 0 ) {
        if (a === "1") {//咨询师
            if (b.length>0&&c.length>0&&d.length>0&&e.length>0&&f.length>0&&g.length>0&&
            h.length>0&&i.length>0&&j.length>0&& sex.length > 0){
                if (c.match(myname)&&d.match(reg)&&e.match(myname)){
                    return true;
                }
                else{
                    layui.use("layer", function () {
                        var layer = layui.layer;
                        layer.msg('信息填写有非法输入，请重新输入');
                    });
                    return false;
                }
            }else{
                layui.use("layer", function () {
                    var layer = layui.layer;
                    layer.msg('信息填写不完整请重新输入');
                });
                return false;
            }

        } else if( a === "0") {//普通管理员
            if (b.length>0&&c.length>0&&d.length>0&&e.length>0&& sex.length > 0){
                if (c.match(myname)&&d.match(reg)&&e.match(myname)){
                    return true;
                }
                else{
                    layui.use("layer", function () {
                        var layer = layui.layer;
                        layer.msg('信息填写有非法输入，请重新输入');
                    });
                    return false;
                }
            }else{
                layui.use("layer", function () {
                    var layer = layui.layer;
                    layer.msg('信息填写不完整请重新输入');
                });
                return false;
            }
        }
    } else {
        alert("请选择你要添加的角色名，并填写完整")
    }
    return false;
}

$(function () {
    $("#1").on().blur(function () {
        var myaccount = $("#1").val();
        var accounttype=$("#0").val();
        if (myaccount!==""){
            myajax("/PsychologicalConsultationPlatform/UserServlet?methodName=checkAccount",
                {account: myaccount,mytype:accounttype}, function (msg) {
                    var megob = JSON.parse(msg);
                    // success为验证查无该账号，可以注册，false则表示有该账号存在
                    if (megob.msg==="false"){
                        $('#rpwr').removeAttr('hidden');
                        $('#rpri').attr('hidden', 'hidden');
                        layui.use("layer", function () {
                            var layer = layui.layer;
                            layer.msg('该账号已经被注册了');
                        })
                    }else {
                        $('#rpri').removeAttr('hidden');
                        $('#rpwr').attr('hidden', 'hidden');
                    }
                });

        }else{
            $('#rpwr').removeAttr('hidden');
            $('#rpri').attr('hidden', 'hidden');
        }
     });
    $("#2").on().blur(function () {
        var myname = /^(([a-zA-Z+\.?\·?a-zA-Z+]{2,30}$)|([\u4e00-\u9fa5+\·?\u4e00-\u9fa5+]{2,30}$))/;
        if (!($('#2').val().match(myname))) {
            $('#rpwr0').removeAttr('hidden');
            $('#rpri0').attr('hidden', 'hidden');
            layui.use("layer", function () {
                var layer = layui.layer;
                layer.msg('姓名格式不正确哦');
            })
        } else {
            $('#rpri0').removeAttr('hidden');
            $('#rpwr0').attr('hidden', 'hidden');
        }
    });

    $("#3").blur(function () {
        var reg = /^[\w]{6,12}$/;
        if (!($('#3').val().match(reg))) {
            $('#rpwr1').removeAttr('hidden');//错号
            $('#rpri1').attr('hidden', 'hidden');//对号
            layui.use("layer", function () {
                var layer = layui.layer;
                layer.msg('请输入合法密码');
            })
        } else {
            $('#rpri1').removeAttr('hidden');
            $('#rpwr1').attr('hidden', 'hidden');
        }
    });

    $("#4").on().blur(function () {
        var myname = /^(([a-zA-Z+\.?\·?a-zA-Z+]{2,30}$)|([\u4e00-\u9fa5+\·?\u4e00-\u9fa5+]{2,30}$))/;
        if (!($('#4').val().match(myname))) {
            $('#rpwr2').removeAttr('hidden');
            $('#rpri2').attr('hidden', 'hidden');
            layui.use("layer", function () {
                var layer = layui.layer;
                layer.msg('输入格式不正确哦');
            })
        } else {
            $('#rpri2').removeAttr('hidden');
            $('#rpwr2').attr('hidden', 'hidden');
        }
    });
    $("#5").on().blur(function () {
        if ( $("#5").val().length<=0){
            $('#rpwr3').removeAttr('hidden');
            $('#rpri3').attr('hidden', 'hidden');
            layui.use("layer", function () {
                var layer = layui.layer;
                layer.msg('请选择一个职称哦');
            })
        } else {
            $('#rpri3').removeAttr('hidden');
            $('#rpwr3').attr('hidden', 'hidden');
        }
    });
    $("#6").on().blur(function () {
        if ( $("#6").val().length<=0){
            $('#rpwr4').removeAttr('hidden');
            $('#rpri4').attr('hidden', 'hidden');
            layui.use("layer", function () {
                var layer = layui.layer;
                layer.msg('请选择一个领域哦');
            })
        } else {
            $('#rpri4').removeAttr('hidden');
            $('#rpwr4').attr('hidden', 'hidden');
        }
    });
    $("#7").on().blur(function () {
        if ( $("#7").val().length<=0){
            $('#rpwr5').removeAttr('hidden');
            $('#rpri5').attr('hidden', 'hidden');
            layui.use("layer", function () {
                var layer = layui.layer;
                layer.msg('不能为空哦');
            })
        } else {
            $('#rpri5').removeAttr('hidden');
            $('#rpwr5').attr('hidden', 'hidden');
        }
    });
    $("#8").on().blur(function () {
        if ( $("#8").val().length<=0){
            $('#rpwr6').removeAttr('hidden');
            $('#rpri6').attr('hidden', 'hidden');
            layui.use("layer", function () {
                var layer = layui.layer;
                layer.msg('不能为空哦');
            })
        } else {
            $('#rpri6').removeAttr('hidden');
            $('#rpwr6').attr('hidden', 'hidden');
        }
    });

    $("#9").on().blur(function () {
        if ( $("#9").val().length<=0){
            $('#rpwr7').removeAttr('hidden');
            $('#rpri7').attr('hidden', 'hidden');
            layui.use("layer", function () {
                var layer = layui.layer;
                layer.msg('请选择价位');
            })
        } else {
            $('#rpri7').removeAttr('hidden');
            $('#rpwr7').attr('hidden', 'hidden');
        }
    });

});
