package ictgradschool.project.util;

import java.io.Serializable;

public class Article implements Serializable {
    private int id;
    private String dateOfEntry;
    private String timeOfEntry;
    private String text;
    private String title;
    private String imageFile;
    private int authorId;
    private String username;
    private String blurb;

    public Article() {

    }

    public Article(int id, String dateOfEntry, String timeOfEntry, String text, String title, String imageFile, int authorId, String username) {
        this.id = id;
        this.dateOfEntry = dateOfEntry.substring(0,11);
        this.text = text;
        this.title = title;
        this.imageFile = imageFile;
        this.timeOfEntry = timeOfEntry.substring(10);
        this.authorId = authorId;
        this.username = username;
        setBlurb();
    }



    public int getAuthorId() {
        return authorId;
    }

    public int getId() {
        return id;
    }

    public String getTimeOfEntry() {
        return timeOfEntry;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getDateOfEntry() {
        return dateOfEntry;
    }

    public void setDateOfEntry(String dateOfEntry) {
        this.dateOfEntry = dateOfEntry.substring(0,11);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTimeOfEntry(String timeOfEntry) {
        this.timeOfEntry = timeOfEntry.substring(10);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageFile() {
        return imageFile;
    }

    public void setImageFile(String imageFile) {
        this.imageFile = imageFile;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBlurb(){
        return blurb;
    }

    public void setBlurb() {
        if(getText().length()<250){
            this.blurb = getText();
        } else {
            this.blurb = getText().substring(0,250)+"...";
        }
    }

}
