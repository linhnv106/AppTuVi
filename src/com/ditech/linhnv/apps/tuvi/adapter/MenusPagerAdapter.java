package com.ditech.linhnv.apps.tuvi.adapter;

import com.ditech.linhnv.apps.tuvi.R;
import com.ditech.linhnv.apps.tuvi.utils.LogUtils;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
/**
 * this class to manage bottom menu bar items
 * @author linhnv
 *
 */
public class MenusPagerAdapter extends PagerAdapter{
	
	private LayoutInflater mInflater;
	private OnMenuClickListener mOnMenuClickListener;
	private FragmentActivity mFragment;
	private final static String[] MENUS={"Bói bài ","Lịch vạn sự ngày ","Chiêm Tinh","Lịch vạn sự Theo tuổi","Bói Tên ",
			"Lịch Tháng ","Tình duyên ","Khám phá Ngày Sinh ","Bói hình dáng ",
			"Xem Điềm Báo ","Xem phong thủy ","Trắc nghiệm Tình Yêu "
	};
	
	public MenusPagerAdapter() {
		super();
	}
	public MenusPagerAdapter(FragmentActivity fragment ,OnMenuClickListener onMenuClickListener) {
		super();
		mOnMenuClickListener=onMenuClickListener;
		mFragment=fragment;
		mInflater=(LayoutInflater)fragment.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return MENUS.length;
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0==arg1;
	}

	@Override
	public int getItemPosition(Object object) {
		return POSITION_NONE;
	}
	@Override
	public Object instantiateItem(ViewGroup container, final int position) {
		View view =mInflater.inflate(R.layout.menu_page, container,false);
		TextView menuTxt=(TextView)view.findViewById(R.id.menu_text);		
		menuTxt.setText(MENUS[position]);
		LogUtils.infor("MenusPagerAdapter", "=>menu:" +menuTxt.getText());
		((ViewPager)container).addView(view, 0);
		view.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				if(mOnMenuClickListener!=null){
					mOnMenuClickListener.onMenuSelected(position);
				}
			}
		});
		
		
		return view;
	}

	@Override
	public float getPageWidth(int position) {
		return 0.4f;
	}
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		// TODO Auto-generated method stub
		((ViewPager)container).removeView((View)object);
	}
	
	public interface OnMenuClickListener{
		void onMenuSelected(int  id);
	}
}
