package model;

import java.util.ArrayList;

/**
 * Created by XoX on 31/03/2015.
 */
public class Channel {
    private String name ;
    private int imgURL ;
    private ArrayList<Program> listPrograms ;

    public Channel(String name, int imgURL) {
        this.name = name;
        this.imgURL = imgURL;
    }

    public int getImgURL() {
        return imgURL;
    }

    public void setImgURL(int imgURL) {
        this.imgURL = imgURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
