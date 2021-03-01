window.addEventListener('load', () => {
    //Methods to load the Authors blogs and user details when the page first loads
    loadAuthorsBlogs();
    loadUserDetails();

    async function loadAuthorsBlogs() {
        //Gets the user's username and userID which have been assigned to the #user using JSP
        let user = document.getElementById('user');
        let userClass = user.classList;

        let userId;
        let username;

        //Loops through the class list to get the user's username and userID and assigns them to the Client-side session storage
        for (let i = 0; i < userClass.length; i++) {

            if (parseInt(userClass[i]) > 0) {
                userId = userClass[i];
                sessionStorage.setItem("userId",userClass[i]);
            } else {
                sessionStorage.setItem("username",userClass[i]);
                username = userClass[i];
            }
        }
        //Fetches the users' blogs and then passes them into the displayArticles method to load them onto the User's home page
        let responseObject = await fetch(`/articleendpoint?author=${userId}`);
        let articles = await responseObject.json();
        displayArticles(articles);
    }

});

async function loadUserDetails(){
    let username = sessionStorage.getItem("username");
    let userId = sessionStorage.getItem("userId");
    console.log(username);
    let responseObject = await fetch(`/deleteuser?getuser=${username}`);
    let user = await responseObject.json();
    console.log(user);
    displayUserDetails(user);
}

function displayUserDetails(user) {
    let fname = user.fName;
    let lname = user.lName;
    let avatar = user.profileImage;
    let dob = user.dateOfBirth;
    let bio = user.bio;

    let image = document.querySelector('#userImg');
    image.src = `../images/${avatar}`;
    document.querySelector('#first').innerHTML = `${fname}`;
    document.querySelector('#last').innerHTML = `${lname}`;
    document.querySelector('#dob').innerHTML = `${dob}`;
    document.querySelector('#bio').innerHTML = `${bio}`;
}






