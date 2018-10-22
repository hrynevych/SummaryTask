<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Discharge" scope="page" />
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
		
		<c:choose>
		<c:when test="${discharge_page_form eq \"form_list\"}">
			<tr>
				<td>
					<h2 class="info">
						Choose the patient to discharge.
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
							<input form="discharge_form" type="radio" name="patientToDischarge" value="${user.login}"/>
						</td>
					</tr>
				</c:forEach>			
			</table>
			</div>
			</c:otherwise>
			</c:choose>

				<form id="discharge_form" action="controller" method="post">
					<input type="hidden" name="command" value="discharge"/>
					<input type="submit" value="Discharge">
					<button type="submit" name="undo" value="undo">Undo</button>					
				</form>
				
			<%-- CONTENT --%>
			</td>
		</tr>
		</c:when>
		<c:when test="${discharge_page_form eq \"form_result\"}">
		<tr>
			<td class="content">
			<%-- CONTENT --%>
			
			<c:choose>
			<c:when test="${empty discharged}">No patients have been discharged recently.</c:when>
	
			<c:otherwise>
			Patient ${discharged.name} ${discharged.surname} (login: ${discharged.login}) was successfully discharged.
			</c:otherwise>
			</c:choose>
						
			<%-- CONTENT --%>
			</td>
		</tr>
		<tr>
			<td>
				<form id="more_form" action="controller">
					<input type="hidden" name="command" value="discharge"/>
					<input type="submit" value="Discharge one more patient">								
				</form>
				<form id="back_form" action="controller">
					<input type="hidden" name="command" value="forward"/>
					<input type="hidden" name="path" value="admin_page"/>
					<input type="submit" value="Back to admin page">								
				</form>
			</td>
		</tr>
		</c:when>
		<c:otherwise>
			<tr>
				<td>
					<h2 class="info">
						Unknown action.
					</h2>
					<form id="back_to_discharge_form" action="controller" method="post">
						<input type="hidden" name="command" value="discharge"/>
						<input type="submit" value="Discharge">					
					</form>
					<form id="back_to_admin_form" action="controller">
						<input type="hidden" name="command" value="forward"/>
						<input type="hidden" name="path" value="admin_page"/>
						<input type="submit" value="Back to admin page">								
					</form>
				</td>
			</tr>
		</c:otherwise>
		</c:choose>

		<%@ include file="/WEB-INF/jspf/footer.jspf" %>
		
	</table>
</body>
</html>