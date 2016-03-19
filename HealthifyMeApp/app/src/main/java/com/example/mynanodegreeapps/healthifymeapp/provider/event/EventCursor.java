package com.example.mynanodegreeapps.healthifymeapp.provider.event;

import java.util.Date;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.mynanodegreeapps.healthifymeapp.provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code event} table.
 */
public class EventCursor extends AbstractCursor implements EventModel {
    public EventCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(EventColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * event status
     * Cannot be {@code null}.
     */
    @NonNull
    public String getStatus() {
        String res = getStringOrNull(EventColumns.STATUS);
        if (res == null)
            throw new NullPointerException("The value of 'status' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * college event
     */
    public boolean getCollege() {
        Boolean res = getBooleanOrNull(EventColumns.COLLEGE);
        if (res == null)
            throw new NullPointerException("The value of 'college' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * event title
     * Cannot be {@code null}.
     */
    @NonNull
    public String getTitle() {
        String res = getStringOrNull(EventColumns.TITLE);
        if (res == null)
            throw new NullPointerException("The value of 'title' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * event thumbnail url
     * Can be {@code null}.
     */
    @Nullable
    public String getThumbnail() {
        String res = getStringOrNull(EventColumns.THUMBNAIL);
        return res;
    }

    /**
     * challenge type
     * Cannot be {@code null}.
     */
    @NonNull
    public String getChallengeType() {
        String res = getStringOrNull(EventColumns.CHALLENGE_TYPE);
        if (res == null)
            throw new NullPointerException("The value of 'challenge_type' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * event description
     * Cannot be {@code null}.
     */
    @NonNull
    public String getDescription() {
        String res = getStringOrNull(EventColumns.DESCRIPTION);
        if (res == null)
            throw new NullPointerException("The value of 'description' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * event start date
     * Cannot be {@code null}.
     */
    @NonNull
    public Date getStartTimestamp() {
        Date res = getDateOrNull(EventColumns.START_TIMESTAMP);
        if (res == null)
            throw new NullPointerException("The value of 'start_timestamp' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * event end date
     * Cannot be {@code null}.
     */
    @NonNull
    public Date getEndTimestamp() {
        Date res = getDateOrNull(EventColumns.END_TIMESTAMP);
        if (res == null)
            throw new NullPointerException("The value of 'end_timestamp' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * event url
     * Cannot be {@code null}.
     */
    @NonNull
    public String getUrl() {
        String res = getStringOrNull(EventColumns.URL);
        if (res == null)
            throw new NullPointerException("The value of 'url' in the database was null, which is not allowed according to the model definition");
        return res;
    }
}
