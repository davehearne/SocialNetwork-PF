package main;

import controllers.NewsFeed;
import models.EventPost;
import models.MessagePost;
import models.PhotoPost;
import models.Post;
import utils.ScannerInput;

public class Driver {

    private NewsFeed newsFeed = new NewsFeed();

    public static void main(String[] args) {
        new Driver();
    }

    public Driver() {
        runMenu();
    }

    private int mainMenu(){
        return ScannerInput.readNextInt("""
               Social Network Menu
                  ---------------------
                  1) Add a Post
                  2) Update a Post
                  3) Delete a Post
                  4) List Posts
                  ---------------------
                  5) Save Posts
                  6) Load Posts
                  ---------------------
                  0) Exit
               ==>>  """);
    }

    private void runMenu(){
        int option = mainMenu();

        while (option != 0){

            switch (option){
                case 1 -> addPost();
                case 2 -> updatePost();
                case 3 -> deletePost();
                case 4 -> viewPosts();
                case 5 -> savePosts();
                case 6 -> loadPosts();
                default -> System.out.println("Invalid option entered: " + option);
            }

            //pause the program so that the user can read what we just printed to the terminal window
            ScannerInput.readNextLine("\nPress enter key to continue...");

            //display the main menu again
            option = mainMenu();
        }

        //the user chose option 0, so exit the program
        System.out.println("Exiting...bye");
        System.exit(0);
    }


    //------------------------------------------------------------------------------------------
    //  Option 1 - Add Posts - the user is asked if it is a message or a photo post
    //             and the required details are then gathered before adding the specific object
    //------------------------------------------------------------------------------------------
    private void addPost(){

        boolean isAdded = false;

        int option = ScannerInput.readNextInt("""
                    ---------------------------
                    |   1) Add a Message Post |
                    |   2) Add a Photo Post   | 
                    |   3) Add an Event Post  |
                    ---------------------------
                    ==>> """);

        switch (option) {
            case 1 -> {
                String authorName = ScannerInput.readNextLine("Enter the Author Name:  ");
                String message = ScannerInput.readNextLine("Enter the Message:  ");
                isAdded = newsFeed.addPost(new MessagePost(authorName, message));
            }
            case 2 -> {
                String authorName = ScannerInput.readNextLine("Enter the Author Name:  ");
                String caption = ScannerInput.readNextLine("Enter the Caption:  ");
                String filename = ScannerInput.readNextLine("Enter the Filename:  ");
                isAdded = newsFeed.addPost(new PhotoPost(authorName, caption, filename));
            }
            case 3 -> {
                String authorName = ScannerInput.readNextLine("Enter the Author Name:  ");
                String eventName = ScannerInput.readNextLine("Enter the Event Name:  ");
                double eventCost = ScannerInput.readNextDouble("Enter the Cost:  ");
                isAdded = newsFeed.addPost(new EventPost(authorName, eventName, eventCost));
            }
            default -> System.out.println("Invalid option entered: " + option);
        }

        if (isAdded){
            System.out.println("Post Added Successfully");
        }
        else{
            System.out.println("No Post Added");
        }
    }


