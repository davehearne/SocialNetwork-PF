package models;

import utils.Utilities;

public class MessagePost extends LikedPost{

    private String message = "";

    public MessagePost(String author, String message) {
        super(author);
        this.message = Utilities.truncateString(message, 40);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        if (Utilities.validateStringLength(message, 40)) {
            this.message = message;
        }
    }

    public String display() {
        String str = super.display();

        if (!message.isEmpty()){
            str += "\t" + message + "\n";
        }
        return str;
    }
}
