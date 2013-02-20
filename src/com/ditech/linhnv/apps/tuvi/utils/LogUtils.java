package com.ditech.linhnv.apps.tuvi.utils;

import android.util.Log;

public class LogUtils {
	private final static boolean isDebug=true;
	
	
	public static void debug(String tag,String msg){
		if(isDebug){
			Log.d(tag, msg);
		}
	}
	public static void error(String tag,String msg){
		if(isDebug){
			Log.e(tag, msg);
		}
	}
	public static void infor(String tag,String msg){
		if(isDebug){
			Log.i(tag, msg);
		}
	}
}
