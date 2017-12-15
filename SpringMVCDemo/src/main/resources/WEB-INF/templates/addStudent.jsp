<%--
  Created by IntelliJ IDEA.
  User: hadoop
  Date: 12/14/17
  Time: 5:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Spring MVC Error Form</title>
</head>
<style>
.error {
    color: #ff0000;
}

.errorStyle {
    color: #000;
    background-color: #ffEEEE;
    border: 3px solid #ff0000;
    padding: 8px;
    margin: 16px;
}
</style>
<body>
<h2>Information of Student</h2>
    <!-- Deprecated FormTag's "commandName" in favor of "modelAttribute" -->
    <form:form method="POST" action="/addStudent" modelAttribute="student">
    <form:errors path="*" cssClass="errorStyle" element="div" />
        <table>
            <tr>
                <td><form:label path="name">name:</form:label></td>
                <td><form:input path="name" /></td>
                <td><form:errors path="name" cssClass="error" /></td>
            </tr>
            <tr>
                <td><form:label path="age">age:</form:label></td>
                <td><form:input path="age" /></td>
            </tr>
            <tr>
                <td><form:label path="id">id:</form:label></td>
                <td><form:input path="id" /></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="Submit" /></td>
            </tr>
        </table>
    </form:form>
</body>
</html>