    //------------------------------------------------------------------------------------------
    //  Option 2 - Update Posts - if posts exist, the user is asked if it is a message or a photo post
    //             and the required details are then gathered before adding the specific object
    //------------------------------------------------------------------------------------------
    private void updatePost() {

        if (newsFeed.numberOfPosts() > 0) {
            boolean isUpdated = false;

            int option = ScannerInput.readNextInt("""
                    ---------------------------
                    |   1) Update a Message Post |
                    |   2) Update a Photo Post   |
                    |   3) Update an Event Post  |
                    ---------------------------
                    ==>> """);

            switch (option) {
                case 1 -> {
                    //ask the user to enter the index of the object to update, and assuming it's valid and is a MessagePost,
                    //gather the new data from the user and update the selected object.
                    showMessagePosts();
                    if (newsFeed.numberOfMessagePosts() > 0) {
                        int messageIndex = ScannerInput.readNextInt("Enter the index of the message to update ==> ");
                        if (newsFeed.isValidMessagePostIndex(messageIndex)) {
                            String author = ScannerInput.readNextLine("Enter the Author Name:  ");
                            String message = ScannerInput.readNextLine("Enter the Message:  ");
                            //pass the index of the product and the new product details to Store for updating and check for success.
                            isUpdated = newsFeed.updateMessagePost(messageIndex, author, message);
                        }
                    }
                }
                case 2 -> {
                    //ask the user to enter the index of the object to update, and assuming it's valid and is a PhotoPost,
                    //gather the new data from the user and update the selected object.
                    showPhotoPosts();
                    if (newsFeed.numberOfPhotoPosts() > 0) {
                        int photoIndex = ScannerInput.readNextInt("Enter the index of the photo post to update ==> ");
                        if (newsFeed.isValidPhotoPostIndex(photoIndex)) {
                            String author = ScannerInput.readNextLine("Enter the Author Name:  ");
                            String caption = ScannerInput.readNextLine("Enter the Caption:  ");
                            String filename = ScannerInput.readNextLine("Enter the Filename:  ");
                            isUpdated = newsFeed.updatePhotoPost(photoIndex, author, caption, filename);
                        }
                    }
                }
                case 3 -> {
                    //ask the user to enter the index of the object to update, and assuming it's valid and is a PhotoPost,
                    //gather the new data from the user and update the selected object.
                    showEventPosts();
                    if (newsFeed.numberOfEventPosts() > 0) {
                        int eventIndex = ScannerInput.readNextInt("Enter the index of the event post to update ==> ");
                        if (newsFeed.isValidEventPostIndex(eventIndex)) {
                            String author = ScannerInput.readNextLine("Enter the Author Name:  ");
                            String name = ScannerInput.readNextLine("Enter the Event name:  ");
                            double cost = ScannerInput.readNextDouble("Enter the Cost:  ");
                            isUpdated = newsFeed.updateEventPost(eventIndex, author, name, cost);
                        }
                    }
                }
                default -> System.out.println("Invalid option entered: " + option);
            }

            if (isUpdated) {
                System.out.println("Post Updated Successfully");
            } else {
                System.out.println("No Post Updated");
            }
        }
        else{
                System.out.println("No posts added yet");
            }
    }


    //------------------------------------------------------------------------------------------
    //  Option 3 - Delete Posts - if posts exist, print all posts and ask the user to input the index
    //             of the post they wish to delete.
    //------------------------------------------------------------------------------------------
    private void deletePost(){
        showPosts();
        if (newsFeed.numberOfPosts() > 0){
            //only ask the user to choose the message post to delete if posts exist
            int indexToDelete = ScannerInput.readNextInt("Enter the index of the post to delete ==> ");
            //pass the index of the message post to NewsFeed for deleting and check for success.
            Post postToDelete = newsFeed.deletePost(indexToDelete);
            if (postToDelete != null){
                System.out.println("Delete Successful! Deleted post: " + postToDelete.display());
            }
            else{
                System.out.println("Delete NOT Successful");
            }
        }
    }

    //---------------------------------------------------------------------
    //  Option 4 - List Posts
    //---------------------------------------------------------------------

    //The user is asked if they want to view all posts, or just the messages or photos ones.
    private void viewPosts() {
        if (newsFeed.numberOfPosts() > 0) {
            int option = ScannerInput.readNextInt("""
                    ---------------------------
                    |   1) View ALL Posts     |
                    |   2) View Message Posts |
                    |   3) View Photo Posts   |
                    |   4) View Event Posts   |
                    ---------------------------
                    ==>>  """);

            switch (option) {
                case 1 -> showPosts();
                case 2 -> showMessagePosts();
                case 3 -> showPhotoPosts();
                case 4 -> showEventPosts();
                default -> System.out.println("Invalid option entered: " + option);
            }
        }
        else{
            System.out.println("Option Invalid - No posts stored");
        }
    }

    //print all the posts in newsfeed i.e. array list.
    private void showPosts(){
        System.out.println("List of All Posts are:");
        System.out.println(newsFeed.show());
    }

    //print the message posts in newsfeed i.e. array list.
    private void showMessagePosts(){
        System.out.println("List of Message Posts are:");
        System.out.println(newsFeed.showMessagePosts());
    }

    //print the photo posts in newsfeed i.e. array list.
    private void showPhotoPosts(){
        System.out.println("List of Photo Posts are:");
        System.out.println(newsFeed.showPhotoPosts());
    }
    private void showEventPosts(){
        System.out.println("List of events are:");
        System.out.println(newsFeed.showEventPosts());
    }
    //---------------------------------------------------------------------
    //  Options 5 and 6 - Save and Load Posts
    //---------------------------------------------------------------------

    //save all the posts in the newsFeed to a file on the hard disk
    private void savePosts() {
        try {
            newsFeed.save();
        } catch (Exception e) {
            System.err.println("Error writing to file: " + e);
        }
    }

    //load all the posts into the newsFeed from a file on the hard disk
    private void loadPosts() {
        try {
            newsFeed.load();
        } catch (Exception e) {
            System.err.println("Error reading from file: " + e);
        }
    }

}
