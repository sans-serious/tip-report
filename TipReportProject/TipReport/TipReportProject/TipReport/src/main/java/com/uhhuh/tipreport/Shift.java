package com.uhhuh.tipreport;

/**
 * Created by olibrooks on 6/21/13.
 */
public class Shift {
    private long id;
    private String tips;
    private String hours;
    private String date;
    private String day;
	private String day_num;
	private String month;
	private String year;
    private int week_of;
	
	public void setWeekOf(String week_of){
		this.week_of = Integer.parseInt(week_of);
	}
	
	public int getWeekOf(){
		return this.week_of;
	}
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
		this.date = this.day + ", "+ this.month + " " +this.day_num + ", " + this.year;
        return this.date;
    }

    public String getDay(){
        return this.day;
    }
	
	public void setDayNum(String day_num){
		this.day_num = day_num;
	}
	
	public String getDayNum(){
		return this.day_num;
	}
	public void setMonth(String month){
		this.month = month;
	}
	
	public String getMonth(){
		return this.month;
	}
	
	public void setYear(String year){
		this.year = year;
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
