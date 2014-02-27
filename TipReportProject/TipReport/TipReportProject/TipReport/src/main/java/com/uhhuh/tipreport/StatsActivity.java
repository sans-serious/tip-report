package com.uhhuh.tipreport;

import java.util.List;
import java.util.Calendar;
import java.util.Locale;
import java.util.Locale;

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
	
	Calendar now = Calendar.getInstance();
	
	String day_of_week = now.getDisplayName(now.DAY_OF_WEEK, now.SHORT, Locale.US);
	day getToday = new day(this, now);
	
	String[] this_week = {Integer.toString(now.get(now.WEEK_OF_YEAR)), Integer.toString(now.get(now.YEAR))};
	
	now.add(now.WEEK_OF_YEAR, -1);
	String[] last_week = {Integer.toString(now.get(now.WEEK_OF_YEAR)), Integer.toString(now.get(now.YEAR))};
	
	now.add(now.WEEK_OF_YEAR, 1);
	now.add(now.YEAR, -1);
	String[] this_week_last_year = {Integer.toString(now.get(now.WEEK_OF_YEAR)), Integer.toString(now.get(now.YEAR))};
	
	Week ThisWeek = new Week(this, this_week);
	Week LastWeek = new Week(this, last_week);
	Week ThisWeekLastYear = new Week(this, this_week_last_year);
	
	
	StatGenerator stats = new StatGenerator();
	stats.start(this);
	
	String avg = stats.avgOfShifts();
    List<String> avg_of_week = stats.weekSoFar();
	List<String> avg_of_day = stats.avgOfDay(day_of_week);
	
	TextView today = (TextView) findViewById(R.id.todays_income);
	
	TextView given_day = (TextView) findViewById(R.id.given_day);
	TextView avg_day_of_week = (TextView) findViewById(R.id.avg_of_day_of_week);
	TextView recent_shift = (TextView) findViewById(R.id.recent_shift);
    TextView avg_of_shifts = (TextView) findViewById(R.id.avg_of_shifts);
	TextView avg_this_week = (TextView) findViewById(R.id.avg_of_this_week);
	TextView sum_this_week = (TextView) findViewById(R.id.sum_of_this_week);
	TextView week_title = (TextView) findViewById(R.id.week_title);
	TextView avg_last_week = (TextView) findViewById(R.id.avg_of_last_week);
	TextView sum_of_last_week = (TextView) findViewById(R.id.sum_of_last_week);
	TextView avg_this_week_last_year = (TextView) findViewById(R.id.avg_of_this_week_last_year);
	TextView sum_this_week_last_year = (TextView) findViewById(R.id.sum_this_week_last_year);
	
	TextView sun = (TextView) findViewById(R.id.sun);
	TextView mon = (TextView) findViewById(R.id.mon);
	TextView tue = (TextView) findViewById(R.id.tue);
	TextView wed = (TextView) findViewById(R.id.wed);
	TextView thu = (TextView) findViewById(R.id.thu);
	TextView fri = (TextView) findViewById(R.id.fri);
	TextView sat = (TextView) findViewById(R.id.sat);
	
	avg_of_shifts.setText(avg);
	avg_this_week.setText(ThisWeek.weekAvg());
	given_day.setText(day_of_week);
	recent_shift.setText(avg_of_week.get(4));
	avg_day_of_week.setText(avg_of_day.get(0));
	sum_this_week.setText(ThisWeek.weekSum());
	week_title.setText(avg_of_week.get(6));
	
	today.setText(getToday.forcast());
	
	avg_last_week.setText(LastWeek.weekAvg());
	sum_of_last_week.setText(LastWeek.weekSum());
	
	avg_this_week_last_year.setText(ThisWeekLastYear.weekAvg());
	sum_this_week_last_year.setText(ThisWeekLastYear.weekSum());
	
	sun.setText(ThisWeek.string_tips_of_day("Sun"));
	mon.setText(ThisWeek.string_tips_of_day("Mon"));
	tue.setText(ThisWeek.string_tips_of_day("Tue"));
	wed.setText(ThisWeek.string_tips_of_day("Wed"));
	thu.setText(ThisWeek.string_tips_of_day("Thu"));
	fri.setText(ThisWeek.string_tips_of_day("Fri"));
	sat.setText(ThisWeek.string_tips_of_day("Sat"));
	
	stats.end();
	
	
	
	
	}
}
