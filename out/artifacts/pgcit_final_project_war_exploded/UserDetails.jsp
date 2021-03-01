<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>UserDetails</title>
    <link rel="stylesheet" href="./CSS/UserDetails.css">
    <link rel="javascript" href="./UserDetails.js">
</head>

<body>

<div class="Logo" onclick="location.href='index.jsp';">
    <h1>Array Of Light Blog Page</h1>
    <p align="center">Be Happy! Be Safe!</p>
</div>

<div class="container">
    <jsp:include page="./navbar.jsp"/>
    <div class="user">
        <div class="infor1">
            <h1>Current UserDetails</h1>
            <table>
                <tbody>
                <tr>
                    <td>Current First Name: <span>${user.getfName()}</span></td>
                </tr>

                <tr>
                    <td>Current Last Name: <span>${user.getlName()}</span></td>
                </tr>

                <tr>
                    <td>UserName: <span>${user.getUserName()}</span></td>
                </tr>

                <tr>
                    <td>Current Bio: <span>${user.getBio()}</span></td>
                </tr>
                <br>
                </tbody>
            </table>

            <form action="/deleteuser" method="get">
                <div class="btn3">
                    <input type="submit" value="delete account" name="delete" id="deleteButton">
                </div>
            </form>
        </div>

        <div class="infor2">

            <h1 id="${userMessage}">${userMessage}</h1>

            <h1>Edit your account</h1>

            <div id="menuItems">
                <form action="/fileupload" method="post" name="edituser" enctype="multipart/form-data">
                    <br>
                    <label for="firstname">Edit First Name: </label>
                    <br>
                    <input type="text" name="firstname" id="firstname" value="${user.getfName()}" required>
                    <br>
                    <label for="lastname">Edit Last Name: </label>
                    <br>
                    <input type="text" name="lastname" id="lastname" value="${user.getlName()}" required>
                    <br>
                    <label for="username">Edit Username: </label>
                    <br>
                    <input type="text" name="username" id="username" value="${user.getUserName()}" required>
                    <br>
                    <label for="bio">Edit Bio: </label>
                    <br>
                    <input type="text" name="bio" id="bio" value="${user.getBio()}" required>
                    <br>
                    <label for="avatar">Edit Avatar: </label>
                    <br>
                    <div class="profile_photo" width="500">
                        <input type="file" accept="image/jpg, image/jpeg, image/png" name="image" id="avatar" required>
                    </div>
                    <br>
                    <input type="submit" value="edit" id="editButton" name="edit">
                    <br>
                    <br>
                </form>
            </div>
        </div>
    </div>
</div>

<div id="footer">
    <p>Perfection is achieved not when there is nothing more to add, but when there is nothing more to take away.---Dan
        Salomon</p>
</div>

</body>
</html>