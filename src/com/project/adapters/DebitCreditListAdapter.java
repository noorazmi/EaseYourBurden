package com.project.adapters;

import java.util.ArrayList;

import com.project.easeyourburden.R;
import com.project.parsers.data.DebitsData;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

public class DebitCreditListAdapter extends BaseAdapter implements Filterable
{
    private ArrayList<DebitsData> originalCopyOfDebitsDataList;
    private ArrayList<DebitsData> debitsDataList;
    private LayoutInflater inflater;
    ArrayList<String> mListItem; // any key of DebitsData Model Class

    public DebitCreditListAdapter(Context context, ArrayList<DebitsData> debitsDataList)
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
	    holder.employeeName = (TextView) convertView.findViewById(R.id.employee_name);
	    holder.amount =  (TextView) convertView.findViewById(R.id.amount);
	    convertView.setTag(holder);
	}
	else
	{
	    holder = (ViewHolder) convertView.getTag();
	}
	
	holder.employeeName.setText(debitsData.getEmployeeName());
	holder.amount.setText(debitsData.getAmount());
	return convertView;
    }

    private class ViewHolder
    {
	TextView employeeName;
	TextView amount;
    }

    @Override
    public Filter getFilter()
    {
	Filter filter = new Filter()
	{
	    
	    @SuppressWarnings("unchecked")
	    @Override
	    protected void publishResults(CharSequence constraint, FilterResults results)
	    {
		debitsDataList = (ArrayList<DebitsData>) results.values;
		notifyDataSetChanged();
	    }
	    
	    @Override
	    protected FilterResults performFiltering(CharSequence constraint)
	    {
		FilterResults results = new FilterResults();  
		ArrayList<DebitsData> filteredList = new ArrayList<DebitsData>();
		if(originalCopyOfDebitsDataList == null)
		{
		    originalCopyOfDebitsDataList = new ArrayList<DebitsData>(debitsDataList);
		}
		
		if(mListItem == null)
		{
                    mListItem = new ArrayList<String>();
                    for(DebitsData debitsData : originalCopyOfDebitsDataList)
                    {
                        mListItem.add(debitsData.getEmployeeName());
                    }
                }
		
		/**
                 * 
                 *  If constraint(CharSequence that is received) is null returns the originalCopyOfDebitsDataList(Original) values
                 *  else does the Filtering and returns filteredArrList(Filtered)  
                 *
                 **/
		
		if(TextUtils.isEmpty(constraint))
		{
		    results.count = originalCopyOfDebitsDataList.size();
		    results.values = originalCopyOfDebitsDataList;
		}
		else
		{
		    constraint = constraint.toString().toLowerCase();
		    for (int i = 0; i < mListItem.size(); i++)
		    {
			String data = mListItem.get(i);
			if (data.toLowerCase().startsWith(constraint.toString())) 
			{
                            filteredList.add(originalCopyOfDebitsDataList.get(i));
                        }
			
		    }
		    // set the Filtered result to return
                    results.count = filteredList.size();
                    results.values = filteredList;
		}
		return results;
	    }
	};
	return filter;
    }

}
