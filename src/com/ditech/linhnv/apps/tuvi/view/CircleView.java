package com.ditech.linhnv.apps.tuvi.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

public class CircleView extends RelativeLayout {
	final static int CENTER_ID = 111;
	private final int RADIUS = 270;

	public CircleView(Context context) {
		super(context);
	}

	public CircleView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public CircleView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	private RelativeLayout.LayoutParams createNewRelativeLayoutParams() {

		RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);

		lp.addRule(RelativeLayout.ABOVE, CENTER_ID);
		lp.addRule(RelativeLayout.RIGHT_OF, CENTER_ID);
		return lp;
	}

	private View prepareElement(View element, int distX, int distY) {
		RelativeLayout.LayoutParams lp = createNewRelativeLayoutParams();
		element.measure(0, 0);
		int deltaX = element.getMeasuredWidth() / 2;
		int deltaY = element.getMeasuredHeight() / 2;
		lp.setMargins(distX - deltaX, 0, 0, RADIUS - distY - deltaY);
		element.setLayoutParams(lp);
		return element;
	}

	public CircleView(Context context, View[] elements) {
		super(context);

		RelativeLayout.LayoutParams lpView = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT,
				RelativeLayout.LayoutParams.MATCH_PARENT);
		this.setLayoutParams(lpView);
		
		View center = new View(context);
		center.setId(CENTER_ID);
		
		RelativeLayout.LayoutParams lpcenter = new RelativeLayout.LayoutParams(
                0, 0);
		lpcenter.addRule(CENTER_HORIZONTAL);
		lpcenter.addRule(CENTER_VERTICAL);
		center.setLayoutParams(lpcenter);
		this.addView(center);
						
		this.addView(prepareElement(elements[0], 0, 0));
        if (elements.length % 2 == 0) {
                 this.addView(prepareElement(elements[elements.length / 2],
                                    0, 2 * RADIUS));
        }
        if (elements.length > 2) {
                 for (int i = 1; i <= (elements.length - 1) / 2; i++) {
                           int y = i * 4 * RADIUS / elements.length;
                           int x = (int) Math.sqrt(Math.pow(RADIUS, 2)
                                             - Math.pow((RADIUS - y), 2));
                           this.addView(prepareElement(elements[i], x, y));
                           this.addView(prepareElement(elements[elements.length
                                             - i], -x, y));
                 }
        }
	}

}
