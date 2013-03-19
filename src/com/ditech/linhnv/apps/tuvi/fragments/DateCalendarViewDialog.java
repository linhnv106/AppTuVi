package com.ditech.linhnv.apps.tuvi.fragments;

import java.util.Calendar;
import java.util.Locale;

import org.json.JSONException;
import org.json.JSONObject;

import com.ditech.linhnv.apps.tuvi.R;
import com.ditech.linhnv.apps.tuvi.tasks.GetDataResultTask;
import com.ditech.linhnv.apps.tuvi.tasks.GetDataResultTask.GetDataResultTaskCallBack;
import com.ditech.linhnv.apps.tuvi.utils.LunisolarCalendar;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class DateCalendarViewDialog extends DialogFragment implements GetDataResultTaskCallBack{
	private ImageView mDateIcon;
	private TextView mDateText;
	private TextView mDateContentText;
	private String titleTxt;
	private StringBuilder params;
	private ProgressBar mProgressBar;
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		
		LayoutInflater inflater =getActivity().getLayoutInflater();
		RelativeLayout layout=(RelativeLayout)inflater.inflate(R.layout.date_calendar_view, null);
		
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setView(layout);
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dismiss();
			}
		});
		
		mDateIcon=(ImageView)layout.findViewById(R.id.date_view_icon);
		mDateText=(TextView)layout.findViewById(R.id.date_view_txt);
		mDateContentText=(TextView)layout.findViewById(R.id.date_view_content_txt);
		mProgressBar=(ProgressBar)layout.findViewById(R.id.date_view_content_proBar);
		mDateText.setText(titleTxt);
		mProgressBar.setVisibility(View.VISIBLE);
		mDateContentText.setVisibility(View.GONE);
		

		return builder.create();
	}
	
	
	public void setCalendar(Calendar calendar){
		StringBuilder title = new StringBuilder();
		params = new StringBuilder("LVN&pr="
				);
		int d = 0;
		String date = "";
		int m = 0;
		String month = "";
		Locale locale = new Locale("vi");
		
		title.append(calendar.getDisplayName(Calendar.DAY_OF_WEEK,Calendar.LONG,locale ));

		d = calendar.get(Calendar.DATE);
		date = d < 10 ? "0" + d : "" + d;
		m = calendar.get(Calendar.MONTH) + 1;
		month = m < 10 ? "0" + m : "" + m;
		title.append(" " +date + "/" + m +"/"+ calendar.get(Calendar.YEAR));
		title.append("");
		try {
		LunisolarCalendar lunarCal=LunisolarCalendar.solar2lunar(new LunisolarCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH)+1, calendar.get(Calendar.DATE)));
		String name=""+lunarCal.getLunarName();		
		title.append("\n" +name);
		
		} catch (Exception e) {
			e.printStackTrace();
		}

		params.append("");
		params.append(date + "/" + month + "/"
				+ calendar.get(Calendar.YEAR));
		params.append("&u=&p=");
		titleTxt=title.toString();
	}
	
	public void getData(){
		GetDataResultTask task = new GetDataResultTask();
		task.setCallBackListener(this);
		task.execute(params.toString());
	}

	@Override
	public void onCompleted(JSONObject result) {
		mProgressBar.setVisibility(View.GONE);
		mDateContentText.setVisibility(View.VISIBLE);
		try{
			String txt= result.getString("content");
			mDateContentText.setText(txt);
			
		}catch(JSONException e){
			e.printStackTrace();
		}
	}


	@Override
	public void onFailed() {
		String txt =getActivity().getResources().getString(R.string.get_data_failed);
		mProgressBar.setVisibility(View.GONE);
		mDateContentText.setVisibility(View.VISIBLE);
		mDateContentText.setText(txt);
	}
}
