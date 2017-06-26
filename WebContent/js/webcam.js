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
    		var LODOP; 
    		LODOP=getLodop();  
    		LODOP.PRINT_INITA(0,0,522,333,"打印控件功能演示_Lodop功能_自定义纸张1");	
    		LODOP.SET_PRINT_PAGESIZE(0,document.getElementById('W1').value,document.getElementById('H1').value,"A4");
    		AddPrintContent(document.getElementById("getval").value);
    		//LODOP.PRINT();	
    	  	LODOP.PREVIEW();
    		function AddPrintContent(strCode) {		
    			LODOP.ADD_PRINT_BARCODE("0.2cm","0.2cm","2.8cm","2.8cm","QRCode",strCode);
    			//LODOP.SET_PRINT_STYLEA(0,"Angle",-90);
    			
    			
    		};
    		function getSelectedPrintIndex(){
    			if (document.getElementById("Radio2").checked) 
    			return document.getElementById("PrinterList").value;
    			else return -1; 		
    		};
    		function getSelectedPageSize(){
    			if (document.getElementById("Radio4").checked) 
    			return document.getElementById("PagSizeList").value;
    			else return ""; 		
    		};	
    		function CreatePrinterList(){
    		    if (document.getElementById('PrinterList').innerHTML!="") return;
    			LODOP=getLodop(); 
    			var iPrinterCount=LODOP.GET_PRINTER_COUNT();
    			for(var i=0;i<iPrinterCount;i++){

    	   			var option=document.createElement('option');
    	   			option.innerHTML=LODOP.GET_PRINTER_NAME(i);
    	   			option.value=i;
    				document.getElementById('PrinterList').appendChild(option);
    			};	
    		};
    		function clearPageListChild(){
    		   var PagSizeList =document.getElementById('PagSizeList'); 
    		   while(PagSizeList.childNodes.length>0){
    	  		   var children = PagSizeList.childNodes;	
    		  		 for(i=0;i<children.length;i++){		
    				PagSizeList.removeChild(children[i]);	
    		  	  };	   
    		   };	   
    		}
    		function CreatePagSizeList(){
    		   LODOP=getLodop(); 
    		   clearPageListChild();
    		   var strPageSizeList=LODOP.GET_PAGESIZES_LIST(getSelectedPrintIndex(),"\n");
    		   var Options=new Array(); 
    	 	   Options=strPageSizeList.split("\n");       
    		   for (i in Options)    
    		   {    
    		     var option=document.createElement('option');   
    			 option.innerHTML=Options[i];
    			 option.value=Options[i];
    	   		 document.getElementById('PagSizeList').appendChild(option);
    		   }  
    		}	
    });  
 }  