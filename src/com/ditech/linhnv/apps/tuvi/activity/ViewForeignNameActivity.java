package com.ditech.linhnv.apps.tuvi.activity;

import java.util.Calendar;

import com.ditech.linhnv.apps.tuvi.R;
import com.ditech.linhnv.apps.tuvi.fragments.ViewForeignResultDialog;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

public class ViewForeignNameActivity extends BaseActivity{
	private DatePicker mDatePicker;
	private Button mBtnResult;
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.view_foreign_name_layout);
		initBottomMenu();
		initProperties();
	}
	
	private void initProperties(){
		mDatePicker=(DatePicker)findViewById(R.id.view_foreign_datePicker);
		mBtnResult=(Button)findViewById(R.id.view_foreign_btnResult);
		
		
		mBtnResult.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Calendar calendar=Calendar.getInstance();
				calendar.set(mDatePicker.getYear(), mDatePicker.getMonth(), mDatePicker.getDayOfMonth());
				displayResult(calendar);
			}
		});
	}
	
	private void displayResult(Calendar calendar){
		ViewForeignResultDialog resultDialog = new ViewForeignResultDialog(calendar);
		resultDialog.show(getSupportFragmentManager(), "ViewForeignResultDialog");
	}
}
