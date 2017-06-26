window.onload = function(){
	//alert(sessionStorage.getItem("qr_id_data"));
	$("#id").text(sessionStorage.getItem("qr_id_data"));
	document.getElementById("pic_id").src = "http://4607ad2e.nat123.cc/VE450/pic/" + sessionStorage.getItem("qr_id_data") + ".png";
	//alert(document.getElementById("pic_id").src);
	//$("#name").load("http://4607ad2e.nat123.cc/VE450/testINPUT.html?id=welcometext");
	/*$.get("testINPUT.html?id=welcometext",function(data){
			$("#name").html(data);
			alert(data);
		})*/
}