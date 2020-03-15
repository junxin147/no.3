


function login() {
    var val = $("#input-val").val().toLowerCase();
    var num = show_num.join("");
    var username = $("#username").val();
    var password = $("#password").val();

    if (val == "") {
        alert("请输入验证码");
        return false;
    } else if (val != num) {
        alert('验证码错误！请重新输入！');
        $(".input-val").val('');
        setTimeout(function () {
            draw(show_num);
        }, 1000)
        return false;
    }
    if (username.value == '') {
        alert('用户名不能为空！');
        return false;
    }
    if (password.value == '') {
        alert('密码不能为空！');
        return false;
    }
    if (username.length > 0 && password.length > 0) {
        myajax("/PsychologicalConsultationPlatform/FrontUserServlet?methodName=login",
            {username: username, password: password}, function (msg) {


                var megob = JSON.parse(msg);
                if (megob.msg === "success") {

                    alert("正在跳转"),
                    location.href = "/PsychologicalConsultationPlatform/FrontUserServlet?methodName=returnlogin";
                } else if (megob.msg === "errorFalse") {
                    alert("你的账号有异常情况，请联系后台管理员")
                } else {
                    alert("账号或密码错误")
                }
            });

    }
}

window.onload = function () {
    var i3 = document.getElementsByClassName('input_3');
    for (var i = 0; i < i3.length; i++) {
        i3[i].onmouseover = function () {
            this.style.backgroundColor = "#23271F";
            this.style.color = "#fff";
        };
        i3[i].onmouseout = function () {
            this.style.backgroundColor = "#fff";
            this.style.color = "#23271F";
        }
    }
    var pass = document.getElementById("password");
    pass.onfocus = function () {
        pass.type = "password";
    }
};
var show_num = [];
$(function () {
    draw(show_num);//加载验证码
    //看不清楚重新获取验证码
    $("#canvas").on('click', function () {
        draw(show_num);
    })
});

function draw(show_num) {
    var canvas_width = $('#canvas').width();
    var canvas_height = $('#canvas').height();
    var canvas = document.getElementById("canvas");//获取到canvas的对象，演员
    var context = canvas.getContext("2d");//获取到canvas画图的环境，演员表演的舞台
    canvas.width = canvas_width;
    canvas.height = canvas_height;
    var sCode = "A,B,C,E,F,G,H,J,K,L,M,N,P,Q,R,S,T,W,X,Y,Z,1,2,3,4,5,6,7,8,9,0";
    var aCode = sCode.split(",");
    var aLength = aCode.length;//获取到数组的长度

    for (var i = 0; i <= 3; i++) {
        var j = Math.floor(Math.random() * aLength);//获取到随机的索引值
        var deg = Math.random() * 30 * Math.PI / 180;//产生0~30之间的随机弧度
        var txt = aCode[j];//得到随机的一个内容
        show_num[i] = txt.toLowerCase();
        var x = 10 + i * 20;//文字在canvas上的x坐标
        var y = 20 + Math.random() * 8;//文字在canvas上的y坐标
        context.font = "bold 23px 微软雅黑";
        context.translate(x, y);
        context.rotate(deg);
        context.fillStyle = randomColor();
        context.fillText(txt, 0, 0);
        context.rotate(-deg);
        context.translate(-x, -y);
    }
    for (var i = 0; i <= 5; i++) { //验证码上显示线条
        context.strokeStyle = randomColor();
        context.beginPath();
        context.moveTo(Math.random() * canvas_width, Math.random() * canvas_height);
        context.lineTo(Math.random() * canvas_width, Math.random() * canvas_height);
        context.stroke();
    }
    for (var i = 0; i <= 30; i++) { //验证码上显示小点
        context.strokeStyle = randomColor();
        context.beginPath();
        var x = Math.random() * canvas_width;
        var y = Math.random() * canvas_height;
        context.moveTo(x, y);
        context.lineTo(x + 1, y + 1);
        context.stroke();
    }
}

function randomColor() {//得到随机的颜色值
    var r = Math.floor(Math.random() * 256);
    var g = Math.floor(Math.random() * 256);
    var b = Math.floor(Math.random() * 256);
    return "rgb(" + r + "," + g + "," + b + ")";
}


