package com.ditech.linhnv.apps.tuvi.activity;

import com.ditech.linhnv.apps.tuvi.R;
import com.ditech.linhnv.apps.tuvi.fragments.ViewErrantNameResultDialog;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ViewErrantNameActivity extends BaseActivity implements ViewErrantNameResultDialog.OnDialogDismiss{
	private EditText mNameInputText;
	private Button mBtnResult;
	private AlertDialog mErrorDialog;
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.fortunetelling_by_name);
		initBottomMenu();
		initProperties();
		
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		
		builder.setTitle("Error!");
		builder.setMessage("Nhập họ và tên của bạn có dấu cách");
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				if(mErrorDialog!=null){
					mErrorDialog.dismiss();
					runOnUiThread(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							mNameInputText.setText("");
							mNameInputText.setHint(getResources().getString(R.string.enter_your_name_hint));
						}
					});
					
				}
			}
		});
		mErrorDialog=builder.create();
		
	}
	
	private void initProperties(){
		mNameInputText =(EditText)findViewById(R.id.fortunetelling_name_edtext);
		mBtnResult=(Button)findViewById(R.id.fortunetelling_name_btnResult);
		mBtnResult.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				displayResult();
			}
		});
	}
	
	private void displayResult(){
		String txt =mNameInputText.getText().toString();
		if(txt==null||txt.trim().length()==0){
			if(mErrorDialog!=null){
				mErrorDialog.show();
			}
		}else {
		String[] data = txt.split(" ");
		if(data.length <2){
			if(mErrorDialog!=null){
				mErrorDialog.show();
			}
		}else{
			ViewErrantNameResultDialog resultDialog = new ViewErrantNameResultDialog(data,this);
			resultDialog.show(getSupportFragmentManager(), "ViewErrantNameResultDialog");
		}
		}
		
		
		
	}

	@Override
	protected void onResume() {
		mNameInputText.setText("");
		mNameInputText.setHint(getResources().getString(R.string.enter_your_name_hint));
		super.onResume();
	}

	@Override
	public void onDismiss() {
		runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				mNameInputText.setText("");
				mNameInputText.setHint(getResources().getString(R.string.enter_your_name_hint));
			}
		});		
	}
	
	
}	
