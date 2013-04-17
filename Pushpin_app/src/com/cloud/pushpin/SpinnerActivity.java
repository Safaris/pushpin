package com.cloud.pushpin;

import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class SpinnerActivity extends MainActivity implements
		OnItemSelectedListener {
	GoogleMap map;
	Context context, context2;
	private String access_token="";

	// passes the map object

	public SpinnerActivity(GoogleMap mapp, Context contextz, Context contextw,String access) {
		map = mapp;
		context = contextz;
		context2 = contextw;
		access_token=access;
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
				 map.clear();
				 Httpclass http=new Httpclass();
				 JSONArray arr=http.getfriends(access_token);
					for(int x=0;x<arr.size();x++)
					{
						JSONObject json=(JSONObject)arr.get(x);
						System.out.println(json);
						System.out.println(json.get("username"));
						if(json.get("lat")==null||json.get("long")==null)
						{
							System.out.println("null message");
						}
						else
						{
							double lat=Double.valueOf(json.get("lat").toString());
							double longi=Double.valueOf(json.get("long").toString());
							LatLng pos1=new LatLng(lat,longi);
			            	MarkerOptions mopt=new MarkerOptions();
			 				mopt.position(pos1);
			 				if(json.get("message").toString()!="null")
			 					mopt.title(json.get("message").toString());
			 				mopt.snippet(json.get("username").toString());
							mopt.visible(true);
							map.addMarker(mopt);
						}
					}
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
		 				Httpclass http2=new Httpclass();
		 				http2.pushpin(loc.getLatitude(), loc.getLongitude(), access_token,savedText.getText().toString().trim());
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
				Intent intent2=new Intent(this.context, instructions.class)
				 .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
				context.startActivity(intent2);
				break;
			//add friends
			case 4:
				AlertDialog.Builder builder2 = new AlertDialog.Builder(context2);
				 LayoutInflater inflater2 = LayoutInflater.from(context);
				 final View view2=inflater2.inflate(R.layout.frienddialog, null);
		    	 builder2.setView(view2);
		    	 final EditText savedText2 =(EditText)view2.findViewById(R.id.message);
		    	
		    	 builder2.setMessage("Enter your new friend's email address")
		         
		         .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
		             public void onClick(DialogInterface dialog, int id) {
		            	String efriend=savedText2.getText().toString().trim();
		 				Httpclass http=new Httpclass();
		 				Map m;
		 				m=http.addfriend(access_token, efriend);
		 				if(m.get("created").equals("true"))
		 				{
		 					Toast.makeText(context,"Added friends email", Toast.LENGTH_SHORT).show();
		 				}
		 				else if(m.get("exists").equals("false"))
		 				{
		 					Toast.makeText(context,"Email does not exist", Toast.LENGTH_SHORT).show();
		 				}
		 				else
		 				{
		 					Toast.makeText(context,"You are already friends!", Toast.LENGTH_SHORT).show();
		 				}
		                
		             }
		         })
		         .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
		             public void onClick(DialogInterface dialog, int id) {
		                 // User cancelled the dialog
		             }
		         });
				 AlertDialog dialog2 = builder2.create();
				 dialog2.show();
			
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
