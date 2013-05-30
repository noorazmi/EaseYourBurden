package com.project.easeyourburden;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.project.dialogs.OkDialogFragment;
import com.project.easeyourburden.interfaces.DialogOkButtonClickListener;
import com.project.parsers.data.LoginData;

public class Login extends Activity implements DialogOkButtonClickListener
{
    private MenuItem signUPMenuItem;
    private OkDialogFragment okDialogFragment;
    private FragmentManager fragmentManager = getFragmentManager();

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
	switch (item.getItemId())
	{

	case android.R.id.home:

		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(false);
		actionBar.setHomeButtonEnabled(false);
		signUPMenuItem.setVisible(true);

	    final LinearLayout loginLayout1 = (LinearLayout) findViewById(R.id.loginLayout);
	    loginLayout1.setVisibility(View.VISIBLE);
	    loginLayout1.animate().alpha(1f).setDuration(500).setListener(null);

	    LinearLayout fragmentParent = (LinearLayout) findViewById(R.id.signup_fragment_parent);

	    AnimatorSet fromRightToLeft = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.anim_fragment_out);
	    fromRightToLeft.setTarget(fragmentParent);
	    fromRightToLeft.start();

	    return true;

	case R.id.sign_up:
		ActionBar actionBar1 = getActionBar();
		actionBar1.setDisplayHomeAsUpEnabled(true);
		actionBar1.setHomeButtonEnabled(true);
		signUPMenuItem.setVisible(false);

	    final LinearLayout loginLayout11 = (LinearLayout) findViewById(R.id.loginLayout);
	    loginLayout11.animate().alpha(0f).setDuration(500).setListener(new AnimatorListenerAdapter()
	    {
		@Override
		public void onAnimationEnd(Animator animation)
		{
		    loginLayout11.setVisibility(View.GONE);
		}
	    });

	    LinearLayout fragmentParent1 = (LinearLayout) findViewById(R.id.signup_fragment_parent);
	    fragmentParent1.setVisibility(View.VISIBLE);

	    AnimatorSet fromRightToLeft1 = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.anim_fragment_in);
	    fromRightToLeft1.setTarget(fragmentParent1);
	    fromRightToLeft1.start();

	    return true;
	default:
	    return super.onOptionsItemSelected(item);
	}
    }

    public void onLoginButtonClick(View v)
    {
	EditText userName = (EditText) findViewById(R.id.username);
	EditText password = (EditText) findViewById(R.id.password);
	if(userName.getText().toString().trim().equals("") || password.getText().toString().trim().equals(""))
	{
	    //showOKDialog();
	    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
	    startActivity(intent);
	}
	else
	{
	    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
	    startActivity(intent);
	}
	
	LoginData.setUserName("Noor Alam");
    }

    public void showOKDialog()
    {

	// DialogFragment.show() will take care of adding the fragment
	// in a transaction. We also want to remove any currently showing
	// dialog, so make our own transaction and take care of that here.
	FragmentTransaction ft = fragmentManager.beginTransaction();
	Fragment prev = fragmentManager.findFragmentByTag("okDialog");
	if (prev != null)
	{
	    ft.remove(prev);
	}
	ft.addToBackStack(null);

	// Create and show the dialog.
	okDialogFragment = OkDialogFragment.getInstance();
	((OkDialogFragment) okDialogFragment).setDialogOkButtonClickListener(this);
	okDialogFragment.show(ft, "okDialog");

    }

    public void hideDialog()
    {
	okDialogFragment.dismiss();
    }

    @Override
    public void onDialogOkButtonClick()
    {
	hideDialog();
    }
    
    /**************************************************************************************************
     * Callback Metohds
     **************************************************************************************************/
    public void onSinupButtonClick(View v)
    {
	showOKDialog();
    }

}
