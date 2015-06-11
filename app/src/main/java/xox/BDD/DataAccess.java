package xox.BDD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import android.text.format.Time;
import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;

import xox.model.Channel;
import xox.model.Program;
import xox.model.Theme;

/**
 * Created by XoX on 11/06/2015.
 */
public class DataAccess  {
    BDDOpenHelper helper ;
    SQLiteDatabase database ;


    public DataAccess(Context context) {
        helper = new BDDOpenHelper(context) ;
}

    public void open() throws SQLiteException {
        database = helper.getWritableDatabase() ;
    }

    public void close() {
        helper.close();
    }

    public Channel addChannel(String name, int imgURL) {
        ContentValues values = new ContentValues() ;
        
        
        values.put(BDDOpenHelper.COLUMN_NOM, name);
        values.put(BDDOpenHelper.COLUMN_IMAGE, imgURL);

        long id = database.insert(BDDOpenHelper.TABLE_CHANNELS, null, values) ;
        Log.d("Id : ", id + "") ;
        Channel channel = new Channel(id, name, imgURL) ;
        return  channel ;
    }

    public void supprimerChannel(Channel channel) {
        supprimerChannel(channel.getId());
    }

    public void supprimerChannel(long id) {
        database.delete(BDDOpenHelper.TABLE_CHANNELS, BDDOpenHelper.COLUMN_ID + "=" + id, null) ;
    }

    public void modifierChannel(Channel channel) {
        ContentValues values = new ContentValues() ;

        values.put(BDDOpenHelper.COLUMN_NOM, channel.getName());
        values.put(BDDOpenHelper.COLUMN_IMAGE, channel.getName());
        database.update(helper.TABLE_CHANNELS, values, BDDOpenHelper.COLUMN_ID + "=" + channel.getId(), null) ;
    }

    public ArrayList<Channel> getAllChannels() {
        Cursor cursor = database.query(helper.TABLE_CHANNELS, null, null, null, null, null, null);
        ArrayList<Channel> channels = new ArrayList<Channel>() ;

        cursor.moveToFirst() ;
        while (!cursor.isAfterLast()) {
            channels.add(cursorToChannel(cursor)) ;
            cursor.moveToNext() ;
        }

        cursor.close();
        return channels ;
    }

    public Channel cursorToChannel(Cursor cursor) {
        Channel channel = new Channel(cursor.getLong(0), cursor.getString(1),
                cursor.getInt(2)) ;
        return channel ;
    }

    public Program addProgram(String name, String details, int imgURL, int videoURL, Time time, String category , long idChannel) {
        ContentValues values = new ContentValues() ;

        values.put(BDDOpenHelper.COLUMN_NOM_PROGRAM, name);
        values.put(BDDOpenHelper.COLUMN_DESC_PROGRAM, details);
        values.put(BDDOpenHelper.COLUMN_TIME_PROGRAM, time.toMillis(true));
        values.put(BDDOpenHelper.COLUMN_VIDEO_PROGRAM, videoURL);
        values.put(BDDOpenHelper.COLUMN_CATEGORIE_PROGRAM, category);
        values.put(BDDOpenHelper.COLUMN_ID_CHANNEL, idChannel);
        values.put(BDDOpenHelper.COLUMN_IMAGE_PROGRAM, imgURL);


        long id = database.insert(BDDOpenHelper.TABLE_PROGRAMS, null, values) ;
        Log.d("Id : ", id + "") ;
        Program program = new Program(name, details,imgURL, videoURL, false, new Theme(category), time, (int)id, (int)idChannel) ;
        return  program ;
    }

    public void supprimerProgram(Program program) {
        supprimerProgram(program.getProgramID());
    }

    public void supprimerProgram(long id) {
        database.delete(BDDOpenHelper.TABLE_PROGRAMS, BDDOpenHelper.COLUMN_ID_PROGRAM + "=" + id, null) ;
    }

    public void modifierProgram(Program program) {
        ContentValues values = new ContentValues() ;

        values.put(BDDOpenHelper.COLUMN_NOM_PROGRAM, program.getName());
        values.put(BDDOpenHelper.COLUMN_DESC_PROGRAM, program.getDetails());
        values.put(BDDOpenHelper.COLUMN_TIME_PROGRAM, program.getTime().toMillis(true));
        values.put(BDDOpenHelper.COLUMN_VIDEO_PROGRAM, program.getVideoURL());
        values.put(BDDOpenHelper.COLUMN_CATEGORIE_PROGRAM, program.getTheme().getDescription());
        values.put(BDDOpenHelper.COLUMN_ID_CHANNEL, program.getChannelID());
        values.put(BDDOpenHelper.COLUMN_IMAGE_PROGRAM, program.getImgURL());

        database.update(helper.TABLE_PROGRAMS, values, BDDOpenHelper.COLUMN_ID_PROGRAM + "=" + program.getProgramID(), null) ;
    }

    public ArrayList<Program> getAllPrograms(long id) {
        Cursor cursor = database.query(helper.TABLE_PROGRAMS, null, BDDOpenHelper.COLUMN_ID_CHANNEL + "=" + id,
                null, null, null, null);
        ArrayList<Program> programs = new ArrayList<Program>() ;

        cursor.moveToFirst() ;
        while (!cursor.isAfterLast()) {
            programs.add(cursorToProgram(cursor)) ;
            cursor.moveToNext() ;
        }

        cursor.close();
        return programs ;
    }

    public Program cursorToProgram(Cursor cursor) {

        Time time = new Time() ;
        time.set(cursor.getLong(3));

        Program program = new Program(cursor.getString(1), cursor.getString(2), cursor.getInt(7),
                 cursor.getInt(4), false, new Theme(cursor.getString(5)), time, cursor.getInt(0), cursor.getInt(6)) ;


        return program ;
    }

}
