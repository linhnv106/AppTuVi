/**
 * @author linhnv
 * 
 * this base class for control  all basic functions of main activities
 * 
 */
package com.ditech.linhnv.apps.tuvi.activity;

import java.util.Locale;

import com.ditech.linhnv.apps.tuvi.R;
import com.ditech.linhnv.apps.tuvi.adapter.ListMenuAdapter;
import com.ditech.linhnv.apps.tuvi.adapter.MenusPagerAdapter;
import com.ditech.linhnv.apps.tuvi.utils.LogUtils;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;

public class BaseActivity extends FragmentActivity implements MenusPagerAdapter.OnMenuClickListener{
	private  PopupWindow mListMenu; 
	private int mMenuHeight;
	boolean mIsShowMenuList=false;
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		Locale.setDefault(new Locale("vi"));
		
	}
	/**
	 * init bottom menu
	 */
	protected void initBottomMenu(){
		ViewPager menuPager=(ViewPager)findViewById(R.id.menuPager);
		MenusPagerAdapter menusPagerAdapter = new MenusPagerAdapter(this,this);
		menuPager.setAdapter(menusPagerAdapter);
		menuPager.setCurrentItem(0);
		initListMenu();
		final Button btnMainMenu=(Button)findViewById(R.id.btn_menu_main);
		
		btnMainMenu.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(mListMenu!=null){					
//					mListMenu.showAsDropDown(btnMainMenu, 10,-800);					
					displayListMenu(btnMainMenu);
				}
			}
		});
	}
	
	private void initListMenu(){
		LayoutInflater layoutInflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);  
	    View popupView = layoutInflater.inflate(R.layout.float_menu, null);  
	    mListMenu = new PopupWindow(popupView,LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
	    mListMenu.setBackgroundDrawable(new BitmapDrawable());
	    mListMenu.setOutsideTouchable(true);
	    mListMenu.setFocusable(true);
	    mListMenu.setOnDismissListener(new OnDismissListener() {
			
			@Override
			public void onDismiss() {
				mIsShowMenuList=false;
			}
		});
	    ListView menuList=(ListView)popupView.findViewById(R.id.menu_lv);
	    ListMenuAdapter adapter= new ListMenuAdapter(this, 0,this);
	    
	    menuList.setAdapter(adapter);
	   
		float x =getResources().getDimension(R.dimen.main_menu_height);
		
//		 menuHeight = (int) (x * logicalDensity + 0.5);//hard code
		mMenuHeight=(int)x;
	    
	}
	private void displayListMenu(final View parrent){
		if(!mIsShowMenuList){
			mListMenu.showAtLocation(parrent, Gravity.LEFT | Gravity.BOTTOM, 0, mMenuHeight);
			mIsShowMenuList=true;
		}else{
			mIsShowMenuList=false;
			mListMenu.dismiss();
		}
	}
	@Override
	public void onMenuSelected(int id) {
		LogUtils.infor("BaseActivity", "onMenu Selected : " + id);
		Intent intent =null;
		if(mIsShowMenuList){
			mIsShowMenuList=false;
			mListMenu.dismiss();
		}
		switch(id){
		case 0://Bói bài 
			
			break;
		case 1://Lịch vạn sự ngày
			intent = new Intent(this, MainActivity.class);	
			break;
		case 2://Chiêm Tinh
			intent = new Intent(this, AstrologyViewActivity.class);	
			break;
		case 3://Lịch vạn sự Theo tuổi
			intent = new Intent(this, AgeCalendarViewActivity.class);	
			break;
		case 4://boi ten
			intent = new Intent(this, FortunetellingByNameActivity.class);			
			break;
		case 5://Lịch Tháng
			intent = new Intent(this, MonthViewActivity.class);	
			break;
		case 6://Tình duyên
			intent=new Intent(this, LoveFortunetellingMenuActivity.class);
			break;
		case 7://Khám phá Ngày Sinh
			intent=new Intent(this, BirthdayDiscoveryMenuActivity.class);
			break;
		case 8://Bói hình dáng
			break;
		case 9://Xem Điềm Báo
			break;
		case 10://Xem phong thủy
			break;
		case 11://Trắc nghiệm Tình Yêu 
			break;
		default :
			break;
		}
		if(intent!=null){
			overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
			startActivity(intent);
		}
	}
}
