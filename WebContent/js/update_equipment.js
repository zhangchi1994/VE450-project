function update() {
	var product_name = document.getElementById("product_name").value;
	var manufacturer = document.getElementById("manu").value;
	var time = document.getElementById("time").value;
	var size = document.getElementById("size").value;
	$.get('UploadServlet', {
		pd_name : product_name,
		manu : manufacturer,
		time : time,
		size : size
	}, function(responseText) { 
		sessionStorage.setItem("name", responseText); 
		//alert(responseText);
		window.location.href = "./webcam.html";
		/*jQuery(function(){
			jQuery('#output').qrcode(responseText);
		})*/
	});
};	