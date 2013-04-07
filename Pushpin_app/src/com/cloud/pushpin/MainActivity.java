package com.cloud.pushpin;


import java.io.IOException;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends FragmentActivity {
	private LocationManager locationManager = null;
	private LocationListener locationListener = null;
	private GoogleMap mMap;
	private Spinner spinner1;
	private boolean accountexist=false;
	private boolean success=false;


	// private LocationListener onLocationChange=null;
	@Override
	//creates the spinner and gps/network detection. Also locates the map
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainxml);
		
	}
	
	public void generate(View view) throws IOException 
	{
		//THIS CODE IS READY TO DEPLOY ONCE BOOLEANS CAN BE RETURNED BY SERVER
		 /*Httpclass http=new Httpclass();
		 boolean result=false;
		 final EditText edit =  (EditText) findViewById(R.id.EditText01);
		 final EditText edit2 =  (EditText) findViewById(R.id.message);
		 String name=edit.toString();
		 String pass=edit2.toString();
		 if(name!="" && pass!="")
		 {
			 Map m=http.login(name, pass);
		 }
		 else{
			 Toast.makeText( getBaseContext(),"No information entered!", Toast.LENGTH_SHORT).show();
		 }
		 result=m.get("boolean")
		 if(result==true)
		 {
			 Intent intent = new Intent(this, MapActivity.class);
			 startActivity(intent);
		 }
		 else
		 {
			 Toast.makeText( getBaseContext(),"Login information is incorrect!", Toast.LENGTH_SHORT).show();
		 }*/
		 
		 Intent intent = new Intent(this, MapActivity.class);
		 startActivity(intent);

	}
	
	public void newacc(View view) throws IOException
	{
		final Context context=getApplicationContext();
		final Context context2=this;
		
		 AlertDialog.Builder builder = new AlertDialog.Builder(context2);
		 LayoutInflater inflater = LayoutInflater.from(context);
		 final View view1=inflater.inflate(R.layout.accountinfo, null);
    	 builder.setView(view1);
    	 final EditText savedText =(EditText)view1.findViewById(R.id.message);
    	 final EditText savedText2 =(EditText)view1.findViewById(R.id.message1);
    	 final EditText savedText3 =(EditText)view1.findViewById(R.id.message2);
    	 
    	 builder.setMessage("Enter your new account information")
         
         .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
             public void onClick(DialogInterface dialog, int id) {
            	 if(savedText2.getText().toString().trim().equals(savedText3.getText().toString().trim()))
            	 {
            		 if(savedText2.getText().toString().trim().equals("")||savedText3.getText().toString().trim().equals(""))
            			 Toast.makeText( getBaseContext(),"Please make sure account name and password fields are not blank", Toast.LENGTH_SHORT).show();
            		 else
            		 {
            			 Httpclass test=new Httpclass();
                		 boolean result=test.newlogin(savedText.getText().toString().trim(), savedText2.getText().toString().trim());
                		 if(result==true)
                			 Toast.makeText( getBaseContext(),"Successfully made account", Toast.LENGTH_SHORT).show();
                		 else
                			 Toast.makeText( getBaseContext(),"Failed to create account", Toast.LENGTH_SHORT).show();
            		 }
            	 }
            	 else
            	 {
            		
            		 Toast.makeText( getBaseContext(),"Passwords do not match.", Toast.LENGTH_SHORT).show();
            	 }

             }
         })
         .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
             public void onClick(DialogInterface dialog, int id) {
                 // User cancelled the dialog
             }
         });
		 AlertDialog dialog = builder.create();
		 dialog.show();
	}

}
