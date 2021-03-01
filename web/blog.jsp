<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home Page</title>
    <link rel="stylesheet" href="./CSS/WelcomeHomePage.css">
    <script src="${pageContext.request.contextPath}/blog.js"></script>
    <script src="./displayArticlesAndComments.js"></script>
</head>
<body>
<div class="Logo" style="margin-bottom:0">
    <h1>Array Of Light Blog Page</h1>
    <p>Be Happy! Be Safe!</p>
</div>

<jsp:include page="./navbar.jsp"/>

<div class="container">
    <div class="main">
        <div class="articleColumn">
            <h1>Articles </h1>
            <div class="articleCard">

            </div>
        </div>
        <div class="sideColumn">
            <jsp:include page="./search.jsp"/>

            <c:if test="${username != null}">
                <jsp:include page="./create.jsp"/>
            </c:if>



        </div>
    </div>
</div>
<div class="footer">
    <p>“Give a man a program, frustrate him for a day.
        Teach a man to program, frustrate him for a lifetime.”
        --- Muhammad Waseem</p>
</div>

</body>
</html>
