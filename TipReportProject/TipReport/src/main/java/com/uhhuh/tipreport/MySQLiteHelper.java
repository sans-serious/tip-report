package com.uhhuh.tipreport;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.Calendar;


public class MySQLiteHelper extends SQLiteOpenHelper{


    //variables for the creation of shifts table
    public static final String TABLE_SHIFTS = "shifts";
    public static final String SHIFTS_COLUMN_ID = "_id";
    public static final String SHIFTS_COLUMN_JOB_ID = "job_id";
    public static final String SHIFTS_COLUMN_TIME_IN = "time_in";
    public static final String SHIFTS_COLUMN_TIME_OUT = "time_out";
    public static final String SHIFTS_COLUMN_DAY_OF_WEEK = "day_of_week";
    public static final String SHIFTS_COLUMN_DAY_OF_MONTH = "day_of_month";
    public static final String SHIFTS_COLUMN_MONTH = "month";
    public static final String SHIFTS_COLUMN_YEAR = "year";
    public static final String SHIFTS_COLUMN_WEEK_ID = "week_id";
    public static final String SHIFTS_COLUMN_TIPS = "tips";

    public static final String TABLE_EMPLOYER = "employer";
    public static final String EMPLOYER_COLUMN_ID = "_id";
    public static final String EMPLOYER_COLUMN_NAME = "employer_name";
    public static final String EMPLOYER_COLUMN_TYPE = "employer_type";
    public static final String EMPLOYER_COLUMN_CITY = "city";
    public static final String EMPLOYER_COLUMN_STATE = "state";
    public static final String EMPLOYER_COLUMN_COUNTRY = "country";
    public static final String EMPLOYER_COLUMN_ADDRESS = "address";
    public static final String EMPLOYER_COLUMN_PRICE_RANGE = "price_range";

    public static final String TABLE_JOB = "job";
    public static final String JOB_COLUMN_ID = "_id";
    public static final String JOB_COLUMN_TITLE = "title";
    public static final String JOB_COLUMN_EMPLOYER = "employer";
    public static final String JOB_COLUMN_PAY_RATE = "pay_rate";

    public static final String TABLE_WEEKS = "weeks";
    public static final String WEEKS_COLUMN_ID = "_id";
    public static final String WEEKS_COLUMN_WEEK_NUMBER = "week_number";
    public static final String WEEKS_COLUMN_YEAR = "year";

    public static final String DATABASE_CREATE_EMPLOYER = "create table "
            + TABLE_EMPLOYER + "(" + EMPLOYER_COLUMN_ID
            + " integer primary key autoincrement, " + EMPLOYER_COLUMN_TYPE
            + " text not null, " + EMPLOYER_COLUMN_NAME
            + " text not null, " + EMPLOYER_COLUMN_CITY
            + " text not null, " + EMPLOYER_COLUMN_STATE
            + " text not null, " + EMPLOYER_COLUMN_COUNTRY
            + " text not null, " + EMPLOYER_COLUMN_ADDRESS
            + " text not null, " + EMPLOYER_COLUMN_PRICE_RANGE
            + " text not null);";

    public static final String DATABASE_CREATE_JOB = "create table "
            + TABLE_JOB + "(" + JOB_COLUMN_ID
            + " integer primary key autoincrement, " + JOB_COLUMN_TITLE
            + " text not null, " + JOB_COLUMN_EMPLOYER
            + " integer not null " + JOB_COLUMN_PAY_RATE
            + " real not null);";

    public static final String DATABASE_CREATE_WEEKS = "create table "
            + TABLE_WEEKS + "(" + WEEKS_COLUMN_ID
            + " integer primary key autoincrement, " + WEEKS_COLUMN_WEEK_NUMBER
            + " integer not null, " + WEEKS_COLUMN_YEAR
            + " integer not null);";

    //sql statement variable for creating shifts table
    public static final String DATABASE_CREATE_SHIFTS = "create table "
            + TABLE_SHIFTS + "(" + SHIFTS_COLUMN_ID
            + " integer primary key autoincrement, " + SHIFTS_COLUMN_JOB_ID
            + " integer not null, " + SHIFTS_COLUMN_TIME_IN
            + " text not null, " + SHIFTS_COLUMN_TIME_OUT
            + " text not null, " + SHIFTS_COLUMN_DAY_OF_WEEK
            + " integer not null, " + SHIFTS_COLUMN_DAY_OF_MONTH
            + " text not null, " + SHIFTS_COLUMN_MONTH
            + " integer not null, " + SHIFTS_COLUMN_YEAR
            + " integer not null, " + SHIFTS_COLUMN_WEEK_ID
            + " integer not null, " + SHIFTS_COLUMN_TIPS
            + " text not null);";

