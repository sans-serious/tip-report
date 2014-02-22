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

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by olibrooks on 5/31/13.
 */
public class ShowShiftActivity extends Activity {
    private ShiftDataSource datasource;
    
	@SuppressLint("NewApi")
    @Override
    public void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_shift);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
            getActionBar().setDisplayHomeAsUpEnabled(true);

        }

        datasource = new ShiftDataSource(this);
        datasource.open();

        List<Tip> shifts = datasource.getAllShifts();

        Collections.reverse(shifts);
		
		final ListView lv1 = (ListView) findViewById(R.id.custom_list);
		lv1.setAdapter(new shiftDetailAdapter(this, (ArrayList) shifts));
		

     
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
