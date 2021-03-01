<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home Page</title>
    <link rel="stylesheet" href="./CSS/WelcomeHomePage.css">
    <script src="./userhome.js"></script>
    <script src="./displayArticlesAndComments.js"></script>
</head>
<body>
<div class="Logo" onclick="location.href='index.jsp';" style="margin-bottom:0">
    <h1>Array Of Light Blog Page</h1>
    <p>Be Happy! Be Safe!</p>
</div>

<div id="navBar">
    <div class="homePage">
        <h1 id ="user" class="${username} ${userID}">Welcome ${username}</h1>
    </div>
    <div>
        <span><a href="./blog.jsp" title="Blog Page">Browse All Blogs</a></span>
        <span><a href="./UserHomePage.jsp" title="Jump to User Page">${username}'s Blog Page</a></span>
        <form action="/session-attribute" method="get">
            <span><button type="submit" class="hide_button" name="logout" title="Click to Logout">Logout</button></span>
        </form>
    </div>

</div>

<div class="container">
    <div class="main">
        <div class="articleColumn">
            <h1>Your Articles</h1>
            <div class="articleCard">

            </div>
        </div>
        <div class="sideColumn">

            <jsp:include page="./create.jsp"/>

            <div class="sideCard">
                <div class="userDetail">
                    <h4 class="card-header">About me</h4>
                    <div class="card-body">
                        <div class="userDetailList">
                            <div class="userImg">
                            <span>
                                <img src="../images/${user.profileImage}" alt="avatarImage" id="userImg">
                            </span>
                            </div>
                                <div id="personalDetail">
                                    <p>First Name:</p>
                                    <p id="first">${user.fName}</p>
                                    <p>Last Name:</p>
                                    <p id="last">${user.lName}</p>
                                    <p>Birthday:</p>
                                    <p id="dob"></p>
                                </div>
                                <div style="padding-top: 30px">
                                    <p>Self Introduction:</p>
                                    <p id="bio"></p>
                                </div>
                                <form action="/deleteuser" method="get" >
                                    <input type="submit" value = "Edit Details" name="userdetails">
                                    <input type="hidden" name="user" value="${username}">
                                </form>
                            </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="footer">
    <p>U are the Semicolon TO MY STATEMENTS!</p>
</div>

</body>
</html>
