package com.uhhuh.tipreport;

import android.content.Context;
import android.widget.Toast;

import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class day
{
	private ShiftDataSource shiftData;
	private Context context;
	private float avg;
	private float sum = 0;
	private int total_shifts = 0;
	private final String[] search_fields = {"num","month","year"};
	private Float today;
	private Calendar c_now;
	
	public day(Context context, Calendar now)
	{
		this.context = context;
		this.c_now = (Calendar) now.clone();
		String[] criteria = setCriteria(c_now);
		
		List<Shift> shifts = new ArrayList<Shift>();
    	shifts = getRecords(search_fields, criteria);
		
		if(shifts.isEmpty()){
			
			
			List<Shift> temp = new ArrayList<Shift>();
			int count = 4;
			while(count > 0){
				c_now.add(c_now.DAY_OF_YEAR, -7);
				criteria = setCriteria(c_now);
				
				temp = getRecords(search_fields,criteria);
				
				if(!temp.isEmpty())
				{
					
					shifts.add(temp.get(0));
				
				}
				
				count -= 1;
			}
			
			for(Shift shift: shifts){
				this.total_shifts += 1;
				this.sum += Float.parseFloat(shift.getTips());
			}
			
			this.avg = this.sum/this.total_shifts;
			
			this.today = this.avg;
			
		}
		else{
			Shift todays_shift = shifts.get(0);
			this.today = Float.parseFloat(todays_shift.getTips());
		}
	}
	
	private String[] setCriteria(Calendar date){
		
		String day_num = Integer.toString(date.get(date.DAY_OF_MONTH));
		String month = date.getDisplayName(date.MONTH, date.LONG, Locale.US);
		String year = Integer.toString(date.get(date.YEAR));

		String[] criteria = {day_num, month, year};
		
		
		return criteria;
	}
	
	public List<Shift> getRecords(String[] search_fields, String[] criteria){
		
		shiftData = new ShiftDataSource(context);
		shiftData.open();
		List<Shift> records = new ArrayList<Shift>();
		records = shiftData.getRecords(search_fields, criteria );
		shiftData.close();
		
		return records;
	}
	
	public String forcast(){
		return String.format("%.2f", this.today);
		
	}
}
