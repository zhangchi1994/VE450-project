function clickbutton() {
	var username = document.getElementById("name").value;
	var password = document.getElementById("pswd").value;
	$.get('ActionServlet', {
		id : username,
		pd : password
	}, function(responseText) {
		//console.log(password);
		$('#welcometext').text(responseText);
	});
};

function checkleave() {
	alert("欢迎下次再来！");
};