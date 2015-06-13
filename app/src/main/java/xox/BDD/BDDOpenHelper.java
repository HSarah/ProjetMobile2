package xox.BDD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Sarra on 16/05/2015.
 */
public class BDDOpenHelper extends SQLiteOpenHelper {

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

    public static final String DATABASE_NAME = "chainestv.db" ;
    public static final int DATABASE_VERSION = 7 ;

    // DataBase Creation SQL Statement :
    private static final String CHANNELS_CREATE = "create table "
            + TABLE_CHANNELS + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_NOM + " TEXT not null, "
            + COLUMN_IMAGE + " TEXT not null);"  ;

    private static final String PROGRAMS_CREATE = "create table "
            + TABLE_PROGRAMS + "("
            + COLUMN_ID_PROGRAM + " integer primary key autoincrement, "
            + COLUMN_NOM_PROGRAM + " TEXT not null, "
            + COLUMN_DESC_PROGRAM + " TEXT not null, "
            + COLUMN_TIME_PROGRAM + " INTEGER not null, "
            + COLUMN_VIDEO_PROGRAM + " TEXT not null, "
            + COLUMN_CATEGORIE_PROGRAM + " TEXT not null, "
            + COLUMN_ID_CHANNEL + " integer,"
            + COLUMN_IMAGE_PROGRAM + " TEXT not null,"
            + " FOREIGN KEY (" + COLUMN_ID_CHANNEL + ") REFERENCES "+TABLE_CHANNELS+" ("+COLUMN_ID+"));"  ;

    public BDDOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CHANNELS_CREATE);
        db.execSQL(PROGRAMS_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(BDDOpenHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CHANNELS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROGRAMS);
        onCreate(db);
    }
}