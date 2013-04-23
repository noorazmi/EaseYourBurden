package com.project.easeyourburden;

import com.project.fragments.SignUP;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class Login extends FragmentActivity
{

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_login);
	ActionBar actionBar = getActionBar();
	actionBar.setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
	getMenuInflater().inflate(R.menu.activity_login, menu);
	return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
	System.out.println("item.getItemId() 111= "+item.getItemId());
	switch (item.getItemId()) {
	
	    case android.R.id.home:
		
		Toast.makeText(getApplicationContext(), "Home  called", Toast.LENGTH_LONG).show();
		return true;
	    case R.id.sign_up:
		System.out.println("item.getItemId() 222= "+item.getItemId());
		Toast.makeText(getApplicationContext(), item.getItemId(), Toast.LENGTH_LONG).show();
		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		SignUP fragment = new SignUP();
		fragmentTransaction.add(R.id.linearLayout1, fragment);
		fragmentTransaction.commit();
	        return true;
	    default:
	        return super.onOptionsItemSelected(item);
	    }
    }
}
