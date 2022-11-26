package models;

import utils.Utilities;

public class Post {

    private String author = "";
    private int likes = 0;

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

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String display() {
        String str = "";

        str += (author + "\n");

        if(likes > 0) {
            str += ("  -  " + likes + " people like this.\n");
        }
        else {
            str += "0 likes.\n";
        }

        return str;
    }
}
