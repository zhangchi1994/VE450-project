function CatchCode() {
	//实际运用可不写，测试代 ， 为单击拍照按钮就获取了当前图像，有其他用途
    var canvans = document.getElementById("canvas");
    var video = document.getElementById("video");
    var context = canvas.getContext("2d");

    canvas.width = video.videoWidth;
    canvas.height = video.videoHeight;
    context.drawImage(video,0,0);
         
    //获取浏览器页面的画布对象
    //以下开始编 数据
    var imgData = canvans.toDataURL("image/jpg");
    //将图像转换为base64数据
    var base64Data = imgData.split(",")[1];
    var filename = sessionStorage.getItem("name");
    alert(sessionStorage.getItem("name"));
    console.log("goServlet OK");
    $.post('SaveIMGServlet', { 
    	data : base64Data, 
    	name : filename 
    	}, function(responseText){  
    		jQuery(function(){
			jQuery('#output').qrcode(responseText);
		})
    });  
 }  