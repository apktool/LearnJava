<%--
  Created by IntelliJ IDEA.
  User: hadoop
  Date: 12/13/17
  Time: 6:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User List</title>
</head>
<body>
    <table>
        <tr>
            <td><form:label path="username">username: </form:label></td>
            <td>${username}</td>
        </tr>
        <tr>
            <td><form:label path="password">password: </form:label></td>
            <td>${password}</td>
        </tr>
        <tr>
            <td><form:label path="address">address: </form:label></td>
            <td>${address}</td>
        </tr>
        <tr>
            <td><form:label path="receivePaper">receivePaper: </form:label></td>
            <td>${receivePaper}</td>
        </tr>
        <tr>
            <td><form:label path="favoriteFrameworks">favoriteFrameworks: </form:label></td>
            <td>
                <%
                String[] favoriteFrameworks = (String[])request.getAttribute("favoriteFrameworks");
                for(String framework: favoriteFrameworks) {
                    out.println(framework + " # ");
                }
                %>
            </td>
        </tr>
        <tr>
            <td><form:label path="gender">gender: </form:label></td>
            <td>${(gender=="M"? "Male": "Female")}</td>
        </tr>
    </table>
</body>
</html>