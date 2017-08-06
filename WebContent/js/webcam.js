function CatchCode() {
	//alert("haha");
    var canvans = document.getElementById("canvas");
    var video = document.getElementById("video");
    var context = canvas.getContext("2d");

    canvas.width = video.videoWidth;
    canvas.height = video.videoHeight;
    context.drawImage(video,0,0);
         
	var picFunction = null;
	var filename = null;
	var user = null;
	var url = location.search; 
	if (url.indexOf("?") != -1) {
		var str = url.substr(1); 
		strs = str.split("=");  
		picFunction = strs[1];		
		filename = strs[2];
	}
	if(picFunction != "fromreport") {
		picFunction = "upload";
		filename = sessionStorage.getItem("name");
	}
    var imgData = canvans.toDataURL("image/jpg");
    var base64Data = imgData.split(",")[1];
    //alert(base64Data);
    $.post('SaveIMGServlet', { 
    	data : base64Data, 
    	name : filename, 
    	selection : picFunction
    	}, function(responseText){  
    		if(picFunction == "upload") {
        		jQuery(function(){
        			jQuery('#output').qrcode(filename);
        		});
        		document.getElementById("getval").value = filename;
    		} else {
    			sessionStorage.setItem("imgName", responseText);
    			window.location.href = "./reportEO.html?hid=" + filename;
    		}
    	});	
 }  