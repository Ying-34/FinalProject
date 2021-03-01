let count = 0;
function displayArticles(articles) {
    //Clear the current Article(s) Card
    let articleCard = document.querySelector('.articleCard');
    articleCard.innerHTML = '';
    //If it's an array of articles then loop through the array and for each article add the article content to the correct divs, etc
    if (articles.length > 0) {
        articleCard.style.gridTemplateColumns = 'auto auto';

        for (let article of articles) {
            createArticle(article);

        }
        if(articles.length === 1){
            console.log(`${articles.text}`);
            articleCard.style.gridTemplateColumns = 'auto';
            let article_text = document.querySelector('.article_text');
            article_text.innerHTML = `${articles[0].text}`;
        }
        //Call the appendButtons method and pass in the array of articles. Then assign the event listener that allows the user to open up the article, and also assign the edit button event listener to each article
        if (count===0){
            appendButtons(articles);
            count=1;
        }

        assignOpenArticleEventlistener();
        assignEditEventlistener();

    } else if(articles.id > 0){
        console.log("Line 25");
        articleCard.style.gridTemplateColumns = 'auto';
        //else if there is still an associated id with an article, it must therefore be trying to load a single article. This passes in an object (instead of an array) to the createArticle and appendButtons methods
        createArticle(articles);

        let article_text = document.querySelector('.article_text');
        article_text.innerHTML = `${articles.text}`;

        let commentCard = document.createElement("div");
        commentCard.className = "commentCard";


        commentCard.innerHTML += `<dl>
                                        <dt>
                                            <label style="font-weight:bold;">Comments:</label><br><br>
                                        </dt>
                                        <dd>
                                            <input type="text" name="getcomment" id="commentText">
                                            <input type="submit" value="Comment!" id="commentBtn1" class="btn" onclick="makeComment(${articles.id})">
                                        </dd>
                                    </dl>`;

        articleCard.append(commentCard);
        let articleColumn = document.querySelector('.articleCard');
        articleColumn.style.display = "flex";
        articleColumn.style.flexDirection = "column";

        let comment = document.createElement("div");
        comment.className = "commentDefault";
        comment.innerHTML = `<p>No Comments yet.</p>`;
        commentCard.append(comment);
        let commentHolder = document.createElement("div");
        commentHolder.id = "commentHolder";
        commentCard.appendChild(commentHolder);

        let artId = `${articles.id}`;
        getBlogComments(artId);
        appendButtons(articles);
        assignEditEventlistener();
    } else {
        //This will probably only show up on the user's home page, when they've first created the account. Otherwise there should always be at least one blog being displayed on wither the user's home page or blog page
        let column = document.querySelector('.articleColumn');
        column.innerHTML = `
                                <div class="article_card">
                                    <h2 class="article_title">You have no blogs. <br>Click on the giant plus to get creating!</h2>     
                                </div>`;
    }
}

function createArticle(article) {

    let articleCard = document.querySelector('.articleCard');
    articleCard.innerHTML += `
                            <div class = "article" name = "one" id="${article.id}" title="Open article to view comments">
                                <h2 class ="${article.id}">${article.title}</h2>
                                <div class = "article_details ${article.id}" name = "one">
                                    <img src="./images/${article.imageFile}" class="article_image ${article.id}">
                                    <div class ="${article.id} articleDetails">
                                            <h5 class ="${article.id}">${article.username}</h5>
                                            <h5 class = "article_date ${article.id}">${article.dateOfEntry} <br>@ ${article.timeOfEntry}</h5>
                                    </div>
                                </div>
                                 <p class = "article_text ${article.id}">${article.blurb}</p>`;

}

function appendButtons(article) {
    let articleList = document.querySelectorAll('.article');

    if(article.length>0) {
        for (let i = 0; i < article.length; i++) {

            let buttonsDiv = document.createElement("div");
            buttonsDiv.className = 'buttonDiv';

            if (`${article[i].username}` === sessionStorage.getItem("username")) {

                buttonsDiv.innerHTML = `<form action="/article" name="delete" method="get">

                                        <br>
                                        <input type="submit" id="deletebutton" class="deleteArticleBtn" name="delete" value ="Delete">
                                        <input type="hidden" name="articleId" value="${article[i].id}">
                                    </form>`;
                buttonsDiv.innerHTML += `<input type="submit"  name="update" value ="Edit" class="editbutton" id="${article[i].id}"> </div>`;

            }
            articleList[i].appendChild(buttonsDiv);
        }
    } else {
        let singleArticle = document.querySelector('.article');

        if (`${article.username}` === sessionStorage.getItem("username")) {
            let buttonsDiv = document.createElement("div");
            buttonsDiv.className = 'buttonDiv';
            buttonsDiv.innerHTML += `<form action="/article" name="delete" method="get">
                                        <br>
                                        <input type="submit" id="deletebutton" name="delete" class="deleteArticleBtn" value ="Delete">
                                        <input type="hidden" name="articleId" value="${article.id}">
                                    </form>
                                    <input type="submit"  name="update" value ="Edit" class="editbutton" id="${article.id}">`;
            singleArticle.appendChild(buttonsDiv);
        }

    }
}


