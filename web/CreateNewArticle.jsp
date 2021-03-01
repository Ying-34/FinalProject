<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link rel="stylesheet" href="CSS/NewArticle.css">
    <title>Create A New Article</title>
</head>
<body>
<div class="Logo" onclick="location.href='index.jsp';">
    <h1>Array Of Light Blog Page</h1>
    <p>Be Happy! Be Safe!</p>
</div>

<div class="newArticle">
    <jsp:include page="navbar.jsp"/>
</div>

<div class="container">
    <div class="main" >
        <form action="./fileupload" method="post" enctype="multipart/form-data">

            <div class="newtop">
                <label for="new-article-title">Title:</label><br>
                <input type="text" name="title" id="new-article-title" placeholder="Your title here" required>
            </div>
            <div class="newtop">
                <label for="new-article-image">Image:</label><br>
                <input type="file" accept="image/jpg, image/jpeg, image/png" name="image" id="new-article-image"
                       required>
            </div>
            <div class="newtop">
                <label for="new-article-body">Content:</label><br>
                <textarea name="text" id="new-article-body" placeholder="Your content here" rows="10"
                          required></textarea>
            </div>
            <div><br>
                <button type="submit" class="submitArticleBtn" name="add">Submit</button>
            </div>
        </form>
    </div>
</div>
<div class="footer">
    <p>Code is like humor. When you have to explain it, it's bad! ---Cory House</p>
</div>
</body>
</html>
