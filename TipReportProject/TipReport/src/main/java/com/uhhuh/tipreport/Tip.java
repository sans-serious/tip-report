package com.uhhuh.tipreport;

/**
 * Created by olibrooks on 6/21/13.
 */
public class Tip {
    private long id;
    private String tips;
    private String hours;
    private String date;
    private String day;

    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

    public String getTips(){
        return this.tips;
    }
	
	public String getHours(){
		return this.hours;
	}

    public String getDate(){
        return this.date;
    }

    public String getDay(){
        return this.day;
    }

    public void setTips(String tips){
        this.tips = tips;
    }
	
	public void setHours(String hours){
		this.hours = hours;
	}

    public void setDate(String date){
        this.date = date;
    }

    public void setDay(String day){
        this.day = day;
    }

    @Override
    public String toString(){
        return tips;
    }
}
