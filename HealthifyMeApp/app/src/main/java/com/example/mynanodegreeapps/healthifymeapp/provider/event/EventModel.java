package com.example.mynanodegreeapps.healthifymeapp.provider.event;

import com.example.mynanodegreeapps.healthifymeapp.provider.base.BaseModel;

import java.util.Date;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Event details
 */
public interface EventModel extends BaseModel {

    /**
     * event status
     * Cannot be {@code null}.
     */
    @NonNull
    String getStatus();

    /**
     * college event
     */
    boolean getCollege();

    /**
     * event title
     * Cannot be {@code null}.
     */
    @NonNull
    String getTitle();

    /**
     * event thumbnail url
     * Can be {@code null}.
     */
    @Nullable
    String getThumbnail();

    /**
     * challenge type
     * Cannot be {@code null}.
     */
    @NonNull
    String getChallengeType();

    /**
     * event description
     * Cannot be {@code null}.
     */
    @NonNull
    String getDescription();

    /**
     * event start date
     * Cannot be {@code null}.
     */
    @NonNull
    Date getStartTimestamp();

    /**
     * event end date
     * Cannot be {@code null}.
     */
    @NonNull
    Date getEndTimestamp();

    /**
     * event url
     * Cannot be {@code null}.
     */
    @NonNull
    String getUrl();
}
