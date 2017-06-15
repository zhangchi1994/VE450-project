function update() {
	var id = document.getElementById("input").value;
	$.get('ReadInfoServlet', {
		id : id
	}, function(responseText) {
		/*console.log("oo");
		$('#welcometext').text(responseText);	
		window.location.href = "http://localhost:8080/VE450/father_page.html";*/
	});
};