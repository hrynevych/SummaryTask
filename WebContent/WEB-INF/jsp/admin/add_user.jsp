<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Add ${userToAdd.name}" scope="page" />
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
					Fill out the form to add new ${userToAdd.name}.
				</h2>
				Fields marked with an asterisk (*) are required.
			</td>
		</tr>
		<tr>
			<td class="content">
			<%-- CONTENT --%>
				<form id="form_to_fill" action="controller" method="post">
					<input type="hidden" name="command" value="addUser"/>
					<input type="hidden" name="form_filled" value="true"/>
					<input type="hidden" name="userParam" value="${userToAdd.name}"/>
					<pre>Name:            <input name="name" required maxlength="30" placeholder="letters, numbers, '-'" autocomplete="off"/>*</pre>
					<pre>Surname:         <input name="surname" required maxlength="30" placeholder="letters, numbers, '-'" autocomplete="off"/>*</pre>
					<pre>Date of birth:   <input type="date" name="date_of_birth" required placeholder="yyyy-mm-dd"/>*</pre>
					<c:if test="${userToAdd.name == 'patient'}">
						<pre>Sex:             <select name="sex" required>
							<option selected disabled>Select</option>
							<option value="M">Male</option>
							<option value="F">Female</option>
						</select>*</pre>
					</c:if>
					<c:if test="${userToAdd.name == 'patient'}">
						<pre>Address:         <input name="address" required maxlength="100" autocomplete="off"/>*</pre>
					</c:if>
					<pre>Phone number:    <input type="tel" name="phone_number" maxlength="20" placeholder="numbers, '-', '+'" autocomplete="off"/></pre>
					<pre>Email:           <input type="email" name="email" maxlength="50" placeholder="username@domain" autocomplete="off"/></pre>
					<pre>Login:           <input name="login" required maxlength="20" placeholder="latin letters, numbers" autocomplete="off"/>*</pre>
					<pre>Password:        <input name="password" required maxlength="20" placeholder="latin letters, numbers" autocomplete="off"/>*</pre>
					<c:if test="${userToAdd.name == 'doctor'}">
						<pre>Category:        <select name="category" required>
							<option selected disabled>Select</option>
							<c:forEach var="category" items="${categories}">
								<option value="${category.id}">${category.name}</option>
							</c:forEach>
						</select>*</pre>
					</c:if>	
					<input type="submit" value="Add new ${userToAdd.name}"/>
				</form>	
			<%-- CONTENT --%>
			</td>
		</tr>
		<c:if test="${not empty delSuccessfully}">
		<tr>
			<td>
				Remark: the ${userToAdd.name} has been deleted from the base successfully.
			</td>
		</tr>
		</c:if>
		<%@ include file="/WEB-INF/jspf/footer.jspf" %>
		
	</table>
</body>
</html>