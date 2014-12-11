<jsp:useBean id="students" scope="request" type="java.util.List"/>
<%--
  Created by IntelliJ IDEA.
  User: naXa!
  Date: 09.12.2014
  Time: 16:00
--%>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ru">
<head>
    <title>University - All Students</title>
</head>
<body>
<table title="Students" border="1" cellpadding="16" style="font-size: large">
    <thead>
    <tr>
        <th>
            <img src="resources/address-book.png" />
        </th>
        <th>Name</th>
        <th>Faculty</th>
        <th>
            <a href="${pageContext.request.contextPath}/add">
                <img src="resources/add-plus-icon.png" alt="[add]" title="Add new student" />
            </a>
        </th>
    </tr>
    </thead>
    <c:forEach items="${students}" var="student">
        <tr>
            <td></td>
            <td>
                <a href="${pageContext.request.contextPath}/add/${student.id}" title="Click to edit">${student.name}</a>
            </td>
            <td>${student.faculty.name}</td>
            <td>
                <form action="${pageContext.request.contextPath}/del/${student.id}" method="POST">
                    <input type="image" src="resources/delete-cross.png" alt="[delete]" title="Delete this student" />
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
