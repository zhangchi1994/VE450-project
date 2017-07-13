function CatchCode() {
    var canvans = document.getElementById("canvas");
    var video = document.getElementById("video");
    var context = canvas.getContext("2d");

    canvas.width = video.videoWidth;
    canvas.height = video.videoHeight;
    context.drawImage(video,0,0);
         
    // 获取浏览器页面的画布对象
    // 以下开始编 数据
	var url = location.search; 
	var picFunction = null;
	var filename = null;
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
	console.log("picfunction: " + picFunction);
	console.log("file: " + filename);
    var imgData = canvans.toDataURL("image/jpg");
    // 将图像转换为base64数据
    var base64Data = imgData.split(",")[1];
    // alert(sessionStorage.getItem("name"));
    console.log("goServlet OK");
    $.post('SaveIMGServlet', { 
    	data : base64Data, 
    	name : filename, 
    	selection : picFunction
    	}, function(responseText){  
    		if(picFunction == "upload") {
        		jQuery(function(){
        			jQuery('#output').qrcode(filename);
        		});  
    		} else {
    			sessionStorage.setItem("imgName", responseText);
    			window.location.href = "http://localhost:8080/VE450/reportEO.html"
    		}
    	});
    		/*
			 * var LODOP; LODOP=getLodop();
			 * LODOP.PRINT_INITA(0,0,522,333,"打印控件功能演示_Lodop功能_自定义纸张1");
			 * LODOP.SET_PRINT_PAGESIZE(0,document.getElementById('W1').value,document.getElementById('H1').value,"A4");
			 * AddPrintContent(document.getElementById("getval").value);
			 * //LODOP.PRINT(); LODOP.PREVIEW(); function
			 * AddPrintContent(strCode) {
			 * LODOP.ADD_PRINT_BARCODE("0.2cm","0.2cm","2.8cm","2.8cm","QRCode",strCode);
			 * //LODOP.SET_PRINT_STYLEA(0,"Angle",-90);
			 *  }; function getSelectedPrintIndex(){ if
			 * (document.getElementById("Radio2").checked) return
			 * document.getElementById("PrinterList").value; else return -1; };
			 * function getSelectedPageSize(){ if
			 * (document.getElementById("Radio4").checked) return
			 * document.getElementById("PagSizeList").value; else return ""; };
			 * function CreatePrinterList(){ if
			 * (document.getElementById('PrinterList').innerHTML!="") return;
			 * LODOP=getLodop(); var iPrinterCount=LODOP.GET_PRINTER_COUNT();
			 * for(var i=0;i<iPrinterCount;i++){
			 * 
			 * var option=document.createElement('option');
			 * option.innerHTML=LODOP.GET_PRINTER_NAME(i); option.value=i;
			 * document.getElementById('PrinterList').appendChild(option); }; };
			 * function clearPageListChild(){ var PagSizeList
			 * =document.getElementById('PagSizeList');
			 * while(PagSizeList.childNodes.length>0){ var children =
			 * PagSizeList.childNodes; for(i=0;i<children.length;i++){
			 * PagSizeList.removeChild(children[i]); }; }; } function
			 * CreatePagSizeList(){ LODOP=getLodop(); clearPageListChild(); var
			 * strPageSizeList=LODOP.GET_PAGESIZES_LIST(getSelectedPrintIndex(),"\n");
			 * var Options=new Array(); Options=strPageSizeList.split("\n"); for
			 * (i in Options) { var option=document.createElement('option');
			 * option.innerHTML=Options[i]; option.value=Options[i];
			 * document.getElementById('PagSizeList').appendChild(option); } }
			 */	
 }  
