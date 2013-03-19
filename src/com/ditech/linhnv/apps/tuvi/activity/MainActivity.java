package com.ditech.linhnv.apps.tuvi.activity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import com.ditech.linhnv.apps.tuvi.R;
import com.ditech.linhnv.apps.tuvi.adapter.DatesPagerAdapter;
import com.ditech.linhnv.apps.tuvi.utils.Constants;
import com.ditech.linhnv.apps.tuvi.utils.LogUtils;
import com.ditech.linhnv.apps.tuvi.utils.LunisolarCalendar;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
/**
 * 
 * @author linhnv
 * this is the main activity for application
 */
public class MainActivity extends BaseActivity {
	private final String TAG="MainActivity";
	private ViewPager mDatePager;
	private DatesPagerAdapter mDatePagerAdapter;
	private LinearLayout mTopCalLayout;//top calendar layout
	private LinearLayout mBottomCalLayout;
	private Calendar mCalendar;//current date
	private ArrayList<Float> mPos;
	private int mCurrentFocused;
	private Timer mTimer;
    // page for predecessor, current, and successor
	Page[] mPages = new Page[3]; 

	public class Page{
		private LinearLayout pageView =(LinearLayout)getLayoutInflater().inflate(R.layout.date_display_layout, null);
		private TextView dateText=(TextView)pageView.findViewById(R.id.date_view);
		
		private int resourceId;
		
		
		
		public int getResourceId() {
			return resourceId;
		}

		public void setResourceId(int resourceId) {
			this.resourceId=resourceId;
			pageView.setBackgroundResource(this.resourceId)	;

		}
		public void createResourceId(){
			Random ran = new Random();
			int i =ran.nextInt(8);
			int idResource = R.drawable.pic1;
			switch(i){
			case 0:
				idResource = R.drawable.pic1;
				break;
			case 1:
				idResource = R.drawable.pic2;
				break;
			case 2:
				idResource = R.drawable.pic2;
				break;
			case 3:
				idResource = R.drawable.pic3;
				break;
			case 4:
				idResource = R.drawable.pic4;
				break;
			case 5:
				idResource = R.drawable.pic5;
				break;
			case 6:
				idResource = R.drawable.pic6;
				break;
			case 7:
				idResource = R.drawable.pic7;
				break;
			default :
				break;
			}
					
			setResourceId(idResource);
		}
		public Page() {
			super();
			createResourceId();
		}

		public LinearLayout getPageView() {
			return pageView;
		}
	
