<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Appointment" scope="page" />
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<style>
	TABLE {
    	border-collapse: collapse; /* Убираем двойные линии между ячейками */
    }
   	TD, TH {
    	padding: 3px; /* Поля вокруг содержимого таблицы */
    	border: 1px solid black; /* Параметры рамки */
   	}
</style>
<body>

	<table id="main-container" border="1">

		<%@ include file="/WEB-INF/jspf/header.jspf" %>
		<tr>
			<td>
				<h2 class="info">
					Choose the patient and doctor to appoint.
				</h2>
			</td>
		</tr>
		<tr>
			<td class="content">
			<%-- CONTENT --%>
			<c:choose>
			<c:when test="${fn:length(patientList) == 0}">No such patients</c:when>
	
			<c:otherwise>
			Select patient:
			<style>
			TABLE {
    			border-collapse: collapse; /* Убираем двойные линии между ячейками */
   			}
   			TD, TH {
    			padding: 3px; /* Поля вокруг содержимого таблицы */
    			border: 1px solid black; /* Параметры рамки */
   			}
   			</style>
   			<div style="height:100px;overflow-y:scroll;">
			<table id="list_of_patients_table">
				<thead>
					<tr>
						<td>№</td>
						<td>Name</td>
						<td>Date of birth</td>
						<td>Sex</td>
						<td>Address</td>
						<td>Login</td>
						<td>Select</td>
					</tr>
				</thead>


				<c:forEach var="user" items="${patientList}">
					<tr>
						<td>${user.id}</td>
						<td>${user.surname} ${user.name}</td>
						<td>${user.dateOfBirth}</td>
						<td>${user.sex}</td>
						<td>${user.address}</td>
						<td>${user.login}</td>
						<td>
							<input form="appoint_form" type="radio" name="selectPat" value="${user.login}"/>
						</td>
					</tr>
				</c:forEach>			
			</table>
			</div>
			</c:otherwise>
			</c:choose>

			<c:choose>
			<c:when test="${fn:length(doctorList) == 0}">No such doctors</c:when>
	
			<c:otherwise>
			<pre>
			</pre>
			Select doctor:
			<style>
			TABLE {
    			border-collapse: collapse; /* Убираем двойные линии между ячейками */
   			}
   			TD, TH {
    			padding: 3px; /* Поля вокруг содержимого таблицы */
    			border: 1px solid black; /* Параметры рамки */
   			}
   			</style>
   			<div style="height:100px;overflow-y:scroll;">
			<table id="list_of_doctors_table">
				<thead>
					<tr>
						<td>№</td>
						<td>Name</td>
						<td>Date of birth</td>
						<td>Category</td>
						<td>Login</td>
						<td>Select</td>
					</tr>
				</thead>


				<c:forEach var="user" items="${doctorList}">
					<tr>
						<td>${user.doctorId}</td>
						<td>${user.doctorSurname} ${user.doctorName}</td>
						<td>${user.doctorDateOfBirth}</td>
						<td>${user.categoryName}</td>
						<td>${user.doctorLogin}</td>
						<td>
							<input form="appoint_form" type="radio" name="selectDoc" value="${user.doctorLogin}"/>
						</td>
					</tr>
				</c:forEach>			
			</table>
			</div>
			</c:otherwise>
			</c:choose>
				<form id="appoint_form" action="controller" method="post">
					<input type="hidden" name="command" value="appoint"/>
					<button type="submit" name="action" value="makeAppointment">Appoint</button>
					<button type="submit" name="action" value="undo">Undo</button>					
				</form>
				
			<%-- CONTENT --%>
			</td>
		</tr>
		
		<%@ include file="/WEB-INF/jspf/footer.jspf" %>
		
	</table>
</body>
</html>