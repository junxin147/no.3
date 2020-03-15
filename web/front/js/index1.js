// input icon
$(function () {
    $(".text_box>div>div").click(
        function () {
            var n = $(this).index();
            $(".text_box div i img").eq(n).stop().animate({marginTop: -22}, 100)
        }
    )
    $(".text_box>div input").blur(
        function () {
            $(".text_box div i img").stop().animate({marginTop: 0}, 100)
        }
    )
});
var reg = /^[\w]{6,12}$/;
var phone = /^[1][3,4,5,7,8][0-9]{9}$/;
var myname = /^(([a-zA-Z+\.?\·?a-zA-Z+]{2,30}$)|([\u4e00-\u9fa5+\·?\u4e00-\u9fa5+]{2,30}$))/;
function f() {
    var g = document.getElementById("0").value;
    var a = document.getElementById("1").value;
    var a1=document.getElementById("rpri");
    var b = document.getElementById("2").value;
    var c = document.getElementById("3").value;
    var d = document.getElementById("4").value;
    var e = document.getElementById("5").value;
    var f = document.getElementsByName('sexy');
    var sex;
    for (var i = 0; i < f.length; i++) {
        if (f[i].checked) {
            sex = f[i].value;
            break;
        }
    }
    var a11=a1.getAttribute('hidden');
    if (sex!=null){
        if (e.length>0&&a.length > 0 && b.length > 0 && c.length > 0 && d.length > 0 && e.length > 0 && sex.length > 0) {
            if (b === c) {
                if (g.match(myname)&&b.match(reg)&&e.match(phone)&&a11===null){
                    return true;
                }else {
                    layui.use("layer", function () {
                        var layer = layui.layer;
                        layer.msg('有误的信息请重新输入');
                    })
                    return false;
                }

            } else {
                layui.use("layer", function () {
                    var layer = layui.layer;
                    layer.msg('两次密码输入不一致，请重输');
                })
            }
        }
        else {
            layui.use("layer", function () {
                var layer = layui.layer;
                layer.msg('请填写完整');
            })
        }
    }else{
        layui.use("layer", function () {
            var layer = layui.layer;
            layer.msg('请填写完整');
        })
    }

    return false;
}

// 为密码添加正则验证
$(function () {
    $("#2").blur(function () {
        var reg = /^[\w]{6,12}$/;
        if (!($('#2').val().match(reg))) {
            //layer.msg('请输入合法密码');
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
    $("#3").on().blur(function () {
        var reg = /^[\w]{6,12}$/;
        if (!($('#3').val().match(reg))) {
            //layer.msg('请输入合法密码');
            $('#rpwr2').removeAttr('hidden');//错号
            $('#rpri2').attr('hidden', 'hidden');//对号
            layui.use("layer", function () {
                var layer = layui.layer;
                layer.msg('请输入合法密码');
            })
        } else {
            if (!($("#3").val() === ($('#2').val()))) {
                $('#rpwr1').removeAttr('hidden');
                $('#rpri1').attr('hidden', 'hidden');
                $('#rpwr2').removeAttr('hidden');
                $('#rpri2').attr('hidden', 'hidden');
                layui.use("layer", function () {
                    var layer = layui.layer;
                    layer.msg('两次密码不一致哦');
                })
            } else if (($('#3').val()) === "") {
                layui.use("layer", function () {
                    var layer = layui.layer;
                    layer.msg('密码不能为空哦');
                })
            } else {
                $('#rpri1').removeAttr('hidden');
                $('#rpwr1').attr('hidden', 'hidden');
                $('#rpri2').removeAttr('hidden');
                $('#rpwr2').attr('hidden', 'hidden');
            }
        }
    })

    $("#4").on().blur(function () {
        if (($("#4").val()) > 150 || ($("#4").val()) < 0) {
            $('#rpwr3').removeAttr('hidden');
            $('#rpri3').attr('hidden', 'hidden');
            layui.use("layer", function () {
                var layer = layui.layer;
                layer.msg('你输入的年龄有点离谱哦，小朋友');
            })
        } else if (($('#4').val()) === "") {
            layui.use("layer", function () {
                var layer = layui.layer;
                layer.msg('年龄不能为空哦');
            })
        } else {
            $('#rpri3').removeAttr('hidden');
            $('#rpwr3').attr('hidden', 'hidden');
        }
    })

    $("#5").on().blur(function () {
         var phone = /^[1][3,4,5,7,8][0-9]{9}$/;
        if (($('#5').val()) === "") {
            layui.use("layer", function () {
                var layer = layui.layer;
                layer.msg('手机号不能为空哦');
            })
            return
        }
        if (!($('#5').val().match(phone))) {
            $('#rpwr4').removeAttr('hidden');
            $('#rpri4').attr('hidden', 'hidden');
            layui.use("layer", function () {
                var layer = layui.layer;
                layer.msg('手机号码格式不正确哦');
            })
        } else {
            $('#rpri4').removeAttr('hidden');
            $('#rpwr4').attr('hidden', 'hidden');
        }
    });

    $("#0").on().blur(function () {
        var myname = /^(([a-zA-Z+\.?\·?a-zA-Z+]{2,30}$)|([\u4e00-\u9fa5+\·?\u4e00-\u9fa5+]{2,30}$))/;
        if (($('#0').val()) === "") {
            layui.use("layer", function () {
                var layer = layui.layer;
                layer.msg('名字不能为空哦');
            })
            return
        }
        if (!($('#0').val().match(myname))) {
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

    $("#1").on().blur(function () {
        var str = $("#1").val();
       //dataType 期望接收到的数据类型
        //data 发送数据
        //success 200  msg servlet发送出来的结果
        //error 404
        if (str!==""){
            $.ajax({
                type: "POST",
                url: "/PsychologicalConsultationPlatform/FrontUserServlet?methodName=checkAccount",
                dataType: "text",
                data: {name: str},
                success:function (msg) {
                    var megob = JSON.parse(msg);
                   // success为验证查无该账号，可以登录，false则表示有该账号存在
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
                },
                error:function (msg) {
                    alert("服务器正忙。。。。");
                }
            })
        }
    })
});


