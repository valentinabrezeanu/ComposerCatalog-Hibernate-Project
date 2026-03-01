<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Composers</title>
<link rel="stylesheet" href="style.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js">
</script>
<script> 
	$(document).ready(function () { 
	    var areEroare = $("#errorNotification").length > 0;
	    var areSucces = $("#successNotification").length > 0;
	
	    if (areEroare) {
	        $(".form-container").hide();
	        $("#sectiuneModifica").show();
	        $("#sectiuneSterge").hide();
	    } 
	    else {
	        $("#sectiuneModifica, #sectiuneSterge").hide();
	        $(".form-container").show();
	    }
	
	    $("#btnUpdate").click(function () {
	        $(".form-container").hide();
	        $("#sectiuneModifica").show(); 
	    }); 
	
	    $("#btnDelete").click(function () { 
	        $(".form-container").hide();
	        $("#sectiuneSterge").show(); 
	    }); 
	    
	    $("#btnCancelDelete").click(function () {
	        $("#sectiuneSterge").hide();     
	        $(".form-container").show();   
	        $("#successNotification, #errorNotification").fadeOut();
	    });
	    $("#btnCancelModify").click(function () {
	        $("#sectiuneModifica").hide();     
	        $(".form-container").show();       
	        $("#successNotification, #errorNotification").fadeOut(); 
	    });
	});
</script>
</head>
<body>
	<h2>Composers</h2>
	<table border="1" style="width: 55%; margin: 15px auto;">
		<tr>
			<td><b>IdComposer:</b></td>
			<td><b>Last name:</b></td>
			<td><b>First name:</b></td>
			<td><b>Age:</b></td>
			<td><b>Number of works:</b></td>
		</tr>
		<c:forEach var="composer" items="${listaComposers}">
			<tr>
				<td>${composer.idcomposer}</td>
				<td>${composer.last_name}</td>
				<td>${composer.first_name}</td>
				<td>${composer.age}</td>
				<td>${composer.nr_works}</td>
			</tr>
		</c:forEach>
	</table>
	
	<c:if test="${not empty mesajSucces}">
			<div id="successNotification">
				 <h2 style="font-size:20px;">${mesajSucces}</h2>
		   </div>
	</c:if><br>
		
	<form action="ComposersController" method="POST">
	<div class="form-container">
			<div align="center">
	            <span class="title">Select ID to action: </span>
	            <select name="idcomposer">
					<c:forEach items="${listaComposers}" var="composer">
						<option value="${composer.idcomposer}">${composer.idcomposer}</option>
					</c:forEach>
				</select>
			</div>
			<div class="action-row">
			    <a href="adauga_Composers.jsp" class="btn">+ Add</a>
			    <button type="button" id="btnUpdate" class="btn">Edit/Modify</button>
			    <a href="index.html" class="btn">Home</a>
			</div>
			<button type="button" class="btn delete-btn" id="btnDelete" class="btn">Delete Selected Records</button>
	</div>
	<div id="sectiuneModifica">
            <p class="quote" align="center">!!! Modify Composer Details !!!</p>
            <div align="center">
			    <c:if test="${not empty mesajEroare}">
			        <div id="errorNotification">
			        	<h2 style="color:red; font-size:20px;">${mesajEroare}</h2>
			        </div>
			    </c:if>
			</div>
			<table>
				<tr> 
					<td>Last name:</td> 
					<td><input type="text" name="Last_name"></td> 
				</tr>
				<tr>
					<td>First name:</td>
					<td><input id="First_name" type="text" name="First_name"></td>
				</tr>
				<tr>
					<td>Age:</td>
					<td><input id="Age" type="number" name="Age"></td>
				</tr>
				<tr>
					<td>Number of works:</td>
					<td><input id="Nr_works" type="number" name="Nr_works"></td>
				</tr>
			</table>
            <div align="center" style="margin-top: 1px; margin-bottom: 20px;">
                <button type="submit" class="btn" name="modificaComposers">Save Changes</button>
            	<button type="button" id="btnCancelModify" class="btn">Cancel/Back</button>
            </div>
        </div>
        <div id="sectiuneSterge" style="text-align: center;">
	            <h2 style="font-size:17px;">Are you sure you want to delete the composer with the ID selected above?</h2><br>
	            <button type="submit" class="btn" name="stergeComposers">Confirm Delete</button>
 				<button type="button" id="btnCancelDelete" class="btn">Cancel/Back</button>
        </div>
	</form>
</body>
</html>
