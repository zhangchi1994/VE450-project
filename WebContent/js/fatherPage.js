window.onload = function(){
	alert(sessionStorage.getItem("data"));
	$("#id").text(sessionStorage.getItem("data"));
	var img = document.getElementById("pic_id");
	//img.src = Server.MapPath("/VE450/pic/" + sessionStorage.getItem("data") + ".png");
	//alert(sessionStorage.getItem("data"));
	//$("#name").load("http://4607ad2e.nat123.cc/VE450/testINPUT.html?id=welcometext");
	/*$.get("testINPUT.html?id=welcometext",function(data){
			$("#name").html(data);
			alert(data);
		})*/
}