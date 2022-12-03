package controllers;

import models.EventPost;
import models.MessagePost;
import models.PhotoPost;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class NewsFeedTest {
    private MessagePost messagePostBelow, messagePostExact, messagePostAbove, messagePostZero;
    private EventPost eventPostBelow, eventPostExact, eventPostAbove, eventPostZero;
    private PhotoPost photoPostBelow, photoPostExact, photoPostAbove, photoPostZero;
    private NewsFeed newsFeed = new NewsFeed();
    private NewsFeed emptyNewsFeed = new NewsFeed();

    @BeforeEach
    void setUp() {
        //author 9 chars, message 39 chars
        messagePostBelow = new MessagePost("Mairead M", "Programming fundamentals assignment due");
        //author 10 chars, message 40 chars
        messagePostExact = new MessagePost("SiobhanDro", "Objects and Classes Lecture starting now");
        //author 11 chars, message 41 chars
        messagePostAbove = new MessagePost("SiobhanRoch", "Computing and Maths Centre open from 9am.");
        //author 0 chars, message 0 chars
        messagePostZero = new MessagePost("", "");

        //Event - author (max 10), eventname (max 35) event cost (0 - 99999)
        //author 9 chars, event 34 chars
        eventPostBelow = new EventPost("Mairead M", "Programming Hackathon : Room IT101", -1);
        //author 10 chars, event 35 chars
        eventPostExact = new EventPost("SiobhanDro", "Programming Fundamentals Quiz Night", 99999);
        //author 11 chars, event 36 chars
        eventPostAbove = new EventPost("SiobhanRoch", "Programming Fundamentals Study Group", 100000);
        //author 0 chars, event 0 chars, cost 0
        eventPostZero = new EventPost("", "", 0);

        //Photo - author (max 10), caption (max 100) filename (40)
        //author 9 chars, caption 99 chars, filename 39
        photoPostBelow = new PhotoPost("Mairead M",
                "Programming Hackathon : Room IT101 : group photo with all participants from BSc (Hons) Applied Yr 1",
                "Hackathon IT101-BSc (Hons) Applied Yr 1");
        //author 10 chars, caption 100 chars, filename 40
        photoPostExact = new PhotoPost("SiobhanDro",
                "Programming Fundamentals Quiz Night 2021- Podium photo of the winning team BSc (Hons) Applied Year 1",
                "Prog Fund Quiz 2021-Applied Winning Team");
        //author 11 chars, caption 101 chars, filename 41
        photoPostAbove = new PhotoPost("SiobhanRoch",
                "Programming Fundamentals Study Group - multiple groups hard at work on the morning of day 1, Sep 2021",
                "Programming Fundamentals Study Group 2021");
        //author 0 chars, caption 0 chars, filename 0 chars
        photoPostZero = new PhotoPost("", "", "");

        //9 - add all objects above except messagePostAbove, photoPostBelow, eventPostExact
        newsFeed.addPost(messagePostBelow);
        newsFeed.addPost(messagePostExact);
        newsFeed.addPost(eventPostBelow);
        newsFeed.addPost(photoPostExact);
        newsFeed.addPost(messagePostZero);
        newsFeed.addPost(photoPostAbove);
        newsFeed.addPost(eventPostAbove);
        newsFeed.addPost(eventPostZero);
        newsFeed.addPost(photoPostZero);
    }

    @AfterEach
    void tearDown(){
        messagePostBelow = messagePostExact = messagePostAbove = messagePostZero = null;
        eventPostBelow = eventPostExact = eventPostAbove = eventPostZero = null;
        photoPostBelow = photoPostExact = photoPostAbove = photoPostZero = null;
        newsFeed = emptyNewsFeed = null;
    }
}
