$(function () {
		layui.use('form', function () {
			var form = layui.form;
			form.render();
			form.on('select(staffwork)', function (data) {
				var val = data.value;
			    var my=$("#myform");
				my.submit();

			})
		});

    //全选函数
    $('.checkbox-all').click(function () {
        if ($(this).prop('checked')) {
            $(this).parent().next().find('.checkboxs').prop('checked', true);
        } else {
            $(this).parent().next().find('.checkboxs').prop('checked', false);
        }
        btn_status();
    })

    //单个checkbox与全选的关系函数
    $('.select-content').on('click', '.checkboxs', function (e) {

        var checkedAll = $(this).parents('.select-content').prev().find('.checkbox-all');
        var checkboxs = $(this).prop('checked');
        if (!checkboxs && checkedAll.prop('checked')) {
            checkedAll.prop('checked', false);
        } else if (checkboxs && !checkedAll.prop('checked')) {
            var lis = $(this).parents('ul').children();
            for (var i = 0; i < lis.length; i++) {
                if ($(lis[i]).find('.checkboxs').prop('checked')) {
                    if (i == lis.length - 1) {
                        checkedAll.prop('checked', true)
                    }
                } else {
                    break;
                }
            }
        }
        stopFunc(e);
    });
    $('.select-content').on('click', 'li', function () {
        $(this).children('.checkboxs').click();
        btn_status();
    })

    // 动态判断改变btn状态
    function btn_status() {
        var btn1 = document.getElementsByClassName('right')[0]
        var btn2 = document.getElementsByClassName('left')[1]
        var left_ul = document.getElementsByClassName('unselect-ul')
        var right_ul = document.getElementsByClassName('selected-ul')
        var left_ul_li = left_ul[0].children
        var right_ul_li = right_ul[0].children
        var left_btn = false
        var right_btn = false
        for (var i = 0; i < left_ul_li.length; i++) {
            if (left_ul_li[i].firstElementChild.checked === true) {
                left_btn = true
            }
        }
        for (var i = 0; i < right_ul_li.length; i++) {
            if (right_ul_li[i].firstElementChild.checked === true) {
                right_btn = true
            }
        }
        if (left_btn) {
            btn1.classList.add('btn-cursor')
        } else {
            btn1.classList.remove('btn-cursor')
        }
        if (right_btn) {
            btn2.classList.add('btn-cursor')
        } else {
            btn2.classList.remove('btn-cursor')
        }

    }

    //左右移按钮点击事件
    $('.arrow-btn').click(function () {
        var checkboxs, origin, target, num = 0;
        if ($(this).hasClass('right')) {
            origin = $('.unselect-ul');
            target = $('.selected-ul');


        } else {
            origin = $('.selected-ul');
            target = $('.unselect-ul');
        }
        checkboxs = origin.find('.checkboxs');
        for (var i = 0; i < checkboxs.length; i++) {
            if ($(checkboxs[i]).prop('checked')) {
                var that = $(checkboxs[i]).parent().clone();
                that.children('input').prop('checked', false);
                target.append(that);
                $(checkboxs[i]).parent().remove();
            } else {
                num++;
            }
        }
        if (checkboxs.length == num) {
//					alert('未选中任何一项');
        } else {
            origin.parent().prev().find('.checkbox-all').prop('checked', false);
        }
        btn_status();
    })
})


function stopFunc(e) {
    e.stopPropagation ? e.stopPropagation() : e.cancelBubble = true;
}

function applyAction() {
    if (confirm("您确定要提交吗?")) {
        var arr1 = [];
        var arr2 = [];
        $('#right span').each(function () {
            arr1.push({
                mName: $(this).text()
            })
        });
        $('#left span').each(function () {
            arr2.push({
                mName: $(this).text()
            })
        });
        var rightjson = JSON.stringify(arr1);
        var leftjson = JSON.stringify(arr2);
        var myoption = $("#staffwork").val();
        alert(myoption)
        myajax("/PsychologicalConsultationPlatform/ShuttleServlet?methodName=myload",
            {rightjson: rightjson, leftjson: leftjson, myoption: myoption}, function (msg) {
                var megob = JSON.parse(msg);
                if (megob.msg == "success") {
                    alert("权限分配完毕");

                } else if (megob.msg == "false") {
                    alert("处理异常，稍后重试");
                } else {
                    alert("你的账号有异常情况，请联系后台管理员");
                    parent.window.location.replace("/PsychologicalConsultationPlatform/background/jsp/index1.jsp")
                }

            })
    }
}