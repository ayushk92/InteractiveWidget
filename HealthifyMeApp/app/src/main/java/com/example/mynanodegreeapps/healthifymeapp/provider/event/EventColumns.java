package com.example.mynanodegreeapps.healthifymeapp.provider.event;

import android.net.Uri;
import android.provider.BaseColumns;

import com.example.mynanodegreeapps.healthifymeapp.provider.EventsProvider;
import com.example.mynanodegreeapps.healthifymeapp.provider.event.EventColumns;

/**
 * Event details
 */
public class EventColumns implements BaseColumns {
    public static final String TABLE_NAME = "event";
    public static final Uri CONTENT_URI = Uri.parse(EventsProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    /**
     * event status
     */
    public static final String STATUS = "status";

    /**
     * college event
     */
    public static final String COLLEGE = "college";

    /**
     * event title
     */
    public static final String TITLE = "title";

    /**
     * event thumbnail url
     */
    public static final String THUMBNAIL = "thumbnail";

    /**
     * challenge type
     */
    public static final String CHALLENGE_TYPE = "challenge_type";

    /**
     * event description
     */
    public static final String DESCRIPTION = "description";

    /**
     * event start date
     */
    public static final String START_TIMESTAMP = "start_timestamp";

    /**
     * event end date
     */
    public static final String END_TIMESTAMP = "end_timestamp";

    /**
     * event url
     */
    public static final String URL = "url";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            STATUS,
            COLLEGE,
            TITLE,
            THUMBNAIL,
            CHALLENGE_TYPE,
            DESCRIPTION,
            START_TIMESTAMP,
            END_TIMESTAMP,
            URL
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(STATUS) || c.contains("." + STATUS)) return true;
            if (c.equals(COLLEGE) || c.contains("." + COLLEGE)) return true;
            if (c.equals(TITLE) || c.contains("." + TITLE)) return true;
            if (c.equals(THUMBNAIL) || c.contains("." + THUMBNAIL)) return true;
            if (c.equals(CHALLENGE_TYPE) || c.contains("." + CHALLENGE_TYPE)) return true;
            if (c.equals(DESCRIPTION) || c.contains("." + DESCRIPTION)) return true;
            if (c.equals(START_TIMESTAMP) || c.contains("." + START_TIMESTAMP)) return true;
            if (c.equals(END_TIMESTAMP) || c.contains("." + END_TIMESTAMP)) return true;
            if (c.equals(URL) || c.contains("." + URL)) return true;
        }
        return false;
    }

}
