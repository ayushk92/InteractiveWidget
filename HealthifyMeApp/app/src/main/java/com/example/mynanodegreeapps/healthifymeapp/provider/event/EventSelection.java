package com.example.mynanodegreeapps.healthifymeapp.provider.event;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import com.example.mynanodegreeapps.healthifymeapp.provider.base.AbstractSelection;

/**
 * Selection for the {@code event} table.
 */
public class EventSelection extends AbstractSelection<EventSelection> {
    @Override
    protected Uri baseUri() {
        return EventColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code EventCursor} object, which is positioned before the first entry, or null.
     */
    public EventCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new EventCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public EventCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code EventCursor} object, which is positioned before the first entry, or null.
     */
    public EventCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new EventCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public EventCursor query(Context context) {
        return query(context, null);
    }


    public EventSelection id(long... value) {
        addEquals("event." + EventColumns._ID, toObjectArray(value));
        return this;
    }

    public EventSelection idNot(long... value) {
        addNotEquals("event." + EventColumns._ID, toObjectArray(value));
        return this;
    }

    public EventSelection orderById(boolean desc) {
        orderBy("event." + EventColumns._ID, desc);
        return this;
    }

    public EventSelection orderById() {
        return orderById(false);
    }

    public EventSelection status(String... value) {
        addEquals(EventColumns.STATUS, value);
        return this;
    }

    public EventSelection statusNot(String... value) {
        addNotEquals(EventColumns.STATUS, value);
        return this;
    }

    public EventSelection statusLike(String... value) {
        addLike(EventColumns.STATUS, value);
        return this;
    }

    public EventSelection statusContains(String... value) {
        addContains(EventColumns.STATUS, value);
        return this;
    }

    public EventSelection statusStartsWith(String... value) {
        addStartsWith(EventColumns.STATUS, value);
        return this;
    }

    public EventSelection statusEndsWith(String... value) {
        addEndsWith(EventColumns.STATUS, value);
        return this;
    }

    public EventSelection orderByStatus(boolean desc) {
        orderBy(EventColumns.STATUS, desc);
        return this;
    }

    public EventSelection orderByStatus() {
        orderBy(EventColumns.STATUS, false);
        return this;
    }

    public EventSelection college(boolean value) {
        addEquals(EventColumns.COLLEGE, toObjectArray(value));
        return this;
    }

    public EventSelection orderByCollege(boolean desc) {
        orderBy(EventColumns.COLLEGE, desc);
        return this;
    }

    public EventSelection orderByCollege() {
        orderBy(EventColumns.COLLEGE, false);
        return this;
    }

    public EventSelection title(String... value) {
        addEquals(EventColumns.TITLE, value);
        return this;
    }

    public EventSelection titleNot(String... value) {
        addNotEquals(EventColumns.TITLE, value);
        return this;
    }

    public EventSelection titleLike(String... value) {
        addLike(EventColumns.TITLE, value);
        return this;
    }

    public EventSelection titleContains(String... value) {
        addContains(EventColumns.TITLE, value);
        return this;
    }

    public EventSelection titleStartsWith(String... value) {
        addStartsWith(EventColumns.TITLE, value);
        return this;
    }

    public EventSelection titleEndsWith(String... value) {
        addEndsWith(EventColumns.TITLE, value);
        return this;
    }

    public EventSelection orderByTitle(boolean desc) {
        orderBy(EventColumns.TITLE, desc);
        return this;
    }

    public EventSelection orderByTitle() {
        orderBy(EventColumns.TITLE, false);
        return this;
    }

    public EventSelection thumbnail(String... value) {
        addEquals(EventColumns.THUMBNAIL, value);
        return this;
    }

    public EventSelection thumbnailNot(String... value) {
        addNotEquals(EventColumns.THUMBNAIL, value);
        return this;
    }

    public EventSelection thumbnailLike(String... value) {
        addLike(EventColumns.THUMBNAIL, value);
        return this;
    }

    public EventSelection thumbnailContains(String... value) {
        addContains(EventColumns.THUMBNAIL, value);
        return this;
    }

    public EventSelection thumbnailStartsWith(String... value) {
        addStartsWith(EventColumns.THUMBNAIL, value);
        return this;
    }

    public EventSelection thumbnailEndsWith(String... value) {
        addEndsWith(EventColumns.THUMBNAIL, value);
        return this;
    }

    public EventSelection orderByThumbnail(boolean desc) {
        orderBy(EventColumns.THUMBNAIL, desc);
        return this;
    }

    public EventSelection orderByThumbnail() {
        orderBy(EventColumns.THUMBNAIL, false);
        return this;
    }

    public EventSelection challengeType(String... value) {
        addEquals(EventColumns.CHALLENGE_TYPE, value);
        return this;
    }

    public EventSelection challengeTypeNot(String... value) {
        addNotEquals(EventColumns.CHALLENGE_TYPE, value);
        return this;
    }

    public EventSelection challengeTypeLike(String... value) {
        addLike(EventColumns.CHALLENGE_TYPE, value);
        return this;
    }

    public EventSelection challengeTypeContains(String... value) {
        addContains(EventColumns.CHALLENGE_TYPE, value);
        return this;
    }

    public EventSelection challengeTypeStartsWith(String... value) {
        addStartsWith(EventColumns.CHALLENGE_TYPE, value);
        return this;
    }

    public EventSelection challengeTypeEndsWith(String... value) {
        addEndsWith(EventColumns.CHALLENGE_TYPE, value);
        return this;
    }

    public EventSelection orderByChallengeType(boolean desc) {
        orderBy(EventColumns.CHALLENGE_TYPE, desc);
        return this;
    }

    public EventSelection orderByChallengeType() {
        orderBy(EventColumns.CHALLENGE_TYPE, false);
        return this;
    }

    public EventSelection description(String... value) {
        addEquals(EventColumns.DESCRIPTION, value);
        return this;
    }

    public EventSelection descriptionNot(String... value) {
        addNotEquals(EventColumns.DESCRIPTION, value);
        return this;
    }

    public EventSelection descriptionLike(String... value) {
        addLike(EventColumns.DESCRIPTION, value);
        return this;
    }

    public EventSelection descriptionContains(String... value) {
        addContains(EventColumns.DESCRIPTION, value);
        return this;
    }

    public EventSelection descriptionStartsWith(String... value) {
        addStartsWith(EventColumns.DESCRIPTION, value);
        return this;
    }

    public EventSelection descriptionEndsWith(String... value) {
        addEndsWith(EventColumns.DESCRIPTION, value);
        return this;
    }

    public EventSelection orderByDescription(boolean desc) {
        orderBy(EventColumns.DESCRIPTION, desc);
        return this;
    }

    public EventSelection orderByDescription() {
        orderBy(EventColumns.DESCRIPTION, false);
        return this;
    }

    public EventSelection startTimestamp(Date... value) {
        addEquals(EventColumns.START_TIMESTAMP, value);
        return this;
    }

    public EventSelection startTimestampNot(Date... value) {
        addNotEquals(EventColumns.START_TIMESTAMP, value);
        return this;
    }

    public EventSelection startTimestamp(long... value) {
        addEquals(EventColumns.START_TIMESTAMP, toObjectArray(value));
        return this;
    }

    public EventSelection startTimestampAfter(Date value) {
        addGreaterThan(EventColumns.START_TIMESTAMP, value);
        return this;
    }

    public EventSelection startTimestampAfterEq(Date value) {
        addGreaterThanOrEquals(EventColumns.START_TIMESTAMP, value);
        return this;
    }

    public EventSelection startTimestampBefore(Date value) {
        addLessThan(EventColumns.START_TIMESTAMP, value);
        return this;
    }

    public EventSelection startTimestampBeforeEq(Date value) {
        addLessThanOrEquals(EventColumns.START_TIMESTAMP, value);
        return this;
    }

    public EventSelection orderByStartTimestamp(boolean desc) {
        orderBy(EventColumns.START_TIMESTAMP, desc);
        return this;
    }

    public EventSelection orderByStartTimestamp() {
        orderBy(EventColumns.START_TIMESTAMP, false);
        return this;
    }

    public EventSelection endTimestamp(Date... value) {
        addEquals(EventColumns.END_TIMESTAMP, value);
        return this;
    }

    public EventSelection endTimestampNot(Date... value) {
        addNotEquals(EventColumns.END_TIMESTAMP, value);
        return this;
    }

    public EventSelection endTimestamp(long... value) {
        addEquals(EventColumns.END_TIMESTAMP, toObjectArray(value));
        return this;
    }

    public EventSelection endTimestampAfter(Date value) {
        addGreaterThan(EventColumns.END_TIMESTAMP, value);
        return this;
    }

    public EventSelection endTimestampAfterEq(Date value) {
        addGreaterThanOrEquals(EventColumns.END_TIMESTAMP, value);
        return this;
    }

    public EventSelection endTimestampBefore(Date value) {
        addLessThan(EventColumns.END_TIMESTAMP, value);
        return this;
    }

    public EventSelection endTimestampBeforeEq(Date value) {
        addLessThanOrEquals(EventColumns.END_TIMESTAMP, value);
        return this;
    }

    public EventSelection orderByEndTimestamp(boolean desc) {
        orderBy(EventColumns.END_TIMESTAMP, desc);
        return this;
    }

    public EventSelection orderByEndTimestamp() {
        orderBy(EventColumns.END_TIMESTAMP, false);
        return this;
    }

    public EventSelection url(String... value) {
        addEquals(EventColumns.URL, value);
        return this;
    }

    public EventSelection urlNot(String... value) {
        addNotEquals(EventColumns.URL, value);
        return this;
    }

    public EventSelection urlLike(String... value) {
        addLike(EventColumns.URL, value);
        return this;
    }

    public EventSelection urlContains(String... value) {
        addContains(EventColumns.URL, value);
        return this;
    }

    public EventSelection urlStartsWith(String... value) {
        addStartsWith(EventColumns.URL, value);
        return this;
    }

    public EventSelection urlEndsWith(String... value) {
        addEndsWith(EventColumns.URL, value);
        return this;
    }

    public EventSelection orderByUrl(boolean desc) {
        orderBy(EventColumns.URL, desc);
        return this;
    }

    public EventSelection orderByUrl() {
        orderBy(EventColumns.URL, false);
        return this;
    }
}
