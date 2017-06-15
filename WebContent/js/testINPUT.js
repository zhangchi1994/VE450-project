function update() {
	var id = document.getElementById("input").value;
	$.get('ReadInfoServlet', {
		id : id
	}, function(responseText) {
		$('#welcometext').text(responseText);	
		sessionStorage.setItem("data", responseText); 
		console.log(responseText);
        alert("信息已保存到data字段中"); 
		window.location.href = "http://4607ad2e.nat123.cc/VE450/father_page.html";
		//window.location.href = "http://4607ad2e.nat123.cc/VE450/testINPUT.html?id=welcometext";
	});
	//window.setTimeout("window.location='http://4607ad2e.nat123.cc/VE450/fatherPage.jsp'",2000); 
};