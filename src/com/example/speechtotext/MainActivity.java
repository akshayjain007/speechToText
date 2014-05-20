package com.example.speechtotext;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.LinearLayout;

public class MainActivity extends Activity implements OnClickListener {


	private LinearLayout lLayout;
	Resources res;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		lLayout = (LinearLayout) findViewById(R.id.main_view);
		lLayout.setOnLongClickListener(new OnLongClickListener() {

			@Override
			public boolean onLongClick(View v) {
				if (v.getId() == R.id.main_view) {
					
					Popup frag = new Popup();
					FragmentManager manager = getFragmentManager();
					FragmentTransaction transaction = manager.beginTransaction();
					
					transaction.setCustomAnimations(R.anim.slide_in_bottom, R.anim.slide_out_top);
					transaction.replace(R.id.lLayoutView, frag, "some");
					//transaction.addToBackStack("null");
					transaction.commit();
				}
				return true;
			}
		});

	}
	private Bitmap CreateBlurredImage (int radius)
	{
	    
		res = this.getResources();
    	int id = R.id.main_view;
// Load a clean bitmap and work from that.
	    Bitmap originalBitmap = BitmapFactory.decodeResource (res, id);

	    // Create another bitmap that will hold the results of the filter.
	    Bitmap blurredBitmap;
	    blurredBitmap = Bitmap.createBitmap (originalBitmap);

	    // Create the Renderscript instance that will do the work.
	    RenderScript rs = RenderScript.create (this);

	    // Allocate memory for Renderscript to work with
	    Allocation input = Allocation.createFromBitmap (rs, originalBitmap, Allocation.MipmapControl.MIPMAP_FULL, Allocation.USAGE_SCRIPT);
	    Allocation output = Allocation.createTyped (rs, input.getType());

	    // Load up an instance of the specific script that we want to use.
	    ScriptIntrinsicBlur script = ScriptIntrinsicBlur.create (rs, Element.U8_4 (rs));
	    script.setInput (input);

	    // Set the blur radius
	    script.setRadius (25);

	    // Start the ScriptIntrinisicBlur
	    script.forEach (output);

	    // Copy the output to the blurred bitmap
	    output.copyTo (blurredBitmap);

	    return blurredBitmap;
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub

	}

}