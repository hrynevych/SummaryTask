<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Success" scope="page" />
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
	<table id="main-container" border="1">

		<%@ include file="/WEB-INF/jspf/header.jspf" %>
			
		<tr>
			<td class="content">
			<%-- CONTENT --%>
			
			<c:choose>
			<c:when test="${empty addedUser}">No ${userToAdd.name}s have been added to the base recently.</c:when>
	
			<c:otherwise>
			<h2 class="info">
				The following ${userToAdd.name} was successfully added to the database:
			</h2>
			<style>
			TABLE {
    			border-collapse: collapse; 
   			}
   			TD, TH {
    			padding: 3px;
    			border: 1px solid black;
   			}
   			</style>
			<table id="added_user">
				<thead>
					<tr>
						<td>№</td>
						<td>Name</td>
						<td>Date of birth</td>
						<c:if test="${userToAdd.name == 'patient'}">
							<td>Sex</td>
							<td>Address</td>
						</c:if>
						<c:if test="${userToAdd.name == 'doctor'}">
							<td>Category</td>
						</c:if>
						<td>Phone number</td>
						<td>Email</td>
						<td>Login</td>
						<td>Password</td>
					</tr>
				</thead>
					
					<tr>
						<td>${addedUser.id}</td>
						<td>${addedUser.surname} ${addedUser.name}</td>
						<td>${addedUser.dateOfBirth}</td>
						<c:if test="${userToAdd.name == 'patient'}">
							<td>${addedUser.sex}</td>
							<td>${addedUser.address}</td>
						</c:if>
						<c:if test="${userToAdd.name == 'doctor'}">
							<td>${docCategory.name}</td>
						</c:if>
						<td>${addedUser.phoneNumber}</td>
						<td>${addedUser.email}</td>
						<td>${addedUser.login}</td>
						<td>${addedUser.password}</td>
					</tr>		
			</table>
			</c:otherwise>
			</c:choose>
						
			<%-- CONTENT --%>
			</td>
		</tr>
		<tr>
			<td>
				<form id="more_form" action="controller">
					<input type="hidden" name="command" value="addUser"/>
					<input type="hidden" name="form_filled" value="false"/>
					<input type="hidden" name="userParam" value="${userToAdd.name}"/>
					<input type="submit" value="Add one more ${userToAdd.name}">								
				</form>
				<form id="back_form" action="controller">
					<input type="hidden" name="command" value="forward"/>
					<input type="hidden" name="path" value="admin_page"/>
					<input type="submit" value="Back to admin page">								
				</form>
				<form id="undo_form" action="controller" method="post">
					<input type="hidden" name="command" value="addUser"/>
					<input type="hidden" name="form_filled" value="false"/>
					<input type="hidden" name="userParam" value="${userToAdd.name}"/>
					<input type="hidden" name="userToDel" value="${addedUser.id}"/>
					<input type="submit" value="Undo">
					&nbsp;&nbsp;(All changes will be rolled back)					
				</form>
			</td>
		</tr>
		
		
		<%@ include file="/WEB-INF/jspf/footer.jspf" %>
		
	</table>
</body>
</html>