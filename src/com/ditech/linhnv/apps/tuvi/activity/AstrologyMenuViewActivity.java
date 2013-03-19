package com.ditech.linhnv.apps.tuvi.activity;

import java.util.Calendar;

import com.ditech.linhnv.apps.tuvi.R;
import com.ditech.linhnv.apps.tuvi.fragments.AstrologyResultDialog;
import com.ditech.linhnv.apps.tuvi.utils.Constants;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;

public class AstrologyMenuViewActivity extends BaseActivity {
	private int mAstrologyId;
	private AstrologyResultDialog mResultDialog;
	private Calendar calendar;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		Bundle b = getIntent().getExtras();
		if (b != null) {
			int id = (int) b.getInt("astrology");
			switch (id) {
			case R.id.aries_imgBtn:
				mAstrologyId = 0;
				break;
			case R.id.taurus_imgBtn:
				mAstrologyId = 1;
				break;
			case R.id.gemini_imgBtn:
				mAstrologyId = 2;
				break;
			case R.id.cancer_imgBtn:
				mAstrologyId = 3;
				break;
			case R.id.leo_imgBtn:
				mAstrologyId = 4;
				break;
			case R.id.virgo_imgBtn:
				mAstrologyId = 5;
				break;
			case R.id.libra_imgBtn:
				mAstrologyId = 6;
				break;
			case R.id.scorpio_imgBtn:
				mAstrologyId = 7;
				break;
			case R.id.sagittarius_imgBtn:
				mAstrologyId = 8;
				break;
			case R.id.capricornus_imgBtn:
				mAstrologyId = 9;
				break;
			case R.id.aquarius_imgBtn:
				mAstrologyId = 10;
				break;
			case R.id.pisces_imgBtn:
				mAstrologyId = 11;
				break;
			default:
				break;
			}
		}
		setContentView(R.layout.astrology_menu_view_layout);
		initBottomMenu();
		initProperties();
		calendar = Calendar.getInstance();
		mResultDialog = new AstrologyResultDialog();
	}

	private void initProperties() {

	}

	/**
	 * on astrology menu selected
	 * 
	 * @param view
	 */
	public void onAstMenuClick(View view) {
		if (view == null) {
			return;
		}
		Intent intent = null;
		StringBuilder title = new StringBuilder();
		StringBuilder params = new StringBuilder("TV&c=" + mAstrologyId
				+ "&pr=");
		int d = 0;
		String date = "";
		int m = 0;
		String month = "";
		switch (view.getId()) {
		case R.id.astrology_menu_today:
			title.append("Chiêm tinh ngày ");

			d = calendar.get(Calendar.DATE);
			date = d < 10 ? "0" + d : "" + d;
			m = calendar.get(Calendar.MONTH) + 1;
			month = m < 10 ? "0" + m : "" + m;
			title.append(date + "/" + m);
			title.append(" cho " + Constants.ASTROLOGIES[mAstrologyId + 1]);

			params.append("D-");
			params.append(date + "/" + month + "/"
					+ calendar.get(Calendar.YEAR));
			params.append("&u=&p=");
			mResultDialog.initDialog(params.toString(), title.toString());
			mResultDialog.getData();
			mResultDialog.show(getSupportFragmentManager(),
					"AstrologyResultDialog");
			break;
		case R.id.astrology_menu_new_week:
			break;
		case R.id.astrology_menu_new_month:
			title.append("Chiêm tinh Tháng ");
			m = calendar.get(Calendar.MONTH) + 1 + 1;
			month = m < 10 ? "0" + m : "" + m;
			title.append(month);
			title.append(" cho " + Constants.ASTROLOGIES[mAstrologyId + 1]);

			params.append("M-");
			params.append(month + "/" + calendar.get(Calendar.YEAR));
			params.append("&u=&p=");
			mResultDialog.initDialog(params.toString(), title.toString());
			mResultDialog.getData();
			mResultDialog.show(getSupportFragmentManager(),
					"AstrologyResultDialog");
			break;
		case R.id.astrology_menu_new_year:
			title.append("Chiêm tinh Năm ");			
			title.append((calendar.get(Calendar.YEAR)+1));
			title.append(" cho " + Constants.ASTROLOGIES[mAstrologyId + 1]);
			params.append("Y-");
			params.append((calendar.get(Calendar.YEAR)+1));
			params.append("&u=&p=");
			mResultDialog.initDialog(params.toString(), title.toString());
			mResultDialog.getData();
			mResultDialog.show(getSupportFragmentManager(),
					"AstrologyResultDialog");
			break;
		case R.id.astrology_menu_random_date:
			intent = new Intent(this, AstrologyRandomDateActivity.class);
			intent.putExtra("astrology", mAstrologyId);
			overridePendingTransition(android.R.anim.slide_in_left,
					android.R.anim.slide_out_right);
			startActivity(intent);
			break;
		case R.id.astrology_menu_random_week:
			intent = new Intent(this, AstrologyWeekViewActivity.class);
			overridePendingTransition(android.R.anim.slide_in_left,
					android.R.anim.slide_out_right);
			startActivity(intent);
			break;
		default:
			break;
		}
	}
}
