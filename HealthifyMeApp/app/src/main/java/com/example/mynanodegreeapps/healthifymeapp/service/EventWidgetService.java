package com.example.mynanodegreeapps.healthifymeapp.service;

import android.app.IntentService;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.text.Html;
import android.util.Log;
import android.widget.RemoteViews;

import com.example.mynanodegreeapps.healthifymeapp.Global;
import com.example.mynanodegreeapps.healthifymeapp.MainActivity;
import com.example.mynanodegreeapps.healthifymeapp.R;
import com.example.mynanodegreeapps.healthifymeapp.model.Event;
import com.example.mynanodegreeapps.healthifymeapp.provider.event.EventColumns;
import com.example.mynanodegreeapps.healthifymeapp.widget.EventWidget;
import com.squareup.picasso.Picasso;

import java.io.IOException;

/**
 * Created by akhatri on 19/03/16.
 */
public class EventWidgetService  extends IntentService {

    public static final String LOG_TAG = "widget_intent_service";
    public static final int COL_ID = 0;
    public static final int COL_STATUS = 1;
    public static final int COL_COLLEGE = 2;
    public static final int COL_TITLE = 3;
    public static final int COL_THUMBNAIL = 4;
    public static final int COL_CHALLENGE_TYPE = 5;
    public static final int COL_DESCRIPTION = 6;
    public static final int COL_START_TIME = 7;
    public static final int COL_END_TIME = 8;
    public static final int COL_URL = 9;

    public EventWidgetService(){ super(LOG_TAG);}


    protected PendingIntent getPendingSelfIntent(Context context, String action) {
        Intent intent = new Intent(context, getClass());
        intent.setAction(action);
        return PendingIntent.getBroadcast(context, 0, intent, 0);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        try {
            String _id = intent.getStringExtra(EventWidget.CURRENT_ID);
            int direction = intent.getIntExtra(EventWidget.DIRECTION, 0);
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
            int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this,
                    EventWidget.class));

            Uri eventsUri = EventColumns.CONTENT_URI;
            Cursor data = getContentResolver().query(eventsUri,
                    null,
                    null,
                    null,
                    EventColumns.START_TIMESTAMP);
            if(_id != null){
                while (data.moveToNext()){
                    if(data.getString(COL_ID).equals(_id)){
                        break;
                    }
                }
            }
            else {
                if (data == null) {
                    return;
                }
                if (!data.moveToFirst()) {
                    data.close();
                    return;
                }
            }

            for (int appWidgetId : appWidgetIds) {
                RemoteViews remoteViews = new RemoteViews(this.getPackageName(), R.layout.events_widget);

                //intent.putExtra(EventWidget.DIRECTION, 1);
                //remoteViews.setOnClickPendingIntent(R.id.rightButton, getPendingSelfIntent(getBaseContext(), EventWidget.RIGHT_CLICKED));



                if(data.moveToNext()) {
                    Intent intentRightClick = new Intent(EventWidget.RIGHT_CLICKED);
                    intentRightClick.putExtra(EventWidget.CURRENT_ID, data.getString(COL_ID));
                    intentRightClick.putExtra(EventWidget.DIRECTION, -1);
                    //intentRightClick.setData(EventColumns.CONTENT_URI.buildUpon().appendPath(data.getString(COL_ID)).build());
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), 0, intentRightClick, PendingIntent.FLAG_UPDATE_CURRENT);
                    remoteViews.setOnClickPendingIntent(R.id.rightButton, pendingIntent);
                }
                data.moveToPrevious();


                if(data.moveToPrevious()) {
                    Intent intentLeftClick = new Intent(EventWidget.LEFT_CLICKED);
                    intentLeftClick.putExtra(EventWidget.CURRENT_ID, data.getString(COL_ID));
                    intentLeftClick.putExtra(EventWidget.DIRECTION, -1);
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), 0, intentLeftClick, PendingIntent.FLAG_UPDATE_CURRENT);
                    remoteViews.setOnClickPendingIntent(R.id.leftButton, pendingIntent);
                }
                data.moveToNext();



//                Intent configIntent =
//                        new Intent(getBaseContext(), EventWidget.class);
//                configIntent.putExtra(
//                        AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
//                configIntent.setData(eventsUri.buildUpon().appendPath(String.valueOf(data.getLong(0) + 1)).build());
//
//                PendingIntent pendingIntent = PendingIntent.getActivity
//                        (getBaseContext(), 0, configIntent,
//                                PendingIntent.FLAG_UPDATE_CURRENT);
//                remoteViews.setOnClickPendingIntent(R.id.rightButton, pendingIntent);

                remoteViews.setTextViewText(R.id.title, data.getString(COL_TITLE));
                remoteViews.setTextViewText(R.id.description, data.getString(COL_DESCRIPTION));
                remoteViews.setTextViewText(R.id.challenge_type, data.getString(COL_CHALLENGE_TYPE));
                if(!data.isNull(COL_THUMBNAIL))
                    remoteViews.setImageViewBitmap(R.id.thumbnail,
                            Picasso.with(getBaseContext()).
                                    load(data.getString(COL_THUMBNAIL)).
                                    get());
                else
                    remoteViews.setImageViewResource(R.id.thumbnail,R.drawable.backupthumbnail);

                remoteViews.setTextViewText(R.id.startDate, Global.getDate(data.getString(COL_START_TIME)));
                remoteViews.setTextViewText(R.id.endDate, Global.getDate(data.getString(COL_END_TIME)));

                remoteViews.setTextViewText(R.id.duration, Global.getTime(data.getString(COL_START_TIME)) + " - " +
                        Global.getTime(data.getString(COL_END_TIME)));

                remoteViews.setTextViewText(R.id.timeZone,Global.getTimeZone(data.getString(COL_START_TIME)));

                remoteViews.setTextViewText(R.id.url, Html.fromHtml(Global.getURLString(data.getString(COL_URL))));

                Intent intentUrlClick = new Intent(EventWidget.URL_CLICKED);
                intentUrlClick.putExtra(EventWidget.URL,data.getString(COL_URL));
                PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), 0, intentUrlClick, PendingIntent.FLAG_UPDATE_CURRENT);
                remoteViews.setOnClickPendingIntent(R.id.url, pendingIntent);


                appWidgetManager.updateAppWidget(appWidgetId, remoteViews);

            }
        }
        catch (IOException ex){
            Log.e(LOG_TAG,ex.getMessage());
        }
    }
}
