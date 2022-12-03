package controllers;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import models.*;
import utils.ISerializer;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class NewsFeed implements ISerializer {

    private List<Post> posts;

    public NewsFeed() {
        posts = new ArrayList<>();
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public boolean addPost(Post post) {
        return posts.add(post);
    }

    public String show() {
        String str = "";

        for (Post post : posts) {
            str += posts.indexOf(post) + ": " + post.displayCondensed() + "\n";
        }

        if (str.isEmpty()) {
            return "No Posts";
        } else {
            return str;
        }
    }

    public String showPhotoPosts() {
        String str = "";

        for (Post post : posts) {
            if (post instanceof PhotoPost) {
                str += posts.indexOf(post) + ": " + post.display() + "\n";
            }
        }

        if (str.isEmpty()) {
            return "No Photo Posts";
        } else {
            return str;
        }
    }

    public String showMessagePosts() {
        String str = "";

        for (Post post : posts) {
            if (post instanceof MessagePost) {
                str += posts.indexOf(post) + ": " + post.display() + "\n";
            }
        }

        if (str.isEmpty()) {
            return "No Message Posts";
        } else {
            return str;
        }
    }

    public String showEventPosts() {
        String str = "";

        for (Post post : posts) {
            if (post instanceof EventPost) {
                str += posts.indexOf(post) + ": " + post.display() + "\n";
            }
        }

        if (str.isEmpty()) {
            return "No Event Posts";
        } else {
            return str;
        }
    }

    public Post deletePost(int indexToDelete) {
        if (isValidIndex(indexToDelete)) {
            return posts.remove(indexToDelete);
        }
        return null;
    }

    public boolean updateMessagePost(int indexToUpdate, String author, String message) {
        //find the object by the index number
        Post foundMessage = findPost(indexToUpdate);

        //if the object exists, use the details passed in the parameters to
        //update the found object in the ArrayList.
        if ((foundMessage != null) && (foundMessage instanceof MessagePost)) {
            foundMessage.setAuthor(author);
            ((MessagePost) foundMessage).setMessage(message);
            return true;
        }

        //if the object was not found, return false, indicating that the update was not successful
        return false;
    }

    public boolean updatePhotoPost(int indexToUpdate, String author, String caption, String filename) {
        //find the object by the index number
        Post foundPost = findPost(indexToUpdate);

        //if the object exists, use the details passed in the parameters to
        //update the found object in the ArrayList.
        if ((foundPost != null) && (foundPost instanceof PhotoPost)) {
            foundPost.setAuthor(author);
            ((PhotoPost) foundPost).setCaption(caption);
            ((PhotoPost) foundPost).setFilename(filename);
            return true;
        }

        //if the object was not found, return false, indicating that the update was not successful
        return false;
    }

    public boolean updateEventPost(int indexToUpdate, String author, String eventName, double eventCost) {
        //find the object by the index number
        Post foundPost = findPost(indexToUpdate);

        //if the object exists, use the details passed in the parameters to
        //update the found object in the ArrayList.
        if ((foundPost != null) && (foundPost instanceof EventPost)) {
            foundPost.setAuthor(author);
            ((EventPost) foundPost).setEventName(eventName);
            ((EventPost) foundPost).setEventCost(eventCost);
            return true;
        }

        //if the object was not found, return false, indicating that the update was not successful
        return false;
    }

    public Post findPost(int index) {
        if (isValidIndex(index)) {
            return posts.get(index);
        }
        return null;
    }

    public int numberOfPosts() {
        return posts.size();
    }

    public int numberOfMessagePosts() {
        int number = 0;
        for (Post post : posts) {
            if (post instanceof MessagePost) {
                number++;
            }
        }
        return number;
    }

    public int numberOfPhotoPosts() {
        int number = 0;
        for (Post post : posts) {
            if (post instanceof PhotoPost) {
                number++;
            }
        }
        return number;
    }

    public int numberOfEventPosts() {
        int number = 0;
        for (Post post : posts) {
            if (post instanceof EventPost) {
                number++;
            }
        }
        return number;
    }

    public void likeAPost(int index) {
        Post post = null;
        if (isValidIndex(index)) {
            post = posts.get(index);
            if ((post instanceof LikedPost)) {
                ((LikedPost) post).likeAPost();
            }
        }
    }

    public void unLikeAPost(int index) {
        Post post = null;
        if (isValidIndex(index)) {
            post = posts.get(index);
            if ((post instanceof LikedPost)) {
                ((LikedPost) post).unlikeAPost();
            }
        }
    }

    /**
     * The load method uses the XStream component to read all the models.MessagePost objects from the posts.xml
     * file stored on the hard disk.  The read objects are loaded into the posts ArrayList
     *
     * @throws Exception An exception is thrown if an error occurred during the load e.g. a missing file.
     */
    @SuppressWarnings("unchecked")
    public void load() throws Exception {
        //list of classes that you wish to include in the serialisation, separated by a comma
        Class<?>[] classes = new Class[]{EventPost.class, MessagePost.class, PhotoPost.class, Post.class};

        //setting up the xstream object with default security and the above classes
        XStream xstream = new XStream(new DomDriver());
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypes(classes);

        //doing the actual serialisation to an XML file
        ObjectInputStream in = xstream.createObjectInputStream(new FileReader("posts.xml"));
        posts = (List<Post>) in.readObject();
        in.close();
    }

    /**
     * The save method uses the XStream component to write all the objects in the posts ArrayList
     * to the posts.xml file stored on the hard disk.
     *
     * @throws Exception An exception is thrown if an error occurred during the save e.g. drive is full.
     */
    public void save() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("posts.xml"));
        out.writeObject(posts);
        out.close();

    }

    public String fileName(){
        return "posts.xml";
    }

    public boolean isValidIndex(int index) {
        return (index >= 0) && (index < posts.size());
    }

    public boolean isValidMessagePostIndex(int index) {
        if (isValidIndex(index)) {
            return (posts.get(index)) instanceof MessagePost;
        }
        return false;
    }

    public boolean isValidPhotoPostIndex(int index) {
        if (isValidIndex(index)) {
            return (posts.get(index)) instanceof PhotoPost;
        }
        return false;
    }

    public boolean isValidEventPostIndex(int index) {
        if (isValidIndex(index)) {
            return (posts.get(index)) instanceof EventPost;
        }
        return false;
    }

}
