package com.cloud.pushpin;

import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;

public class SpinnerActivity extends MapActivity implements
		OnItemSelectedListener {
	GoogleMap map;
	Context context, context2;
	private String access_token="";
	private Spinner spinner1;

	// passes the map object,key, and references

	public SpinnerActivity(GoogleMap mapp, Context contextz, Context contextw,String access,Spinner spin) {
		map = mapp;
		context = contextz;
		context2 = contextw;
		access_token=access;
		spinner1=spin;
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
			//logout option selected
			case 1:
				Intent s = new Intent(this.context, MainActivity.class)
				 .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
				context.startActivity(s);
				((Activity)context2).finish();
				
				
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
				//launches new activity
				Intent intent2=new Intent(this.context, instructions.class)
				 .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
				context.startActivity(intent2);
				break;
			//add friends
			case 4:
				//generates a dialog box to add email of friends
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
		 				//if email was successfully added
		 				if(m.get("created").equals("true"))
		 				{
		 					Toast.makeText(context,"Added friends email", Toast.LENGTH_SHORT).show();
		 				}
		 				//if the email does not exist
		 				else if(m.get("exists").equals("false"))
		 				{
		 					Toast.makeText(context,"Email does not exist", Toast.LENGTH_SHORT).show();
		 				}
		 				//if the user is already friends
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
			//show friends list
			case 5:
				Intent d = new Intent(this.context, Listfriends.class)
				 .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
			
				d.putExtra("access_token", access_token);
				context.startActivity(d);
				break;
			default:
				break;
		 
		}
		if (pos == 0) {
            
        } else {
           //reset spinner option 
           spinner1.setSelection(0);
        }
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

}
