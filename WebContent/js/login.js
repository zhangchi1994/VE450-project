function clickbutton() {
	var username = document.getElementById("name").value;
	var password = document.getElementById("pswd").value;
	$.get('ActionServlet', {
		id : username,
		pd : password
	}, function(responseText) {
		$('#welcometext').text(responseText);
	});
};

function reset() {
	document.getElementById("name").value="";
	document.getElementById("pswd").value="";
};