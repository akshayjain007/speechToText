package com.example.speechtotext;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class CustomTypeFace extends TextView {

	  public CustomTypeFace(Context context, AttributeSet attrs, int defStyle) {
	      super(context, attrs, defStyle);
	      init();
	  }

	 public CustomTypeFace(Context context, AttributeSet attrs) {
	      super(context, attrs);
	      init();
	  }

	 public CustomTypeFace(Context context) {
	      super(context);
	      init();
	 }


	public void init() {
	    Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/expletussans.ttf");
	    setTypeface(tf);

	}
}