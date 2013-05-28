package com.project.fragments;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.app.Fragment;
import android.content.Context;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Xml;
import android.util.Xml.Encoding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.easeyourburden.views.SimpleViewPagerIndicator;
import com.project.adapters.DebitListAdapter;
import com.project.easeyourburden.R;
import com.project.parsers.DebitCreditStatusParser;
import com.project.parsers.data.DebitsCreditsStatusData;
import com.project.parsers.data.DebitsData;

public class HomeFragment extends Fragment
{
    private Resources resources;
    private DebitCreditStatusParser debitCreditStatusParser;
    private View debitView,creditView,statusView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
	View v = inflater.inflate(R.layout.home_fragment, container, false);
	HomeFragmentPageAdapter adapter = new HomeFragmentPageAdapter();
	ViewPager acounViewpager = (ViewPager) v.findViewById(R.id.acount_viewpager);
	acounViewpager.setAdapter(adapter);
	acounViewpager.setOffscreenPageLimit(3);
	acounViewpager.setCurrentItem(0);
	SimpleViewPagerIndicator pageIndicator = (SimpleViewPagerIndicator) v.findViewById(R.id.page_indicator);
	pageIndicator.setViewPager(acounViewpager);
	pageIndicator.notifyDataSetChanged();
	return v;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) 
    {
        super.onActivityCreated(savedInstanceState);
        resources = getResources();
        new LoadDebitsCreditsStatus().execute();
        
    }
    private class HomeFragmentPageAdapter extends PagerAdapter
    {

	@Override
	public int getCount()
	{
	    return 3;
	}

	public Object instantiateItem(View collection, int position)
	{
	    LayoutInflater inflater = (LayoutInflater) collection.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    int resId = 0;
	    View view = null;
	    System.out.println("position= "+position);
	    
	    
	    
	    
	    switch (position)
	    {
	    	case 0:
	    	   // resId = R.layout.debits_credits;
	    	    debitView = inflater.inflate(R.layout.debits_credits, null);
	    	    view = debitView;
    		    break;
	    	case 1:
	    	    //resId = R.layout.debits_credits;
	    	    creditView = inflater.inflate(R.layout.debits_credits, null);
	    	    view = creditView;
	    	    break;
	    	case 2:
    		    //resId = R.layout.fragment_status;
	    	    statusView = inflater.inflate(R.layout.fragment_status, null);
	    	    view = statusView;
    		    break;
	    }
	    
	    //View view = inflater.inflate(resId, null);
	    ((ViewPager) collection).addView(view, 0);
	    return view;
	}

	@Override
	public void destroyItem(View arg0, int arg1, Object arg2)
	{
	    ((ViewPager) arg0).removeView((View) arg2);
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1)
	{
	    return arg0 == ((View) arg1);
	}

	@Override
	public Parcelable saveState()
	{
	    return null;
	}
    }
    
    private class LoadDebitsCreditsStatus extends AsyncTask<Void, Void, Void>
    {

	@Override
	protected Void doInBackground(Void... params)
	{
	    InputStream stream = null;
	    try
	    {
		stream = resources.openRawResource(R.raw.employees);
		debitCreditStatusParser = new DebitCreditStatusParser();
		Xml.parse(stream, Encoding.UTF_8, debitCreditStatusParser);
		
		
	    }
	    catch (Exception e) 
	    {
		e.printStackTrace();
	    }
	    finally
	    {
		if(stream != null)
		{
		    try
		    {
			stream.close();
			stream = null;
		    }
		    catch (IOException e)
		    {
			e.printStackTrace();
		    }
		}
	    }
	    
	    return null;
	}
	@Override
	protected void onPostExecute(Void result)
	{
	    DebitsCreditsStatusData debitsCreditsStatusData = debitCreditStatusParser.getDebitsCreditsStatusData();
	    
	    TextView v = (TextView) debitView.findViewById(R.id.description);
	    v.setText(resources.getString(R.string.total_expended_amount_by_others_on_you));
	    v = (TextView) debitView.findViewById(R.id.total_amount);
	    v.setText(debitsCreditsStatusData.getTotalDebitAmount()+"/-");
	    
	    
	    
	    v = (TextView) creditView.findViewById(R.id.description);
	    v.setText(resources.getString(R.string.total_expended_amount_by_you_on_others_));
	    v = (TextView) creditView.findViewById(R.id.total_amount);
	    v.setText(debitsCreditsStatusData.getTotalCreditAmount()+"/-");
	    
	    
	    ListView debitsListview = (ListView) debitView.findViewById(R.id.debits_listview);
	    ListView creditsListview = (ListView) creditView.findViewById(R.id.debits_listview);
	    ListView statusListview = (ListView) statusView.findViewById(R.id.status_listview);
	    
	    
	    ArrayList<DebitsData> dataList = debitsCreditsStatusData.getDebitsDataList();
	    DebitListAdapter listAdapter = new DebitListAdapter(getActivity(), dataList);
	    debitsListview.setAdapter(listAdapter);
	    
	    dataList = debitsCreditsStatusData.getCreditsDataList();
	    listAdapter = new DebitListAdapter(getActivity(), dataList);
	    creditsListview.setAdapter(listAdapter);
	    
	    
	    
	    debitsListview.setCacheColorHint(0);
	    creditsListview.setCacheColorHint(0);
	}
	
    }
    
}
