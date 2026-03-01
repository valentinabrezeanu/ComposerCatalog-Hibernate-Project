<%@page import="DAOImpl.CompositionsDaoImpl"%>
<%@page import="DAOImpl.ComposersDaoImpl"%>
<%@page import="pojo.Compositions"%>
<%@page import="java.util.ArrayList"%>
<%@page import="pojo.Composers"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Collaborations</title>
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
	<% 
			CompositionsDaoImpl compositionDaoImpl = new CompositionsDaoImpl(); 
			ComposersDaoImpl composerDaoImpl = new ComposersDaoImpl(); 
            List<Compositions> listaCompositions = new ArrayList<>(); 
            listaCompositions = compositionDaoImpl.afiseazaCompositions(); 
            List<Composers> listaComposers = new ArrayList<>(); 
            listaComposers = composerDaoImpl.afiseazaComposers(); 
            request.setAttribute("listaCompositions", listaCompositions); 
            request.setAttribute("listaComposers", listaComposers); 
%>
	<h2>Collaborations</h2>
	<div class="table-container">
		<table border="1" style="width: 50%; margin: 25px auto;">
			<tr>
				<td><b>IdCollaboration:</b></td>
				
				<td><b>IdComposition:</b></td>
				<td><b>Title:</b></td>
				<td><b>Release date:</b></td>
				<td><b>Duration:</b></td>
				<td><b>Genre:</b></td>
				
				<td><b>IdComposer:</b></td>
				<td><b>Last name:</b></td>
				<td><b>First name:</b></td>
				<td><b>Age:</b></td>
				<td><b>Number of works:</b></td>
				
				<td><b>Role composer:</b></td>
				<td><b>Start date:</b></td>
				
			</tr>
			<c:forEach var="collaboration" items="${listaCollaborations}">
				<tr>
					<td>${collaboration.idcollaboration}</td>
					<td>${collaboration.compositions.idcomposition}</td>
					<td>${collaboration.compositions.title}</td>
					<td>${collaboration.compositions.release_date}</td>
					<td>${collaboration.compositions.duration}</td>
					<td>${collaboration.compositions.genre}</td>
					<td>${collaboration.composers.idcomposer}</td>
					<td>${collaboration.composers.last_name}</td>
					<td>${collaboration.composers.first_name}</td>
					<td>${collaboration.composers.age}</td>
					<td>${collaboration.composers.nr_works}</td>
					<td>${collaboration.role_composer}</td>
					<td>${collaboration.start_date}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<c:if test="${not empty mesajSucces}">
			<div id="successNotification">
				 <h2 style="font-size:20px;">${mesajSucces}</h2>
		   </div>
	</c:if><br>
	<form action="CollaborationsController" method="POST">
		<div class="form-container">
			<div align="center">
	            <span class="title">Select ID to action: </span>
	            <select name="idcollaboration">
					<c:forEach items="${listaCollaborations}" var="collaboration">
						<option value="${collaboration.idcollaboration}">${collaboration.idcollaboration}</option>
					</c:forEach>
				</select> 
			</div>
			<div class="action-row">
			    <a href="adauga_Collaborations.jsp" class="btn">+ Add</a>
			    <button type="button" id="btnUpdate" class="btn">Edit/Modify</button>
			    <a href="index.html" class="btn">Home</a>
			</div>
			<button type="button" class="btn delete-btn" id="btnDelete">Delete Selected Records</button>
		</div><br><br>
		<div id="sectiuneModifica">
            <p class="quote" align="center">!!! Modify Collaboration Details !!!</p>
            <div align="center">
			    <c:if test="${not empty mesajEroare}">
			        <div id="errorNotification">
			        	<h2 style="color:red; font-size:20px;">${mesajEroare}</h2>
			        </div>
			    </c:if>
			</div>
			
				<table>
					<tr>
						<td>IdComposition:</td> 
						<td><select name="idcomposition" style="width: 300px; padding: 5px;">
		    				<c:forEach items="${listaCompositions}" var="composition">
		        				<option value="${composition.idcomposition}">
		            				${composition.idcomposition}, ${composition.title}, ${composition.release_date}, ${composition.duration}, ${composition.genre}
		        				</option>
		   					 </c:forEach>
						</select></td>
					</tr>
					<tr>
						<td>IdComposer:</td> 
						<td><select name="idcomposer" style="width: 300px; padding: 5px;">
			    				<c:forEach items="${listaComposers}" var="composer">
			        				<option value="${composer.idcomposer}">
			            				${composer.idcomposer}, ${composer.last_name}, ${composer.first_name}, ${composer.age}, ${composer.nr_works}
			       					 </option>
			    				</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td>Role composer:</td>
						<td><input id="Role_composer" type="text" name="Role_composer" size="40"></td>
					</tr>
					<tr>
						<td>Start date:</td>
						<td><input id="Start_date" type="date" name="Start_date" style="width: 285px; padding: 5px;"></td>
					</tr>
				</table>
			<div align="center" style="margin-top: 1px; margin-bottom: 20px;">
                <button type="submit" class="btn" name="modificaCollaborations">Save Changes</button>
            	<button type="button" id="btnCancelModify" class="btn">Cancel/Back</button>
            </div>
          </div>
          <div id="sectiuneSterge" style="text-align: center;">
	            <h2 style=" color: white; font-size:17px;">Are you sure you want to delete the composer with the ID selected above?</h2><br>
	            <button type="submit" class="btn" name="stergeCollaborations">Confirm Delete</button>
 				<button type="button" id="btnCancelDelete" class="btn">Cancel/Back</button>
          </div>	
	</form>
</body>
</html>
