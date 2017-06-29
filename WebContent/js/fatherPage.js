window.onload = function(){
	alert(sessionStorage.getItem("test"));
	 var url = location.search; //获取url中"?"符后的字串
	 if (url.indexOf("?") != -1) 
		 var str = url.substr(1);
	 alert(str);
	$("#id").text(str);
	document.getElementById("pic_id").src = "http://localhost:8080/VE450/pic/" + str + ".jpg";
}