<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
</head>
<body>
	<p>Following csv files are uploaded successfully.</p>
	<ol>
		<c:forEach items="${files}" var="file">
			<li>${file}</li>
		</c:forEach>
	</ol>

	<form:form method="post" action="processrulefile.html" modelAttribute="ruleFile"
		enctype="multipart/form-data">

		<p>Select rule file to upload. </p>

		<table id="fileTable">
			<tr>
				<td><input name="files" type="file" /></td>
			</tr>
		</table>
		<br />
		<input type="submit" value="Process Rule File" />
	</form:form>
</body>
</html>