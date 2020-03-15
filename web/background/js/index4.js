function myucheck() {
    var text=$("#mytext").val();
    if (text.length>0){
        return true
    }else {
        alert("请输入回复");
        return false
    }

}