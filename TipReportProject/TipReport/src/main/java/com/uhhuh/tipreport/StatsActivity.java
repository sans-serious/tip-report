package com.uhhuh.tipreport;

import java.util.List;

import android.*;
import android.app.*;
import android.os.*;
import android.widget.*;

public class StatsActivity extends Activity
{

@Override
    protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_stats);
	StatGenerator stats = new StatGenerator();
	stats.start(this);
	
	String avg = stats.avgOfShifts();
    List<String> avg_of_week = stats.weekSoFar();
	List<String> avg_of_day = stats.avgOfDay(avg_of_week.get(3));
	
	TextView given_day = (TextView) findViewById(R.id.given_day);
	TextView avg_day_of_week = (TextView) findViewById(R.id.avg_of_day_of_week);
	TextView recent_shift = (TextView) findViewById(R.id.recent_shift);
    TextView avg_of_shifts = (TextView) findViewById(R.id.avg_of_shifts);
	TextView avg_this_week = (TextView) findViewById(R.id.avg_of_week);
	TextView sum_this_week = (TextView) findViewById(R.id.sum_of_week);
	TextView week_title = (TextView) findViewById(R.id.week_title);
	
	
	
	avg_of_shifts.setText(avg);
	avg_this_week.setText(avg_of_week.get(0));
	given_day.setText(avg_of_week.get(3));
	recent_shift.setText(avg_of_week.get(4));
	avg_day_of_week.setText(avg_of_day.get(0));
	sum_this_week.setText(avg_of_week.get(1));
	week_title.setText(avg_of_week.get(6));
	
	
	stats.end();
	
	
	
	
	}
}
