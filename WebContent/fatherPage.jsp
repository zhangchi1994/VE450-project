<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
	<script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<title>father_page</title>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">

</head>

<%
	//String value1 = request.getAttribute("value1").toString();
	//System.out.println(value1);
%>
<script type="text/javascript">
	alert("hi");
</script>

<body>
	<p>
		<img src="father_pic.png" alt="father_pic" width="308" height="218">
	</p>

	<table class="table table-bordered">
		<caption>Basic Information</caption>
		<thead>
			<tr>
				<th>Name</th>
				<th>Size</th>
				<th>Number</th>
				<th>Condition</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td id="name"></td>
				<td id="size"></td>
				<td id="number"></td>
				<td id="condition"></td>
		</tbody>
	</table>

	<table class="table table-bordered">
		<caption>Running Information</caption>
		<thead>
			<tr>
				<th>Name</th>
				<th>ID</th>
				<th>Condition</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td><a
					href="file:///Users/liuqing/Desktop/html/son_page_A.html"
					target="_blank">A</a></td>
				<td>003</td>
				<td>Abnormal</td>
			</tr>
			<tr>
				<td><a
					href="file:///Users/liuqing/Desktop/html/son_page_B.html"
					target="_blank">B</a></td>
				<td>004</td>
				<td>Normal</td>
			</tr>
		</tbody>
	</table>
	<div id="welcometext"></div>
</body>

</html>