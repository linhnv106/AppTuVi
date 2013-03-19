package com.ditech.linhnv.apps.tuvi.activity;

import com.ditech.linhnv.apps.tuvi.R;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class CoupleFortunetellingByAgeActivity extends BaseActivity{
	private Spinner mSpinner1;
	private Spinner mSpinner2;
	private Button mBtnResult;
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.couple_telling_by_age_layout);
		initBottomMenu();
		initPropeties();
	}
	
	
	private void initPropeties(){
		mSpinner1=(Spinner)findViewById(R.id.age_spin_01);
		mSpinner2=(Spinner)findViewById(R.id.age_spin_02);
		
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
		        R.array.ages_array_01, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		mSpinner1.setAdapter(adapter);
		ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
		        R.array.ages_array_02, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		mSpinner2.setAdapter(adapter2);
		
		mBtnResult=(Button)findViewById(R.id.couple_telling_by_age_resultBtn);
	}
}
