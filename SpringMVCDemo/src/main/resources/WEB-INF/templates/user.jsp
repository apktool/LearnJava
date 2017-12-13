<%--
  Created by IntelliJ IDEA.
  User: hadoop
  Date: 12/13/17
  Time: 6:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Password</title>
</head>
<body>
<form:form method="POST" action="/addUser">
    <table>
        <tr>
            <td><form:label path="username">username:</form:label></td>
            <td><form:input path="username" /></td>
        </tr>
        <tr>
            <td><form:label path="password">password</form:label></td>
            <td><form:password path="password" /></td>
        </tr>
        <tr>
            <td><form:label path="address">address</form:label></td>
            <td><form:textarea path="address" /></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Submit"/>
            </td>
        </tr>
    </table>
</form:form>
</body>
</html>
