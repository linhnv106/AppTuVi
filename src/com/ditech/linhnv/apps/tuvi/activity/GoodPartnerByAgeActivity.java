package com.ditech.linhnv.apps.tuvi.activity;

import java.lang.reflect.Field;

import com.ditech.linhnv.apps.tuvi.R;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

public class GoodPartnerByAgeActivity extends BaseActivity{
	private DatePicker mDatePicker01;
	private DatePicker mDatePicker02;

	private Button mBtnResult;
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.good_partner_by_age_layout);
		initBottomMenu();
		initProperties();
	}
	
	private void initProperties(){
		mDatePicker01=(DatePicker)findViewById(R.id.partner_by_age_datePicker01);
		mDatePicker02=(DatePicker)findViewById(R.id.partner_by_age_datePicker02);

		mBtnResult=(Button)findViewById(R.id.view_your_talent_btnResult);
		try {
			Field field1 = DatePicker.class.getDeclaredField("mDaySpinner");
			field1.setAccessible(true);
			View fieldInstance1 = (View) field1.get(mDatePicker01);
			fieldInstance1.setVisibility(View.GONE);
			Field field2 = DatePicker.class.getDeclaredField("mMonthSpinner");
			field2.setAccessible(true);
			View fieldInstance2 = (View) field2.get(mDatePicker01);
			fieldInstance2.setVisibility(View.GONE);
			
			
			Field field3 = DatePicker.class.getDeclaredField("mDaySpinner");
			field3.setAccessible(true);
			View fieldInstance3 = (View) field3.get(mDatePicker02);
			fieldInstance3.setVisibility(View.GONE);
			Field field4 = DatePicker.class.getDeclaredField("mMonthSpinner");
			field4.setAccessible(true);
			View fieldInstance4 = (View) field4.get(mDatePicker02);
			fieldInstance4.setVisibility(View.GONE);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
