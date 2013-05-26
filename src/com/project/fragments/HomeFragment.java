package com.project.fragments;

import java.io.IOException;
import java.io.InputStream;
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
import android.util.Xml.Encoding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.project.easeyourburden.R;
import com.project.parsers.DebitCreditStatusParser;
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
	acounViewpager.setCurrentItem(0);
	return v;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        resources = getResources();
        activity = getActivity();
        
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
	    	    resId = R.layout.fragment_debits;
    		    break;
	    	case 1:
	    	    resId = R.layout.fragment_credits;
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
		debitCreditStatusParser = new DebitCreditStatusParser();
		Xml.parse(stream, Encoding.UTF_8, debitCreditStatusParser);
		
		
	    }
	    catch (Exception e) 
	    {
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
	    ArrayList<DebitsData> debitsDataList = debitCreditStatusParser.getDebitsList();
	    ListView debitsListview = (ListView) getActivity().findViewById(R.id.debits_listview);
	}
	
    }
    
}
