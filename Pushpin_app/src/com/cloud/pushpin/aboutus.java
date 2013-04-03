package com.cloud.pushpin;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class aboutus extends Activity{
	
	//displays the about us info
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.xml2);
/*		TextView t=new TextView(this); 
	    t=(TextView)findViewById(R.id.textView1); 
	    t.setText("PushPin is an application created by a group of students in a cloud class at TCNJ. It was used primarily " +
	    		 "as a learning experience for Android. The Creators are: Sean Safari, Eddie Spencer, Eric Palace, Jared Milburn, Marty Drag, and " +
	    		 "Daniel Cahill.");*/
	    
		Log.i("TAG","GOTHERE");
		
	}
	
	//returns to previous activity
	public void finishact(View view)
	{
		//finishes the activity
		finish();
	}
	
	

}
