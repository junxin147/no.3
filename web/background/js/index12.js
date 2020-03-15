$(function () {

    layui.use('form', function () {
        var form = layui.form;

        form.render();
        form.on('select(mydomain)', function (data) {
            var val = data.value;
            var b = $("#myform");
            b.submit();
        })
    })
})
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


function mydelete(obj) {
    var subject_Id = $(obj).parents("tr").find(".subject_Id").text();
    myajax("/PsychologicalConsultationPlatform/MySubjectServlet?methodName=myDelete",
        {subject_Id: subject_Id}, function (msg) {
            var megob = JSON.parse(msg);
            if (megob.msg == "success") {
                alert("删除成功");
                window.location.reload();
            } else if (megob.msg == "false") {
                alert("异常情况");

            } else {
                alert("你的账号有异常情况，请联系上一级管理员");
            }
        })


}

function myupdata(obj) {
    var subject_Id = $(obj).parents("tr").find(".subject_Id").text();
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
            btn: ['保存', '取消并关闭'],
                content: '/PsychologicalConsultationPlatform/MySubjectServlet?methodName=updataInfo&subject_Id='
                    +subject_Id,
            yes: function (index, layero) {
                // 获取iframe层的body
                var body = layer.getChildFrame('body', index);

                var optionA= body.find('#a').val().length;
                var optionB= body.find('#b').val().length;
                var optionC= body.find('#c').val().length;
                var optionD= body.find('#d').val().length;
                var domain= body.find('#domain').val().length;
                var mytext= body.find('#mytext').val().length;
                 if (optionA>0&&optionB>0&&optionC>0&&optionD>0&&domain>0&&mytext>0){
                     // 找到隐藏的提交按钮模拟点击提交
                     body.find('#sureupdata').click();
                     layer.close(index); //关闭弹窗
                     layer.msg('正在处理信息中', {icon: 1});
                     setTimeout(function(){
                         location.reload(true);
                     },1000);
                     layer.close(index);
                 }else {
                     layer.msg('信息不全，请填写', {icon: 1});

                 }


            }, btn2: function (index, layero) {
                layer.close(index);
            },

            }
        );
    })
}


function myinsert(obj) {
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
                btn: ['保存', '取消并关闭'],
        // /PsychologicalConsultationPlatform/background/jsp/index13.jsp
                content: '/PsychologicalConsultationPlatform/BackLinkServlet?methodName=linkthirteen',
                yes: function (index, layero) {
                    // 获取iframe层的body
                    var body = layer.getChildFrame('body', index);

                    var optionA= body.find('#a').val().length;
                    var optionB= body.find('#b').val().length;
                    var optionC= body.find('#c').val().length;
                    var optionD= body.find('#d').val().length;
                    var domain= body.find('#domain').val().length;
                    var mytext= body.find('#mytext').val().length;
                    if (optionA>0&&optionB>0&&optionC>0&&optionD>0&&domain>0&&mytext>0){
                        // 找到隐藏的提交按钮模拟点击提交
                        body.find('#sureupdata').click();
                        layer.close(index); //关闭弹窗
                        layer.msg('正在处理信息中', {icon: 1});
                        setTimeout(function(){
                           window.location.reload();
                        },1000);
                        layer.close(index);
                    }else {
                        layer.msg('信息不全，请填写', {icon: 1});

                    }


                }, btn2: function (index, layero) {
                    layer.close(index);
                },

            }
        );
    })
}