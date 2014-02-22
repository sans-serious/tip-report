package com.uhhuh.tipreport;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.view.View;
import android.widget.AdapterView;

import android.widget.Toast;
import android.content.Context;


import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.widget.AdapterView.*;
import android.*;

/**
 * Created by olibrooks on 5/31/13.
 */
public class ShowAllShiftActivity extends Activity {
    private ShiftDataSource datasource;
	private ViewSwitcher switcher;
	
    
	@SuppressLint("NewApi")
    @Override
    public void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_shift);
        
		switcher = (ViewSwitcher) findViewById(R.id.shift_switch);
		
		
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
            getActionBar().setDisplayHomeAsUpEnabled(true);

        }

        datasource = new ShiftDataSource(this);
        datasource.open();

        final List<Shift> shifts = datasource.getAllShifts();

        Collections.reverse(shifts);
		
		final ListView lv1 = (ListView) findViewById(R.id.custom_list);
		final shiftDetailAdapter shiftAdapter = new shiftDetailAdapter(this, (ArrayList<Shift>) shifts);
		lv1.setAdapter(shiftAdapter);
		
		lv1.setOnItemClickListener(new OnItemClickListener(){

				@Override
				public void onItemClick(AdapterView<?> a, View v, int position, long id){
					Object o = lv1.getItemAtPosition(position);
					Shift shift = (Shift) o;
					Context context = getApplicationContext();
					String msg = "entry deleted";
					int duration = Toast.LENGTH_SHORT;
					Toast toast = Toast.makeText(context, msg, duration);
					toast.show();
					datasource.deleteTip(shift);
					shifts.remove(shift);
					shiftAdapter.notifyDataSetChanged();
					}
				});
		
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
