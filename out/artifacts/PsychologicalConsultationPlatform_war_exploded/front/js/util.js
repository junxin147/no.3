function filterSpaceNode(nodes) {
    for (var i = 0; i < nodes.length; i++) {
        if (nodes[i].nodeType === 3 && /^\s+$/.test(nodes[i].nodeValue)) {
            //得到空白节点之后，移到父节点上，删除子节点
            nodes[i].parentNode.removeChild(nodes[i]);
        }
    }
    return nodes;
}

function myajax(urlPath,sendData,successFunction)
{
	$.ajax({
		type:"POST",
		url:urlPath,
		dataType:"text",
		data:sendData,
		success:successFunction,
		error:function () {
			layui.use("layer", function () {
				var layer = layui.layer;
				layer.msg('服务器正忙', {icon: 2});
			})
		}
	});
}