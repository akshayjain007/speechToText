package com.example.speechtotext;


import java.util.ArrayList;



import android.animation.ObjectAnimator;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HubGlow extends Fragment implements AnimationListener, OnClickListener {
	
	private TextView mText, txt, tv, tv_dropdown_symbol;
	ImageView iv0, iv10;
	FrameLayout fv;
	LinearLayout ll_suggestions_heading, ll_suggestions_list;
	private SpeechRecognizer sr;
	String txtview = "";
	String cmnd="";
	ObjectAnimator anim;
	int flag=0;
	MediaPlayer mp;
	
	private static final String TAG = "MyStt3Activity";
	Animation animFadein, animFadeopposite, animFadehalf, animFadehalfoppposite;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.hubglow, container, false);
		mText = (TextView)view.findViewById(R.id.txtText);
		tv_dropdown_symbol = (TextView)view.findViewById(R.id.tv_dropdown_symbol);
		fv = (FrameLayout)view.findViewById(R.id.frameLayout1);
		ll_suggestions_heading = (LinearLayout)view.findViewById(R.id.ll_suggestions_heading);
		ll_suggestions_list = (LinearLayout)view.findViewById(R.id.ll_suggestions_list);
		//iv10=(ImageView) fv.findViewById(R.id.i10);
		
		tv = (TextView) fv.findViewById(R.id.text);
		Typeface font = Typeface.createFromAsset(getActivity().getAssets(),
				"fonts/fontawesome-webfont.ttf");
		tv.setTypeface(font);
		Typeface font2 = Typeface.createFromAsset(getActivity().getAssets(),
				"fonts/expletussans.ttf");
		
		mText.setTypeface(font2);
		sr = SpeechRecognizer.createSpeechRecognizer(getActivity());       
        sr.setRecognitionListener(new listener()); 
		//Toast.makeText(getActivity(), "Your wish", Toast.LENGTH_SHORT).show();
        anim = ObjectAnimator.ofInt(tv, "textColor",Color.rgb(255, 8, 68));
		anim.setDuration(1000);
		anim.start();
		
		ll_suggestions_heading.setOnClickListener(this);
		
		Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);        
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,"en-US");
            intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE,"voice.recognition.test");

            intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS,5); 
                 sr.startListening(intent);
                 Log.i("111111","11111111");
                 
                // load the animation
                 animFadein = AnimationUtils.loadAnimation(getActivity(),
                         R.anim.rotate);
                 animFadeopposite = AnimationUtils.loadAnimation(getActivity(),
                         R.anim.rotate_counterclockwise);
                 animFadehalf = AnimationUtils.loadAnimation(getActivity(),
                         R.anim.rotate_half_speed);
                 animFadehalfoppposite = AnimationUtils.loadAnimation(getActivity(),
                         R.anim.rotate_half_speed_back);
                 // set animation listener
                 animFadein.setAnimationListener(this);
                 animFadeopposite.setAnimationListener(this);
                 animFadehalf.setAnimationListener(this);
                 animFadehalfoppposite.setAnimationListener(this);
                 fv.startAnimation(animFadein);
              //   tv.startAnimation(animFadeopposite);
                 tv.startAnimation(animFadehalfoppposite);
                 iv10.startAnimation(animFadeopposite);
		return view;
	}
	
	
	   class listener implements RecognitionListener          
	   {

		@Override
		public void onBeginningOfSpeech() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onBufferReceived(byte[] buffer) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onEndOfSpeech() {
			// TODO Auto-generated method stub
			//tv.setTextColor(Color.BLACK);
			tv.setTextColor(Color.rgb(36, 168, 19));
		}

		@Override
		public void onError(int error) {
			// TODO Auto-generated method stub
			Log.d(TAG,  "error " +  error);
            switch ( error)
            {
            case 1:mText.setText("error: Network operation timed out ");
           	 break;
            case 2:mText.setText("error: Other network related errors ");
           	 break;
            case 3:mText.setText("error: Audio recording error ");
           	 break;
            case 4:mText.setText("error: Server sends error status " );
           	 break;
            case 5:mText.setText("error: Other client side errors ");
           	 break;
            case 6:mText.setText("error: No speech input ");
           	 break;
            case 7:mText.setText("error: No recognition result matched " );
           	 break;
            case 8:mText.setText("error: RecognitionService busy " );
           	 break;
            case 9:mText.setText("error: Insufficient permissions ");
           	 break;
            }
			
		}

		@Override
		public void onEvent(int eventType, Bundle params) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onPartialResults(Bundle partialResults) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onReadyForSpeech(Bundle params) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onResults(Bundle results) {
			// TODO Auto-generated method stub
			String str = new String();
            Log.d(TAG, "onResults " + results);
            ArrayList<String> data = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
            for (int i = 0; i < data.size(); i++)
            {
		          Log.d(TAG, "result " + data.get(i));         
		          str += data.get(i);
            }
           // mText.setText(data.get(0));
            for(int j=0; j<data.get(0).length();j++)
            {
            	cmnd = cmnd + data.get(0).charAt(j)+ " ";
            	mText.setText(cmnd);
            	mp = MediaPlayer.create(getActivity(), R.raw.off);
				mp.setOnCompletionListener(new OnCompletionListener() {	
		            @Override
		            public void onCompletion(MediaPlayer mp) {
		            	mp.reset();
		            	mp.release();
		            }

		        });
				mp.start();
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

            }
		}

		@Override
		public void onRmsChanged(float rmsdB) {
			// TODO Auto-generated method stub

			
			int volumeNo0 = (int)(((rmsdB*rmsdB*rmsdB)+120.))+100;
			int volumeNo1 = (int)(((rmsdB*rmsdB)+120.))+50;
			int volumeNo2 = (int)(((rmsdB*rmsdB)+120.))+35;
			int volumeNo3 = (int)(((rmsdB)+120.))+25;
			int volumeNo4 = (int)((120.));
			
			FrameLayout.LayoutParams lp0 = new FrameLayout.LayoutParams((volumeNo0+40), (volumeNo0+40));
			FrameLayout.LayoutParams lp1 = new FrameLayout.LayoutParams((volumeNo1+40), (volumeNo1+40));
			FrameLayout.LayoutParams lp2 = new FrameLayout.LayoutParams((volumeNo2+40), (volumeNo2+40));
			FrameLayout.LayoutParams lp3 = new FrameLayout.LayoutParams((volumeNo3+40), (volumeNo3+40));
			FrameLayout.LayoutParams lp4 = new FrameLayout.LayoutParams((volumeNo4+40), (volumeNo4+40));
			FrameLayout.LayoutParams lp5 = new FrameLayout.LayoutParams(60,60);

			lp0.gravity = Gravity.CENTER;
			lp1.gravity = Gravity.CENTER;
			lp2.gravity = Gravity.CENTER;
			lp3.gravity = Gravity.CENTER;
			lp4.gravity = Gravity.CENTER;
			lp5.gravity = Gravity.CENTER;
			
			
			
			Log.i("circle", "change");
			//txt.setText(Double.toString(volumeNo1+40));			
		}     
	   }


	@Override
	public void onAnimationEnd(Animation arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onAnimationRepeat(Animation animation) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onAnimationStart(Animation animation) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId())
		{
		case R.id.ll_suggestions_heading:
			
			if(flag==0){
				
			
			ll_suggestions_list.setVisibility(View.VISIBLE);  
		    TranslateAnimation slide = new TranslateAnimation(0, 0, -5000,0 );   
		    slide.setDuration(500);   
		    slide.setFillAfter(true);   
		    ll_suggestions_list.startAnimation(slide);
		    flag=1;
		    tv_dropdown_symbol.setText(R.string.tv_dropdown_symbol_cancel);
			}
			else if(flag==1)
			{
				  
			    TranslateAnimation slide = new TranslateAnimation(0,0000, 0,10000 );   
			    slide.setDuration(1000);   
			    slide.setFillAfter(true);   
			    ll_suggestions_list.startAnimation(slide);
			    flag=0;
			    tv_dropdown_symbol.setText(R.string.tv_dropdown_symbol);
			    ll_suggestions_list.setVisibility(View.VISIBLE);
			}
			break;
		}
		
	}	
}
