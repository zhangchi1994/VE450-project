function clickbutton_login() {
	var username = document.getElementById("name").value;
	var password = document.getElementById("pswd").value;
	var url = location.search;
	//alert(username);
	//alert(password)
	$.get('ActionServlet', {
		id : username,
		pd : password
	}, function(responseText) {
		//console.log(password);
		sessionStorage.setItem("whoLogin", username);
		$('#welcometext').text(responseText);
		if(responseText == "wm")
			window.location.href = "./index_warehouse_engineer.html";
		if(responseText == "me"){
			sessionStorage.setItem("whoScan", "me");
			window.location.href = "./index_maintenance_engineer.html";
		}
		if(responseText == "eo") {
			sessionStorage.setItem("whoScan", "eo");
			window.location.href = "./qr_scan.html";
		}
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
		window.location.href = "./login.html";
	});
};
