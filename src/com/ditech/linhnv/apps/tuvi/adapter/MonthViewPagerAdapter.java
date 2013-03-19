package com.ditech.linhnv.apps.tuvi.adapter;

import java.util.GregorianCalendar;
import java.util.Locale;

import com.ditech.linhnv.apps.tuvi.R;
import com.ditech.linhnv.apps.tuvi.utils.LogUtils;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;

public class MonthViewPagerAdapter extends PagerAdapter{
	private final int PAGE_NUM=3;
	private LayoutInflater mLayoutInflater;
	private GregorianCalendar mCurrent;
	private FragmentActivity mFragmentActivity;
	private MonthPage[] mPages;
	private OnDateSelected mOnDateSelected;
	public MonthViewPagerAdapter(FragmentActivity fragment ,OnDateSelected onDateSelected) {
		super();
		mOnDateSelected=onDateSelected;
		mLayoutInflater=(LayoutInflater)fragment.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mFragmentActivity=fragment;
		mCurrent=(GregorianCalendar) GregorianCalendar.getInstance(Locale.US);
		mPages= new MonthPage[3];
		mPages[0]= new MonthPage();
		mPages[1]= new MonthPage();
		mPages[2]=new MonthPage();
	}

	public MonthViewPagerAdapter() {
		super();
	}

	@Override
	public int getCount() {
		return PAGE_NUM;
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0==arg1;
	}
	
	
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View)object);
	}

	@Override
	public int getItemPosition(Object object) {
		return POSITION_NONE;
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		LogUtils.infor("MonthViewPagerAdapter", "Linhnv->>> instantiateItem");
		LinearLayout view =mPages[position].getPageView();
		GregorianCalendar calendar = (GregorianCalendar)mCurrent.clone();
		switch(position){
		case 0:
			calendar.add(GregorianCalendar.MONTH, -1);
			mPages[position].setMonthView(calendar);
			break;
		case 1:
			mPages[position].setMonthView(calendar);
			break;
		case 2:
			calendar.add(GregorianCalendar.MONTH, +1);
			mPages[position].setMonthView(calendar);
			break;
		default:
			break;
		}
		

		((ViewPager)container).addView(view, 0);
		return view ;
	}
	

	public GregorianCalendar getmCurrent() {
		return mCurrent;
	}

	public void setmCurrent(GregorianCalendar mCurrent) {
		this.mCurrent = mCurrent;
	}


	class MonthPage{
		private GregorianCalendar currentMonth;
		private MonthViewAdapter adapter;
		LinearLayout pageView=(LinearLayout)mLayoutInflater.inflate(R.layout.month_view_page, null);
		GridView monthView=(GridView)pageView.findViewById(R.id.month_gridView);
				
		public LinearLayout getPageView() {
			return pageView;
		}

		public void setPageView(LinearLayout pageView) {
			this.pageView = pageView;
		}

		public void setMonthView(GregorianCalendar calendar){
			currentMonth=calendar;
			adapter = new MonthViewAdapter(mFragmentActivity, calendar);
			monthView.setAdapter(adapter);
			
			monthView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
//					(CalendarAdapter) arg0.getAdapter()).setSelected(v);
					String selectedGridDate =(String)( (MonthViewAdapter)arg0.getAdapter()).getItem(arg2);
					String[] separatedTime = selectedGridDate.split("-");
					String gridvalueString = separatedTime[2].replaceFirst("^0*",
							"");// taking last part of date. ie; 2 from 2012-12-02.
					int gridvalue = Integer.parseInt(gridvalueString);
					// navigate to next or previous month on clicking offdays.
					if ((gridvalue > 10) && (arg2 < 8)) {
						LogUtils.infor("MonthViewPagerAdapter", "go to previous month");
						if(mOnDateSelected!=null){
							mOnDateSelected.OnOtherMonthDateSelected(true);
						}
					} else if ((gridvalue < 7) && (arg2 > 28)) {
						LogUtils.infor("MonthViewPagerAdapter", "go to next month");
						if(mOnDateSelected!=null){
							mOnDateSelected.OnOtherMonthDateSelected(false);
						}
					}else{
						if(mOnDateSelected!=null){
							mOnDateSelected.OnCurrentMonthDateSelected(new GregorianCalendar(Integer.parseInt(separatedTime[0]), Integer.parseInt(separatedTime[1])-1, Integer.parseInt(separatedTime[2])));
						}
					}
				}
			});
		}
		
		protected void setNextMonth() {
			if (currentMonth.get(GregorianCalendar.MONTH) == currentMonth
					.getActualMaximum(GregorianCalendar.MONTH)) {
				currentMonth.set((currentMonth.get(GregorianCalendar.YEAR) + 1),
						currentMonth.getActualMinimum(GregorianCalendar.MONTH), 1);
			} else {
				currentMonth.set(GregorianCalendar.MONTH,
						currentMonth.get(GregorianCalendar.MONTH) + 1);
			}

		}

		protected void setPreviousMonth() {
			if (currentMonth.get(GregorianCalendar.MONTH) == currentMonth
					.getActualMinimum(GregorianCalendar.MONTH)) {
				currentMonth.set((currentMonth.get(GregorianCalendar.YEAR) - 1),
						currentMonth.getActualMaximum(GregorianCalendar.MONTH), 1);
			} else {
				currentMonth.set(GregorianCalendar.MONTH,
						currentMonth.get(GregorianCalendar.MONTH) - 1);
			}

		}
		
		protected void refreshCalendar() {
			adapter.refreshDate();
			adapter.notifyDataSetChanged();
		}
		
	}
	
	public interface OnDateSelected{
		void OnOtherMonthDateSelected(boolean goToPre);
		void OnCurrentMonthDateSelected(GregorianCalendar selectedDated);
	}
}
