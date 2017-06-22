function update() {
	var id = document.getElementById("input").value;
	$.get('ReadInfoServlet', {
		id : id
	}, function() {
		//$('#welcometext').text(responseText);	
		window.location.href = "http://4607ad2e.nat123.cc/VE450/fatherPage.jsp";
	});
};