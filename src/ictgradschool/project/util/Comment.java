package ictgradschool.project.util;

public class Comment {
    private int id;
    private String text;
    private String time;
    private String username;
    private int userId;
    private int articleId;

    public Comment(int id, String text, String time, String username, int userId, int articleId) {
        this.id = id;
        this.text = text;
        this.time = time;
        this.username = username;
        this.userId = userId;
        this.articleId = articleId;
    }

    public Comment() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
