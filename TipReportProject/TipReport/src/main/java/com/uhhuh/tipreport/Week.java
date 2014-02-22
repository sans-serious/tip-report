package com.uhhuh.tipreport;

import android.content.Context;
import java.util.*;

public class Week{
    private ShiftDataSource shiftData;
	private Context context;

    final int SUNDAY = 0;
	final int MONDAY = 1;
	final int TUESDAY = 2;
	final int WEDNESDAY = 3;
    final int THURSDAY= 4;
	final int FRIDAY = 5;
	final int SATURDAY= 6;
	
	private HashMap days_of_week = new HashMap();
	
	public Week(Context context, int week_number){
		this.context = context;
		shiftData = new ShiftDataSource(context);
		shiftData.open();
		
		List<Shift> records = new ArrayList<Shift>();
		
		records = shiftData.getRecords("week", new String[]{Integer.toString(week_number)});
		
		shiftData.close();
		
		
	}
	
	public void setWeek(Integer week){
		
	}
	
	
	}
