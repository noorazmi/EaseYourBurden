package com.project.easeyourburden;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class HomeActivity extends Activity
{

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_home);
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
}
