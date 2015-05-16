package model;

import android.text.format.Time;

/**
 * Created by Sarra on 02/04/2015.
 */
public class Program {
    private String name;
    private String details ;
    private int imgURL ;
    private int videoURL;
    private Boolean interesting ;
    private Time time;
    private int programID ;

    public String getDetails() {
        return details;
    }

    public Program(String name, String details, int imgURL, int videoURL, Boolean interesting, Theme theme, TimeSlot timeSlot, Time time,
                   int programID) {
        this.name = name;
        this.details = details;
        this.imgURL = imgURL;
        this.videoURL = videoURL;
        this.interesting = interesting;
        this.theme = theme;
        this.timeSlot = timeSlot;
        this.time = time ;
        this.programID = programID ;
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

    public int getImgURL() {
        return imgURL;
    }

    public void setImgURL(int imgURL) {
        this.imgURL = imgURL;
    }

    public int getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(int videoURL) {
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

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(TimeSlot timeSlot) {
        this.timeSlot = timeSlot;
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
    private TimeSlot timeSlot ;
    private Notification notification;



}
