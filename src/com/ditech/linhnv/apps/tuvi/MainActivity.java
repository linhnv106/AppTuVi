package com.ditech.linhnv.apps.tuvi;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import com.ditech.linhnv.apps.tuvi.adapter.DatesPagerAdapter;
import com.ditech.linhnv.apps.tuvi.utils.LogUtils;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.widget.LinearLayout;
import android.widget.TextView;
/**
 * 
 * @author linhnv
 * this is the main activity for application
 */
public class MainActivity extends BaseActivity {
	private final String TAG="MainActivity";
	private final int PAGE_NUMS=3;
	private ViewPager mDatePager;
	private PagerAdapter mDatePagerAdapter;
	private LinearLayout mTopCalLayout;//top calendar layout
	private Calendar mCalendar;//current date
	private boolean mIsScrolled;
	private ArrayList<Float> mPos;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mCalendar=Calendar.getInstance(new Locale("vi"));
		LogUtils.infor(TAG, "date : " + mCalendar.getDisplayName(Calendar.DAY_OF_WEEK,Calendar.LONG, new Locale("vi")));
		initViewProperites();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/**
	 * init views properties
	 */
	private void initViewProperites(){
		//
		mTopCalLayout=(LinearLayout)findViewById(R.id.top_calendar);								
		mDatePager=(ViewPager)findViewById(R.id.datePager);
		mDatePagerAdapter = new DatesPagerAdapter(this);
		mDatePager.setAdapter(mDatePagerAdapter);
		
		/**
		 * make the infinite view pager
		 */
		mPos=new ArrayList<Float>();
		mDatePager.setCurrentItem(1);
		mDatePager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				mPos.clear();
				LogUtils.error(TAG, "onPageSelected  :" +mDatePager.getCurrentItem() );
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				try{
					LogUtils.infor(TAG, "onPageScrolled  position :" +arg0);
					mPos.add(arg1);
					
					if(mPos.size()>0){
						if (arg0 == PAGE_NUMS-1
                                & mPos.get(0) > mPos.get(mPos.size() - 1)
                                & mIsScrolled == true) {
							LogUtils.error(TAG,"<----- swipe <-----  right" );
                            try {
                            	mIsScrolled = false;
                                mDatePager.setCurrentItem(1, false);
                            } catch (Exception e) {
                              LogUtils.error(TAG,"<----- swipe <-----  " + e.toString());
                            }
						}else if (arg0 == 0
	                            & mPos.get(0) < mPos.get(mPos.size() - 1)
	                            & mIsScrolled == true) {
	                        try {
	                            mIsScrolled = false;
	                            mDatePager.setCurrentItem(PAGE_NUMS - 1,
	                                    false);
	                        } catch (Exception e) {
	                        	LogUtils.error(TAG,"<----- swipe <-----  " + e.toString());
	                        }

	                    } else if (arg0 == 0 & mPos.size() == 1
	                            & mIsScrolled == true) {
	                        try {
	                        	mIsScrolled = false;
	                            mDatePager.setCurrentItem(PAGE_NUMS - 1,
	                                    false);
	                        } catch (Exception e) {
	                        	LogUtils.error(TAG,"<----- swipe <-----  " + e.toString());
	                        }

	                    }

					}
				}catch(Exception e){
					LogUtils.error(TAG, "DatePager Error :" +e.getMessage());
				}
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				mIsScrolled=true;
				LogUtils.infor(TAG, "onPageScrollStateChanged  :" +mDatePager.getCurrentItem() );
			}
		});
		
		initTopCalendar(mCalendar);
		
		
	}
	/**
	 * init top calendar
	 * @param calendar
	 */
	private void initTopCalendar(Calendar calendar){
		if(mTopCalLayout==null){
			return;
		}
		TextView dateTxt =(TextView)mTopCalLayout.findViewById(R.id.top_cal_date);
		TextView monthTxt=(TextView)mTopCalLayout.findViewById(R.id.top_cal_month);
		TextView yearTxt=(TextView)mTopCalLayout.findViewById(R.id.top_cal_year);
		Locale locale = new Locale("vi"); 
		dateTxt.setText(calendar.getDisplayName(Calendar.DAY_OF_WEEK,Calendar.LONG,locale ).toUpperCase());
		monthTxt.setText(calendar.getDisplayName(Calendar.MONTH,Calendar.LONG, locale).toUpperCase());
		yearTxt.setText(""+calendar.get(Calendar.YEAR));

	}
	
}
