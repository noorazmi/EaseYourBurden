package com.project.adapters;

import java.util.ArrayList;

import com.project.easeyourburden.R;
import com.project.parsers.data.DebitsData;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class DebitListAdapter extends BaseAdapter
{
    private ArrayList<DebitsData> debitsDataList;
    private LayoutInflater inflater;

    public DebitListAdapter(Context context, ArrayList<DebitsData> debitsDataList)
    {
	inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	this.debitsDataList = debitsDataList;
    }

    @Override
    public int getCount()
    {
	int noOfRows = 0;
	try
	{
	    noOfRows = debitsDataList.size();
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
	return debitsDataList.get(position);
    }

    @Override
    public long getItemId(int id)
    {
	return id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
	ViewHolder holder;
	DebitsData debitsData = debitsDataList.get(position);
	if(convertView == null)
	{
	    convertView = inflater.inflate(R.layout.debit_credit_row, null); 
	    holder = new ViewHolder();
	    holder.employeeId = (TextView) convertView.findViewById(R.id.employee_name);
	}
	return null;
    }

    private class ViewHolder
    {
	TextView employeeName;
	TextView employeeId;
	TextView amount;
    }

}
