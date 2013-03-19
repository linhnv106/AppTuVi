package com.ditech.linhnv.apps.tuvi.adapter;

import com.ditech.linhnv.apps.tuvi.R;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class WeekTitleAdapter extends BaseAdapter{

	private final String[] mWeekTitle= { "CN","T2","T3","T4","T5","T6","T7"			
	};
	
	private LayoutInflater mLayoutInflater;
	
	
	public WeekTitleAdapter() {
		super();
	}
	public WeekTitleAdapter(FragmentActivity fragment) {
		super();
		mLayoutInflater=(LayoutInflater)fragment.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return mWeekTitle.length;
	}

	@Override
	public Object getItem(int arg0) {
		return mWeekTitle[arg0];
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		Holder holder;
		if(arg1==null){
			arg1=(LinearLayout)mLayoutInflater.inflate(R.layout.week_item, null);
			holder= new Holder();
			holder.title=(TextView)arg1.findViewById(R.id.week_title);
			arg1.setTag(holder);
		}else{
			holder=(Holder)arg1.getTag();
		}
		if(arg0==0||arg0==6){
			holder.title.setTextColor(Color.RED);
		}else{
			holder.title.setTextColor(Color.GREEN);
		}
		holder.title.setText(mWeekTitle[arg0]);
		
		return arg1;
	}
	class Holder{
		TextView title;
	}
}
