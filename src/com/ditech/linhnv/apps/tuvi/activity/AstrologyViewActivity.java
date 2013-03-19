package com.ditech.linhnv.apps.tuvi.activity;

import com.ditech.linhnv.apps.tuvi.R;
import com.ditech.linhnv.apps.tuvi.utils.LogUtils;
import com.ditech.linhnv.apps.tuvi.view.CircleView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

public class AstrologyViewActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.astrology_view_layout);
		initBottomMenu();
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
		LogUtils.infor("AstrologyViewActivity", "on btn click");
		
		Intent intent = new Intent(this, AstrologyMenuViewActivity.class);
		intent.putExtra("astrology", view.getId());
		overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);			
		startActivity(intent);
	}
}
