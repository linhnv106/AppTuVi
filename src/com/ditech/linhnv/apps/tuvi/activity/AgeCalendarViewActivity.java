package com.ditech.linhnv.apps.tuvi.activity;

import java.util.Calendar;
import java.util.Locale;

import com.ditech.linhnv.apps.tuvi.R;
import com.ditech.linhnv.apps.tuvi.fragments.AstrologyResultDialog;
import com.ditech.linhnv.apps.tuvi.utils.Constants;
import com.ditech.linhnv.apps.tuvi.utils.LogUtils;
import com.ditech.linhnv.apps.tuvi.utils.LunisolarCalendar;
import com.ditech.linhnv.apps.tuvi.view.CircleView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

public class AgeCalendarViewActivity extends BaseActivity {
	private AstrologyResultDialog mResultDialog;
	private Calendar calendar;
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.age_calendar_view_layout);
		initBottomMenu();
		calendar=Calendar.getInstance();
		mResultDialog= new AstrologyResultDialog();
//		initProperties();
		
	}
	
	
	private void initProperties(){
//		FrameLayout container = (FrameLayout)findViewById(R.id.astrology_containerView);
		
		 int numberOfElements = 12;
         View[] elems = new View[numberOfElements];
       /*  ImageButton button = new ImageButton(this);
         button.setImageResource(R.drawable.bach_duog);*/
         
        
         for(int i=0;i<12;i++){
        	 ImageButton button = new ImageButton(this);
        	 button.setLayoutParams(new RelativeLayout.LayoutParams(
                     RelativeLayout.LayoutParams.WRAP_CONTENT,
                     RelativeLayout.LayoutParams.WRAP_CONTENT));
             button.setImageResource(R.drawable.bach_duog);
        	 elems[i]=button;
         }
         CircleView circleView = new CircleView(this, elems);
//         setContentView(circleView);
//       container.addView(circleView);
	
         }
	public void onBtnClick(View view){
		if(view==null){
			return;
		}
		LogUtils.infor("AgeCalendarViewActivity", "on btn click");
		int ageId=0;
		switch (view.getId()) {
		case R.id.mouse_imgBtn:
			ageId = 0;
			break;
		case R.id.buffalo_imgBtn:
			ageId = 1;
			break;
		case R.id.tiger_imgBtn:
			ageId = 2;
			break;
		case R.id.cat_imgBtn:
			ageId = 3;
			break;
		case R.id.dragon_imgBtn:
			ageId = 4;
			break;
		case R.id.snake_imgBtn:
			ageId = 5;
			break;
		case R.id.horse_imgBtn:
			ageId = 6;
			break;
		case R.id.goat_imgBtn:
			ageId = 7;
			break;
		case R.id.monkey_imgBtn:
			ageId = 8;
			break;
		case R.id.chicken_imgBtn:
			ageId = 9;
			break;
		case R.id.dog_imgBtn:
			ageId = 10;
			break;
		case R.id.pig_imgBtn:
			ageId = 11;
			break;
		default:
			break;
		}
		StringBuilder title = new StringBuilder();
		StringBuilder params = new StringBuilder("LVS&t=" + ageId
				+ "&pr=");
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
		mResultDialog.initDialog(params.toString(), title.toString());
		mResultDialog.getData();
		
		mResultDialog.show(getSupportFragmentManager(), "AgeCalendarViewResultDialog");
		
//		Intent intent = new Intent(this, AstrologyMenuViewActivity.class);
//		intent.putExtra("astrology", view.getId());
//		overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);			
//		startActivity(intent);
	}
}
