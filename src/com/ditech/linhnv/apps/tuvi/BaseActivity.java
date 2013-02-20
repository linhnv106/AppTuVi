/**
 * @author linhnv
 * 
 * this base class for control  all basic functions of main activities
 * 
 */
package com.ditech.linhnv.apps.tuvi;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;

public class BaseActivity extends FragmentActivity{

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
	}

}