		public TextView getDateText() {
			return dateText;
		}
		public void setDateText(String text) {
			this.dateText.setText(text);
		}
		
		
		
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mPages[0] = new Page();
		mPages[1] = new Page();
		mPages[2] = new Page();
		mCalendar=Calendar.getInstance(new Locale("vi"));
		LogUtils.infor(TAG, "date : " + mCalendar.getDisplayName(Calendar.DAY_OF_WEEK,Calendar.LONG, new Locale("vi")));
		initBottomMenu();
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
		mBottomCalLayout=(LinearLayout)findViewById(R.id.bottom_calendar);
		mDatePager=(ViewPager)findViewById(R.id.datePager);
		mDatePagerAdapter = new DatesPagerAdapter(this,mPages);
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
				mCurrentFocused=arg0;
				LogUtils.error(TAG, "onPageSelected  :" +mCurrentFocused );
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				try{
					LogUtils.infor(TAG, "onPageScrolled  position :" +arg0);
					
					
//					mPos.add(arg1);
//					
//					if(mPos.size()>0){
//						if (arg0 == PAGE_NUMS-1
//                                & mPos.get(0) > mPos.get(mPos.size() - 1)
//                                & mIsScrolled == true) {
//							LogUtils.error(TAG,"<----- swipe <-----  right" );
//                            try {
//                            	mIsScrolled = false;
//                                mDatePager.setCurrentItem(1, false);
//                            } catch (Exception e) {
//                              LogUtils.error(TAG,"<----- swipe <-----  " + e.toString());
//                            }
//						}else if (arg0 == 0
//	                            & mPos.get(0) < mPos.get(mPos.size() - 1)
//	                            & mIsScrolled == true) {
//	                        try {
//	                            mIsScrolled = false;
//	                            mDatePager.setCurrentItem(PAGE_NUMS - 1,
//	                                    false);
//	                            LogUtils.error(TAG,"<----- swipe <-----  2" );
//	                        } catch (Exception e) {
//	                        	LogUtils.error(TAG,"<----- swipe <-----  " + e.toString());
//	                        }
//
//	                    } else if (arg0 == 0 & mPos.size() == 1
//	                            & mIsScrolled == true) {
//	                        try {
//	                        	mIsScrolled = false;
//	                            mDatePager.setCurrentItem(PAGE_NUMS - 1,
//	                                    false);
//	                            LogUtils.error(TAG,"<----- swipe <-----  3" );
//	                        } catch (Exception e) {
//	                        	LogUtils.error(TAG,"<----- swipe <-----  " + e.toString());
//	                        }
//
//	                    }
//
//					}
				}catch(Exception e){
					LogUtils.error(TAG, "DatePager Error :" +e.getMessage());
				}
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				if(arg0==ViewPager.SCROLL_STATE_IDLE){
					int resourceId=mPages[1].getResourceId();
					if(mCurrentFocused==0){
						LogUtils.debug(TAG, "go left");
						mCalendar.add(Calendar.DATE, -1);
						mDatePagerAdapter.setmCurrent(mCalendar);
						mDatePagerAdapter.notifyDataSetChanged();
						resourceId=mPages[0].getResourceId();
						mDatePagerAdapter.getmPages()[2].setResourceId(mDatePagerAdapter.getmPages()[1].getResourceId());
						mDatePagerAdapter.getmPages()[1].setResourceId(resourceId);
						mDatePagerAdapter.getmPages()[0].createResourceId();
					}else if(mCurrentFocused==2){
						LogUtils.debug(TAG, "go right");
						mCalendar.add(Calendar.DATE, +1);
						mDatePagerAdapter.setmCurrent(mCalendar);
						mDatePagerAdapter.notifyDataSetChanged();
						resourceId=mPages[2].getResourceId();
						mDatePagerAdapter.getmPages()[0].setResourceId(mDatePagerAdapter.getmPages()[1].getResourceId());
						mDatePagerAdapter.getmPages()[1].setResourceId(resourceId);
						mDatePagerAdapter.getmPages()[2].createResourceId();
						
					}
					//update calendar
					initTopCalendar(mCalendar);
					initBottomCalendar(mCalendar);
					
					mDatePager.setCurrentItem(1,false);
				    LogUtils.infor(TAG, "onPageScrollStateChanged  :" +mDatePager.getCurrentItem() );

				}
			}
		});
		
		initTopCalendar(mCalendar);
		initBottomCalendar(mCalendar);
		
		
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
/**
 * init bottom calendar-lunar calendar
 * @param calendar
 */
	private void initBottomCalendar(Calendar calendar){
		LogUtils.infor(TAG,"===>date: " +calendar.get(Calendar.DATE));
		LogUtils.infor(TAG,"===>month: " +calendar.get(Calendar.MONTH));
		LogUtils.infor(TAG,"===>year: " +calendar.get(Calendar.YEAR));
		TextView lunarName = (TextView)mBottomCalLayout.findViewById(R.id.lunar_name_txt);
		TextView lunarDate=(TextView)mBottomCalLayout.findViewById(R.id.lunar_date_text);
		TextView lunarMonth=(TextView)mBottomCalLayout.findViewById(R.id.lunar_month_text);
		TextView goodHoursTxt=(TextView)mBottomCalLayout.findViewById(R.id.lunar_date_good_hours_txt);
		final TextView lunarTimeName=(TextView)mBottomCalLayout.findViewById(R.id.lunar_name_time_name);
		final TextView timeTV=(TextView)mBottomCalLayout.findViewById(R.id.lunar_name_time);
		try {
		LunisolarCalendar lunarCal=LunisolarCalendar.solar2lunar(new LunisolarCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH)+1, calendar.get(Calendar.DATE)));
		String name=""+lunarCal.getLunarName();		
		lunarName.setText(name);
		String date=""+lunarCal.getDay();
	
		lunarDate.setText(date);
		
		String month=""+lunarCal.getMonth()+"/"+lunarCal.getYear();
	
		lunarMonth.setText(month);
		String[] tmp=lunarCal.getDate_name().split(" ");
		String d=tmp[tmp.length-1];
		LogUtils.infor(TAG, ">>>>Ngay : " +d);
		int i=0;
		if(d.equalsIgnoreCase("Dần")||d.equalsIgnoreCase("Thân")){
			i=0;
		}else if(d.equalsIgnoreCase("Mão")||d.equalsIgnoreCase("Dậu")){
			i=1;
		}else if(d.equalsIgnoreCase("Thìn")||d.equalsIgnoreCase("Tuất")){
			i=2;
		}else if(d.equalsIgnoreCase("Tý")||d.equalsIgnoreCase("Ngọ")){
			i=3;
		} else if(d.equalsIgnoreCase("Tị")||d.equalsIgnoreCase("Hợi")){
			i=4;
		} else if(d.equalsIgnoreCase("Sửu")||d.equalsIgnoreCase("Mùi")){
			i=5;
		}
		float h =(float)calendar.get(Calendar.HOUR_OF_DAY)+(float)(calendar.get(Calendar.MINUTE))/60;
		lunarTimeName.setText(LunisolarCalendar.getTimeName(h));	
		goodHoursTxt.setText("Giờ Hoàng Đạo " +Constants.GOOD_HOURS[i]);
		
		
		
		} catch (Exception e) {
			e.printStackTrace();
		}
        final Calendar tmp=(Calendar)calendar.clone();
        final Calendar tmp2=Calendar.getInstance();
		TimerTask timerTask = new TimerTask() {
			int lastUpdate=-1;
			@Override
			public void run() {
				long mil= System.currentTimeMillis();
				tmp.setTimeInMillis(mil);
//				LogUtils.infor(TAG, "time:"+tmp.getTime());
				MainActivity.this.runOnUiThread(new Runnable() {
					
					@Override
					public void run() {
						int h=tmp.get(Calendar.HOUR_OF_DAY);
						int m=tmp.get(Calendar.MINUTE);
						int s=tmp.get(Calendar.SECOND);
						StringBuilder time =new StringBuilder( h<10?"0"+h:""+h).append(":");
						time.append( m<10?"0"+m:""+m).append(":");
						time.append( s<10?"0"+s:""+s);
						timeTV.setText(time);
						if(lastUpdate==-1){
							lastUpdate=h;
						}else{
							if(Math.abs(lastUpdate-h)==1){
								lastUpdate=h;
								float h2=(float)tmp2.get(Calendar.HOUR_OF_DAY)+(float)(tmp2.get(Calendar.MINUTE))/60;
								lunarTimeName.setText(LunisolarCalendar.getTimeName(h2));	
							}
						}
					
					}
				});
			}
		};
		mTimer= new Timer();
		mTimer.schedule(timerTask, 0, 1000);
	}

@Override
protected void onDestroy() {
	if(mTimer!=null){
		mTimer.cancel();
	}
	super.onDestroy();
}

}
