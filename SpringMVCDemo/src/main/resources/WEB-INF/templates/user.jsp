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
            <td><form:label path="receivePaper">receivePaper</form:label></td>
            <td><form:checkbox path="receivePaper" /></td>
        </tr>
        <tr>
            <td><form:label path="favoriteFrameworks">subscribePaper</form:label></td>
            <td><form:checkboxes items="${webFrameworkList}" path="favoriteFrameworks" /></td>
        </tr>
        <tr>
            <td><form:label path="gender">gender</form:label></td>
            <td>
                <form:radiobutton path="gender" value="M" label="Male" checked="true"/>
                <form:radiobutton path="gender" value="F" label="Female"/>
            </td>
        </tr>
        <tr>
            <td><form:label path="favoriteNumber">favoriteNumber</form:label></td>
            <td><form:radiobuttons items="${numbersList}" path="favoriteNumber" /></td>
        </tr>
        <tr>
            <td><form:label path="country">Country:</form:label></td>
            <td>
                <form:select path="country">
                    <form:option value="NONE" label="Select..."/>
                    <form:options items="${countryList}"/>
                </form:select>
            </td>
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
