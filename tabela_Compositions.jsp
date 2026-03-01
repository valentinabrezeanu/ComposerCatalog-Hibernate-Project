<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Compositions</title>
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
	    <h2>Compositions</h2>
		<table border="1" style="width: 55%; margin: 15px auto;">
			<tr>
				<td><b>IdComposition:</b></td>
				<td><b>Title:</b></td>
				<td><b>Release date:</b></td>
				<td><b>Duration:</b></td>
				<td><b>Genre:</b></td>
			</tr>
			<c:forEach var="composition" items="${listaCompositions}">
				<tr>
					<td>${composition.idcomposition}</td>
					<td>${composition.title}</td>
					<td>${composition.release_date}</td>
					<td>${composition.duration}</td>
					<td>${composition.genre}</td>
				</tr>
			</c:forEach>
		</table>
	
		<c:if test="${not empty mesajSucces}">
			<div id="successNotification">
				 <h2 style="font-size:20px;">${mesajSucces}</h2>
		   </div>
		</c:if><br>
	
		<form action="CompositionsController" method="POST">
		<div class="form-container">
			<div align="center">
	            <span class="title">Select ID to action: </span>
	            <select name="idcomposition" style="background-color: #FAEBD7;">
	                <c:forEach items="${listaCompositions}" var="composition">
	                    <option value="${composition.idcomposition}">${composition.idcomposition}</option>
	                </c:forEach>
	            </select>
	        </div>
			<div class="action-row">
			    <a href="adauga_Compositions.jsp" class="btn">+ Add</a>
			    <button type="button" class="btn" id="btnUpdate">Edit/Modify</button>
			    <a href="index.html" class="btn">Home</a>
			</div>
			<button type="button" class="btn delete-btn" id="btnDelete" class="btn">Delete Selected Records</button>
		</div>
	
		<div id="sectiuneModifica">
            <p class="quote" align="center">!!! Modify Composition Details !!!</p>
            <div align="center">
			    <c:if test="${not empty mesajEroare}">
			        <div id="errorNotification">
			        	<h2 style="color:red; font-size:20px;">${mesajEroare}</h2>
			        </div>
			    </c:if>
			</div>
            <table>
                <tr>
                    <td>Title:</td>
                    <td><input id="Title" type="text" name="Title" size="40"></td>
                </tr>
                <tr>
                    <td>Release date:</td>
                    <td>
                    	<input id="Release_date" type="date" name="Release_date"
                    	style="width: 280px; padding: 5px;">
                    </td>
                </tr>
                <tr>
                    <td>Duration:</td>
                    <td>
                    	<input id="Duration" type="number" name="Duration"
                    	style="width: 280px; padding: 5px;">
                    </td>
                </tr>
                <tr>
                    <td>Genre:</td>
                    <td><input id="Genre" type="text" name="Genre" size="40"></td>
                </tr>
            </table>
            <div align="center" style="margin-top: 1px; margin-bottom: 20px;">
                <button type="submit" class="btn" name="modificaCompositions">Save Changes</button>
            	<button type="button" id="btnCancelModify" class="btn">Cancel/Back</button>
            </div>
        </div>
        <div id="sectiuneSterge" style="text-align: center;">
	            <h2 style="font-size:17px;">Are you sure you want to delete the composition with the ID selected above?</h2><br>
	            <button type="submit" class="btn" name="stergeCompositions">Confirm Delete</button>
 				<button type="button" id="btnCancelDelete" class="btn">Cancel/Back</button>
        </div>
	</form>
</body>
</html>
