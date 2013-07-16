<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
</head>
<body>
    <p>Following files are uploaded successfully.</p>
    <ol>
        <c:forEach items="${files}" var="file">
            <li>${file}</li>
        </c:forEach>
    </ol>
</body>
</html>