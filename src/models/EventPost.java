package models;

import utils.Utilities;

public class EventPost extends Post{

    private String eventName = "";
    private double eventCost = 0;

    public EventPost(String author, String eventName, double eventCost) {
        super(author);
        this.eventName = Utilities.truncateString(eventName, 35);
        setEventCost(eventCost);
    }
    /**
     *  return the event name
     * @return String = event name
     */
    public String getEventName(){
        return this.eventName;
    }
    /**
     * set the event name and validate the length
     * @param eventName
     */
    public void setEventName( String eventName){
        if(Utilities.validateStringLength(eventName, 35)){
            this.eventName = eventName;
        }
    }

    /**
     * This returns the event cost
     * @return eventCost
     */
    public double getEventCost(){
        return eventCost;
    }

    public void setEventCost(double eventCost){
            if(Utilities.validateCostRange(eventCost, 0, 99999)){
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

}
