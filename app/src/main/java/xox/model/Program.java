package xox.model;

import android.text.format.Time;

/**
 * Created by Sarra on 02/04/2015.
 */
public class Program {
    private String name;
    private String details ;
    private String imgURL ;
    private String videoURL;
    private Boolean interesting ;
    private Time time;
    private int programID ;
    private int channelID ;

    public String getDetails() {
        return details;
    }

    public Program(String name, String details, String imgURL, String videoURL, Boolean interesting, Theme theme, Time time,
                   int programID, int channelID) {
        this.name = name;
        this.details = details;
        this.imgURL = imgURL;
        this.videoURL = videoURL;
        this.interesting = interesting;
        this.theme = theme;
        this.time = time ;
        this.programID = programID ;
        this.channelID = channelID ;
    }

    public int getProgramID() {
        return programID;
    }

    public void setProgramID(int programID) {
        this.programID = programID;
    }

    public Time getTime() {
        return time;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    public Boolean getInteresting() {
        return interesting;
    }

    public void setInteresting(Boolean interesting) {
        this.interesting = interesting;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }


    public Notification getNotification() {
        return notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private Theme theme ;
    private Notification notification;

    public int getChannelID() {
        return channelID;
    }

    public void setChannelID(int channelID) {
        this.channelID = channelID;
    }

    public void setTime(Time time) {
        this.time = time;
    }


}
