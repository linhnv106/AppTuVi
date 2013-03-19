package com.ditech.linhnv.apps.tuvi.activity;

import java.lang.reflect.Field;

import com.ditech.linhnv.apps.tuvi.R;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

public class SecretOfBirthdayActivity extends BaseActivity{
	private DatePicker mDatePicker;
	private Button mBtnResult;
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.secret_of_birthday);
		initBottomMenu();
		initProperties();
	}
	
	private void initProperties(){
		mDatePicker=(DatePicker)findViewById(R.id.secret_of_birthday_datePicker);
		mBtnResult=(Button)findViewById(R.id.secret_of_birthday_btnResult);
		try {
			Field field2 = DatePicker.class.getDeclaredField("mMonthSpinner");
			field2.setAccessible(true);
			View fieldInstance2 = (View) field2.get(mDatePicker);
			fieldInstance2.setVisibility(View.GONE);
			Field field1 = DatePicker.class.getDeclaredField("mYearSpinner");
			field1.setAccessible(true);
			View fieldInstance1 = (View) field1.get(mDatePicker);
			fieldInstance1.setVisibility(View.GONE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
