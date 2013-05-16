package com.project.easeyourburden;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class HomeActivity extends Activity
{
    private ActionBar actionBar;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_home);
	actionBar = getActionBar();
	actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
	createTabs();
    }

    private void createTabs()
    {
	Tab tab = actionBar.newTab().setText("Home").setTabListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
	getMenuInflater().inflate(R.menu.activity_home, menu);
	return true;
    }

    public void openActivity(View v)
    {
	Intent i = new Intent(getBaseContext(), MainActivity.class);
	startActivity(i);
	System.out.println();
    }
    
    public static class UITabListener<T extends Fragment> implements TabListener
    {
	private final Activity mActivity;
        private final String mTag;
        private final Class<T> mClass;
        private final Bundle mArgs;
        private Fragment mFragment;
        
        public UITabListener(Activity activity, String tag, Class<T> clz) {
            this(activity, tag, clz, null);
        }


	public UITabListener(Activity activity, String tag, Class<T> clz, Bundle args)
	{
	    mActivity = activity;
            mTag = tag;
            mClass = clz;
            mArgs = args;
	}


	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft)
	{
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft)
	{
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft)
	{
	    
	}
	
    }
    
}
