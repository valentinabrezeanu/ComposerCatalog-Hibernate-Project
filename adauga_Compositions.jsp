<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Compositions</title>
<link rel="stylesheet" href="style.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script> 
	$(document).ready(function () { 
	    var areSucces = $("#successNotification").length > 0;
	
	    if (areSucces) {
	        $("#add").hide();
	    } 
	});
</script>
</head>
<body>
	<h2>Add a new composition</h2>
	<div align="center">
				<c:if test="${not empty mesajSucces}">
					<div id="successNotification">
						 <h2 style="font-size:20px;">${mesajSucces}</h2>
				   </div>
				   <form action="CompositionsController" method="POST">
				       <input type="submit" class="btn"  name="afiseazaCompositions" value="View Updated">
				       &nbsp; &nbsp;<br>
			       </form>
				</c:if><br>
			    <c:if test="${not empty mesajEroare}">
			        <div id="errorNotification">
			        	<h2 style="color:red; font-size:20px;">${mesajEroare}</h2>
			        </div>
			    </c:if>
	</div>
	<div id="add">
		<form action="CompositionsController" method="POST">
			<table>
				<tr>
					<td>Title:</td>
					<td><input type="text" name="Title" size="40"></td>
				</tr>
				<tr>
					<td>Release date:</td>
					<td><input type="date" name="Release_date" style="width: 280px; padding: 5px;"></td>
				</tr>
				<tr>
					<td>Duration:</td>
					<td><input type="number" name="Duration" style="width: 280px; padding: 5px;"></td>
				</tr>
				<tr>
					<td>Genre:</td>
					<td><input type="text" name="Genre" size="40"></td>
				</tr>
			</table>
			<div align="center">
            	<input type="submit" class="btn" name="adaugaCompositions" value="+ Add">
        		<input type="submit" class="btn" name="afiseazaCompositions" value="Back to Table">
			</div>
		</form>
	</div>

</body>
</html>
