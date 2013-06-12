package com.project.fragments;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import android.app.Fragment;
import android.content.Context;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Xml;
import android.util.Xml.Encoding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.TextView;

import com.easeyourburden.views.SimpleViewPagerIndicator;
import com.project.adapters.DebitCreditListAdapter;
import com.project.adapters.StatusListAdapter;
import com.project.easeyourburden.R;
import com.project.parsers.DebitCreditStatusParser;
import com.project.parsers.data.DebitsCreditsStatusData;
import com.project.parsers.data.DebitsData;

public class HomeFragment extends Fragment implements OnQueryTextListener
{
    private Resources resources;
    private DebitCreditStatusParser debitCreditStatusParser;
    private View debitView,creditView,statusView;
    private ListView debitsListview;
    private ListView creditsListview;
    private ListView statusListview;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
	View v = inflater.inflate(R.layout.home_fragment, container, false);
	HomeFragmentPageAdapter adapter = new HomeFragmentPageAdapter();
	ViewPager acounViewpager = (ViewPager) v.findViewById(R.id.acount_viewpager);
	acounViewpager.setAdapter(adapter);
	acounViewpager.setOffscreenPageLimit(3);
	acounViewpager.setCurrentItem(1);
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
	    	    statusView = inflater.inflate(R.layout.status, null);
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
	
	@Override
	public CharSequence getPageTitle(int position)
	{
	    String title = null;
	    switch (position)
	    {
	    case 0:
		title=resources.getString(R.string.debit);
		break;
	    case 1:
		title=resources.getString(R.string.credit);
		break;
	    case 2:
		title=resources.getString(R.string.status);
		break;
	    }
	    return title;
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
	    
	    
	    /** Total amount  expended by others on you. **/
	    TextView v = (TextView) debitView.findViewById(R.id.description);
	    v.setText(resources.getString(R.string.total_expended_amount_by_others_on_you));
	    v = (TextView) debitView.findViewById(R.id.total_amount);
	    v.setText(debitsCreditsStatusData.getTotalDebitAmount()+"/-");
	    
	    debitsListview = (ListView) debitView.findViewById(R.id.debits_listview);
	    ArrayList<DebitsData> debitsDataList = debitsCreditsStatusData.getDebitsDataList();
	    DebitCreditListAdapter debitListAdapter = new DebitCreditListAdapter(getActivity(), debitsDataList);
	    debitsListview.setAdapter(debitListAdapter);
	    debitsListview.setCacheColorHint(0);
	    debitsListview.setTextFilterEnabled(true);
	    
	    
	    
	    /** Total amount expended by you on others. **/
	    v = (TextView) creditView.findViewById(R.id.description);
	    v.setText(resources.getString(R.string.total_expended_amount_by_you_on_others_));
	    v = (TextView) creditView.findViewById(R.id.total_amount);
	    v.setText(debitsCreditsStatusData.getTotalCreditAmount()+"/-");
	    
	    creditsListview = (ListView) creditView.findViewById(R.id.debits_listview);
	    ArrayList<DebitsData> creditsDataList = debitsCreditsStatusData.getCreditsDataList();
	    DebitCreditListAdapter creditListAdapter = new DebitCreditListAdapter(getActivity(), creditsDataList);
	    creditsListview.setAdapter(creditListAdapter);
	    creditsListview.setCacheColorHint(0);
	    //creditsListview.setTextFilterEnabled(true);
	    
	    
	    
	    /** Balance Status **/
	    statusListview = (ListView) statusView.findViewById(R.id.status_listview);
	    ArrayList<DebitsData> statusDataList = debitsCreditsStatusData.getStatusDataList();
	    StatusListAdapter statusListAdapter = new StatusListAdapter(getActivity(), statusDataList);
	    statusListview.setAdapter(statusListAdapter);
	    statusListview.setCacheColorHint(0);
	    //statusListview.setTextFilterEnabled(true);
	    
	}
	
    }

    @Override
    public boolean onQueryTextChange(String value)
    {
	System.out.println("value = "+value);
	if(TextUtils.isEmpty(value))
	{
	    debitsListview.clearTextFilter();
	    creditsListview.clearTextFilter();
	    //statusListview.clearTextFilter();
	}
	else
	{
	    debitsListview.setFilterText(value);
	    creditsListview.setFilterText(value);
	    //statusListview.setFilterText(value);
	}
	return true;
    }
    @Override
    public boolean onQueryTextSubmit(String query)
    {
	return false;
    }
    
}
