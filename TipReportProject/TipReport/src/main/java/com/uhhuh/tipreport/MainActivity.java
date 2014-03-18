package com.uhhuh.tipreport;


import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.Calendar;
import java.util.Locale;


public class MainActivity extends Activity {
	private ShiftDataSource dataSource;
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        LinearLayout mainParent = (LinearLayout) findViewById(R.id.main_view_parent);

        View tipEntry = getLayoutInflater().inflate(R.layout.tip_entry_box, null);
        View tipStats = getLayoutInflater().inflate(R.layout.activity_stats_2, null);

        mainParent.addView(tipEntry);
        mainParent.addView(tipStats);

        dataSource = new ShiftDataSource(this);
        dataSource.open();
            }

    public void saveShift(View view){
		
		HashMap<String, String> data = new HashMap<String, String>();
		
		Calendar cal_date = Calendar.getInstance();

        EditText employer = (EditText) findViewById(R.id.employer);
        EditText position = (EditText) findViewById(R.id.position);
        EditText clockIn = (EditText) findViewById(R.id.clock_in_time);
        EditText clockOut = (EditText) findViewById(R.id.clock_out_time);
        EditText tips = (EditText) findViewById(R.id.tips);




        String day = cal_date.getDisplayName(cal_date.DAY_OF_WEEK,cal_date.SHORT, Locale.US);
		String month = cal_date.getDisplayName(cal_date.MONTH, cal_date.LONG, Locale.US);
		String day_num = Integer.toString(cal_date.get(cal_date.DAY_OF_MONTH));
		String year = Integer.toString(cal_date.get(cal_date.YEAR));
		String week_of = Integer.toString(cal_date.get(cal_date.WEEK_OF_YEAR));


        


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
