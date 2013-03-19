package com.ditech.linhnv.apps.tuvi.activity;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import com.ditech.linhnv.apps.tuvi.R;
import com.ditech.linhnv.apps.tuvi.utils.LogUtils;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class AstrologyWeekViewActivity extends BaseActivity implements
		DatePicker.OnDateChangedListener {
	private DatePicker mDatePicker;
	private ListView mListView;
	private ListWeekAdapter mAdapter;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.astrology_week_view_layout);
		initBottomMenu();
		initProperties();
	}

	private void initProperties() {
		mDatePicker = (DatePicker) findViewById(R.id.weekView_date_picker);
		try {
			Field field2 = DatePicker.class.getDeclaredField("mDaySpinner");
			field2.setAccessible(true);
			View fieldInstance2 = (View) field2.get(mDatePicker);
			fieldInstance2.setVisibility(View.GONE);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Calendar calendar = Calendar.getInstance();
		mDatePicker
				.init(calendar.get(Calendar.YEAR),
						calendar.get(Calendar.MONTH),
						calendar.get(Calendar.DATE), this);

		mListView = (ListView) findViewById(R.id.weekView_listWeek);

		mAdapter = new ListWeekAdapter(this, calendar);

		mListView.setAdapter(mAdapter);

	}

	private class ListWeekAdapter extends BaseAdapter {
		private int weekNum;
		private Calendar calendar;
		private LayoutInflater layoutInflater;
		private int firstweek;
		private ArrayList<WeekEntry> weekData;

		public ListWeekAdapter(Context context, Calendar calendar) {
			this.calendar = (Calendar) calendar.clone();
			
			layoutInflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			weekData = new ArrayList<WeekEntry>();
			processData(this.calendar);

		}

		private synchronized void processData(Calendar calendar) {
			weekNum = calendar.getActualMaximum(Calendar.WEEK_OF_MONTH);
			if (weekData != null && !weekData.isEmpty()) {
				for (WeekEntry data : weekData) {
					data = null;
				}
			}
			weekData.clear();
			LogUtils.infor(
					"Linhnv",
					"today :"
							+ calendar.getDisplayName(Calendar.DAY_OF_WEEK,
									Calendar.SHORT, new Locale("us")));// Sun=1;
			int firstDay = 1;

			int lastDate = 0;
			for (int i = 0; i < weekNum; i++) {
				LogUtils.infor("Linhnv", "firstDay :" + firstDay);
				calendar.set(Calendar.DATE, firstDay);
				firstweek = calendar.get(Calendar.WEEK_OF_YEAR);
				LogUtils.infor("Linhnv",
						"week  :" + calendar.get(Calendar.WEEK_OF_YEAR));
				LogUtils.infor(
						"Linhnv",
						"Day of week :"
								+ calendar.getDisplayName(Calendar.DAY_OF_WEEK,
										Calendar.SHORT, new Locale("vi")));// Sun=1;
				lastDate = firstDay + Calendar.SATURDAY
						- calendar.get(Calendar.DAY_OF_WEEK) + 1;
				if(lastDate>31){
					lastDate=31;
				}
				LogUtils.infor("Linhnv", "last :" + lastDate);
				/*
				 * Calendar tmp =(Calendar) calendar.clone();
				 * tmp.add(Calendar.DATE, lastDate);
				 * lastDate=tmp.get(Calendar.DAY_OF_MONTH);
				 */
				WeekEntry entry1 = new WeekEntry(firstweek, firstDay, lastDate);
				entry1.setMonth(calendar.get(Calendar.MONTH)+1);
				weekData.add(entry1);
				firstDay = lastDate + 1;
			}

		}

		@Override
		public int getCount() {
			LogUtils.infor("Linhnv-AStrologyWeekView", "week num :" + weekNum);
			return weekNum;
		}

		@Override
		public Object getItem(int arg0) {
			return weekData.get(arg0);
		}

		@Override
		public long getItemId(int arg0) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parrent) {
			final Holder holder;
			if (convertView == null) {

				holder = new Holder();
				convertView = (LinearLayout) layoutInflater.inflate(
						R.layout.week_item, null);
				holder.weekTitle = (TextView) convertView
						.findViewById(R.id.week_title);
				convertView.setTag(holder);

			} else {
				holder = (Holder) convertView.getTag();
			}
			if(position<weekData.size()){
				holder.weekTitle.setText(weekData.get(position).toString());
			}
			convertView.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					LogUtils.debug("AstrologyWeekView", "on week selected :");
				}

			});
			return convertView;
		}

		class Holder {
			TextView weekTitle;
		}
	}

	private class WeekEntry {
		private int week;//
		private int firstDate;//
		private int lastDate;
		private int month;
		public WeekEntry() {
			super();
		}

		public WeekEntry(int week, int firstDate, int lastDate) {
			super();
			this.week = week;
			this.firstDate = firstDate;
			this.lastDate = lastDate;
			// LogUtils.infor("AstrologyWeekViewActivity", "list week :" +
			// week+" from "+firstDate +"to " +lastDate);
		}

		public int getWeek() {
			return week;
		}

		public void setWeek(int week) {
			this.week = week;
		}

		public int getFirstDate() {
			return firstDate;
		}

		public void setFirstDate(int firstDate) {
			this.firstDate = firstDate;
		}

		public int getLastDate() {
			return lastDate;
		}

		public void setLastDate(int lastDate) {
			this.lastDate = lastDate;
		}
		public void setMonth(int month){
			this.month=month;
		}
		@Override
		public String toString() {

			return "Tuan thu " + week + " tu :" + firstDate + "/"+month+" den "
					+ lastDate+ "/"+month;
		}

	}

	@Override
	public void onDateChanged(DatePicker view, int year, int monthOfYear,
			int dayOfMonth) {
		final Calendar calendar = Calendar.getInstance();
		calendar.set(year, monthOfYear, dayOfMonth);
		new AsyncTask<Void, Void, Void>() {

			@Override
			protected Void doInBackground(Void... params) {
				mAdapter.processData(calendar);

				return null;
			}

			@Override
			protected void onPostExecute(Void result) {
				mAdapter.notifyDataSetChanged();
			}

		}.execute();
	}

}
