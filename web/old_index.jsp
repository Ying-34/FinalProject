<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Temporary Landing Page</title>
    <style type="text/css">
        body {
            width: 600px;
            margin: 6em auto;
            color: #444;
            text-align: justify;
            font-family: sans-serif;
            font-size: 14pt;
            line-height: 150%;
        }

        h1, h2 {
            text-decoration: lightgrey underline;
            color: #222;
        }

        h2 {
            margin-top: 2em;
        }

        code {
            background-color: rgba(255, 167, 182, 0.41);
            color: darkred;
            font-family: monospace;
            border: 1px solid darkred;
            padding: 1px 5px;
        }

        a:visited, a:active, a {
            color: dodgerblue;
            text-decoration: none;
        }

        a:hover {
            text-decoration: underline;
        }
    </style>

    <script src="./blog.js"></script>
</head>
<body>


<h1>Temporary Landing Page</h1>

<p>TODO: Replace or modify this page with an appropriate homepage for your project.</p>

<p><a href="./CreateNewAccount.html">Create New Account</a></p>
<p><a href="CreateNewArticle.jsp">Create new article</a></p>
<p><a href="Login.jsp">Login</a></p>
<%--<form action="/articleendpoint" method="get">--%>
<p ><a href="./blog.jsp">Browse Blogs</a></p>
<%--</form>--%>
<%--<button type="submit" name="all"><span>Blogs</span></button>--%>



</body>
</html>