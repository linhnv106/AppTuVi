package com.ditech.linhnv.apps.tuvi.fragments;

import com.ditech.linhnv.apps.tuvi.R;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ViewErrantNameResultDialog extends DialogFragment {
	private StringBuilder errantName = new StringBuilder("");
	private StringBuilder title = new StringBuilder("Thân chào bạn ");
	private OnDialogDismiss mOnDialogDismiss;
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		LayoutInflater inflater = getActivity().getLayoutInflater();
		LinearLayout layout=(LinearLayout)inflater.inflate(R.layout.view_errant_name_result, null);
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setView(layout);
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dismiss();
				if(mOnDialogDismiss!=null){
					mOnDialogDismiss.onDismiss();
				}
			}
			
		});
		
		TextView titleText=(TextView)layout.findViewById(R.id.view_errant_result_name);
		titleText.setText(title.toString().toUpperCase());
		TextView errantNameText=(TextView)layout.findViewById(R.id.view_errant_result_txt);	
		errantNameText.setText(errantName.toString());
		return builder.create();
	}
	public ViewErrantNameResultDialog(String[] data ,OnDialogDismiss onDialogDismiss) {
		super();
		mOnDialogDismiss=onDialogDismiss;
		for(String s:data){
			title.append(" " + s);
		}
		int i= data.length;
		char last = data[0].trim().toUpperCase().charAt(0);
		char first=data[i-1].trim().toUpperCase().charAt(0);
		switch(last){
		case 'A':
			errantName.append(" " +"Kỳ Môn");
			break;
		case 'B':
			errantName.append(" " +"Huyền Thiên");
			break;
		case 'C':
			errantName.append(" " +"Nhật Nguyệt");
			break;
		case 'D':
			errantName.append(" " +"Độc Long");
			break;
		case 'E':
			errantName.append(" " +"Dạ Xoa");
			break;
		case 'F':
			errantName.append(" " +"Thần Dương");
			break;
		case 'G':
			errantName.append(" " +"Ngọc Nữ");
			break;
		case 'H':
			errantName.append(" " +"Tiêu Diêu");
			break;
		case 'K':
			errantName.append(" " +"Thiên Canh");
			break;
		case 'L':
			errantName.append(" " +"Thiên Vũ");
			break;
		case 'M':
			errantName.append(" " +"Bát Quái");
			break;
		case 'N':
			errantName.append(" " +"Thái Ất");
			break;
			
		case 'O':
			errantName.append(" " +"Lạc Anh");
			break;
		case 'U':
			errantName.append(" " +"Thần Môn");
			break;
		case 'P':
			errantName.append(" " +"Nhạn Xà");
			break;
		case 'Q':
			errantName.append(" " +"Thái Cực");
			break;
		case 'V':
			errantName.append(" " +"Lục Hợp");
			break;
		case 'R':
			errantName.append(" " +"Hồi Phong");
			break;
		case 'S':
			errantName.append(" " +"Hỗn Độn");
			break;
		case 'T':
			errantName.append(" " +"Càn Khôn");
			break;
		case 'X':
			errantName.append(" " +"Thiên Môn");
			break;
		case 'Y':
			errantName.append(" " +"Huyền Thiên");
			break;
		default :
			break;
		}
		if(i>2){
			char mid= data[1].trim().toUpperCase().charAt(0);
			switch(mid){
			case 'A':
				errantName.append(" " +"Phất Huyệt");
				break;
			case 'B':
				errantName.append(" " +"Giáng Ma");
				break;
			case 'C':
				errantName.append(" " +"Tích Lịch");
				break;
			case 'D':
				errantName.append(" " +"Âm Dương");
				break;
			case 'E':
				errantName.append(" " +"Tang Môn");
				break;
			case 'F':
				errantName.append(" " +"Tu La");
				break;
			case 'G':
				errantName.append(" " +"Toái Thạch");
				break;
			case 'H':
				errantName.append(" " +"Cửu Cửu");
				break;
			case 'K':
				errantName.append(" " +"Cẩm");
				break;
			case 'L':
				errantName.append(" " +"Vô Ngấn");
				break;
			case 'M':
				errantName.append(" " +"Lưỡng Nghi");
				break;
			case 'N':
				errantName.append(" " +"Ngũ Thần");
				break;
				
			case 'O':
				errantName.append(" " +"Xuyên Vân");
				break;
			case 'U':
				errantName.append(" " +"Vô Ảnh");
				break;
			case 'P':
				errantName.append(" " +"Phá Ngọc");
				break;
			case 'Q':
				errantName.append(" " +"Kỳ");
				break;
			case 'V':
				errantName.append(" " +"Vô Song");
				break;
			case 'R':
				errantName.append(" " +"Tấn Lôi");
				break;
			case 'S':
				errantName.append(" " +"Phục Ma");
				break;
			case 'T':
				errantName.append(" " +"Du Thân");
				break;
			case 'X':
				errantName.append(" " +"Liên Hoàn");
				break;
			case 'Y':
				errantName.append(" " +"Thần");
				break;
			default :
				break;
			}
		}
		
		switch(first){
		case 'A':
			errantName.append(" " +"Châm");
			break;
		case 'B':
			errantName.append(" " +"Bổng");
			break;
		case 'C':
			errantName.append(" " +"Chưởng");
			break;
		case 'D':
			errantName.append(" " +"Đao");
			break;
		case 'E':
			errantName.append(" " +"Chảo");
			break;
		case 'F':
			errantName.append(" " +"Chỉ");
			break;
		case 'G':
			errantName.append(" " +"Phủ");
			break;
		case 'H':
			errantName.append(" " +"Câu");
			break;
		case 'K':
			errantName.append(" " +"Côn");
			break;
		case 'L':
			errantName.append(" " +"Trượng");
			break;
		case 'M':
			errantName.append(" " +"Tiên");
			break;
		case 'N':
			errantName.append(" " +"Kiếm");
			break;
			
		case 'O':
			errantName.append(" " +"Tiêu");
			break;
		case 'U':
			errantName.append(" " +"Chão");
			break;
		case 'P':
			errantName.append(" " +"Đao");
			break;
		case 'Q':
			errantName.append(" " +"Quyền");
			break;
		case 'V':
			errantName.append(" " +"Mâu");
			break;
		case 'R':
			errantName.append(" " +"Thủ");
			break;
		case 'S':
			errantName.append(" " +"Công");
			break;
		case 'T':
			errantName.append(" " +"Chùy");
			break;
		case 'X':
			errantName.append(" " +"Thương");
			break;
		case 'Y':
			errantName.append(" " +"Kiếm");
			break;
		default :
			break;
		}
		
	}
	public interface OnDialogDismiss{
			void onDismiss();
	}
}
