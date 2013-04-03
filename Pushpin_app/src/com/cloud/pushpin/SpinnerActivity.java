package com.cloud.pushpin;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class SpinnerActivity extends MainActivity implements
		OnItemSelectedListener {
	GoogleMap map;
	Context context, context2;

	// passes the map object

	public SpinnerActivity(GoogleMap mapp, Context contextz, Context contextw) {
		map = mapp;
		context = contextz;
		context2 = contextw;
	}

	@Override
	public void onResume() {
		super.onResume();
	}

	@Override
	public void onPause() {
		super.onPause();
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int pos,
			long id) {
		Object choice=parent.getItemAtPosition(pos);
		switch(pos){
			//default option
			case 0:
				break;
			//pushpin option selected
			case 1:
				/*DialogFragment newFragment = new dialog();O
			    newFragment.show(getSupportFragmentManager(), "missiles");
				*/
				 AlertDialog.Builder builder = new AlertDialog.Builder(context2);
				 LayoutInflater inflater = LayoutInflater.from(context);
				 final View view1=inflater.inflate(R.layout.pushdialog, null);
		    	 builder.setView(view1);
		    	 final EditText savedText =(EditText)view1.findViewById(R.id.message);
		    	
		    	 builder.setMessage("Enter your message")
		         
		         .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
		             public void onClick(DialogInterface dialog, int id) {
		            	Location loc=map.getMyLocation();
		 				LatLng pos1=new LatLng(loc.getLatitude(),loc.getLongitude());
		            	MarkerOptions mopt=new MarkerOptions();
		 				mopt.position(pos1);
		 				mopt.title(savedText.getText().toString().trim());
						mopt.visible(true);
						map.addMarker(mopt);
		                
		             }
		         })
		         .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
		             public void onClick(DialogInterface dialog, int id) {
		                 // User cancelled the dialog
		             }
		         });
				 AlertDialog dialog = builder.create();
				 dialog.show();
			
				
				break;
			//about us option selected
			case 2:
				//launches new activity
				Intent intent=new Intent(this.context, aboutus.class)
				 .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
				context.startActivity(intent);
				
				break;
			//instructions
			case 3:
				break;
			//add friends
			case 4:
				break;
			default:
				break;
		
		
		
		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

}
