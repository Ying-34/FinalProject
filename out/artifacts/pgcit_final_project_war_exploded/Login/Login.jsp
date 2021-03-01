<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Blog login Page</title>
    <link rel="stylesheet" href="../CSS/CreateNewAccount.css">
    <script src="../Login.js"></script>
</head>
<body>
<div class="Logo" onclick="location.href='../index.jsp';">
    <h1>Array Of Light Blog Page</h1>
    <p>Be Happy! Be Safe!</p>
</div>
<div class="container">
    <div id="navBar">
        <h1 class="h1">Log In</h1>
        <div>
            <span><a href="../CreateNewAccount.html" title="CreateNewAccount">Create New Account</a></span>
            <span><a href="../blog.jsp" title="Jump to Home Page">Browse All Blogs</a></span>
        </div>
    </div>
    <div id="main" style="margin: 38% 0">
<%--        TODO: . add before userlogin--%>
        <form action="/userlogin" method="post">
            <h1>Log in</h1>

            <div class="username">
                Username:<br>
                <input type="text" name="username">
                <c:if test="${username!=null}">
                    <span id="match"><label>The username is not found please try again!</label></span>
                </c:if>
            </div>

            <div class="password">
                Password:<br>
                <input type="password" name="password">
                <c:if test="${password!=null}">
                    <span id="match"><label>The username is not found please try again!</label></span>
                </c:if>
            </div>

            <div class="btn">
                <input type="submit" value="submit">
            </div>

        </form>
    </div>
</div>
<div class="footer">
    <p>You build it , You run it. ---Werner Vogels</p>
</div>

</body>
</html>