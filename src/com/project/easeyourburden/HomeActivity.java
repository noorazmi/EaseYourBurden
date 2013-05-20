package com.project.easeyourburden;

import java.lang.reflect.Field;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.ViewConfiguration;

import com.project.fragments.HomeFragment;
import com.project.fragments.ManagerFragment;

public class HomeActivity extends Activity
{
    private ActionBar actionBar;
    private Resources res;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_home);
	res = getResources();
	actionBar = getActionBar();
	actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
	//actionBar.setDisplayOptions(0,ActionBar.DISPLAY_SHOW_TITLE);
	//getOverflowMenu();
	createTabs();
	
    }

    private void createTabs()
    {
	actionBar.addTab(actionBar.newTab().setTag("home").setText("Home").setTabListener(new UITabListener<HomeFragment>(this, "simple", HomeFragment.class)));

	actionBar.addTab(actionBar.newTab().setTag("manager").setText(res.getString(R.string.manager)).setTabListener(new UITabListener<ManagerFragment>(this, "manager", ManagerFragment.class)));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
	getMenuInflater().inflate(R.menu.activity_home, menu);
	return true;
    }
    
    private void getOverflowMenu() {

	    try {
	       ViewConfiguration config = ViewConfiguration.get(this);
	       Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
	       if(menuKeyField != null) {
	           menuKeyField.setAccessible(true);
	           menuKeyField.setBoolean(config, false);
	       }
	   } catch (Exception e) {
	       e.printStackTrace();
	   }
	 }

    // public void openActivity(View v)
    // {
    // Intent i = new Intent(getBaseContext(), MainActivity.class);
    // startActivity(i);
    // System.out.println();
    // }

    public static class UITabListener<T extends Fragment> implements TabListener
    {
	private final Activity mActivity;
	private final String mTag;
	private final Class<T> mClass;
	private Fragment mFragment;

	public UITabListener(Activity activity, String tag, Class<T> clz)
	{
	    mActivity = activity;
	    mTag = tag;
	    mClass = clz;
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft)
	{
	    System.out.println("onTabReselected "+tab.getTag());
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft)
	{
	    System.out.println("onTabSelected "+tab.getTag());
	    if (mFragment == null)
	    {
		mFragment = Fragment.instantiate(mActivity, mClass.getName());
		ft.add(R.id.fragment_container,mFragment, mTag);
	    }
	    else
	    {
		ft.attach(mFragment);
	    }

	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft)
	{
	    System.out.println("onTabUnselected "+tab.getTag());
	    ft.detach(mFragment);
	}

    }

}
