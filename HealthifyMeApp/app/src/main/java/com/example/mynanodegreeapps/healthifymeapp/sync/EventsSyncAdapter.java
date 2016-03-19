package com.example.mynanodegreeapps.healthifymeapp.sync;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SyncRequest;
import android.content.SyncResult;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.example.mynanodegreeapps.healthifymeapp.Global;
import com.example.mynanodegreeapps.healthifymeapp.R;
import com.example.mynanodegreeapps.healthifymeapp.model.Event;
import com.example.mynanodegreeapps.healthifymeapp.provider.event.EventColumns;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by akhatri on 19/03/16.
 */
public class EventsSyncAdapter extends AbstractThreadedSyncAdapter {

    private final String LOG_TAG = EventsSyncAdapter.class.getSimpleName();

    // Interval at which to sync , in seconds.
    // 60 seconds (1 minute) * 180 = 3 hours
    public static final int SYNC_INTERVAL = 30;
    public static final int SYNC_FLEXTIME = 0;

    public static final String ACTION_DATA_UPDATED = "events.ACTION_DATA_UPDATED";

    public EventsSyncAdapter(Context context, boolean autoInitialize) {
        super(context, autoInitialize);
    }

    @Override
    public void onPerformSync(Account account, Bundle extras, String authority, ContentProviderClient provider, SyncResult syncResult) {
        getEventsData();

        //Update widget after getting data
        Intent intent = new Intent(ACTION_DATA_UPDATED);
        getContext().sendBroadcast(intent);
    }

    private void getEventsData(){

        try {
            URL eventsURL = Global.getEventsUrl();
            String jsonData = Global.getJSONDataFromURL(eventsURL);
            ArrayList<Event> events = Global.getEventsFromJSON(new JSONObject(jsonData));

            Vector<ContentValues> cVVector = new Vector<ContentValues>(events.size());

            for(int i = 0; i < events.size(); i++){
                if(!events.get(i).getCollege() && events.get(i).getStatus().equals("ONGOING")) {
                    ContentValues contentValues = new ContentValues();

                    contentValues.put(EventColumns.STATUS, events.get(i).getStatus());
                    contentValues.put(EventColumns.COLLEGE, events.get(i).getCollege());
                    contentValues.put(EventColumns.TITLE, events.get(i).getTitle());
                    contentValues.put(EventColumns.THUMBNAIL, events.get(i).getThumbnail());
                    contentValues.put(EventColumns.CHALLENGE_TYPE, events.get(i).getChallenge_type());
                    contentValues.put(EventColumns.DESCRIPTION, events.get(i).getDescription());
                    contentValues.put(EventColumns.START_TIMESTAMP, Global.simpleDateFormat.format(events.get(i).getStart_time()));
                    contentValues.put(EventColumns.END_TIMESTAMP, Global.simpleDateFormat.format(events.get(i).getEnd_time()));
                    contentValues.put(EventColumns.URL, events.get(i).getUrl());

                    cVVector.add(contentValues);
                }

            }
            if ( cVVector.size() > 0 ) {
                ContentValues[] cvArray = new ContentValues[cVVector.size()];
                cVVector.toArray(cvArray);
                getContext().getContentResolver().bulkInsert(EventColumns.CONTENT_URI, cvArray);
            }
        }
        catch (JSONException ex){
            Log.e(LOG_TAG,ex.getMessage());
        }
    }

    /**
     * Helper method to schedule the sync adapter periodic execution
     */
    public static void configurePeriodicSync(Context context, int syncInterval, int flexTime) {
        Account account = getSyncAccount(context);
        String authority = context.getString(R.string.content_authority);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // we can enable inexact timers in our periodic sync
            SyncRequest request = new SyncRequest.Builder().
                    syncPeriodic(syncInterval, flexTime).
                    setSyncAdapter(account, authority).
                    setExtras(new Bundle()).build();
            ContentResolver.requestSync(request);
        } else {
            ContentResolver.addPeriodicSync(account,
                    authority, new Bundle(), syncInterval);
        }
    }

    /**
     * Helper method to have the sync adapter sync immediately
     * @param context The context used to access the account service
     */
    public static void syncImmediately(Context context) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(ContentResolver.SYNC_EXTRAS_EXPEDITED, true);
        bundle.putBoolean(ContentResolver.SYNC_EXTRAS_MANUAL, true);
        ContentResolver.requestSync(getSyncAccount(context),
                context.getString(R.string.content_authority), bundle);
    }

    public static Account getSyncAccount(Context context) {
        // Get an instance of the Android account manager
        AccountManager accountManager =
                (AccountManager) context.getSystemService(Context.ACCOUNT_SERVICE);

        // Create the account type and default account
        Account newAccount = new Account(
                context.getString(R.string.app_name), context.getString(R.string.sync_account_type));

        // If the password doesn't exist, the account doesn't exist
        if ( null == accountManager.getPassword(newAccount) ) {

        /*
         * Add the account and account type, no password or user data
         * If successful, return the Account object, otherwise report an error.
         */
            if (!accountManager.addAccountExplicitly(newAccount, "", null)) {
                return null;
            }
            /*
             * If you don't set android:syncable="true" in
             * in your <provider> element in the manifest,
             * then call ContentResolver.setIsSyncable(account, AUTHORITY, 1)
             * here.
             */
            onAccountCreated(newAccount,context);

        }
        return newAccount;
    }

    private static void onAccountCreated(Account newAccount, Context context) {
        /*
         * Since we've created an account
         */
        EventsSyncAdapter.configurePeriodicSync(context, SYNC_INTERVAL, SYNC_FLEXTIME);

        /*
         * Without calling setSyncAutomatically, our periodic sync will not be enabled.
         */
        ContentResolver.setSyncAutomatically(newAccount, context.getString(R.string.content_authority), true);

        /*
         * Finally, let's do a sync to get things started
         */
        syncImmediately(context);
    }

    public static void initializeSyncAdapter(Context context) {
        getSyncAccount(context);
    }
}
