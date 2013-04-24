package com.project.easeyourburden;

import com.project.fragments.SignUP;

import android.os.Bundle;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.app.ActionBar;
import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class Login extends FragmentActivity
{
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private SignUP signUpFragment;
    private MenuItem signUPMenuItem;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_login);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
	getMenuInflater().inflate(R.menu.activity_login, menu);
	signUPMenuItem = menu.getItem(0);
	return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
	switch (item.getItemId()) {
	
	    case android.R.id.home:
		View v = findViewById(R.id.loginLayout);
		v.setVisibility(View.VISIBLE);
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(false);
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		fragmentTransaction.detach(signUpFragment);
		fragmentTransaction.commit();
		signUPMenuItem.setVisible(true);
		return true;
	    case R.id.sign_up:
		View v1 = findViewById(R.id.loginLayout);
		v1.setVisibility(View.GONE);
		ActionBar actionBar1 = getActionBar();
		actionBar1.setDisplayHomeAsUpEnabled(true);
		signUPMenuItem.setVisible(false);
		signUpFragment = new SignUP();
		FragmentTransaction fragmentTransaction2 = fragmentManager.beginTransaction();
		//fragmentTransaction2.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left);
		fragmentTransaction2.add(R.id.parent_layout, signUpFragment);
		fragmentTransaction2.commit();
		
		RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.parent_layout);
		AnimatorSet fromRightToLeft = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.anim_fragment_in);
		//set the view as target
		fromRightToLeft.setTarget(relativeLayout);
		//start the animation
		fromRightToLeft.start();
		
		
	        return true;
	    default:
	        return super.onOptionsItemSelected(item);
	    }
    }
}
