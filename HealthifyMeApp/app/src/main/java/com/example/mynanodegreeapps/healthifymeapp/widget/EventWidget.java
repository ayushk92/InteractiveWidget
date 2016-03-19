package com.example.mynanodegreeapps.healthifymeapp.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;

import com.example.mynanodegreeapps.healthifymeapp.R;
import com.example.mynanodegreeapps.healthifymeapp.service.EventWidgetService;
import com.example.mynanodegreeapps.healthifymeapp.sync.EventsSyncAdapter;

/**
 * Created by akhatri on 19/03/16.
 */
public class EventWidget extends AppWidgetProvider {

    private static final String LOG_TAG = EventWidget.class.getSimpleName();

    public static final String RIGHT_CLICKED = "events.widget.rightclick";
    public static final String LEFT_CLICKED = "events.widget.leftclick";
    public static final String URL_CLICKED = "events.widget.urlclick";

    public static final String DIRECTION = "direction";
    public static final String CURRENT_ID = "current_id";
    public static final String URL = "url";

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.events_widget);

        Intent service_start = new Intent(context, EventWidgetService.class);
        context.startService(service_start);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        if(EventsSyncAdapter.ACTION_DATA_UPDATED.equals(intent.getAction())){
            context.startService(new Intent(context,EventWidgetService.class));
        }
        else if(RIGHT_CLICKED.equals(intent.getAction())){
            Intent intent1 = new Intent(context, EventWidgetService.class);
            //Log.d(LOG_TAG,intent.getData().toString());
            String _id = intent.getStringExtra(EventWidget.CURRENT_ID);
            intent1.putExtra(EventWidget.CURRENT_ID,_id);
            intent1.putExtra(EventWidget.DIRECTION,1);
            context.startService(intent1);
        }
        else if(LEFT_CLICKED.equals(intent.getAction())){
            Intent intent1 = new Intent(context, EventWidgetService.class);
            intent1.putExtra(EventWidget.CURRENT_ID,intent.getStringExtra(EventWidget.CURRENT_ID));
            context.startService(intent1);
        }
        else if(URL_CLICKED.equals(intent.getAction())){
            String url = intent.getStringExtra(URL);
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.setData(Uri.parse(url));
            context.startActivity(i);

        }
    }

    @Override
    public void onAppWidgetOptionsChanged(Context context, AppWidgetManager appWidgetManager, int appWidgetId, Bundle newOptions) {
        super.onAppWidgetOptionsChanged(context, appWidgetManager, appWidgetId, newOptions);
        Intent service_start = new Intent(context, EventWidgetService.class);
        service_start.putExtra(DIRECTION,0);
        context.startService(service_start);
    }


}

