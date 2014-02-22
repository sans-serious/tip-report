package com.uhhuh.tipreport;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by olibrooks on 6/21/13.
 */
public class MySQLiteHelper extends SQLiteOpenHelper{
    public static final String TABLE_TIPS = "tips";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TIP = "tip";
    public static final String COLUMN_HOURS = "hours";
    public static final String COLUMN_DAY = "day";
	public static final String COLUMN_MONTH = "month";
	public static final String COLUMN_DAY_NUM = "num";
	public static final String COLUMN_YEAR = "year";
    public static final String COLUMN_WEEK = "week";
  
	private static final String TABLE_WEEKS = "weeks";
	private static final String COLUMN_W_ID = "_id";
	private static final String COLUMN_WEEK_NUM = "week_num";
	private static final String COLUMN_WEEK_START = "week_start";

	private static final String DATABASE_NAME = "tips.db";
    private static int DATABASE_VERSION = 6;
	
    //Database Creation sql command
    public static final String DATABASE_CREATE_TIPS = "create table "
            + TABLE_TIPS + "(" + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_TIP
            + " text not null, " + COLUMN_HOURS
            + " text not null, " + COLUMN_DAY 
			+ " text not null, " + COLUMN_MONTH
        	+ " text not null, " + COLUMN_DAY_NUM
        	+ " text not null, " + COLUMN_YEAR 
			+ " text not null, " + COLUMN_WEEK
			+ " text not null);";
			
	public static final String DATABASE_CREATE_WEEK = "create table "
	        + TABLE_WEEKS + "(" + COLUMN_W_ID
			+ " integer primary key autoincrement, "
			+ COLUMN_WEEK_NUM + " text not null, "
			+ COLUMN_WEEK_START + " text not null);";

    public MySQLiteHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database){
        database.execSQL(DATABASE_CREATE_TIPS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                + newVersion + " which will destroy all old data" );
    }

}
