package com.project.dialogs;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.project.easeyourburden.Login;
import com.project.easeyourburden.R;
import com.project.easeyourburden.interfaces.DialogOkButtonClickListener;

public class OkDialogFragment extends DialogFragment
{
    public DialogOkButtonClickListener dialogOkButtonClickListener;
    
   

    public static OkDialogFragment getInstance()
    {
	OkDialogFragment okDialogFragment = new OkDialogFragment();
	return okDialogFragment;
    }
    
    public void setDialogOkButtonClickListener(DialogOkButtonClickListener dialogOkButtonClickListener)
    {
	this.dialogOkButtonClickListener = dialogOkButtonClickListener;
	
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Holo_Light_Dialog);
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
	View v = inflater.inflate(R.layout.ok_dialog_fragment, container,false);
	Button button = (Button)v.findViewById(R.id.ok_button);
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // When button is clicked, call up to owning activity.
               //((Login)getActivity()).hideDialog();
        	dialogOkButtonClickListener.onDialogOkButtonClick();
        	
            }
        });

        return v;
	
    }
    
}
