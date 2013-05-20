package com.project.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.easeyourburden.R;

public class HomeFragment extends Fragment
{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
	View v = inflater.inflate(R.layout.home_fragment, container, false);
	HomeFragmentPageAdapter adapter = new HomeFragmentPageAdapter();
	ViewPager myPager = (ViewPager) v.findViewById(R.id.myfivepanelpager);
	myPager.setAdapter(adapter);
	myPager.setCurrentItem(2);
	return v;
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
}
