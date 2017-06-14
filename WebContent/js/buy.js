function update() {
	var id = document.getElementById("special_id").value;
	var product_name = document.getElementById("product_name").value;
	var manufacturer = document.getElementById("manu").value;
	var time = document.getElementById("time").value;
	var size = document.getElementById("size").value;
	$.get('UploadServlet', {
		sp_id : id,
		pd_name : product_name,
		manu : manufacturer,
		time : time,
		size : size
	}, function(responseText) {
		$('#welcometext').text(responseText);
	});
};