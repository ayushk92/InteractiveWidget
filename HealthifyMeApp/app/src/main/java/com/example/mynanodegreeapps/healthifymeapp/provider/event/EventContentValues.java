package com.example.mynanodegreeapps.healthifymeapp.provider.event;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.mynanodegreeapps.healthifymeapp.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code event} table.
 */
public class EventContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return EventColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, @Nullable EventSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(Context context, @Nullable EventSelection where) {
        return context.getContentResolver().update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * event status
     */
    public EventContentValues putStatus(@NonNull String value) {
        if (value == null) throw new IllegalArgumentException("status must not be null");
        mContentValues.put(EventColumns.STATUS, value);
        return this;
    }


    /**
     * college event
     */
    public EventContentValues putCollege(boolean value) {
        mContentValues.put(EventColumns.COLLEGE, value);
        return this;
    }


    /**
     * event title
     */
    public EventContentValues putTitle(@NonNull String value) {
        if (value == null) throw new IllegalArgumentException("title must not be null");
        mContentValues.put(EventColumns.TITLE, value);
        return this;
    }


    /**
     * event thumbnail url
     */
    public EventContentValues putThumbnail(@Nullable String value) {
        mContentValues.put(EventColumns.THUMBNAIL, value);
        return this;
    }

    public EventContentValues putThumbnailNull() {
        mContentValues.putNull(EventColumns.THUMBNAIL);
        return this;
    }

    /**
     * challenge type
     */
    public EventContentValues putChallengeType(@NonNull String value) {
        if (value == null) throw new IllegalArgumentException("challengeType must not be null");
        mContentValues.put(EventColumns.CHALLENGE_TYPE, value);
        return this;
    }


    /**
     * event description
     */
    public EventContentValues putDescription(@NonNull String value) {
        if (value == null) throw new IllegalArgumentException("description must not be null");
        mContentValues.put(EventColumns.DESCRIPTION, value);
        return this;
    }


    /**
     * event start date
     */
    public EventContentValues putStartTimestamp(@NonNull Date value) {
        if (value == null) throw new IllegalArgumentException("startTimestamp must not be null");
        mContentValues.put(EventColumns.START_TIMESTAMP, value.getTime());
        return this;
    }


    public EventContentValues putStartTimestamp(long value) {
        mContentValues.put(EventColumns.START_TIMESTAMP, value);
        return this;
    }

    /**
     * event end date
     */
    public EventContentValues putEndTimestamp(@NonNull Date value) {
        if (value == null) throw new IllegalArgumentException("endTimestamp must not be null");
        mContentValues.put(EventColumns.END_TIMESTAMP, value.getTime());
        return this;
    }


    public EventContentValues putEndTimestamp(long value) {
        mContentValues.put(EventColumns.END_TIMESTAMP, value);
        return this;
    }

    /**
     * event url
     */
    public EventContentValues putUrl(@NonNull String value) {
        if (value == null) throw new IllegalArgumentException("url must not be null");
        mContentValues.put(EventColumns.URL, value);
        return this;
    }

}
