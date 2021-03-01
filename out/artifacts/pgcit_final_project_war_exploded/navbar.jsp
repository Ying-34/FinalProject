<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="./CSS/WelcomeHomePage.css">

<c:choose>
    <%--No-one is signed in --%>
    <c:when test='${(username == "") || (username == null)}'>
        <div id="navBar">
            <h1>All Blogs</h1>
            <div class="logoutDiv">
                <span><a href="./blog.jsp" title="Blog Page">Browse All Blogs</a></span>
                <span><a href="./CreateNewAccount.html" title="Create a new account">Create Account</a></span>
                <span><a href="./Login/Login.jsp" title="Click and Login">Login</a></span>
            </div>
        </div>
    </c:when>

    <%--A user is logged in for below code.  git  --%>
    <c:otherwise>
        <div id="navBar">
            <h1 class="h1">Welcome ${username}</h1>
            <div class="logoutDiv">
                <span><a href="./blog.jsp" title="Blog Page">Browse All Blogs</a></span>
                <span><a href="./UserHomePage.jsp" title="Jump to User Page">${username}'s Blog Page</a></span>
                <form action="./session-attribute" method="get">
                    <span><button type="submit" class="hide_button" name="logout">Logout</button></span>
                </form>
            </div>
        </div>
    </c:otherwise>
</c:choose>