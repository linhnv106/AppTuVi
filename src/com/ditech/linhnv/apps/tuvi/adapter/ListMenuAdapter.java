package com.ditech.linhnv.apps.tuvi.adapter;

import com.ditech.linhnv.apps.tuvi.R;
import com.ditech.linhnv.apps.tuvi.adapter.MenusPagerAdapter.OnMenuClickListener;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ListMenuAdapter extends ArrayAdapter<String>{
	private LayoutInflater mInflater;
	private OnMenuClickListener mOnMenuClickListener;
	private final String[] MENUS={"Bói bài ","Lịch vạn sự ngày ","Chiêm Tinh","Lịch vạn sự Theo tuổi","Bói Tên ",
			"Lịch Tháng ","Tình duyên ","Khám phá Ngày Sinh ","Bói hình dáng ",
			"Xem Điềm Báo ","Xem phong thủy ","Trắc nghiệm Tình Yêu "
	};
	public ListMenuAdapter(Context context, int textViewResourceId ,OnMenuClickListener onMenuClickListener) {
		super(context, textViewResourceId);
		mOnMenuClickListener=onMenuClickListener;
		mInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	@Override
	public int getCount() {
		return MENUS.length;
	}
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		final Holder holder;
		if(convertView==null){
			convertView=(View)mInflater.inflate(R.layout.menu_item, null);
			holder = new Holder();
			holder.menuTxt=(TextView)convertView.findViewById(R.id.menu_item_text);
			convertView.setTag(holder);
		}else{
			holder=(Holder)convertView.getTag();
		}
		holder.menuTxt.setText(MENUS[position]);
		convertView.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				if(mOnMenuClickListener!=null){
					mOnMenuClickListener.onMenuSelected(position);
				}
			}
		});
		return convertView;
	}
	class Holder{
		TextView menuTxt;
	}
}
