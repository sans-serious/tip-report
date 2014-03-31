package com.uhhuh.tipreport;


import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.Calendar;
import java.util.Locale;


public class MainActivity extends Activity {

    MySQLiteHelper SQLhelper = new MySQLiteHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_main);


        LinearLayout mainParent = (LinearLayout) findViewById(R.id.main_view_parent);

        View tipEntry = getLayoutInflater().inflate(R.layout.tip_entry_box, null);
        View tipStats = getLayoutInflater().inflate(R.layout.activity_stats_2, null);

        mainParent.addView(tipEntry);
        mainParent.addView(tipStats);

        EditText clock_in = (EditText) findViewById(R.id.clock_in_time);
        EditText clock_out = (EditText) findViewById(R.id.clock_out_time);

        Calendar now = Calendar.getInstance();
        String hour_clocked_in = now.getDisplayName(now.HOUR_OF_DAY,now.SHORT,Locale.US);
        String minutes_clocked_in = now.getDisplayName(now.MINUTE,now.SHORT, Locale.US);
        String hour_clocked_out = now.getDisplayName(now.HOUR_OF_DAY,now.SHORT,Locale.US);
        String minutes_clocked_out = now.getDisplayName(now.MINUTE,now.SHORT, Locale.US);


        clock_in.setText(hour_clocked_in + ":" + minutes_clocked_in);
        clock_out.setText(hour_clocked_out + ":" + minutes_clocked_out);


            }

    public void saveShift(View view){
		
		HashMap<String, String> data = new HashMap<String, String>();

		Calendar cal_date = Calendar.getInstance();

        EditText employer = (EditText) findViewById(R.id.employer);
        EditText job_title = (EditText) findViewById(R.id.job_title);
        EditText clockIn = (EditText) findViewById(R.id.clock_in_time);
        EditText clockOut = (EditText) findViewById(R.id.clock_out_time);
        EditText tips = (EditText) findViewById(R.id.tips);

        String day = cal_date.getDisplayName(cal_date.DAY_OF_WEEK,cal_date.SHORT, Locale.US);
		String month = cal_date.getDisplayName(cal_date.MONTH, cal_date.LONG, Locale.US);
		String day_num = Integer.toString(cal_date.get(cal_date.DAY_OF_MONTH));
		String year = Integer.toString(cal_date.get(cal_date.YEAR));
		String week_of = Integer.toString(cal_date.get(cal_date.WEEK_OF_YEAR));





        Week this_week = new Week();
        Shift this_shift = new Shift();

        //check if employer is in db
        Integer employer_ID = SQLhelper.getEmployerID(employer.toString());
        if(employer_ID == -1){
            //start AddEmployer activity
        }

        //construct week and determine if it is already in database
        this_week.setWeek_num(cal_date.get(cal_date.WEEK_OF_YEAR));
        if(this_week.getWeek_num() == 1 && cal_date.get(cal_date.MONTH) == cal_date.DECEMBER){
            this_week.setYear(cal_date.get(cal_date.YEAR) + 1);
        }
        else{
            this_week.setYear(cal_date.get(cal_date.YEAR));
        }

        // if in database retrieve id, else create and retain id
        this_week.set_id(SQLhelper.getWeekId(this_week.getWeek_num(), this_week.getYear()));
        if(this_week.getYear() == -1){
            this_week.set_id(SQLhelper.addWeek(this_week));
        }
        // determine if job title is in database if so, retrieve id, else pop up dialog and create job
        long job_id = SQLhelper.getJobID(job_title.toString());
        if(job_id == -1){
            //pop-up to add job and details!
        }
        //assign appropriate week/job/employer ids to this_shift, enter shift into database
        this_shift.SetJobID(job_id);
        this_shift.SetEmployer_id(employer_ID);
        this_shift.SetTimeIn(clockIn.toString());
        this_shift.SetTimeOut(clockOut.toString());
        this_shift.setMonth(cal_date.getDisplayName(cal_date.MONTH, cal_date.SHORT,Locale.US));
        this_shift.SetDayOfMonth(cal_date.getDisplayName(cal_date.DAY_OF_MONTH, cal_date.SHORT,Locale.US));
        this_shift.SetDayOfWeek(cal_date.getDisplayName(cal_date.DAY_OF_WEEK, cal_date.SHORT,Locale.US));
        this_shift.SetYear(cal_date.get(cal_date.YEAR));
        this_shift.setWeekID(this_week.get_id());
        this_shift.setTips(Float.parseFloat(tips.toString()));

        //switch to stats view





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
