<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Admin" scope="page" />
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<style>
	TABLE {
    	border-collapse: collapse; 
   	}
   	TD, TH {
    	padding: 3px; 
    	border: 1px solid black;
   	}
</style>
<body>
	<table id="main-container" border="1">
		<%@ include file="/WEB-INF/jspf/header.jspf" %>
		<tr>
			<td class="content">
			<%-- CONTENT --%>
			
				<form id="show_lists_form" action="controller" method="post">
					<select name="command">
						<option selected disabled>Show list of</option>
						<option value="listPatients">Patients</option>
						<option value="listDoctors">Doctors</option>
					</select>
					<input type="submit" value="Show">								
				</form>
				
				<form id="adding_form" action="controller" method="post">
					<input type="hidden" name="command" value="addUser"/>
					<input type="hidden" name="form_filled" value="false"/>
					<select name="userParam">
						<option selected disabled>Add new</option>
						<option value="patient">Patient</option>
						<option value="doctor">Doctor</option>
						<option value="nurse">Nurse</option>
					</select>
					<input type="submit" value="Add">								
				</form>
			
			<form id="appoint_doctor_form" action="controller" method="post">
					<input type="hidden" name="command" value="appoint"/>
					<input type="hidden" name="action" value="begin"/>
					<input type="submit" value="Appointment">				
			</form>
			
			<form id="discharge_patient_form" action="controller" method="post">
					<input type="hidden" name="command" value="discharge"/>
					<input type="submit" value="Discharge patient">								
			</form>
						
			<%-- CONTENT --%>
			</td>
		</tr>
		<%@ include file="/WEB-INF/jspf/footer.jspf" %>
	</table>
</body>
</html>