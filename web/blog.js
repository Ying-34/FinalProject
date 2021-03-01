window.addEventListener("load", () => {
    loadAllBlogs();

    async function loadAllBlogs() {
        let responseObject = await fetch(`./articleendpoint?all=all`);

        let articles = await responseObject.json();
        displayArticles(articles);
    }

});


async function loadQueryEndpoint() {
    // let sort = document.getElementById("sortFunc").value;
    let query = document.getElementById("searchValue").value;

    let responseObject = await fetch('./articleendpoint?query=' + query);
    console.log(responseObject);

    let articles = await responseObject.json();
    console.log(articles);
    displayArticles(articles);
}



async function sortArticles(){
    let articles = document.querySelectorAll('.article');
    let articleArray = [];
    for (let i = 0; i < articles.length; i++) {
        articleArray.push(articles[i].id);
    }
    let articleArrayString = articleArray.toString();
    let order = document.querySelector('#sortName').value;
    let direction = document.querySelector('#sortDir').value;

    let responseObject = await fetch(`./articleendpoint?sort=${articleArrayString}&direction=${direction}&order=${order}`);
    let articleList = await responseObject.json();

    displayArticles(articleList);
}

