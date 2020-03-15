function mycheck1() {
    var text=$("#myappraise").val();
    if (text.length>0){
        return true
    }else {
        alert("请输入评价");
        return false
    }

}