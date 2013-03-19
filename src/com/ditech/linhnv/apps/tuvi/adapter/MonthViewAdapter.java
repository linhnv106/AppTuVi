package com.ditech.linhnv.apps.tuvi.adapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;

import java.text.DateFormat;

import org.apache.commons.logging.Log;

import com.ditech.linhnv.apps.tuvi.R;
import com.ditech.linhnv.apps.tuvi.utils.LogUtils;
import com.ditech.linhnv.apps.tuvi.utils.LunisolarCalendar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MonthViewAdapter extends BaseAdapter{
	private Context mContext;
	private Calendar mCurrent;
	private GregorianCalendar mPreMonth;
	private GregorianCalendar mPreMonthMaxSet;
	private GregorianCalendar mSelecteDate;
	private DateFormat mDateFormat;
	
	private int mFirstDay;
	private int maxWeekNum;
	
	private int mMaxPreMonth;
	private int mCalMaxPre;
	private int mLastWeekDay;
	private int mDaysLeft;
	private String mItemValue;
	private String mCurrentDateString;
	private int mMonthLengh;
	private List<String> mDayStrings;
	private LayoutInflater mLayoutInflater;
	private View mPreviousView;
	private Resources mRes;
	public MonthViewAdapter(Context context ,GregorianCalendar calendar){
		mContext=context;
		mRes=mContext.getResources();
		mLayoutInflater=(LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mDayStrings = new ArrayList<String>();
		Locale.setDefault(Locale.US);
		mCurrent =calendar;
		mSelecteDate =(GregorianCalendar) GregorianCalendar.getInstance();
		mCurrent.set(GregorianCalendar.DAY_OF_MONTH, 1);
		mDateFormat= new SimpleDateFormat("yyyy-MM-dd",new Locale("vi"));
		mCurrentDateString=mDateFormat.format(mSelecteDate.getTime());
		refreshDate();
	}	
	@Override
	public int getCount() {
		return mDayStrings.size();
	}

	@Override
	public Object getItem(int arg0) {
		return mDayStrings.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parrent) {
//		LogUtils.infor("MonthViewAdapter", "Linhnv->>> getView");

		final Holder holder;
		if(convertView==null){
			convertView=mLayoutInflater.inflate(R.layout.calendar_item, null);
			holder = new Holder();
			holder.date=(TextView)convertView.findViewById(R.id.date);
			holder.lunarDate=(TextView)convertView.findViewById(R.id.lunarDate);
			convertView.setTag(holder);
		}else{
			holder=(Holder)convertView.getTag();
		}
		
		String[] separatedTime=mDayStrings.get(position).split("-");
		String gridvalue = separatedTime[2].replaceFirst("^0*", "");
		
		if ((Integer.parseInt(gridvalue) > 1) && (position < mFirstDay)) {
			// setting offdays to white color.
			holder.date.setTextColor(Color.WHITE);
			holder.date.setClickable(false);
			holder.date.setFocusable(false);
		} else if ((Integer.parseInt(gridvalue) < 7) && (position > 28)) {
			holder.date.setTextColor(Color.WHITE);
			holder.date.setClickable(false);
			holder.date.setFocusable(false);
		} else {
			// setting curent month's days in blue color.
			holder.date.setTextColor(Color.BLUE);
		}
		if (mDayStrings.get(position).equals(mCurrentDateString)) {
			LogUtils.infor("MonthViewAdapter", "Linhnv ->  mCurrentDateString :" +mCurrentDateString);
			
			setSelected(convertView);
			mPreviousView = convertView;
		} else {
			convertView.setBackgroundResource(R.drawable.list_item_background);
		}
		
		GregorianCalendar cal = new GregorianCalendar(Integer.parseInt(separatedTime[0]), Integer.parseInt(separatedTime[1]), Integer.parseInt(separatedTime[2]));
		try {
			LunisolarCalendar lunisolarCalendar = LunisolarCalendar.solar2lunar(new LunisolarCalendar(Integer.parseInt(separatedTime[0]), Integer.parseInt(separatedTime[1]), Integer.parseInt(separatedTime[2])));
			LogUtils.infor("MonthViewAdapter", "=>>>lunar date  :"+lunisolarCalendar.getDay());
			holder.lunarDate.setText(""+lunisolarCalendar.getDay());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		int day_of_week = cal.get(GregorianCalendar.DAY_OF_WEEK);
		int color=mRes.getColor(R.color.regular_day_text_color);
		
		/*switch(day_of_week){
			case GregorianCalendar.SUNDAY :
				if (((Integer.parseInt(gridvalue) > 1) && (position < mFirstDay)) ||(Integer.parseInt(gridvalue) < 7) && (position > 28)){
					color=mRes.getColor(R.color.sunday_other_month_text_color);
					holder.date.setClickable(false);
					holder.date.setFocusable(false);
				}else{
					color=mRes.getColor(R.color.sunday_text_color);
				}
				break;
			case GregorianCalendar.SATURDAY:
				if (((Integer.parseInt(gridvalue) > 1) && (position < mFirstDay)) ||(Integer.parseInt(gridvalue) < 7) && (position > 28)){
					color=mRes.getColor(R.color.saturday_text_other_month_color);
					holder.date.setClickable(false);
					holder.date.setFocusable(false);
				}else{
					color=mRes.getColor(R.color.saturday_text_color);
				}
				break;
			default :
				if (((Integer.parseInt(gridvalue) > 1) && (position < mFirstDay)) ||(Integer.parseInt(gridvalue) < 7) && (position > 28)){
					color=Color.WHITE;
					holder.date.setClickable(false);
					holder.date.setFocusable(false);
				}else{
					
				}

				break;
		}*/
//		holder.date.setTextColor(color);
//		LogUtils.debug("MonthViewAdapter", "date value : " +gridvalue);
		holder.date.setText(gridvalue);
		
		return convertView;
	}
	public View setSelected(View view) {
		if (mPreviousView != null) {
			mPreviousView.setBackgroundResource(R.drawable.list_item_background);
		}
		mPreviousView = view;
		view.setBackgroundResource(R.drawable.calendar_cel_selectl);
		return view;
	}
	
	public void refreshDate(){
		mDayStrings.clear();
		Locale.setDefault(new Locale("vi"));		
		mPreMonth=(GregorianCalendar)mCurrent.clone();
		
		mFirstDay=mCurrent.get(Calendar.DAY_OF_WEEK);
		maxWeekNum=mCurrent.getActualMaximum(Calendar.WEEK_OF_MONTH);
		LogUtils.infor("MonthViewAdapter", "number of week : " +maxWeekNum);
		LogUtils.debug("MonthViewAdapter", "month -> : " + mCurrent.get(Calendar.MONTH));
		LogUtils.debug("MonthViewAdapter", "month -> : " + mCurrent.getActualMaximum(Calendar.DAY_OF_MONTH));
		mMonthLengh=maxWeekNum*7;
		mMaxPreMonth=getMaxP();
		
		mCalMaxPre=mMaxPreMonth-(mFirstDay-1);
		
		mPreMonthMaxSet=(GregorianCalendar)mPreMonth.clone();
		mPreMonthMaxSet.set(GregorianCalendar.DAY_OF_MONTH,mCalMaxPre+1);
		
		for(int i=0;i<mMonthLengh;i++){
			mItemValue=mDateFormat.format(mPreMonthMaxSet.getTime());
			mPreMonthMaxSet.add(GregorianCalendar.DATE, 1);
			mDayStrings.add(mItemValue);
		}
		
	}
	
	private int getMaxP(){
		int maxP=0;
		if(mCurrent.get(GregorianCalendar.MONTH)==mCurrent.getActualMinimum(GregorianCalendar.MONTH)){
			mPreMonth.set((mCurrent.get(GregorianCalendar.YEAR) - 1),
					mCurrent.getActualMaximum(GregorianCalendar.MONTH), 1);
		}else{
			mPreMonth.set(GregorianCalendar.MONTH ,
					mCurrent.get(GregorianCalendar.MONTH)- 1);
		}
		maxP=mPreMonth.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
		return maxP;
	}
	
	
	class Holder{
		TextView date;
		TextView lunarDate;
	}
}
