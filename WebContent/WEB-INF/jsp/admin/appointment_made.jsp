<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Success" scope="page" />
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
			<td class="content">
			<%-- CONTENT --%>
			
			<c:choose>
			<c:when test="${(empty pat) || (empty doc)}">No appointments have been made recently.</c:when>
	
			<c:otherwise>
			Doctor ${doc.doctorName} ${doc.doctorSurname} (login: ${doc.doctorLogin}, category: ${doc.categoryName}) 
			was successfully appointed to the patient ${pat.name} ${pat.surname} (login: ${pat.login}).
			</c:otherwise>
			</c:choose>
						
			<%-- CONTENT --%>
			</td>
		</tr>
		<tr>
			<td>
				<form id="more_form" action="controller">
					<input type="hidden" name="command" value="appoint"/>
					<input type="submit" value="Make one more appointment">								
				</form>
				<form id="back_form" action="controller">
					<input type="hidden" name="command" value="forward"/>
					<input type="hidden" name="path" value="admin_page"/>
					<input type="submit" value="Back to admin page">								
				</form>
			</td>
		</tr>
		
		
		<%@ include file="/WEB-INF/jspf/footer.jspf" %>
		
	</table>
</body>
</html>