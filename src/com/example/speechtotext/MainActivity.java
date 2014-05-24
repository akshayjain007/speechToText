package com.example.speechtotext;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Resources;
import android.os.Bundle;
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
					
					HubGlow frag = new HubGlow();
					Popup frag1 = new Popup();
					FragmentManager manager = getFragmentManager();
					FragmentTransaction transaction = manager.beginTransaction();
					
					transaction.setCustomAnimations(R.anim.slide_in_bottom, R.anim.slide_out_top);
					transaction.replace(R.id.lLayoutView, frag1, "some");
					//transaction.addToBackStack("null");
					transaction.commit();
				}
				return true;
			}
		});

	}
	
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub

	}

}