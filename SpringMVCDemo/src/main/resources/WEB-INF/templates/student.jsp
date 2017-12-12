<%--
  Created by IntelliJ IDEA.
  User: hadoop
  Date: 12/12/17
  Time: 9:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="name" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Spring MVC Form Demo</title>
</head>
<body>

    <h2>Student Information</h2>
    <form:form action="/addStudent" method="POST">
        <table>
            <tr>
                <td><form:label path="name">name:</form:label></td>
                <td><form:input path="name"/></td>
            </tr>
            <tr>
                <td><form:label path="age">age:</form:label></td>
                <td><form:input path="age"/></td>
            </tr>
            <tr>
                <td><form:label path="id">id:</form:label></td>
                <td><form:input path="id"/></td>
            </tr>
            <tr>
                <td colspan="2"><input value="Submit" type="submit"/></td>
            </tr>
        </table>
    </form:form>
</body>
</html>
