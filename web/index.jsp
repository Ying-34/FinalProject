<%--
  Created by IntelliJ IDEA.
  User: Yvonne
  Date: 16/06/20
  Time: 21:59
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>WelcomePage</title>
    <link rel="stylesheet" href="./CSS/WelcomePage.css">
</head>

<body>

<div class="logo">
    <h1 style="text-align: center;">Array Of Light Blog Page</h1>
    <p class="logo_sentence">Be Happy! Be Safe!</p>
</div>

<div class="container">
    <div id="navBar">
        <h1 class="h1">Welcome, Rookie Programmers!</h1>
    </div>

    <div class="left">
        <div class="button1" style="cursor:pointer;"><a class="a1" href="./CreateNewAccount.html">Create New Account</a></div>
    </div>

    <div class="main">
        <div class="button2" style="cursor:pointer;"><a class="a2" href="./Login.jsp">Login</a></div>
    </div>

    <div class="right">
        <div class="button3" onclick="location.href='blog.jsp';" style="cursor:pointer;"><a class="a3" href="./blog.jsp">Blog Page</a></div>
    </div>
</div>

<div class="footer">
    <p>"Talk is cheap. Show me the code."---Linus Torvalds<img src="./images/finger.jpeg"></p>
</div>

</body>
</html>