package com.ditech.linhnv.apps.tuvi.activity;

import java.lang.reflect.Field;

import com.ditech.linhnv.apps.tuvi.R;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

public class PhoneNumberCompatibilityActivity extends BaseActivity{
	private DatePicker mDatePicker;
	private Button mBtnResult;
	private EditText mCarNumEditText;
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.compatibility02_layout);
		initBottomMenu();
		initProperties();
	}
	
	private void initProperties(){
		mDatePicker=(DatePicker)findViewById(R.id.compatibility02_datePicker);
		mBtnResult=(Button)findViewById(R.id.compatibility02_btnResult);
		mCarNumEditText=(EditText)findViewById(R.id.phone_number_editText);
		try {
			Field field1 = DatePicker.class.getDeclaredField("mDaySpinner");
			field1.setAccessible(true);
			View fieldInstance1 = (View) field1.get(mDatePicker);
			fieldInstance1.setVisibility(View.GONE);
			Field field2 = DatePicker.class.getDeclaredField("mMonthSpinner");
			field2.setAccessible(true);
			View fieldInstance2 = (View) field2.get(mDatePicker);
			fieldInstance2.setVisibility(View.GONE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
