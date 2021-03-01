window.addEventListener('load',()=>{
    //Whenever the login page is loaded we want to clear the session storage on the client side
    sessionStorage.removeItem("username");
    sessionStorage.removeItem("userId");
});