<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="List of doctors" scope="page" />
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
	<table id="main-container" border="1">

		<%@ include file="/WEB-INF/jspf/header.jspf" %>
		<tr>
			<td class="content">
			<%-- CONTENT --%>

			<c:choose>
			<c:when test="${fn:length(doctorList) == 0}">No such doctors</c:when>
	
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
			<table id="list_of_doctors_table">
				<thead>
					<tr>
						<td>№</td>
						<td>Name</td>
						<td>Date of birth</td>
						<td>Phone number</td>
						<td>Email</td>
						<td>Category</td>
						<td>Number of patients</td>
					</tr>
				</thead>


				<c:forEach var="doc" items="${doctorList}">
					
					<tr>
						<td>${doc.doctorId}</td>
						<td>${doc.doctorSurname} ${doc.doctorName}</td>
						<td>${doc.doctorDateOfBirth}</td>
						<td>${doc.doctorPhoneNumber}</td>
						<td>${doc.doctorEmail}</td>
						<td>${doc.categoryName}</td>
						<td>${doc.patientsNumber}</td>
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
				<form id="choose_form" action="controller">
					<input type="hidden" name="command" value="listDoctors"/>
					<select name="category">
						<option selected disabled>Choose category</option>
						<option value="all">All categories</option>
						<c:forEach var="category" items="${categoryList}">
							<option value="${category.name}">${category.name}</option>
						</c:forEach>
					</select>
					<select name="sort">
						<option selected disabled>Sort by</option>
						<option value="surname">Surname</option>
						<option value="category">Category</option>
						<option value="patients">Patients</option>
					</select>
					<input type="submit" value="Select">								
				</form>
			</td>
		</tr>
		
		
		<%@ include file="/WEB-INF/jspf/footer.jspf" %>
		
	</table>
</body>
</html>