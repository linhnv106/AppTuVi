package com.ditech.linhnv.apps.tuvi.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.Locale;

import org.json.JSONException;
import org.json.JSONObject;

public class Utils {

	public static String getCalendarText(Calendar calendar) {
		LogUtils.infor("Utils", "getCalendarText of :" + calendar.getTime());

		StringBuilder result = new StringBuilder("Ngày");
		Locale local = new Locale("vi");
		result.append(calendar.get(Calendar.DATE));
		result.append(
				" "
						+ calendar.getDisplayName(Calendar.MONTH,
								Calendar.LONG, local)).append(" Năm ");
		result.append(calendar.get(Calendar.YEAR));
		;
		return result.toString();
	}

	public static JSONObject getJSonObject(InputStream in) {
		JSONObject result = null;

		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		try {
			result = new JSONObject(sb.toString());
		} catch (JSONException e) {
			LogUtils.error("getJSonObject", "getJSonObject :" + e.getMessage());
		} catch (Exception e) {
			LogUtils.error("getJSonObject", "getJSonObject :" + e.getMessage());
		}

		return result;
	}
}
