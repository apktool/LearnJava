<%--
  Created by IntelliJ IDEA.
  User: hadoop
  Date: 12/12/17
  Time: 8:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hello World</title>
</head>
<body>
    <h2>Hello, ${message}</h2>
    <p><a href="/staticPage">Click Here</a> Will redirect url to static page</p>
    <p><a href="/redirect">Click Here</a> Will redirect url to redirect, and then redirect to finalPage</p>
    <p><a href="/user">Click Here</a> Will redirect url user page</p>
</body>
</html>
