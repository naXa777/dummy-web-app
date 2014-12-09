<jsp:useBean id="students" scope="request" type="java.util.List" />
<%--
  Created by IntelliJ IDEA.
  User: naXa!
  Date: 09.12.2014
  Time: 16:00
--%>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ru">
  <head></head>
  <body>
    <h1>Students</h1>
    <table>
    <c:forEach items="${students}" var="student">
        <tr>
            <td>${student.id}</td>
            <td>${student.name}</td>
            <td>${student.faculty.name}</td>
        </tr>
    </c:forEach>
    </table>
  </body>
</html>
