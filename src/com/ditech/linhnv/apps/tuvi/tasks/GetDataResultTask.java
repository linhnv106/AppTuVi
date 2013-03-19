package com.ditech.linhnv.apps.tuvi.tasks;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

import com.ditech.linhnv.apps.tuvi.utils.Constants;
import com.ditech.linhnv.apps.tuvi.utils.LogUtils;
import com.ditech.linhnv.apps.tuvi.utils.Utils;

import android.os.AsyncTask;

public class GetDataResultTask extends AsyncTask<String, Void, JSONObject>{
	private GetDataResultTaskCallBack mGetDataResultTaskCallBack;
	@Override
	protected JSONObject doInBackground(String... params) {
		JSONObject obj=null;
		InputStream is =null;
		try{
			String myUrl =Constants.URL+params[0];
			URL url = new URL(myUrl);
			
			LogUtils.infor("GetAstrologyResultTask", "FromURL: " +myUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setReadTimeout(10000 /* milliseconds */);
	        conn.setConnectTimeout(15000 /* milliseconds */);
	        conn.setRequestMethod("GET");
	        conn.setDoInput(true);
	        // Starts the query
	        conn.connect();
	        int response = conn.getResponseCode();
	        LogUtils.debug("GetAstrologyResultTask", "The response is: " + response);
	        is = conn.getInputStream();
	        obj=Utils.getJSonObject(is);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
			 if (is != null) {
				 try{
		            is.close();
				 }catch(IOException e){
					 e.printStackTrace();
				 }
				 
		        } 
		}
		return obj;
	}
	
	
	@Override
	protected void onPostExecute(JSONObject result) {
		if(mGetDataResultTaskCallBack!=null){
			if(result==null){
				mGetDataResultTaskCallBack.onFailed();
			}else{
				mGetDataResultTaskCallBack.onCompleted(result);
			}
		}
	}
	public void setCallBackListener(GetDataResultTaskCallBack callBack){
		mGetDataResultTaskCallBack=callBack;
	}

	public interface GetDataResultTaskCallBack{
		void onCompleted(JSONObject result);
		void onFailed();
	}
}
