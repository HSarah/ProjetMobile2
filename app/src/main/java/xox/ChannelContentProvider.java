package xox;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.format.Time;

import xox.BDD.BDDOpenHelper;

/**
 * Created by XoX on 08/05/2015.
 */
public class ChannelContentProvider extends ContentProvider {

    /* Static and Final declarations : */
    private static final int CHANNELS = 10 ;
    private static final int CHANNEL_ID = 20 ;
    private static final int CATEGORY_ID = 30 ;
    private static final int PERIOD_ID = 40 ;
    private static final int TIME_ID = 50 ;



    private static final UriMatcher URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH) ;

    static {
        URI_MATCHER.addURI(ChannelContract.AUTHORITY, ChannelContract.CHANNELS_PATH, CHANNELS);
        URI_MATCHER.addURI(ChannelContract.AUTHORITY, ChannelContract.CHANNELS_PATH+"/#", CHANNEL_ID);
        URI_MATCHER.addURI(ChannelContract.AUTHORITY, ChannelContract.CATEGORY_PATH+"/*", CATEGORY_ID);
        URI_MATCHER.addURI(ChannelContract.AUTHORITY, ChannelContract.PERIOD_PATH+"/#", PERIOD_ID);
        URI_MATCHER.addURI(ChannelContract.AUTHORITY, ChannelContract.TIME_PATH+"/#", TIME_ID);

    }
    /*******************************************************/

    private BDDOpenHelper helper ;
    private SQLiteDatabase database  ;

    @Override
    public boolean onCreate() {
        helper = new BDDOpenHelper(getContext()) ;
        database = null ;
        return true;
    }

   /* private void openDataBase() {
        if(database==null || !database.isOpen()) {
            database = helper.getWritableDatabase() ;
        }
    }

    private void closeDataBase() {
        if (database!=null && database.isOpen()) {
            database.close();
            database = null ;
        }
    }*/

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        int uriType = URI_MATCHER.match(uri) ;
        Cursor cursor = null ;
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder() ;



        switch (uriType) {
            case CHANNELS :
                queryBuilder.setTables(BDDOpenHelper.TABLE_CHANNELS);
                break;
            case CHANNEL_ID :
                queryBuilder.setTables(BDDOpenHelper.TABLE_PROGRAMS);
                queryBuilder.appendWhere(BDDOpenHelper.COLUMN_ID_CHANNEL + "=" + uri.getLastPathSegment());
                break;
            case CATEGORY_ID :
                queryBuilder.setTables(BDDOpenHelper.TABLE_PROGRAMS);
                queryBuilder.appendWhere(BDDOpenHelper.COLUMN_CATEGORIE_PROGRAM + "=" +uri.getLastPathSegment());
                break;
            case TIME_ID :
                queryBuilder.setTables(BDDOpenHelper.TABLE_PROGRAMS);
                Time t1 = new Time() ;
                Time t2 = new Time() ;

                t1.set(0, 0, Integer.parseInt(uri.getLastPathSegment()), 12, 06, 2015);
                t2.set(0, 59, Integer.parseInt(uri.getLastPathSegment()), 12, 06, 2015) ;

                long inf = t1.toMillis(true) ;
                long sup = t2.toMillis(true) ;

                queryBuilder.appendWhere(BDDOpenHelper.COLUMN_TIME_PROGRAM + ">=" + inf
                        + " AND " + BDDOpenHelper.COLUMN_TIME_PROGRAM  + "<=" + sup );
                break;
            case PERIOD_ID :
                queryBuilder.setTables(BDDOpenHelper.TABLE_PROGRAMS);
                long vinf = 0 ;
                long vsup = 0 ;
                Time t = new Time() ;
                switch (Integer.parseInt(uri.getLastPathSegment())) {
                    case 1:
                        t.set(0, 0, 4, 12, 06, 2015);
                        vinf = t.toMillis(true) ;
                        t.set(0, 0, 12, 12, 06, 2015);
                        vsup = t.toMillis(true) ;
                        break;
                    case 2:
                        t.set(0, 0, 12, 12, 06, 2015);
                        vinf = t.toMillis(true) ;
                        t.set(0, 0, 16, 12, 06, 2015);
                        vsup = t.toMillis(true) ;
                        break;
                    case 3:
                        t.set(0, 0, 16, 12, 06, 2015);
                        vinf = t.toMillis(true) ;
                        t.set(0, 0, 19, 12, 06, 2015);
                        vsup = t.toMillis(true) ;
                        break;
                    case 4:
                        t.set(0, 0, 19, 12, 06, 2015);
                        vinf = t.toMillis(true) ;
                        t.set(0, 0, 4, 13, 06, 2015);
                        vsup = t.toMillis(true) ;
                        break;
                    default: break;
                }

                queryBuilder.appendWhere(BDDOpenHelper.COLUMN_TIME_PROGRAM + ">=" + vinf
                        + " AND " + BDDOpenHelper.COLUMN_TIME_PROGRAM  + "<=" + vsup );
                break;
            default: return null ;
        }
        /******************************************************/

        database = helper.getWritableDatabase() ;
        cursor = queryBuilder.query(database, projection, selection, selectionArgs, null, null, sortOrder) ;

        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
