package com.project.easeyourburden;

import java.io.InputStreamReader;
import java.lang.reflect.Field;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewConfiguration;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.TextView;

import com.project.fragments.HomeFragment;
import com.project.fragments.ManagerFragment;

public class HomeActivity extends Activity
{
    private ActionBar actionBar;
    private Resources res;
    private SearchView searchView;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_home);
	res = getResources();
	actionBar = getActionBar();
	actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
	// actionBar.setDisplayOptions(0,ActionBar.DISPLAY_SHOW_TITLE);
	// getOverflowMenu();
	createTabs();

    }

    private void createTabs()
    {
	actionBar.addTab(actionBar.newTab().setTag("home").setText("Home").setTabListener(new UITabListener<HomeFragment>(this, "home", HomeFragment.class)));

	actionBar.addTab(actionBar.newTab().setTag("manager").setText(res.getString(R.string.manager)).setTabListener(new UITabListener<ManagerFragment>(this, "manager", ManagerFragment.class)));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
	searchView.setIconifiedByDefault(false);
	searchView.setSubmitButtonEnabled(true);
	//searchView.setQueryHint("Search Sender");
	searchView.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);

	// getMenuInflater().inflate(R.menu.activity_home, menu);

	menu.add(0, 1, 1, null).setIcon(android.R.drawable.ic_menu_search).setActionView(searchView).setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
	if (searchView != null)
	{
	    System.out.println("searchView11 = null");
	}
	return super.onCreateOptionsMenu(menu);
    }

    private void getOverflowMenu()
    {

	try
	{
	    ViewConfiguration config = ViewConfiguration.get(this);
	    Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
	    if (menuKeyField != null)
	    {
		menuKeyField.setAccessible(true);
		menuKeyField.setBoolean(config, false);
	    }
	}
	catch (Exception e)
	{
	    e.printStackTrace();
	}
    }

    // public void openActivity(View v)
    // {
    // Intent i = new Intent(getBaseContext(), MainActivity.class);
    // startActivity(i);
    // System.out.println();
    // }

    public class UITabListener<T extends Fragment> implements TabListener
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
	    System.out.println("onTabReselected " + tab.getTag());
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft)
	{
	    System.out.println("onTabSelected " + tab.getTag());
	    if (mFragment == null)
	    {
		mFragment = Fragment.instantiate(mActivity, mClass.getName());
		if (tab.getTag().equals("home"))
		{
		    searchView = new SearchView(getActionBar().getThemedContext());
		    searchView.setOnQueryTextListener((OnQueryTextListener) mFragment);
		    if (searchView == null)
		    {
			System.out.println("searchView = null");
		    }
		    else if (mFragment == null)
		    {
			System.out.println("mFragment = null");
		    }

		}
		ft.add(R.id.fragment_container, mFragment, mTag);
	    }
	    else
	    {
		ft.attach(mFragment);
	    }

	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft)
	{
	    System.out.println("onTabUnselected " + tab.getTag());
	    ft.detach(mFragment);
	}

    }

}
