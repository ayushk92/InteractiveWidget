package com.example.mynanodegreeapps.healthifymeapp.provider;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.DefaultDatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import com.example.mynanodegreeapps.healthifymeapp.BuildConfig;
import com.example.mynanodegreeapps.healthifymeapp.provider.event.EventColumns;

public class EventsSQLiteOpenHelper extends SQLiteOpenHelper {
    private static final String TAG = EventsSQLiteOpenHelper.class.getSimpleName();

    public static final String DATABASE_FILE_NAME = "Events.db";
    private static final int DATABASE_VERSION = 1;
    private static EventsSQLiteOpenHelper sInstance;
    private final Context mContext;
    private final EventsSQLiteOpenHelperCallbacks mOpenHelperCallbacks;

    // @formatter:off
    public static final String SQL_CREATE_TABLE_EVENT = "CREATE TABLE IF NOT EXISTS "
            + EventColumns.TABLE_NAME + " ( "
            + EventColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + EventColumns.STATUS + " TEXT NOT NULL, "
            + EventColumns.COLLEGE + " INTEGER NOT NULL, "
            + EventColumns.TITLE + " TEXT NOT NULL, "
            + EventColumns.THUMBNAIL + " TEXT, "
            + EventColumns.CHALLENGE_TYPE + " TEXT NOT NULL, "
            + EventColumns.DESCRIPTION + " TEXT NOT NULL, "
            + EventColumns.START_TIMESTAMP + " INTEGER NOT NULL, "
            + EventColumns.END_TIMESTAMP + " INTEGER NOT NULL, "
            + EventColumns.URL + " TEXT NOT NULL "
            + ", CONSTRAINT unique_title UNIQUE(title) ON CONFLICT REPLACE"
            + " );";

    // @formatter:on

    public static EventsSQLiteOpenHelper getInstance(Context context) {
        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = newInstance(context.getApplicationContext());
        }
        return sInstance;
    }

    private static EventsSQLiteOpenHelper newInstance(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            return newInstancePreHoneycomb(context);
        }
        return newInstancePostHoneycomb(context);
    }


    /*
     * Pre Honeycomb.
     */
    private static EventsSQLiteOpenHelper newInstancePreHoneycomb(Context context) {
        return new EventsSQLiteOpenHelper(context);
    }

    private EventsSQLiteOpenHelper(Context context) {
        super(context, DATABASE_FILE_NAME, null, DATABASE_VERSION);
        mContext = context;
        mOpenHelperCallbacks = new EventsSQLiteOpenHelperCallbacks();
    }


    /*
     * Post Honeycomb.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private static EventsSQLiteOpenHelper newInstancePostHoneycomb(Context context) {
        return new EventsSQLiteOpenHelper(context, new DefaultDatabaseErrorHandler());
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private EventsSQLiteOpenHelper(Context context, DatabaseErrorHandler errorHandler) {
        super(context, DATABASE_FILE_NAME, null, DATABASE_VERSION, errorHandler);
        mContext = context;
        mOpenHelperCallbacks = new EventsSQLiteOpenHelperCallbacks();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        if (BuildConfig.DEBUG) Log.d(TAG, "onCreate");
        mOpenHelperCallbacks.onPreCreate(mContext, db);
        db.execSQL(SQL_CREATE_TABLE_EVENT);
        mOpenHelperCallbacks.onPostCreate(mContext, db);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            setForeignKeyConstraintsEnabled(db);
        }
        mOpenHelperCallbacks.onOpen(mContext, db);
    }

    private void setForeignKeyConstraintsEnabled(SQLiteDatabase db) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            setForeignKeyConstraintsEnabledPreJellyBean(db);
        } else {
            setForeignKeyConstraintsEnabledPostJellyBean(db);
        }
    }

    private void setForeignKeyConstraintsEnabledPreJellyBean(SQLiteDatabase db) {
        db.execSQL("PRAGMA foreign_keys=ON;");
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void setForeignKeyConstraintsEnabledPostJellyBean(SQLiteDatabase db) {
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        mOpenHelperCallbacks.onUpgrade(mContext, db, oldVersion, newVersion);
    }
}
