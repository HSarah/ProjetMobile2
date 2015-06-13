package xox.model;

import java.util.ArrayList;

/**
 * Created by XoX on 31/03/2015.
 */
public class Channel {

    private long id ;
    private String name ;
    private String imgURL ;
    private ArrayList<Program> listPrograms ;

    public Channel(long id, String name, String imgURL) {
        this.id = id ;
        this.name = name;
        this.imgURL = imgURL;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
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
