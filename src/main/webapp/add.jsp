<jsp:useBean id="faculties" scope="request" type="java.lang.Iterable" />
<jsp:useBean id="student" scope="request" class="by.naxa.model.Student" />
<jsp:useBean id="rates" scope="request" class="java.lang.String" />
<%--
  Created by IntelliJ IDEA.
  User: naXa!
  Date: 09.12.2014
  Time: 17:09
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html lang="en">
<head>
    <title>University - ${student.id gt 0? 'Edit' : 'Add'} student profile</title>
</head>
<body>
<form name="form1" action="${pageContext.request.contextPath}/add" method="POST" style="font-size: large">
    <div align="center">
        <br /><br />
        <label>
            Student's name:
            <input name="name" type="text" size="25" required
                   value="${(not empty student.name)? student.name : 'Enter student\'s name here' }" />
        </label><br />
        <!--label>
            Student's profile photo:
            <input id="photo" type="?" draggable="true"/>
        </label><br /-->
        <label>
            Student's faculty:
            <select name="faculty" required>
                <c:forEach items="${faculties}" var="faculty">
                    <option <c:if test="${student.faculty eq faculty}">selected</c:if>>${faculty.name}</option>
                </c:forEach>
            </select>
        </label><br />
        <label>
            Student's rates:
            <input name="rates" type="text" value="${(not empty rates)? rates : 'Enter student\'s rates here'}" />
        </label><br />
        <input name="id" type="hidden" value="${student.id}" />
        <br /><br />
        <button type="submit">Save</button>
        <a href="${pageContext.request.contextPath}/list">
            <input type="button" value="Cancel" />
        </a>
        <br /><br />
    </div>
</form>
</body>
</html>
