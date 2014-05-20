package com.example.speechtotext;

import java.util.ArrayList;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Popup extends Fragment implements AnimationListener {
	
	private TextView mText, txt, tv;
	ImageView iv0, iv1, iv2, iv3, iv4;
	FrameLayout fv;
	private SpeechRecognizer sr;
	String txtview = "";
	private static final String TAG = "MyStt3Activity";
	Animation animFadein, animFadeout;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.popup1, container, false);
		mText = (TextView)view.findViewById(R.id.txtText);
		txt = (TextView)view.findViewById(R.id.textrms);
		fv = (FrameLayout)view.findViewById(R.id.frameLayout1);
		
		iv0=(ImageView) fv.findViewById(R.id.image_back0);
		iv1=(ImageView) fv.findViewById(R.id.image_back1);
		iv2=(ImageView) fv.findViewById(R.id.image_back2);
		iv3=(ImageView) fv.findViewById(R.id.image_back3);
		iv4=(ImageView) fv.findViewById(R.id.image_back4);
		
		tv = (TextView) fv.findViewById(R.id.text);
		sr = SpeechRecognizer.createSpeechRecognizer(getActivity());       
        sr.setRecognitionListener(new listener()); 
		//Toast.makeText(getActivity(), "Your wish", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);        
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,"en-US");
            intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE,"voice.recognition.test");

            intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS,5); 
                 sr.startListening(intent);
                 Log.i("111111","11111111");
                 
                // load the animation
                 animFadein = AnimationUtils.loadAnimation(getActivity(),
                         R.anim.rotate);
                 animFadeout = AnimationUtils.loadAnimation(getActivity(),
                         R.anim.rotate_counterclockwise);
                 // set animation listener
                 animFadein.setAnimationListener(this);
                 animFadeout.setAnimationListener(this);
                 fv.startAnimation(animFadein);
                 tv.startAnimation(animFadeout);
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
			tv.setTextColor(Color.BLACK);
			
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
            mText.setText(data.get(0));  
		}

		@Override
		public void onRmsChanged(float rmsdB) {
			// TODO Auto-generated method stub

			
			int volumeNo0 = (int)(((rmsdB*rmsdB*rmsdB)+120.))+100;
			int volumeNo1 = (int)(((rmsdB*rmsdB*rmsdB)+120.))+75;
			int volumeNo2 = (int)(((rmsdB*rmsdB)+120.))+50;
			int volumeNo3 = (int)(((rmsdB)+120.))+25;
			int volumeNo4 = (int)((120.));
			
			FrameLayout.LayoutParams lp0 = new FrameLayout.LayoutParams((volumeNo0+40), (volumeNo0+40));
			FrameLayout.LayoutParams lp1 = new FrameLayout.LayoutParams((volumeNo1+40), (volumeNo1+40));
			FrameLayout.LayoutParams lp2 = new FrameLayout.LayoutParams((volumeNo2+40), (volumeNo2+40));
			FrameLayout.LayoutParams lp3 = new FrameLayout.LayoutParams((volumeNo3+40), (volumeNo3+40));
			FrameLayout.LayoutParams lp4 = new FrameLayout.LayoutParams((volumeNo4+40), (volumeNo4+40));

			lp0.gravity = Gravity.CENTER;
			lp1.gravity = Gravity.CENTER;
			lp2.gravity = Gravity.CENTER;
			lp3.gravity = Gravity.CENTER;
			lp4.gravity = Gravity.CENTER;
			
			iv0.setLayoutParams(lp0);
			iv1.setLayoutParams(lp1);
			iv2.setLayoutParams(lp2);
			iv3.setLayoutParams(lp3);
			iv4.setLayoutParams(lp4);

			tv.setTextColor(Color.BLUE);
			tv.setTextColor(Color.RED);
			Log.i("circle", "change");
			txt.setText(Double.toString(volumeNo1+40));			
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
}
