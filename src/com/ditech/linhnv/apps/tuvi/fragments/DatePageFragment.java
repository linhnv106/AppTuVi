package com.ditech.linhnv.apps.tuvi.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ditech.linhnv.apps.tuvi.R;

public class DatePageFragment extends Fragment{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
//		ViewGroup rootView =(ViewGroup)inflater.inflate(R.layout.date_display_layout, container,false);
		return inflater.inflate(R.layout.date_display_layout, container,false);
	}
	
}