	private static final String DATABASE_NAME = "tips.db";
    private static final int DATABASE_VERSION = 7;



    public MySQLiteHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database){

        database.execSQL(DATABASE_CREATE_SHIFTS);
        database.execSQL(DATABASE_CREATE_EMPLOYER);
        database.execSQL(DATABASE_CREATE_JOB);
        database.execSQL(DATABASE_CREATE_WEEKS);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                + newVersion + " which will destroy all old data" );

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WEEKS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SHIFTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_JOB);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EMPLOYER);

        onCreate(db);
    }

    public long addShift(Shift shift){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues shift_values = new ContentValues();

        shift_values.put(SHIFTS_COLUMN_JOB_ID, shift.GetJobID());
        shift_values.put(SHIFTS_COLUMN_WEEK_ID, shift.GetWeekID());
        shift_values.put(SHIFTS_COLUMN_TIME_IN, shift.GetTimeIn());
        shift_values.put(SHIFTS_COLUMN_TIME_OUT, shift.GetTimeOut());
        shift_values.put(SHIFTS_COLUMN_DAY_OF_WEEK, shift.GetDayOfWeek());
        shift_values.put(SHIFTS_COLUMN_DAY_OF_MONTH, shift.GetDayOfMonth());
        shift_values.put(SHIFTS_COLUMN_MONTH, shift.GetMonth());
        shift_values.put(SHIFTS_COLUMN_YEAR, shift.GetYear());
        shift_values.put(SHIFTS_COLUMN_TIPS, shift.GetTips());

        long shift_id = db.insert(TABLE_SHIFTS,null, shift_values);

        db.close();

        return shift_id;

    }

    public long addJob(Job job){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues job_values = new ContentValues();

        job_values.put(JOB_COLUMN_TITLE, job.getTitle());
        job_values.put(JOB_COLUMN_EMPLOYER, job.getEmployer_id());
        job_values.put(JOB_COLUMN_PAY_RATE, job.getPay_rate());
        long job_id = db.insert(TABLE_JOB, null, job_values);

        db.close();

        return job_id;

    }

    public Long addWeek(Week week){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues week_values = new ContentValues();

        week_values.put(WEEKS_COLUMN_WEEK_NUMBER, week.getWeek_num());
        week_values.put(WEEKS_COLUMN_YEAR, week.getYear());

        Long week_id = db.insert(TABLE_WEEKS, null, week_values);

        db.close();

        return week_id;

    }

    public long addEmployer(Employer employer){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues employer_values = new ContentValues();

        employer_values.put(EMPLOYER_COLUMN_NAME, employer.getEmployer_name());
        employer_values.put(EMPLOYER_COLUMN_TYPE, employer.getEmployer_type());
        employer_values.put(EMPLOYER_COLUMN_PRICE_RANGE, employer.getPrice());
        employer_values.put(EMPLOYER_COLUMN_CITY, employer.getCity());
        employer_values.put(EMPLOYER_COLUMN_STATE, employer.getState());
        employer_values.put(EMPLOYER_COLUMN_COUNTRY, employer.getCountry());
        employer_values.put(EMPLOYER_COLUMN_ADDRESS, employer.getAddress());

        long employer_id = db.insert(TABLE_EMPLOYER, null, employer_values);

        db.close();

        return employer_id;
    }

    public Integer getEmployerID(String Employer){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor results = db.query(TABLE_EMPLOYER, new String[]{EMPLOYER_COLUMN_ID},
                EMPLOYER_COLUMN_NAME + "=?", new String[]{Employer}, null, null, null);

        db.close();
        results.moveToFirst();
        return results.getInt(results.getColumnIndex(EMPLOYER_COLUMN_ID));

    }

    public Long getWeekId(Integer week_of_year, Integer year){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor results = db.query(TABLE_WEEKS, new String[]{WEEKS_COLUMN_ID},
                WEEKS_COLUMN_WEEK_NUMBER + "=? and " + WEEKS_COLUMN_YEAR + " =?",
                new String[]{Integer.toString(week_of_year), Integer.toString(year)}, null, null,
                null);

        db.close();
        results.moveToFirst();
        return results.getLong(results.getColumnIndex(WEEKS_COLUMN_ID));

    }

    public Long getJobID(String job_title){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor results = db.query(TABLE_JOB, new String[]{JOB_COLUMN_ID},
                JOB_COLUMN_TITLE + "=?", new String[]{job_title}, null, null, null);
        db.close();
        results.moveToFirst();
        return results.getLong(results.getColumnIndex(JOB_COLUMN_ID));
    }

}
