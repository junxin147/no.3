function myradio() {
   var finalnum= $("input[type='radio']:checked");
    if (finalnum.length>=5) {
        layui.use("layer", function () {
            var layer = layui.layer;
            layer.msg('正在处理信息中', {icon: 1});
        });
        return true;
    }else {
        layui.use("layer", function () {
            var layer = layui.layer;
            layer.msg('信息填写不完整', {icon: 1});
        });
        return false;
    }



}