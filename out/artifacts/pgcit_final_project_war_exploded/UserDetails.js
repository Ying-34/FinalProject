window.addEventListener("load",function () {

    let usernameTaken = document.getElementById('Sorry that username is taken');
    let usernameField = document.getElementById('username');
    usernameField.addEventListener('input',usernameIsTaken);

    function usernameIsTaken() {
        if (usernameTaken.innerText !== "") {
            alert("That username is already in use");
        }
    }

});