package com.ditech.linhnv.apps.tuvi.activity;

import com.ditech.linhnv.apps.tuvi.R;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class FortunetellingByBloodTypeActivity extends BaseActivity{
	private Spinner mSpinner;
	private Button mBtnResult;
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.couple_telling_by_blood_type_layout);
		initBottomMenu();
		initProperties();
	}
	
	private void initProperties(){
		mSpinner=(Spinner)findViewById(R.id.blood_type_spin);
		mBtnResult=(Button)findViewById(R.id.couple_telling_by_blood_type_resultBtn);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
		        R.array.blood_type, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		mSpinner.setAdapter(adapter);
	}
	
}
