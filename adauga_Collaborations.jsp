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
	<h2>Add a new collaboration</h2>
	<div align="center">
				<c:if test="${not empty mesajSucces}">
					<div id="successNotification">
						<h2 style="font-size:20px;">${mesajSucces}</h2>
					</div>
					<form action="CollaborationsController" method="POST">
					     <input type="submit" class="btn"  name="afiseazaCollaborations" value="View Updated">
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
		<form action="CollaborationsController" method="POST">
			<table>
				<tr>
					<td>Composition:</td>
					<td><select name="idcomposition">
							<c:forEach items="${listaCompositions}" var="composition">
								<option value="${composition.idcomposition}">${composition.idcomposition},
									${composition.title}, ${composition.release_date}, ${composition.duration}, ${composition.genre}</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td>Composers:</td>
					<td><select name="idcomposer">
							<c:forEach items="${listaComposers}" var="composer">
								<option value="${composer.idcomposer}">${composer.idcomposer},
									${composer.last_name}, ${composer.first_name}, ${composer.age}, ${composer.nr_works}</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td>Role_composer:</td>
					<td><input type="text" name="Role_composer"></td>
				</tr>
				<tr>
					<td>Start_date:</td>
					<td><input type="date" name="Start_date"></td>
				</tr>
			</table>
			<div align="center">
            	<input type="submit" class="btn" name="adaugaCollaborations" value="+ Add">
        		<input type="submit" class="btn" name="afiseazaCollaborations" value="Back to Table">
			</div>
		</form>
	</div>
</body>
</html>