function myclick(){
  var a= prompt("请输入你的充值金额,最低100元");

    if (a!=null&&a.length>0){
        var pattern = /^[1-9]\d*$/;
        if (pattern.test(a)){
            if (a/100>=1){
             myajax("/PsychologicalConsultationPlatform/FrontUserServlet?methodName=recharge",
                 {money:a},
             function (msg) {
                 var megob = JSON.parse(msg);
               if (megob.msg=="success"){
                   alert("充值成功") ;
                   window.location.reload();
               }else if (megob.msg=="false"){
                   alert("异常情况，请稍后再试") ;
               }


             })
            }else{
                alert("你输入的金额没达到最低充值金额")
            }
        }
    }else {
        alert("你的输入不合法，请重新尝试")
    }
}

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