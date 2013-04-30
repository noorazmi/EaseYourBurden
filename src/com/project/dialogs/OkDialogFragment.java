package com.project.dialogs;

import android.R;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class OkDialogFragment extends DialogFragment
{
    private int type;
    public static OkDialogFragment getInstance(int type)
    {
	OkDialogFragment okDialogFragment = new OkDialogFragment();
	Bundle bundle = new Bundle();
	bundle.putInt("type", type);
	okDialogFragment.setArguments(bundle);
	return okDialogFragment;
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        type = getArguments().getInt("type");
        int style = type;
        int theme = type;
        setStyle(style, theme);
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
	View v = inflater.inflate(R.layout.select_dialog_item, container);
	return v;
    }
}
