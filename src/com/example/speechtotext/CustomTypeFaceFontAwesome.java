package com.example.speechtotext;


import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class CustomTypeFaceFontAwesome extends TextView {

	  public CustomTypeFaceFontAwesome(Context context, AttributeSet attrs, int defStyle) {
	      super(context, attrs, defStyle);
	      init();
	  }

	 public CustomTypeFaceFontAwesome(Context context, AttributeSet attrs) {
	      super(context, attrs);
	      init();
	  }

	 public CustomTypeFaceFontAwesome(Context context) {
	      super(context);
	      init();
	 }


	public void init() {
	    Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/fontawesome-webfont.ttf");
	    setTypeface(tf);

	}
}