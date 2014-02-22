package com.uhhuh.tipreport;

import android.content.Context;

import java.util.List;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Date;
import java.text.SimpleDateFormat;

import android.widget.Toast;
import android.content.Context;


public class StatGenerator {
    private ShiftDataSource shiftData;
	private Context context;
	
	public void start(Context context){
	shiftData = new ShiftDataSource(context);
	shiftData.open();
	this.context = context;
	}
	
	public String avgOfShifts(){
		final List<Shift> Shifts= shiftData.getAllShifts();
	    int num_of_shifts = 0;
		float sum_of_shifts = 0;
		for(Shift shift: Shifts){
			float t = Float.parseFloat(shift.getTips());
			sum_of_shifts += t;
			num_of_shifts += 1;
		}
		
		float shift_avg = sum_of_shifts/num_of_shifts;
		
		return String.format("%.2f",shift_avg);
	}
	
	public List<String> avgOfDay(String day){
		String[] criteria = {day};
		List<String> results= new ArrayList<String>();
		
		List<Shift> shifts = shiftData.getRecords("day", criteria);
	    
		float total = 0;
		int num_of_shifts = 0;
		
		for(Shift shift: shifts){
			total += Float.parseFloat(shift.getTips());
			num_of_shifts += 1;
		}
		
		float avg = total/num_of_shifts;
		
		results.add(String.format("%.2f",avg));
		
	    return results;
	}
	
	public List<String> weekSoFar(){
		Calendar now = Calendar.getInstance();
		String week_of = "week";
		int this_week = now.get(now.WEEK_OF_YEAR);
		int this_year = now.get(now.YEAR);
		int criteria = this_week;
		List<String> results = new ArrayList<String>();
		List<Shift> shifts = new ArrayList<Shift>();
		
		while(shifts.isEmpty() == true){
			
			shifts = shiftData.getRecords(week_of, new String[]{Integer.toString(criteria)});
		    criteria -= 1;
		}
		
	    float total = 0;
		int num_shifts = 0;
	    long id_of_shift = 0;
		Shift most_recent = new Shift();
		
		for(Shift shift: shifts){
			if(shift.getId() > id_of_shift){
				id_of_shift = shift.getId();
				most_recent = shift;
			}
			
			total += Float.parseFloat(shift.getTips());
			num_shifts += 1;
		}
		
		
		
		float avg = total/num_shifts;
	
		results.add(String.format("%.2f", avg));
		results.add(String.format("%.2f", total));
		results.add(Integer.toString(num_shifts));
		results.add(most_recent.getDay());
		results.add(most_recent.getDate());
		results.add(most_recent.getTips());
		
		Date date = new Date();
		if(criteria != this_week - 1){
			now.clear();
			now.set(now.WEEK_OF_YEAR, criteria + 1);
			now.set(now.YEAR, this_year);
			date = now.getTime();
		}
		else{
			now.clear();
			now.set(now.WEEK_OF_YEAR, this_week);
			now.set(now.YEAR, this_year);
			date = now.getTime();
			
			String t_l = Integer.toString(this_year);
			int duration = Toast.LENGTH_LONG;
			Toast toast = Toast.makeText(this.context, t_l, duration);
			toast.show();
			
		}
		
		
		SimpleDateFormat df = new SimpleDateFormat("MMM d");
		String recent_week = df.format(date);
		
		results.add(recent_week);
		
		
		
		return results;
	}
	
	public void end(){
		shiftData.close();
	}

}
