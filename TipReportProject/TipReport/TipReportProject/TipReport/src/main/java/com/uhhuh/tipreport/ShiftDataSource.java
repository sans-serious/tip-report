package com.uhhuh.tipreport;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Set;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

/**
 * Created by olibrooks on 6/22/13.
 */
public class ShiftDataSource {

    private Context contexty;
    private SQLiteDatabase database;
    private MySQLiteHelper dbhelper;
    private String[] allColumns = { MySQLiteHelper.COLUMN_ID, MySQLiteHelper.COLUMN_TIP,
    MySQLiteHelper.COLUMN_HOURS, MySQLiteHelper.COLUMN_DAY, MySQLiteHelper.COLUMN_DAY_NUM,
	MySQLiteHelper.COLUMN_MONTH, MySQLiteHelper.COLUMN_YEAR,  MySQLiteHelper.COLUMN_WEEK};

    public ShiftDataSource(Context context){
		this.contexty = context;
        dbhelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbhelper.getWritableDatabase();
    }
    
	public List<Shift> getRecords(String[] column, String[] criteria){
		String q_column = "";
		int col_len = column.length;
		int col_index = 0;
		for(String value: column){
			col_index += 1;
			q_column += value;
			q_column += "=?";
			if(col_index != col_len){
				q_column += " AND ";
			}
		}
		
	
		
		List <Shift> tips = new ArrayList<Shift>();
		Cursor cursor = database.query(dbhelper.TABLE_TIPS, null, q_column,
		criteria, null, null, null);
		
		cursor.moveToFirst();
		if(cursor.getCount() > 0){
		
		    while(!cursor.isAfterLast()){
			    Shift tip = cursorToTip(cursor);
		    	tips.add(tip);
		    	cursor.moveToNext();
			}
		}
		cursor.close();
		return tips;
	}
	
    public void close(){
        dbhelper.close();
    }

    public Shift createTip(HashMap<String, String> user_input){
        ContentValues values = new ContentValues();
		
		Set<String> keys = user_input.keySet();
		for(String key: keys){
			values.put(key, user_input.get(key));
		}
		
        long insertId = database.insert(MySQLiteHelper.TABLE_TIPS, null, values);
        Cursor cursor = database.query(MySQLiteHelper.TABLE_TIPS, allColumns,
                MySQLiteHelper.COLUMN_ID + " = " + insertId, null, null, null, null);
        cursor.moveToFirst();
        Shift newTip = cursorToTip(cursor);
        cursor.close();
        return newTip;
    }

    public void deleteTip(Shift tip){
        long id = tip.getId();
        System.out.println("tip deleted with id: " + id);
        database.delete(MySQLiteHelper.TABLE_TIPS, MySQLiteHelper.COLUMN_ID + " = " +
        id, null);
    }

    public List<Shift> getAllShifts(){
        List<Shift> tips = new ArrayList<Shift>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_TIPS, allColumns, null, null,
                null, null, null);

        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            Shift shift = cursorToTip(cursor);
            tips.add(shift);
            cursor.moveToNext();
        }
        cursor.close();
        return tips;
    }

    private Shift cursorToTip(Cursor cursor){
        Shift tip = new Shift();
        tip.setId(cursor.getLong(0));
        tip.setTips(cursor.getString(1));
		tip.setHours(cursor.getString(2));
        tip.setDay(cursor.getString(3));
		tip.setMonth(cursor.getString(5));
		tip.setDayNum(cursor.getString(4));
		tip.setYear(cursor.getString(6));
		tip.setWeekOf(cursor.getString(7));
        return tip;

    }
}
