package com.example.mynanodegreeapps.healthifymeapp.sync;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by akhatri on 19/03/16.
 */
public class EventsSyncService  extends Service {

    private static final Object sSyncAdapterLock = new Object();
    private static EventsSyncAdapter eventsSyncAdapter = null;

    @Override
    public void onCreate() {
        Log.d("FootbalSyncService", "onCreate - FootballScoresSyncService");
        synchronized (sSyncAdapterLock) {
            if (eventsSyncAdapter == null) {
                eventsSyncAdapter = new EventsSyncAdapter(getApplicationContext(), true);
            }
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return eventsSyncAdapter.getSyncAdapterBinder();
    }
}
