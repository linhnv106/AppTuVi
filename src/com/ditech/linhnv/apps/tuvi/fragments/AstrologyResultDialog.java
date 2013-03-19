package com.ditech.linhnv.apps.tuvi.fragments;

import org.json.JSONException;
import org.json.JSONObject;

import com.ditech.linhnv.apps.tuvi.R;
import com.ditech.linhnv.apps.tuvi.tasks.GetDataResultTask;
import com.ditech.linhnv.apps.tuvi.tasks.GetDataResultTask.GetDataResultTaskCallBack;
import com.ditech.linhnv.apps.tuvi.utils.LogUtils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class AstrologyResultDialog extends DialogFragment implements GetDataResultTaskCallBack{
	private String params;
	private TextView mResultView;
	private ProgressBar mProgressBar;
	private String dialogTitle;
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		
		LayoutInflater inflater =getActivity().getLayoutInflater();
		RelativeLayout layout=(RelativeLayout)inflater.inflate(R.layout.astrology_result_dialog, null);	
		mResultView=(TextView)layout.findViewById(R.id.astrology_result_txt);
		mProgressBar=(ProgressBar)layout.findViewById(R.id.astrology_result_progressBar);
		mProgressBar.setVisibility(View.VISIBLE);
		mResultView.setVisibility(View.GONE);
		TextView titleView=(TextView)layout.findViewById(R.id.astrology_result_title);
		titleView.setText(dialogTitle);		
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setView(layout);
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dismiss();
			}
		});
		
		return builder.create();
	}
	
	public void initDialog(String params,String title){
		this.params=params;
		dialogTitle=title;
	}
	public void getData(){
		GetDataResultTask task = new GetDataResultTask();
		task.setCallBackListener(this);
		task.execute(params);
	}

	@Override
	public void onCompleted(JSONObject result) {
		mProgressBar.setVisibility(View.GONE);
		mResultView.setVisibility(View.VISIBLE);
		
		try {
			String text = result.get("content").toString();
			mResultView.setText(text);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		LogUtils.infor("AstrologyResultDialog", "onCompleted :" +result.toString());
	}

	@Override
	public void onFailed() {
		LogUtils.infor("AstrologyResultDialog", "onFailed :");
		mProgressBar.setVisibility(View.GONE);
		mResultView.setVisibility(View.VISIBLE);
	}
	

}
