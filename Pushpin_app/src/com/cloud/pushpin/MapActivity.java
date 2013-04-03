package com.cloud.pushpin;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;

public class MapActivity extends FragmentActivity {
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
		setContentView(R.layout.activity_main);
		//unnecessary because of google map method...
/*		locationManager = (LocationManager) this
				.getSystemService(Context.LOCATION_SERVICE);
		locationListener = new MyLocationListener();
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0,
				s0, locationListener);
		locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);*/
		
		
	    
		
		SupportMapFragment fm = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
		mMap=fm.getMap();
		mMap.setMyLocationEnabled(true);
		mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

		additemsonSpinner1();
		Location maploc=mMap.getMyLocation();
		if(maploc!=null)
		{
		 maploc.getLongitude(); Toast.makeText( getBaseContext(),
				  "Location changed: Lat: " + maploc.getLatitude() + " Lng: " +
				  maploc.getLongitude(), Toast.LENGTH_SHORT).show();
		}
		
		System.out.println("got here");
		
		
		
	}
	
	@Override
	public void onPause()
	{
		super.onPause();
	}
	
	@Override
	public void onResume()
	{
		super.onResume();
	}
	
	private void acheck()
	{
		final Context context=getApplicationContext();
		final Context context2=this;
		
		
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Do you have an account?")
	            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
	              public void onClick(DialogInterface dialog, int id) {
	            	  AlertDialog.Builder builder2 = new AlertDialog.Builder(context2);
	 				 LayoutInflater inflater = LayoutInflater.from(context);
	 				 final View view1=inflater.inflate(R.layout.accountinfo, null);
	 		    	 builder2.setView(view1);
	 		    	 final EditText savedText =(EditText)view1.findViewById(R.id.message);
	 		    	
	 		    	 builder2.setMessage("Enter your account information")
	 		         
	 		         .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
	 		             public void onClick(DialogInterface dialog, int id) {
	 		            	 success=true;
	 		                
	 		             }
	 		         })
	 		         .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
	 		             public void onClick(DialogInterface dialog, int id) {
	 		                 // User cancelled the dialog
	 		             }
	 		         });
	 				 AlertDialog dialog2 = builder2.create();
	 				 dialog2.show();
	                       
	                }
	            })
	             .setNegativeButton("No", new DialogInterface.OnClickListener() {
	               public void onClick(DialogInterface dialog, int id) {
	                       // User cancelled the dialog
	                 }
	             });
		AlertDialog dialog = builder.create(); 
		dialog.show();
		
	}
	//populates the spinner
	public void additemsonSpinner1()
	{
		spinner1=(Spinner)findViewById(R.id.spinner1);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
		        R.array.pusharr, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1 );
		// Apply the adapter to the spinner
		spinner1.setAdapter(adapter);
		//adds a listener for the option selected
		Context context=getApplicationContext();
		Context context2=this;
		
		spinner1.setOnItemSelectedListener(new SpinnerActivity(mMap,context,context2));
		
	}
}