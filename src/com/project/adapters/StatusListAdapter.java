package com.project.adapters;

import java.util.ArrayList;

import com.project.easeyourburden.R;
import com.project.parsers.data.DebitsData;
import com.project.parsers.data.LoginData;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

public class StatusListAdapter extends BaseAdapter implements Filterable
{
    private ArrayList<DebitsData> statusDataList;
    private LayoutInflater inflater;
    
    public StatusListAdapter(Context context, ArrayList<DebitsData> statusDataList)
    {
	inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	this.statusDataList = statusDataList;
    }

    @Override
    public int getCount()
    {
	int noOfRows = 0;
	try
	{
	    noOfRows = statusDataList.size();
	}
	catch (Exception e)
	{
	    e.printStackTrace();
	}

	return noOfRows;
    }

    @Override
    public Object getItem(int position)
    {
	return statusDataList.get(position);
    }

    @Override
    public long getItemId(int position)
    {
	return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
	ViewHolder holder;
	DebitsData statusData = statusDataList.get(position);
	if(convertView == null)
	{
	    convertView = inflater.inflate(R.layout.status_row, null);
	    holder = new ViewHolder();
	    holder.creditorEmployeeName = (TextView) convertView.findViewById(R.id.creditor_employee_name);
	    holder.debitorEmployeeName = (TextView) convertView.findViewById(R.id.debitor_employee_name);
	    holder.amount = (TextView) convertView.findViewById(R.id.amount);
	    convertView.setTag(holder);
	}
	else
	{
	    holder = (ViewHolder) convertView.getTag();
	}
	if(statusData.getType().equals("Creditor"))
	{
	    holder.creditorEmployeeName.setText(statusData.getEmployeeName());
	    holder.debitorEmployeeName.setText(LoginData.getUserName());
	}
	else if(statusData.getType().equals("Debitor"))
	{
	    holder.debitorEmployeeName.setText(statusData.getEmployeeName());
	    holder.creditorEmployeeName.setText(LoginData.getUserName()); 
	}
	
	return convertView;
    }
    
    private class ViewHolder
    {
	TextView creditorEmployeeName;
	TextView debitorEmployeeName;
	TextView amount;
    }

    @Override
    public Filter getFilter()
    {
	// TODO Auto-generated method stub
	return null;
    }

}
