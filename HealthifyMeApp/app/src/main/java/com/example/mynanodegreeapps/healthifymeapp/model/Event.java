package com.example.mynanodegreeapps.healthifymeapp.model;

import java.util.Date;

/**
 * Created by akhatri on 19/03/16.
 */
public class Event {
    //status, college, title, thumbnail, challenge_type, description, start_timestamp, end_timestamp, url
    private String status;
    private Boolean college;
    private String title;
    private String thumbnail;
    private String challenge_type;
    private String description;
    private Date start_time;
    private Date end_time;
    private String url;

    public Event(String status,Boolean college,String title,String thumbnail,String challenge_type,String description,Date start_time,Date end_time,String url){
        this.status = status;
        this.college = college;
        this.title = title;
        this.thumbnail = thumbnail;
        this.challenge_type = challenge_type;
        this.description = description;
        this.start_time = start_time;
        this.end_time = end_time;
        this.url = url;
    }

    public String getStatus(){
        return status;
    }
    public Boolean getCollege(){
        return college;
    }
    public String getTitle(){
        return title;
    }
    public String getThumbnail(){
        return thumbnail;
    }
    public String getChallenge_type(){
        return challenge_type;
    }
    public String getDescription(){
        return description;
    }
    public Date getStart_time(){
        return start_time;
    }
    public Date getEnd_time(){
        return end_time;
    }
    public String getUrl(){
        return url;
    }

}
