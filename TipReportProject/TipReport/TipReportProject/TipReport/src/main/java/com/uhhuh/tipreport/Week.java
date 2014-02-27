package com.uhhuh.tipreport;

import android.content.Context;
import android.widget.Toast;

import java.util.*;
import android.location.*;

public class Week{
    private ShiftDataSource shiftData;
	private Context context;
	private float avg;
	private float sum;

    final String SUNDAY = "Sun";
	final String MONDAY = "Mon";
	final String TUESDAY = "Tue";
	final String WEDNESDAY = "Wed";
    final String THURSDAY= "Thu";
	final String FRIDAY = "Fri";
	final String SATURDAY= "Sat";
	
	private HashMap days_of_week = new HashMap();
	
	
	public Week(Context context, String[] criteria){
		
		this.context = context;
	
    	shiftData = new ShiftDataSource(context);
		shiftData.open();
		List<Shift> records = new ArrayList<Shift>();
		records = shiftData.getRecords(new String[]{"week", "year"}, criteria );
		shiftData.close();
		
		int num = 0;
		float total = 0;
		
		for(Shift shift: records){
			String day = shift.getDay();
			float tips = Float.parseFloat(shift.getTips());
			days_of_week.put(day, tips);
			
			total+= tips;
			num += 1;
			
			this.sum = total;
			this.avg = total/num;
		}
		
		
		
		}
		
		public Float tips_of_day(String day){
			float tips = (Float) days_of_week.get(day);
			return tips;
		}
		
		public String string_tips_of_day(String day){
			
		    
			
			if(days_of_week.get(day) == null){
				return "-";
			}
			
			Float tip = (Float) days_of_week.get(day);
			return String.format("%.2f", tip);
		}
		
		
		public String weekAvg(){

			return String.format("%.2f", this.avg);
		}
		
		public String weekSum(){
			return String.format("%.2f", this.sum);
		}
		
		
	
	
	}
