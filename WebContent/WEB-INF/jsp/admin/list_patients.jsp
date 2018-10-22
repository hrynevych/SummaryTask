<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="List of patients" scope="page" />
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
	<table id="main-container" border="1">

		<%@ include file="/WEB-INF/jspf/header.jspf" %>
			
		<tr>
			<td class="content">
			<%-- CONTENT --%>

			<c:choose>
			<c:when test="${fn:length(patientList) == 0}">No such patients</c:when>
	
			<c:otherwise>
			<style>
			TABLE {
    			border-collapse: collapse; /* Убираем двойные линии между ячейками */
   			}
   			TD, TH {
    			padding: 3px; /* Поля вокруг содержимого таблицы */
    			border: 1px solid black; /* Параметры рамки */
   			}
   			</style>
			<table id="list_of_patients_table">
				<thead>
					<tr>
						<td>№</td>
						<td>Name</td>
						<td>Date of birth</td>
						<td>Sex</td>
						<td>Address</td>
						<td>Phone number</td>
						<td>Email</td>
					</tr>
				</thead>


				<c:forEach var="user" items="${patientList}">
					
					<tr>
						<td>${user.id}</td>
						<td>${user.surname} ${user.name}</td>
						<td>${user.dateOfBirth}</td>
						<td>${user.sex}</td>
						<td>${user.address}</td>
						<td>${user.phoneNumber}</td>
						<td>${user.email}</td>
					</tr>

				</c:forEach>			
			</table>
			</c:otherwise>
			</c:choose>
						
			<%-- CONTENT --%>
			</td>
		</tr>
		<tr>
			<td>
				<form id="sort_form" action="controller">
					<input type="hidden" name="command" value="listPatients"/>
					<select name="sort">
						<option selected disabled>Sort by</option>
						<option value="surname">Surname</option>
						<option value="date">Date of birth</option>
					</select>
					<input type="submit" value="Sort">								
				</form>
			</td>
		</tr>
		
		
		<%@ include file="/WEB-INF/jspf/footer.jspf" %>
		
	</table>
</body>
</html>