async function assignEditEventlistener() {
    let article = document.querySelectorAll('.editbutton').forEach((element) =>
        element.addEventListener('click', editArticle));
}

async function assignOpenArticleEventlistener() {
    let article = document.querySelectorAll('.article').forEach((element) =>
        element.addEventListener('click', loadSingleArticle));
}

async function loadSingleArticle(articleId) {


    let articleClasslist = articleId.target.classList;
    for (let i = 0; i < articleClasslist.length; i++) {
        if (articleClasslist[i] >= 0) {

            let responseObject = await fetch(`/article?one=${articleClasslist[i]}`);
            let article = await responseObject.json();

            displayArticles(article);
        }
    }
}

async function makeComment(articleId) {

    let user = sessionStorage.getItem("userId");

    let username = sessionStorage.getItem("username");

    if(username=== null){
        alert("Please log-in to make a comment!");
        document.querySelector('#commentText').value = '';
    } else {
        let comment = document.querySelector("#commentText");
        let responseObject = await fetch(`/comment?create=${comment.value}&user=${user}&article=${articleId}&username=${username}`);
        let commentlist = await responseObject.json();

        let commentholder = document.querySelector('#commentHolder');
        commentholder.innerHTML = '';

        getBlogComments(articleId);

        document.querySelector('#commentText').value = '';
    }

}


async function getBlogComments(articleId) {

    let responseObject = await fetch(`/comment?all=${articleId}`);
    let commentlist = await responseObject.json();

    let commentHolder = document.querySelector("#commentHolder");

    if(commentlist.length>0) {
        for (let comment of commentlist) {

            let commentText = document.createElement("div");
            let deleteBtn = document.createElement("button");
            deleteBtn.className = 'deleteBtn';

            deleteBtn.addEventListener('click', function () {
                console.log("delete");
                deleteComment(articleId, comment);
            });

            commentText.className = 'comment';
            commentText.append(deleteBtn);
            commentText.innerHTML += `<dl><dt>${comment.username}: </dt><dd>${comment.text} <span>${comment.time}<span></dd></dl>`;

            commentHolder.appendChild(commentText);

        }
        let deleteTheButton = document.querySelectorAll('.deleteBtn');
        for (let i = 0; i < deleteTheButton.length; i++) {
            deleteTheButton[i].addEventListener('click', function () {
                console.log("Line 215");
                deleteComment(articleId, commentlist[i]);
            });
        }


        document.querySelector(".commentDefault").innerHTML = ``;
    } else {
        document.querySelector(".commentDefault").innerHTML = `<p>No Comments yet.</p>`;
    }
}

async function deleteComment(articleId, comment) {

    let loginUsersId = sessionStorage.getItem("userId");
    let responseObject = await fetch(`/article?one=${articleId}`);
    let article = await responseObject.json();

    if(`${article.authorId}` === loginUsersId || `${comment.userId}` === loginUsersId){
        let responseObject = await fetch(`/comment?delete=${comment.id}&article=${articleId}`);
        let remainingComments = await responseObject;

        if(remainingComments === null){

        } else {
            let commentholder = document.querySelector('#commentHolder');
            commentholder.innerHTML = '';
            getBlogComments(articleId);
        }
    } else {
        alert("You cannot delete this comment!");
    }

}

async function editArticle(event) {
    let articleId = event.target.id;
    let responseObject = await fetch(`/article?one=${articleId}`);
    let article = await responseObject.json();

    let articleCard = document.querySelector('.articleCard');
    articleCard.innerHTML = `
                            <form action="/article" name="update" method="get">
                            <div class = "article" name = "update" id="${article.id}">
                                <div class="editArticle">
                                <h2 class ="${article.id} article_title"></h2>
                                <label for="title">Blog Title</label>
                                <input type="text" value="${article.title}" name="title" id ="title" required>
                                <label for="text">Blog Content</label>
                                <textarea name="text" rows="25" id = "text" required>${article.text}</textarea>
                                <input type="hidden" name="articleId" value="${article.id}">
                                </div>
                            </div>
                            <input type="submit" name="update" value="Submit Changes">
                            </form>`;
}
