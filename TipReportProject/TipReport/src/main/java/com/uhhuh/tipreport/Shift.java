package com.uhhuh.tipreport;

/**
 * Created by olibrooks on 3/18/14.
 */
public class Shift {

    private int _id;
    private int job_id;
    private String time_in;
    private String time_out;
    private String day_of_week;
    private String day_of_month;
    private int year;
    private int week_id;
    private float tips;

    public void SetID(int id){
        this._id = id;
    }

    public void SetJobID(int jobID){
        this.job_id = jobID;
    }

    public void SetTimeIn(String timeIn){
        this.time_in = timeIn;
    }

    public void SetTimeOut(String timeOut){
        this.time_out = timeOut;
    }

    public void SetDayOfWeek(String dayOfWeek){
        this.day_of_week = dayOfWeek;
    }

    public void SetDayOfMonth(String dayOfMonth){
        this.day_of_month = dayOfMonth;
    }

    public void SetYear(int year){
        this.year = year;
    }

    public void setWeekID(int weekID){
        this.week_id = weekID;
    }

    public void setTips(float tips){
        this.tips = tips;
    }

    public int GetID(int id){
        return this._id;
    }

    public int GetJobID(int jobID){
        return this.job_id;
    }

    public String GetTimeIn(String timeIn){
        return this.time_in;
    }

    public String GetTimeOut(String timeOut){
        return this.time_out;
    }

    public String GetDayOfWeek(String dayOfWeek){
        return this.day_of_week;
    }

    public String GetDayOfMonth(String dayOfMonth){
        return this.day_of_month;
    }

    public int GetYear(int year){
        return this.year;
    }

    public int GetWeekID(int weekID){
        return this.week_id;
    }

    public float GetTips(float tips){
        return this.tips;
    }

}
