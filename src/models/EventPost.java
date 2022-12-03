package models;

import utils.Utilities;

public class EventPost extends Post {

    private String eventName = "";
    private double eventCost = 0;

    public EventPost (String author, String eventName, double eventCost){
        super(author);
        this.eventName = Utilities.truncateString(eventName, 35);
        setEventCost(eventCost);
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        if (Utilities.validateStringLength(eventName, 35)) {
            this.eventName = eventName;
        }
    }

    public double getEventCost() {
        return eventCost;
    }

    public void setEventCost(double eventCost) {
        if (Utilities.validateCostRange(eventCost, 0, 99999)){
            this.eventCost = eventCost;
        }
    }

    public String display() {
        String str = super.display();

        if (!eventName.isEmpty()){
            str += "\tEvent name: " + eventName + "\n";
            str += "\tEvent cost: " + eventCost + "\n";
        }

        return str;
    }
    @Override
    public String displayCondensed() {
        return super.getAuthor() + ": Event(" + eventName + ", €" + eventCost + ")";
    }

}
