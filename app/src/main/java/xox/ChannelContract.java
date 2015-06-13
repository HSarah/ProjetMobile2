package xox;

import android.net.Uri;

/**
 * Created by XoX on 08/05/2015.
 */
public final class ChannelContract {
    public static final String AUTHORITY = "ChannelContentProvider";
    public  static final String CHANNELS_PATH = "channels" ;
    public  static final String CATEGORY_PATH = "category" ;
    public  static final String PERIOD_PATH = "period" ;
    public  static final String TIME_PATH = "time" ;


    public static final String TABLE_CHANNELS = "channelstable" ;
    public static final String COLUMN_ID = "_ID" ;
    public static final String COLUMN_NOM = "nom" ;
    public static final String COLUMN_IMAGE = "imageURL" ;


    /** La table programme **/
    public static final String TABLE_PROGRAMS = "programstable" ;
    public static final String COLUMN_ID_PROGRAM = "_ID" ;
    public static final String COLUMN_NOM_PROGRAM = "nomProgram" ;
    public static final String COLUMN_DESC_PROGRAM= "descProgram" ;
    public static final String COLUMN_IMAGE_PROGRAM = "imageURLProgram" ;
    public static final String COLUMN_TIME_PROGRAM = "timeProgram" ;
    public static final String COLUMN_VIDEO_PROGRAM = "videoURLProgram" ;
    public static final String COLUMN_CATEGORIE_PROGRAM = "categorieProgram" ;
    public static final String COLUMN_ID_CHANNEL = "_IDChannel" ;


    public static final Uri CONTENT_URI = Uri.parse("content://"+ AUTHORITY + "/" + CHANNELS_PATH) ;


}
