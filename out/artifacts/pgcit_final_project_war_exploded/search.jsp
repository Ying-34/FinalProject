<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<div class="sideCard">
    <div class="search">
        <h4 class="card-header">Search</h4>
        <div class="card-body">
                <div class="searchInput">
                    <input type="text" name = "query" id="searchValue" placeholder="Search..">
                    <button id="searchBtn" class="btn" onclick="loadQueryEndpoint()">Go!</button>
                </div>
        </div>
    </div>

</div>

<div class="sideCard">
    <div class="sort">
        <h4 class="card-header"> Sort </h4>
        <div class="card-body">

                <div class="searchInput">
                    <select id = "sortName" name="sortName">
                        <option  name="title" value="title">Title</option>
                        <option  name = "username" value="username">Author</option>
                        <option  name = "dateOfEntry" value="dateOfEntry">Date</option>
                    </select>
                    <select id = "sortDir" name="sortDir">
                        <option  name="ascend" value="ASC">Ascending</option>
                        <option  name = "descend" value="DESC">Descending</option>
                    </select>

                </div>
                <button id="sortBtn" class="btn" onclick="sortArticles()">
                    Go!
                </button>

        </div>
    </div>
</div>
