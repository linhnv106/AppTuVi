package com.ditech.linhnv.apps.tuvi.activity;

import com.ditech.linhnv.apps.tuvi.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LoveFortunetellingMenuActivity  extends BaseActivity{

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.love_fortunetelling_menu_layout);
		initBottomMenu();
	}
	
	
	public void onMenuClick(View view){
		if(view==null){
			return;
		}
		Intent intent = null;
		switch(view.getId()){
		case R.id.love_menu01:
			intent=new Intent(this, CoupleFortunetellingByAgeActivity.class);
			break;
		case R.id.love_menu02:
			
			break;
		case R.id.love_menu03:
			intent=new Intent(this, FortunetellingByBloodTypeActivity.class);
			break;
		default:
			break;
		}
		
		if(intent!=null){
			overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
			startActivity(intent);
		}
	}
}
