package com.uhhuh.tipreport;

/**
 * Created by olibrooks on 3/18/14.
 */
public class Shift {

    private int _id;

    private long employer_id;
    private long job_id;
    private long week_id;
    private String time_in;
    private String time_out;
    private String day_of_week;
    private String day_of_month;
    private int year;
    private float tips;
    private String month;

    public void SetID(int id){
        this._id = id;
    }

    public void SetJobID(long jobID){
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

    public void setWeekID(long weekID){
        this.week_id = weekID;
    }

    public void setTips(float tips){
        this.tips = tips;
    }

    public void setMonth(String month){ this.month = month;}

    public int GetID(){
        return this._id;
    }

    public long getEmployer_id() {
        return employer_id;
    }

    public void SetEmployer_id(long employer_id) {
        this.employer_id = employer_id;
    }

    public Long GetJobID(){
        return this.job_id;
    }

    public String GetTimeIn(){
        return this.time_in;
    }

    public String GetTimeOut(){
        return this.time_out;
    }

    public String GetDayOfWeek(){
        return this.day_of_week;
    }

    public String GetDayOfMonth(){
        return this.day_of_month;
    }

    public int GetYear(){
        return this.year;
    }

    public long GetWeekID(){
        return this.week_id;
    }

    public float GetTips(){
        return this.tips;
    }

    public String GetMonth() { return this.month; }

}
