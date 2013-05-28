package com.project.fragments;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

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
    private Activity activity;
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
        activity = getActivity();
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
	    System.out.println("position= "+position);
	    switch (position)
	    {
	    	case 0:
	    	    resId = R.layout.debits_credits;
    		    break;
	    	case 1:
	    	    resId = R.layout.debits_credits;
	    	    break;
	    	case 2:
    		    resId = R.layout.fragment_status;
    		    break;
	    }
	    View view = inflater.inflate(resId, null);
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
		BufferedReader r = new BufferedReader(new InputStreamReader(stream));
		StringBuilder total = new StringBuilder();
		String line;
		while ((line = r.readLine()) != null) {
		    total.append(line);
		}
		
		debitCreditStatusParser = new DebitCreditStatusParser();
		//Xml.parse(stream, Encoding.UTF_8, debitCreditStatusParser);
		String result = total.toString();
		System.out.println("result= "+result);
		Xml.parse(result, debitCreditStatusParser);
		
		
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
	    DebitsCreditsStatusData debitsCreditsStatusData = debitCreditStatusParser.DebitsCreditsStatusData();
	    ArrayList<DebitsData> debitsDataList = debitsCreditsStatusData.getDebitsDataList();
	    ListView debitsListview = (ListView) getActivity().findViewById(R.id.debits_listview);
	    DebitListAdapter debitListAdapter = new DebitListAdapter(getActivity(), debitsDataList);
	    debitsListview.setAdapter(debitListAdapter);
	    debitsListview.setCacheColorHint(0);
	}
	
    }
    
}
