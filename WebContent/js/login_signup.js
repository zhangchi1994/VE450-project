function clickbutton_login() {
	var username = document.getElementById("name").value;
	var password = document.getElementById("pswd").value;
	$.get('ActionServlet', {
		id : username,
		pd : password
	}, function(responseText) {
		//console.log(password);
		$('#welcometext').text(responseText);
		if(responseText == "wm")
			window.location.href = "http://4607ad2e.nat123.cc/VE450/warehouse_engineer.html";
	});
};

function clickbutton_signup() {
	var username = document.getElementById("name").value;
	var password = document.getElementById("pswd").value;
	var characteristic = document.getElementsByName("characteristic");
	for(var i=0; i<characteristic.length; i++){ 
		if(characteristic[i].checked){ 
			chk = i; 
			break; 
		} 
	}  
	//alert(characteristic[chk].value);
	$.post('ActionServlet', {
		id : username,
		pd : password,
		ty : characteristic[chk].value
	}, function(responseText) {
		//console.log(password);
		$('#welcometext').text(responseText);
		window.location.href = "http://4607ad2e.nat123.cc/VE450/login.html";
	});
};
