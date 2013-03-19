package com.ditech.linhnv.apps.tuvi.adapter;

import java.util.Calendar;
import java.util.Locale;
import java.util.Random;

import com.ditech.linhnv.apps.tuvi.R;
import com.ditech.linhnv.apps.tuvi.activity.MainActivity.Page;
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
import android.widget.LinearLayout;
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
	private Page[] mPages = null;
	ViewPager mContainer;
	
	public Page[] getmPages() {
		return mPages;
	}


	public void setmPages(Page[] mPages) {
		this.mPages = mPages;
	}


	public DatesPagerAdapter() {
		super();
	}

	
	public DatesPagerAdapter(FragmentActivity fragment,Page[] pages) {
		super();
		mCurrent=Calendar.getInstance(new Locale("vi"));
		this.mPages=pages;
		mInflater=(LayoutInflater)fragment.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}


	@Override
	public int getItemPosition(Object object) {
		return POSITION_NONE;
	}


	@Override
	public int getCount() {
		return this.mPages.length;
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0==arg1;
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		LogUtils.debug("instantiateItem", " position: "+position);	
//		mContainer=(ViewPager)container;
		LinearLayout view = mPages[position].getPageView();
		
		
		String date=String.valueOf(mCurrent.get(Calendar.DATE));
		
		switch(position){
		case 0:
			
			date=String.valueOf(mCurrent.get(Calendar.DATE)-1);
			mPages[position].setDateText(date);
			break;
		case 1:
			date=String.valueOf(mCurrent.get(Calendar.DATE));
			mPages[position].setDateText(date);
			break;
		case 2:
			
			date=String.valueOf(mCurrent.get(Calendar.DATE)+1);
			mPages[position].setDateText(date);
			break;
		 default:
			 break;
		}
		
//		View v =(View)mInflater.inflate(R.layout.date_display_layout, container,false);
		((ViewPager)container).addView(view, 0);
//		temp=null;
		return view;
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
