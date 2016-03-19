package com.example.mynanodegreeapps.healthifymeapp;

import android.net.Uri;
import android.util.Log;

import com.example.mynanodegreeapps.healthifymeapp.model.Event;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by akhatri on 19/03/16.
 */
public class Global {

    public static final String LOG_TAG = Global.class.getSimpleName();

    public static final String EVENTS_URI = "https://www.hackerearth.com/api/events/upcoming/?format=json";
    public static final String JSON_ARRAY_EVENTS = "response";
    public static final String STATUS_KEY = "status";
    public static final String COLLEGE_KEY = "college";
    public static final String TITLE_KEY = "title";
    public static final String THUMBNAIL_KEY = "thumbnail";
    public static final String CHALLENGE_TYPE_KEY = "challenge_type";
    public static final String DESCRIPTION_KEY = "description";
    public static final String START_TIME_KEY = "start_timestamp";
    public static final String END_TIME_KEY = "end_timestamp";
    public static final String URL_KEY = "url";

    public static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yyyy, hh:mm a z");

    public static URL getEventsUrl(){
        try {
            Uri getEvetsURI = Uri.parse(EVENTS_URI).buildUpon()
                    .build();
            return new URL(getEvetsURI.toString());
        }
        catch (MalformedURLException ex){
            Log.e("getEventUrl", ex.getMessage());
        }
        return  null;
    }

    public static ArrayList<Event> getEventsFromJSON(JSONObject eventsJSON){
        ArrayList<Event> events = new ArrayList<Event>();

        try {
            JSONArray jsonArrayEvents = eventsJSON.getJSONArray(JSON_ARRAY_EVENTS);

            for(int i = 0; i < jsonArrayEvents.length(); i++){
                JSONObject eventJSONObject = (JSONObject)jsonArrayEvents.get(i);
                String status = null;
                if(!eventJSONObject.isNull(STATUS_KEY)){
                    status = eventJSONObject.getString(STATUS_KEY);
                }
                Boolean college = null;
                if(!eventJSONObject.isNull(COLLEGE_KEY)){
                    college = "1".equals(eventJSONObject.getString(COLLEGE_KEY));
                }
                String title = null;
                if(!eventJSONObject.isNull(TITLE_KEY)){
                    title = eventJSONObject.getString(TITLE_KEY);
                }
                String thumbnail = null;
                if(!eventJSONObject.isNull(THUMBNAIL_KEY)){
                    thumbnail = eventJSONObject.getString(THUMBNAIL_KEY);
                }
                String challenge_type = null;
                if(!eventJSONObject.isNull(CHALLENGE_TYPE_KEY)){
                    challenge_type = eventJSONObject.getString(CHALLENGE_TYPE_KEY);
                }
                String description = null;
                if(!eventJSONObject.isNull(DESCRIPTION_KEY)){
                    description = eventJSONObject.getString(DESCRIPTION_KEY);
                }

                Date start_time = null;
                if(!eventJSONObject.isNull(START_TIME_KEY)){
                    start_time = simpleDateFormat.parse(eventJSONObject.getString(START_TIME_KEY));
                }
                Date end_time = null;
                if(!eventJSONObject.isNull(END_TIME_KEY)){
                    end_time = simpleDateFormat.parse(eventJSONObject.getString(END_TIME_KEY));
                }
                String url = null;
                if(!eventJSONObject.isNull(URL_KEY)){
                    url =  eventJSONObject.getString(URL_KEY);
                }
                events.add(new Event(status
                                     ,college
                                     ,title
                                     ,thumbnail
                                     ,challenge_type
                                     ,description
                                     ,start_time
                                     ,end_time
                                     ,url));

            }
        }
        catch (JSONException ex){
            Log.e(LOG_TAG,ex.getMessage());
        }
        catch (ParseException ex){
            Log.e(LOG_TAG,ex.getMessage());
        }
        return  events;
    }

    public static String getJSONDataFromURL(URL url){
        HttpURLConnection urlConnection = null;
        StringBuffer buffer = new StringBuffer();
        BufferedReader reader = null;
        try {

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            if (inputStream == null) {
                // Nothing to do.
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                // But it does make debugging a *lot* easier if you print out the completed
                // buffer for debugging.
                buffer.append(line + "\n");
            }
        }
        catch (Exception ex){
            Log.e("getJSONDataFromURL", ex.getMessage());
        }
        finally {
            if(urlConnection != null)
                urlConnection.disconnect();
        }
        return  buffer.toString();

    }

    public static String getDate(String timestamp){
        return timestamp.split(",")[0];
    }
    public static String getTime(String timestamp){
        String time = timestamp.split(",")[1].toString().trim();
        return time.substring(0,time.lastIndexOf(" "));
    }
    public static String getTimeZone(String timestamp){
        String tempTimeZone = timestamp.split(",")[1].toString().trim();
        return tempTimeZone.substring(tempTimeZone.lastIndexOf(" "));
    }

    public static String getURLString(String url){
        return "<a href='" + url + "'><u> Open </u></a>";
    }
}
