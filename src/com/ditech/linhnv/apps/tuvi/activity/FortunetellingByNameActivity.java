package com.ditech.linhnv.apps.tuvi.activity;

import com.ditech.linhnv.apps.tuvi.R;
import com.ditech.linhnv.apps.tuvi.utils.LogUtils;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class FortunetellingByNameActivity extends BaseActivity{

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.fortunetelling_by_name_menu_layout);
		initBottomMenu();
	}
	
	private void initProperties(){
		
	}
	
	public void onMenuClick(View view){
		if(view==null){
			return;
		}
		Intent intent = null;
		switch(view.getId()){
		case R.id.by_name_menu01:
			break;
		case R.id.by_name_menu02:
			break;
		case R.id.by_name_menu03:
			break;
		case R.id.by_name_menu04:
			break;
		case R.id.by_name_menu05:
			break;
		case R.id.by_name_menu06:
			//xem ten nc ngoai
			LogUtils.infor("Fortunetelling by name", "view foreign name onclick");
			intent = new Intent(this, ViewForeignNameActivity.class);
			overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
			startActivity(intent);			
			break;
		case R.id.by_name_menu07:
			//xem ten giang ho
			intent = new Intent(this, ViewErrantNameActivity.class);
			overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
			startActivity(intent);	
			break;
		default:
			break;
		}
		
	}
}
