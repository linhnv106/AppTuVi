package com.ditech.linhnv.apps.tuvi.activity;

import java.util.List;

import com.ditech.linhnv.apps.tuvi.R;
import com.ditech.linhnv.apps.tuvi.utils.LogUtils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class BirthdayDiscoveryMenuActivity extends BaseActivity {
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.birthday_discovery_layout);
		initBottomMenu();
		initProperties();
	}
	private void initProperties(){
		ListView listView=(ListView)findViewById(R.id.birthday_discovery_menu_lv);
		BirthdayMenuAdapter adapter = new BirthdayMenuAdapter(this, 1, getResources().getStringArray(R.array.birthday_menus));
		listView.setAdapter(adapter);
	}
	
	private void onBirthdayMenuSelected(int id){
		LogUtils.infor("BirthdayDiscoveryMenuActivity", "onBirthdayMenuSelected : " +id);
		Intent intent =null;
		switch(id){
		case 0:
			intent = new Intent(this, SecretOfBirthdayActivity.class);
			break;
		case 1:
			intent = new Intent(this, LoveFortunetellingByStarGroupsActivity.class);
			break;
		case 2:
			intent = new Intent(this, SecretOfStarGroupsActivity.class);
			break;
		case 3:
			intent = new Intent(this, HowToKeepYourLoveActivity.class);
			break;
		case 4:
			break;
		case 5:
			intent = new Intent(this, ViewYourTalentActivity.class);
			break;
		case 6:
			intent = new Intent(this, HoroscopeWholeLifeActivity.class);
			break;
		case 7:
			intent = new Intent(this, GoodPartnerByAgeActivity.class);
			break;
		case 8:
			intent = new Intent(this, CarNumberCompatibilityActivity.class);
			break;
		case 9:
			intent = new Intent(this, PhoneNumberCompatibilityActivity.class);
			break;
		default :
			break;
		}
		if(intent!=null){
			overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
			startActivity(intent);
		}
	}
	
	class BirthdayMenuAdapter extends ArrayAdapter<String>{
		
		public BirthdayMenuAdapter(Context context, int textViewResourceId,
				String[] objects) {
			super(context, textViewResourceId, objects);
			// TODO Auto-generated constructor stub
		}

		public BirthdayMenuAdapter(Context context, int resource,
				int textViewResourceId, List<String> objects) {
			super(context, resource, textViewResourceId, objects);
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			TextView textView;
			if(convertView==null){
				convertView=getLayoutInflater().inflate(R.layout.birthday_menu_item, null);
				textView=(TextView)convertView.findViewById(R.id.birthday_menu_item_text);
				convertView.setTag(textView);
			}else{
				textView=(TextView)convertView.getTag();
			}
			textView.setText(getItem(position));
			convertView.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					onBirthdayMenuSelected(position);
				}
			});
			return convertView;
		}
		
	}
}
