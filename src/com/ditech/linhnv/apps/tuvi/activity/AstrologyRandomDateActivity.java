package com.ditech.linhnv.apps.tuvi.activity;

import java.lang.reflect.Field;
import java.util.Calendar;

import com.ditech.linhnv.apps.tuvi.R;
import com.ditech.linhnv.apps.tuvi.fragments.AstrologyResultDialog;
import com.ditech.linhnv.apps.tuvi.utils.Constants;
import com.ditech.linhnv.apps.tuvi.utils.LogUtils;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

public class AstrologyRandomDateActivity extends BaseActivity{
	private final static String LOG_TAG=AstrologyRandomDateActivity.class.getSimpleName();
	private DatePicker mDatePicker;
	private Button mBtnViewResult;
	private int mAstrologyId;
	private AstrologyResultDialog mResultDialog;
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		Bundle b = getIntent().getExtras();
		if(b!=null){
			mAstrologyId=b.getInt("astrology");
		}
		setContentView(R.layout.astrology_random_date_layout);
		initBottomMenu();
		initProperties();
		mResultDialog=new AstrologyResultDialog();
	}
	
	private void initProperties(){
		mDatePicker=(DatePicker)findViewById(R.id.random_date_picker);
		
		
	}
	public void onBtnViewResultClick(View view){
		if(view==null){
			return;
		}
		if(view.getId()==R.id.astrology_random_date_result_btn){
			LogUtils.infor(LOG_TAG, "day :" +mDatePicker.getDayOfMonth());
			LogUtils.infor(LOG_TAG, "month :" +mDatePicker.getMonth());
			LogUtils.infor(LOG_TAG, "year :" +mDatePicker.getYear());
		}
		StringBuilder title = new StringBuilder();
		StringBuilder params = new StringBuilder("TV&c=" + mAstrologyId
				+ "&pr=");
		int d = 0;
		String date = "";
		int m = 0;
		String month = "";
		
		title.append("Chiêm tinh ngày ");

		d = mDatePicker.getDayOfMonth();
		date = d < 10 ? "0" + d : "" + d;
		m = mDatePicker.getMonth();
		month = m < 10 ? "0" + m : "" + m;
		title.append(date + "/" + m +"/"+mDatePicker.getYear());
		title.append(" cho " + Constants.ASTROLOGIES[mAstrologyId + 1]);

		params.append("D-");
		params.append(date + "/" + month + "/"
				+ mDatePicker.getYear());
		params.append("&u=&p=");
		mResultDialog.initDialog(params.toString(), title.toString());
		mResultDialog.getData();
		mResultDialog.show(getSupportFragmentManager(),
				"AstrologyResultDialog");
		
	}
}
