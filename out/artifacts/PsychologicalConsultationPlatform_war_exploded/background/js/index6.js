var fromNode;
var path;
var startNode;
window.onload = function () {
    startNode = document.getElementById("pageTurn").value;
    fromNode = document.getElementById("form3");
};

function myOpen() {
    layui.use("layer", function () {
            var layer = layui.layer;
//layer.msg("大家好，这是最简单的弹层", { time: 9000, type: 1,title:['测试一下','font-size:18px'] });
            layer.open({
                id: 'myOpen',
                offset: 10,
                type: 2,
                title: '新增加人员',
                shadeClose: false,
                shade: 0.4,
                btn: ['保存并添加', '取消并关闭'],
                maxmin: false, //开启最大化最小化按钮
                area: ['893px', '500px'],
                content: '/PsychologicalConsultationPlatform/BackLinkServlet?methodName=addnew',
                yes: function (index, layero) {
                    // 获取iframe层的body
                    var body = layer.getChildFrame('body', index);
                    // 找到隐藏的提交按钮模拟点击提交
                    body.find('#addStaff').click();
                    layer.close(index); //关闭弹窗
                    layer.msg('正在处理添加信息中', {icon: 1});
                    setTimeout(function(){
                        location.reload(true);
                    },1000);
                    layer.close(index);
                }, btn2: function (index, layero) {
                    layer.close(index);
                },

            });
        }
    );

}

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

function mybackstatus(node) {
    var myaccount = $(node).parents("tr").find(".insertAccount").text();
    layui.use("layer", function () {
        var layer = layui.layer;
        layer.confirm('你确定要' + node.value + '吗？', {
            btn: ['确认', '取消'] //按钮
        }, function () {
            var mynonde = $(node).val();
            if (mynonde === "禁用") {
                mynonde = 2;
            } else {
                mynonde = 1;
            }
            myajax("/PsychologicalConsultationPlatform/BackUserTbActServlet?methodName=myStage",
                {account: myaccount, stage: mynonde}, function (msg) {
                    var megob = JSON.parse(msg);
                    if (megob.msg === "success") {
                        $(node).parents("tr").find(".insertStage").text(node.value);
                        var newValue;
                        if ($(node).attr("value") === "禁用") {
                            $(node).attr("value", "启用");
                        } else {
                            $(node).attr("value", "禁用");
                        }
                        layer.msg('正在处理中', {icon: 1});
                    } else if (megob.msg === "returnlogin"){
                        layer.msg('异常情况', {icon: 2});
                        layer.confirm('你的账号有异常情况，请联系后台', {
                            btn: ['确认'] //按钮
                        }, function () {
                        parent.window.location.replace("/PsychologicalConsultationPlatform/background/jsp/index1.jsp");
                    })
                    }
                    else {
                        layer.msg('异常情况，修改失败', {icon: 2});
                    }

                }
            );
        }, function () {
            layer.msg('感谢你的手下留情', {
                time: 10000, //10s后自动关闭
                btn: ['不客气']
            });
        });

    });

}

function mybackdelete(node) {
    var myaccount = $(node).parents("tr").find(".insertAccount").text();
    layui.use("layer", function () {
        var layer = layui.layer;
        layer.confirm('你确定要' + node.value + '吗？', {
            btn: ['确认', '取消'] //按钮
        }, function () {
            var mynonde = 3;
            myajax("/PsychologicalConsultationPlatform/BackUserTbActServlet?methodName=myStage",
                {account: myaccount, stage: mynonde}, function (msg) {
                    var megob = JSON.parse(msg);
                    if (megob.msg === "success") {
                        $(node).parents("tr").find(".insertStage").text("已删除");
                        var ob = $(node).parent().children();
                        var fa_node= $(node).parent();
                        for (var i = 0; i < ob.length; i++) {
                             ob[i].remove();
                        }
                        fa_node.append("<input type='button' class='layui-btn layui-btn-disabled layui-btn-radius' value='启用'>");
                        fa_node.append("<input type='button' class='layui-btn layui-btn-disabled layui-btn-radius' value='删除'>");
                        fa_node.append("<input type='button' class='layui-btn layui-btn-disabled layui-btn-radius' value='重置密码'>");
                        layer.msg('正在处理中', {icon: 1});
                    }else if (megob.msg === "returnlogin"){
                        layer.msg('异常情况', {icon: 2});
                        layer.confirm('你的账号有异常情况，请联系后台', {
                            btn: ['确认'] //按钮
                        }, function () {
                            parent.window.location.replace("/PsychologicalConsultationPlatform/background/jsp/index1.jsp");
                        })
                    }
                    else {
                        layer.msg('异常情况，修改失败', {icon: 2});
                    }
                }
            );
        }, function () {
            layer.msg('感谢你的手下留情', {
                time: 10000, //10s后自动关闭
                btn: ['不客气']
            });
        });

    });

}

function mybackreset(node) {
    var myaccount = $(node).parents("tr").find(".insertAccount").text();
    layui.use("layer", function () {
        var layer = layui.layer;
        layer.confirm('你确定要' + node.value + '吗？', {
            btn: ['确认', '取消'] //按钮
        }, function () {
            myajax("/PsychologicalConsultationPlatform/BackUserTbActServlet?methodName=myreset",
                {account: myaccount}, function (msg) {
                    var megob = JSON.parse(msg);
                    if (megob.msg === "success") {
                        layer.msg('密码重置成功', {icon: 1});
                    }else if (megob.msg === "returnlogin"){
                        layer.msg('异常情况', {icon: 2});
                        layer.confirm('你的账号有异常情况，请联系后台', {
                            btn: ['确认'] //按钮
                        }, function () {
                            parent.window.location.replace("/PsychologicalConsultationPlatform/background/jsp/index1.jsp");
                        })
                    }
                    else {
                        layer.msg('异常情况，修改失败', {icon: 2});
                    }
                }
            );
        }, function () {
            layer.msg('感谢你的手下留情', {
                time: 10000, //10s后自动关闭
                btn: ['不客气']
            });
        });
    });

}




