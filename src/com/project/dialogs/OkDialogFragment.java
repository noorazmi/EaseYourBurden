package com.project.dialogs;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class OkDialogFragment extends DialogFragment
{
    public static OkDialogFragment getInstance()
    {
	OkDialogFragment okDialogFragment = new OkDialogFragment();
	Bundle bundle = new Bundle();
	return okDialogFragment;
    }
    	

}
