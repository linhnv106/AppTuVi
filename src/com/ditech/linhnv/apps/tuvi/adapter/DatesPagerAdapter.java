package com.ditech.linhnv.apps.tuvi.adapter;

import java.util.Calendar;

import com.ditech.linhnv.apps.tuvi.R;
import com.ditech.linhnv.apps.tuvi.fragments.DatePageFragment;
import com.ditech.linhnv.apps.tuvi.utils.LogUtils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
/**
 * 
 * @author linhnv
 * DatesPagerAdapter - the adapter manages each page for displaying date on main screen
 * 
 */

public class DatesPagerAdapter extends PagerAdapter{

	private final static int NUM_PAGES=3;//we need only 3 page for displaying date : current,previous,next 
	private LayoutInflater mInflater;
	
	private Calendar mCurrent;
	
	public DatesPagerAdapter() {
		super();
	}

	
	public DatesPagerAdapter(FragmentActivity fragment) {
		super();
		mInflater=(LayoutInflater)fragment.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}


	@Override
	public int getItemPosition(Object object) {
		return POSITION_NONE;
	}


	@Override
	public int getCount() {
		return NUM_PAGES;
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0==arg1;
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		LogUtils.debug("instantiateItem", " position: "+position);	
		View v =(View)mInflater.inflate(R.layout.date_display_layout, container,false);
		((ViewPager)container).addView(v, 0);
		return v;
	}


	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View)object);
	}


	public Calendar getmCurrent() {
		return mCurrent;
	}


	public void setmCurrent(Calendar mCurrent) {
		this.mCurrent = mCurrent;
	}
	
	
	
}
