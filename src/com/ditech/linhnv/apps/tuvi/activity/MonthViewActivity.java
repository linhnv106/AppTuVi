package com.ditech.linhnv.apps.tuvi.activity;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import com.ditech.linhnv.apps.tuvi.R;
import com.ditech.linhnv.apps.tuvi.adapter.MonthViewPagerAdapter;
import com.ditech.linhnv.apps.tuvi.adapter.WeekTitleAdapter;
import com.ditech.linhnv.apps.tuvi.fragments.DateCalendarViewDialog;
import com.ditech.linhnv.apps.tuvi.utils.LogUtils;
import com.ditech.linhnv.apps.tuvi.utils.LunisolarCalendar;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MonthViewActivity extends BaseActivity implements MonthViewPagerAdapter.OnDateSelected {
	private final String LOG_TAG="MonthViewActivity";
	private ViewPager mMViewPager;
	private MonthViewPagerAdapter mAdapter ;
	private int mPageFocused;
	private ImageButton mBtnPre;//view pre month
	private ImageButton mBtnNext;//view next month
	private LinearLayout mTopCalLayout;//top calendar layout
	private Calendar mCalendar;//current date
	private DateCalendarViewDialog mCalendarViewDialog;
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.month_view);
		mCalendar=Calendar.getInstance(new Locale("vi"));
		initBottomMenu();
		initProperties();
		mCalendarViewDialog= new DateCalendarViewDialog();
	}
	
	private void initProperties(){
		mMViewPager=(ViewPager)findViewById(R.id.monthPager);
		mAdapter= new MonthViewPagerAdapter(this,this);
		mMViewPager.setAdapter(mAdapter);
		mMViewPager.setCurrentItem(1);
		
		initMonthTitle((GregorianCalendar)mCalendar);
		mMViewPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				mPageFocused=arg0;
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				if(arg0==ViewPager.SCROLL_STATE_IDLE){
					GregorianCalendar current= mAdapter.getmCurrent();
					
					if(mPageFocused==0){
						LogUtils.infor(LOG_TAG, "MonthViewActivity - go left");
						current.add(GregorianCalendar.MONTH, -1);
						mAdapter.setmCurrent(current);
						mAdapter.notifyDataSetChanged();
					}else if(mPageFocused==2){
						LogUtils.infor(LOG_TAG, "MonthViewActivity - go right");
						current.add(GregorianCalendar.MONTH, +1);
						mAdapter.setmCurrent(current);
						mAdapter.notifyDataSetChanged();
					}
					initMonthTitle(current);
					mMViewPager.setCurrentItem(1, false);
					
				}
			}
		});
		
		mBtnNext=(ImageButton)findViewById(R.id.btn_next_month);
		mBtnPre=(ImageButton)findViewById(R.id.btn_pre_month);
		
		
		mBtnNext.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				mMViewPager.setCurrentItem(2, true);
				/*GregorianCalendar current= mAdapter.getmCurrent();
				current.add(GregorianCalendar.MONTH, +1);
				mAdapter.setmCurrent(current);
				mAdapter.notifyDataSetChanged();
				initMonthTitle(current);
				mMViewPager.setCurrentItem(1, false);
				LogUtils.infor(LOG_TAG, "date : " + current.getDisplayName(Calendar.MONTH,Calendar.LONG, new Locale("vi")));
				*/
			}
		});
		mBtnPre.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				mMViewPager.setCurrentItem(0, true);
				/*GregorianCalendar current= mAdapter.getmCurrent();
				current.add(GregorianCalendar.MONTH, -1);
				mAdapter.setmCurrent(current);
				mAdapter.notifyDataSetChanged();
				mMViewPager.setCurrentItem(1, false);
				LogUtils.infor(LOG_TAG, "date : " + current.getDisplayName(Calendar.MONTH,Calendar.LONG, new Locale("vi")));
				initMonthTitle(current);*/
			}
		});
		
		GridView weekTitle=(GridView)findViewById(R.id.week_gridView);
		WeekTitleAdapter adapter= new WeekTitleAdapter(this);
		weekTitle.setAdapter(adapter);				
		mTopCalLayout=(LinearLayout)findViewById(R.id.top_calendar_monthView);	
		initTopCalendar(mCalendar);
	}
	
	
	private void initMonthTitle(final GregorianCalendar calendar){
		runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				TextView monthTitle=(TextView)findViewById(R.id.current_month_txt);				
				monthTitle.setText(calendar.getDisplayName(Calendar.MONTH,Calendar.LONG, new Locale("vi")).toUpperCase());				
			}
		});
		
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

	@Override
	public void OnOtherMonthDateSelected(boolean goToPre) {
		if(!goToPre){
			mMViewPager.setCurrentItem(2, true);
			/*GregorianCalendar current= mAdapter.getmCurrent();
			current.add(GregorianCalendar.MONTH, +1);
			mAdapter.setmCurrent(current);
			mAdapter.notifyDataSetChanged();
			initMonthTitle(current);
			mMViewPager.setCurrentItem(1, true);*/
		}else{
			mMViewPager.setCurrentItem(0, true);
			/*GregorianCalendar current= mAdapter.getmCurrent();
			current.add(GregorianCalendar.MONTH, -1);
			mAdapter.setmCurrent(current);
			mAdapter.notifyDataSetChanged();
			initMonthTitle(current);
			mMViewPager.setCurrentItem(1, true);*/
		}
	}

	@Override
	public void OnCurrentMonthDateSelected(GregorianCalendar selectedDated) {
		LogUtils.infor(LOG_TAG, "Date:" +selectedDated.getTime());
		mCalendarViewDialog.setCalendar(selectedDated);
		mCalendarViewDialog.getData();
		mCalendarViewDialog.show(getSupportFragmentManager(), "mCalendarViewDialog");
		
	}

}
