import controllers.NewsFeed;
import models.EventPost;
import models.MessagePost;
import models.PhotoPost;

public class Test {

    public static void main(String args[]) {
        // Create 2 MessagePost objects.
        MessagePost messagePost1 = new MessagePost("Mary", "Hi there.");
        MessagePost messagePost2 = new MessagePost("John", "I'm on my way.");

        // Create 2 PhotoPosts objects.
        PhotoPost photoPost1 = new PhotoPost("Larry", "img1.jpg", "First day in college");
        PhotoPost photoPost2 = new PhotoPost("Moe", "img2.jpg", "Graduation day");

        // Create 2 PhotoPosts objects.
        EventPost eventPost1 = new EventPost("Homer", "Bart's Birthday", 10);
        EventPost eventPost2 = new EventPost("Marge", "Simpson Family Reunion", 20);

        // Create 1 NewsFeed object.
        NewsFeed newsFeed = new NewsFeed();

        System.out.println("-----------------Adding Message and Photo Posts------------------");
        // Add 1 PhotoPost object to the NewsFeed object.
        if (newsFeed.addPost(photoPost1)) {
            System.out.println("Photo Post 1 Added Successfully ("
                    + photoPost1.getAuthor() + ": "
                    + photoPost1.getCaption() + ","
                    + photoPost1.getFilename() + ")");
        }

        // Add 1 MessagePost object to the NewsFeed object.
        if (newsFeed.addPost(messagePost1)) {
            System.out.println("Message Post 1 Added Successfully ("
                    + messagePost1.getAuthor() + ": "
                    + messagePost1.getMessage() + ")");
        }

        // Add an EventPost object to the NewsFeed object.
        if (newsFeed.addPost(eventPost1)) {
            System.out.println("Event Post 1 Added Successfully ("
                    + eventPost1.getAuthor() + ": "
                    + eventPost1.getEventName() + ","
                    + eventPost1.getEventCost() + ")");
        }

        // Add another PhotoPost object to the NewsFeed object.
        if (newsFeed.addPost(photoPost2)) {
            System.out.println("Photo Post 2 Added Successfully ("
                    + photoPost2.getAuthor() + ": "
                    + photoPost2.getCaption() + ","
                    + photoPost2.getFilename() + ")");
        }

        // Add an EventPost object to the NewsFeed object.
        if (newsFeed.addPost(eventPost2)) {
            System.out.println("Event Post 2 Added Successfully ("
                    + eventPost2.getAuthor() + ": "
                    + eventPost2.getEventName() + ","
                    + eventPost2.getEventCost() + ")");
        }

        // Add another MessagePost object to the NewsFeed object.
        if (newsFeed.addPost(messagePost2)) {
            System.out.println("Message Post 2 Added Successfully ("
                    + messagePost2.getAuthor() + ": "
                    + messagePost2.getMessage() + ")");
        }

        // List all messagePost and photoPosts from the NewsFeed object.
        System.out.println("-----------------Newsfeed - show() method contents------------------");
        System.out.println(newsFeed.show());
    }

}
