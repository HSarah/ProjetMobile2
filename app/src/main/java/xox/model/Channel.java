package xox.model;

import java.util.ArrayList;

/**
 * Created by XoX on 31/03/2015.
 */
public class Channel {

    private long id ;
    private String name ;
    private int imgURL ;
    private ArrayList<Program> listPrograms ;

    public Channel(long id, String name, int imgURL) {
        this.id = id ;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
