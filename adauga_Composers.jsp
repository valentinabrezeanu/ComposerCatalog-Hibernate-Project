<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Composers</title>
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
	<h2>Add a new composer</h2>
		<div align="center">
					<c:if test="${not empty mesajSucces}">
						<div id="successNotification">
							 <h2 style="font-size:20px;">${mesajSucces}</h2>
					   </div>
					   <form action="ComposersController" method="POST">
					       <input type="submit" class="btn"  name="afiseazaComposers" value="View Updated">
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
		<form action="ComposersController" method="POST">
			<table>
				<tr>
					<td>Last name:</td>
					<td><input type="text" name="Last_name" size="40"></td>
				</tr>
				<tr>
					<td>First name:</td>
					<td><input type="text" name="First_name" size="40"></td>
				</tr>
				<tr>
					<td>Age:</td>
					<td><input type="number" name="Age" style="width: 280px; padding: 5px;"></td>
				</tr>
				<tr>
					<td>Number of works:</td>
					<td><input type="number" name="Nr_works" style="width: 280px; padding: 5px;"></td>
				</tr>
			</table>
			<div align="center">
            	<input type="submit" class="btn" name="adaugaComposers" value="+ Add">
        		<input type="submit" class="btn" name="afiseazaComposers" value="Back to Table">
			</div>
		</form>
	</div>
</body>
</html>
