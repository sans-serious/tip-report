package com.uhhuh.tipreport;

import java.util.List;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.content.Context;
import android.widget.Toast;


import java.util.Date;
import java.text.SimpleDateFormat;
import android.*;

import java.util.Calendar;
import java.util.Locale;


public class MainActivity extends Activity {
	private ShiftDataSource dataSource;
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataSource = new ShiftDataSource(this);
        dataSource.open();
            }

    public void saveShift(View view){
		
		HashMap<String, String> data = new HashMap<String, String>();
		
		Calendar cal_date = Calendar.getInstance() ;
		
		EditText enter_tips = (EditText) findViewById(R.id.enterTips);
        EditText enter_hours = (EditText) findViewById(R.id.enterHours);

        String hours = enter_hours.getText().toString();
        String tips = enter_tips.getText().toString();
		
		cal_date.add(cal_date.HOUR_OF_DAY, -8);
	
        
		String day = cal_date.getDisplayName(cal_date.DAY_OF_WEEK,cal_date.SHORT, Locale.US);
		String month = cal_date.getDisplayName(cal_date.MONTH, cal_date.LONG, Locale.US);
		String day_num = Integer.toString(cal_date.get(cal_date.DAY_OF_MONTH));
		String year = Integer.toString(cal_date.get(cal_date.YEAR));
		String week_of = Integer.toString(cal_date.get(cal_date.WEEK_OF_YEAR));
		
        
        
		
		if(tips.length() != 0 || hours.length() != 0){
        
		data.put(MySQLiteHelper.COLUMN_TIP, tips);
		data.put(MySQLiteHelper.COLUMN_HOURS, hours);
		data.put(MySQLiteHelper.COLUMN_MONTH, month);
		data.put(MySQLiteHelper.COLUMN_DAY, day);
		data.put(MySQLiteHelper.COLUMN_DAY_NUM, day_num);
		data.put(MySQLiteHelper.COLUMN_YEAR, year);
        data.put(MySQLiteHelper.COLUMN_WEEK, week_of);

        dataSource.createTip(data);
		dataSource.close();
		

		
		Intent intent = new Intent(this, StatsActivity.class);
    	startActivity(intent);
		
        }
        


    }
	
	public void showStats(View view){
		Intent intent = new Intent(this, StatsActivity.class);
		startActivity(intent);
		
	}
	
	public void showShifts(View view){
	Intent intent = new Intent(this, ShowAllShiftActivity.class);
	startActivity(intent);
	}



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
