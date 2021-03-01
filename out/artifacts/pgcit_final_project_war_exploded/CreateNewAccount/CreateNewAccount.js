window.addEventListener("load", function () {

    let submitButton = document.getElementById("submitButton");
    let match = document.querySelector("#match");

    let menuItems = document.getElementById("menuItems").querySelectorAll("input");
    addEventListeners();

    function addEventListeners() {
        for (let i = 0; i < menuItems.length; i++) {
            menuItems[i].addEventListener('input', disableButton);
        }
    }

    let userNameInvaild;

    //called on input event
    function disableButton() {

        for (let i = 0; i < menuItems.length; i++) {
            console.log(menuItems[i]);
            if (menuItems[i].value === "" || checkPasswordsMatch() === false || userNameInvaild) {
                submitButton.disabled = true;
                submitButton.style.backgroundColor = "red";
                break;
            } else {
                submitButton.disabled = false;
                submitButton.style.backgroundColor = "green";

            }

        }
    }

    //get Json Object from User end point.
    async function getUserObject() {

        let users = await fetch("/userendpoint");
        let userObject = await users.json();

        await getUserNameList(userObject)
    }

    let userNameList = [];
    //Populates username list
    function getUserNameList(userObject) {

        for (let i = 0; i < userObject.length; i++) {
            if (userObject[i].userName !== undefined) {
                userNameList.push(userObject[i].userName);
            }
        }
        checkIfUserExists();
    }

    let usernameField = document.querySelector("#username");
    usernameField.addEventListener('input', checkIfUserExists);

    function checkIfUserExists() {

        let usernameValid = true;

        if(usernameField.value.length>20){
            alert("Sorry that username is too long");
            usernameField.style.border = "3px solid red";
            usernameValid = false;
            userNameInvaild = true;
            disableButton();
        }

        for (let i = 0; i < userNameList.length; i++) {

            if (usernameField.value === userNameList[i]) {
                alert("Sorry that username already exists");
                usernameField.style.border = "3px solid red";
                usernameValid = false;
                userNameInvaild = true;
                disableButton();
            }
        }
        if (usernameValid) {
            usernameField.style.border = "2px solid rgba(0, 0, 0, .125)";
            userNameInvaild = false;
            disableButton();
        }

    }

    let password = document.querySelector("#password");
    let confirmedPassword = document.querySelector("#confirmed_password");

    confirmedPassword.addEventListener('input', inputMessage);


    function checkPasswordsMatch() {
        return password.value !== "" && password.value === confirmedPassword.value;
    }

    function inputMessage() {

        if (checkPasswordsMatch()) {
            match.innerText = "Passwords match :)";
        } else {
            match.innerText = "Passwords need to match";
        }
    }

    getUserObject();
    disableButton();

    radioBtnOnClick();

});

function radioBtnOnClick() {
    let radioBtn = document.querySelectorAll('.radioBtn');
    let avatarImage = document.querySelectorAll('.avatarImage');
    for (let i = 0; i < radioBtn.length; i++) {
        radioBtn[i].addEventListener('click', function () {
            for (let j = 0; j < radioBtn.length; j++) {
                if (j === i) {
                    console.log([j]);
                    avatarImage[j].style.border = "thick solid pink";
                    avatarImage[j].style.width = "150px";
                    avatarImage[j].style.height = "150px";
                } else {
                    avatarImage[j].style.border = "none";
                    avatarImage[j].style.width = "100px";
                    avatarImage[j].style.height = "100px";
                }
            }


        })
    }
}