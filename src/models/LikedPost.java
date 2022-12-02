package models;

public class LikedPost extends Post {

    private int likes = 0;

    public LikedPost(String author){
        super(author);
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public void likeAPost(){
        likes++;
    }

    public void unlikeAPost(){
        likes--;
    }

    public String display() {
        String str = super.display();

        if(likes > 0) {
            str += ("  -  " + likes + " people like this.\n");
        }
        else {
            str += "0 likes.\n";
        }

        return str;
    }

}
