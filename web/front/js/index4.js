function check(node) {
	var divNode = document.getElementById("div2");
	var childDivArr = divNode.getElementsByTagName("div");

	var childArr = filterSpaceNode(divNode.childNodes);
	var showDiv = node.nextSibling;


	for (var i = 0; i < childDivArr.length; i++) {
		if (childDivArr[i] === showDiv)
		{
			childDivArr[i].className = "open";
		} else{
			childDivArr[i].className = "close";
		}
	}
}