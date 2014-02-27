package com.uhhuh.tipreport;

import android.*;
import android.content.*;
import android.view.*;
import android.widget.*;
import java.util.*;

public class shiftDetailAdapter extends BaseAdapter {
	private ArrayList<Shift> listData;
	
	private LayoutInflater layoutInflater;
	
	public shiftDetailAdapter(Context context, ArrayList<Shift> listData){
		this.listData = listData;
		layoutInflater = layoutInflater.from(context);
	}

    @Override
    public int getCount(){
        return listData.size();
    }


    @Override
    public Object getItem(int position){
        return listData.get(position);
    }
	
	@Override
	public long getItemId(int position){
		return position;
	}

    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder holder;
        if (convertView == null){
            convertView = layoutInflater.inflate(R.layout.shift_detail_row, null);
            holder = new ViewHolder();
			holder.tipsView = (TextView) convertView.findViewById(R.id.tips);
			holder.hoursView = (TextView) convertView.findViewById(R.id.hours);
            holder.dateView = (TextView) convertView.findViewById(R.id.date);
			holder.shiftOptions = (LinearLayout) convertView.findViewById(R.id.shift_options);
			convertView.setTag(holder);
		    }else{
			holder = (ViewHolder) convertView.getTag();
			}
		
		holder.tipsView.setText("$" + listData.get(position).getTips());
		holder.hoursView.setText(listData.get(position).getHours() + " hours");
        holder.dateView.setText(listData.get(position).getDate());
		holder.shiftOptions.setVisibility(View.GONE);
		
		return convertView;
    }
	
	static class ViewHolder{
		TextView tipsView;
		TextView hoursView;
        TextView dateView;
		LinearLayout shiftOptions;
	}
	
}
