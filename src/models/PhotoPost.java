package models;

import utils.Utilities;

public class PhotoPost extends Post {

    private String caption = "";
    private String filename = "";

    public PhotoPost(String author, String caption, String filename) {
        super(author);
        this.caption = Utilities.truncateString(caption, 100);
        this.filename = Utilities.truncateString(filename, 40);
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        if (Utilities.validateStringLength(caption, 100)) {
            this.caption = caption;
        }
    }

    public void setFilename(String filename) {
        if (Utilities.validateStringLength(filename, 40)) {
            this.filename = filename;
        }
    }

    public String getFilename() {
        return filename;
    }

    public String display() {
        String str = super.display();

        if (!caption.isEmpty()){
            str += "\t" + caption + "\n";
        }

        if (!filename.isEmpty()){
            str += "\t" + filename + "\n";
        }

        return str;
    }
}
