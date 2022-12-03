package models;

import utils.Utilities;

public abstract class Post {

    private String author = "";

    public abstract String displayCondensed();

    public Post(String author) {
        this.author = Utilities.truncateString(author, 10);
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        if (Utilities.validateStringLength(author, 10)) {
            this.author = author;
        }
    }

    public String display() {
        return (author + "\n");
    }
}