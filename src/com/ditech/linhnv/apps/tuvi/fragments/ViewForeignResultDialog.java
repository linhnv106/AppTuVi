package com.ditech.linhnv.apps.tuvi.fragments;

import java.util.Calendar;

import com.ditech.linhnv.apps.tuvi.R;
import com.ditech.linhnv.apps.tuvi.utils.LogUtils;
import com.ditech.linhnv.apps.tuvi.utils.Utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ViewForeignResultDialog extends DialogFragment {
	private Calendar calendar;
	private StringBuilder chineseName = new StringBuilder(
			"Tên Trung Quốc của bạn là :");
	private StringBuilder koreanName = new StringBuilder(
			"Tên hàn quôc của bạn là :");
	private StringBuilder laosName = new StringBuilder(
			"Tên tiếng Lào của bạn là :");
	private String title;

	private final static String[] chineseLastNames = { "Liễu", "Đường", "Nhan",
			"Âu Dương", "Diệp", "Đông Phương", "Đỗ", "Lăng", "Hoa", "Mạc" };
	private final static String[] chineseMidNames = { "Lam", "Thiên", "Bích",
			"Vô", "Song", "Ngân", "Ngọc", "Kỳ", "Trúc", "", "Y", "Nhược" };
	private final static String[] chineseFirstNames = { "Lam", "Nguyệt",
			"Tuyết", "Thần", "Ninh", "Bình", "Lạc", "Doanh", "Thu", "Khuê",
			"Ca", "Thiên", "Tâm", "Hàn", "Y", "Điểm", "Song", "Dung", "Như",
			"Huệ", "Đình", "Giai", "Phong", "Tuyên", "Tư", "Vy", "Nhi", "Vân",
			"Giang", "Phi", "Phúc" };

	private final static String[] koreanLastNames = { "Park", "Kim", "Shin",
			"Choi", "Song", "Kang", "Han", "Lee", "Sung", "Jung" };
	private final static String[] koreanMidNames = { "Yong", "Ji", "Je", "Hye",
			"Dong", "Sang", "Ha", "Hyo", "Soo", "Eun", "Hyun", "Rae" };
	private final static String[] koreanFirstNames = { "Hwa", "Woo", "Joon",
			"Hee", "Kyo", "Kyung", "Wook", "Jin", "Jae", "Hoon", "Ra", "Bin",
			"Sun", "Ri", "Soo", "Rim", "Ah", "Ae", "Neul", "Mun", "In", "Mi",
			"Ki", "Sang", "Byung", "Seok", "Gun", "Yoo", "Sup", "Won", "Sub" };
	private final static String[] laosLastNames = { "Xỉn Bựa", "Phỏi", "Nòi",
			"Khăn", "Khạc", "Nhổ Toẹt ", "Thạc Xoay", "Phăn", "Xoăn Tít",
			"Củ Lều" };
	private final static String[] laosMidNames = { "Tày Xô", "Khơ Mú", "Nùng",
			"Min Chều", "Páp Lịt", "Gảy Kua", "Tu Gây", "Vắt Xổ", "Mổ Kò",
			"Náng Phổn", "Kạ Rịt", "Lò Kịt" };
	private final static String[] laosFirstNames = { "Mủ", "Vổ", "Móm", "Trĩ",
			"Xin", "Thoắt", "Tòe", "Vẩu", "Lác", "Quẩy", "Mắn", "Vảy", "Bát",
			"Nhổ", "Phỉ", "Xỉ", "Phây", "Tẻn", "Nản", "Chóe", "Kói", "Lốn",
			"Chàm", "Ven", "Bón", "Khoai", "Hủi", "Quăn", "Xém", "Xịt", "Lít" };

	public ViewForeignResultDialog(Calendar calendar) {
		super();
		this.calendar = calendar;
		chineseName
				.append(" "
						+ chineseLastNames[calendar.get(Calendar.YEAR) % 10])
				.append(" " + chineseMidNames[calendar.get(Calendar.MONTH)])
				.append(" "
						+ chineseFirstNames[calendar.get(Calendar.DATE) - 1]);

		koreanName
				.append(" " + koreanLastNames[calendar.get(Calendar.YEAR) % 10])
				.append(" " + koreanMidNames[calendar.get(Calendar.MONTH)])
				.append(" " + koreanFirstNames[calendar.get(Calendar.DATE) - 1]);

		laosName.append(" " + laosLastNames[calendar.get(Calendar.YEAR) % 10])
				.append(" " + laosMidNames[calendar.get(Calendar.MONTH)])
				.append(" " + laosFirstNames[calendar.get(Calendar.DATE) - 1]);

	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		LayoutInflater inflater = getActivity().getLayoutInflater();
		LinearLayout container = (LinearLayout) inflater.inflate(
				R.layout.view_foreign_result, null);
		builder.setView(container).setPositiveButton("OK",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface arg0, int arg1) {

					}

				});
		TextView chineseNameTxt = (TextView) container
				.findViewById(R.id.view_foreign_chinese_name);
		TextView koreanNameTxt = (TextView) container
				.findViewById(R.id.view_foreign_korean_name);
		TextView laosNameTxt = (TextView) container
				.findViewById(R.id.view_foreign_laos_name);
		TextView givenDate = (TextView) container
				.findViewById(R.id.view_foreign_givenDate);
		givenDate.setText(Utils.getCalendarText(calendar));
		chineseNameTxt.setText(this.chineseName.toString());
		koreanNameTxt.setText(koreanName.toString());
		laosNameTxt.setText(laosName.toString());
		return builder.create();

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	public Calendar getCalendar() {
		return calendar;
	}

	public void setCalendar(Calendar calendar) {
		this.calendar = calendar;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
