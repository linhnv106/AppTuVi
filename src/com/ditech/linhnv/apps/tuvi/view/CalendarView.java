package com.ditech.linhnv.apps.tuvi.view;

import com.ditech.linhnv.apps.tuvi.utils.LogUtils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
/**
 * 
 * @author linhnv
 * this custom class is a subclass of view , to display calendar
 */
public class CalendarView extends View{

	private int mRowWidth;
	private int mRowHeight;
	private Paint mLinePaint;
	private Paint mTextPaint;
	private final int COL_NUMS=7;
	private int mCurrentCol;
	private int mCurrentRow;
	
	public CalendarView(Context context) {
		super(context);
		init();
	}

	public CalendarView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public CalendarView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	
	
	private void init(){
		Resources res = getResources();
		mLinePaint=new Paint(Paint.ANTI_ALIAS_FLAG);
		mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		
		
		//draw a table 7x7
		
		mRowWidth=getMeasuredWidth()/COL_NUMS;
		mLinePaint.setColor(Color.BLUE);
		for(int i=0;i<COL_NUMS;i++){
			canvas.drawLine(i*mRowWidth, 0, i*mRowWidth, getMeasuredWidth(), mLinePaint);
		}
		
		for(int j=0;j<COL_NUMS;j++){
			canvas.drawLine(getMeasuredWidth(), j*mRowWidth,0 , j*mRowWidth, mLinePaint);
		}
		//draw border line
		canvas.drawLine(getMeasuredWidth(), 0, getMeasuredWidth(), getMeasuredWidth(), mLinePaint);
		canvas.drawLine(0, 0, 0, getMeasuredWidth(), mLinePaint);
		canvas.drawLine(getMeasuredWidth(), getMeasuredWidth(),0 , getMeasuredWidth(), mLinePaint);
		canvas.drawLine(getMeasuredWidth(), 0,0 , 0, mLinePaint);
		LogUtils.infor("CalendarView", "Linhnv-onDraw view");
		super.onDraw(canvas);
	}

	
	
	
	
}
