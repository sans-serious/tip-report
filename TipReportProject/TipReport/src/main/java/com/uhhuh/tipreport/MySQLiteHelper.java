package com.uhhuh.tipreport;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


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

    public static final String TABLE_WEEKS = "weeks";
    public static final String WEEKS_COLUMN_ID = "_id";
    public static final String WEEKS_COLUMN_WEEK_NUMBER = "week_number";
    public static final String WEEKS_COLUMN_YEAR = "year";

    public static final String DATABASE_CREATE_EMPLOYER = "create table "
            + TABLE_EMPLOYER + "(" + EMPLOYER_COLUMN_ID
            + " integer primary key autoincrement, " + EMPLOYER_COLUMN_TYPE
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
            + " integer not null, foreign key(" +
            JOB_COLUMN_EMPLOYER + ") references " + TABLE_EMPLOYER
            + "(" +EMPLOYER_COLUMN_ID +"));";

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
            + " text not null, foreign key(" +
            SHIFTS_COLUMN_JOB_ID + ") references " + TABLE_JOB
            + "(" + JOB_COLUMN_ID + ") foreign key(" + SHIFTS_COLUMN_WEEK_ID +
            ") references " + TABLE_WEEKS + "(" + WEEKS_COLUMN_ID + "));";

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
    }

}
