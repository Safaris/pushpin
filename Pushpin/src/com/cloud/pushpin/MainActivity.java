package com.cloud.pushpin;


import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends FragmentActivity {
	private LocationManager locationManager = null;
	private LocationListener locationListener = null;
	private GoogleMap mMap;
	private Spinner spinner1;
	


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
				0, locationListener);
		locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);*/
		SupportMapFragment fm = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
		mMap=fm.getMap();
		mMap.setMyLocationEnabled(true);
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
		spinner1.setOnItemSelectedListener(new SpinnerActivity(mMap));
		
	}
	public class MyLocationListener implements LocationListener {
		@Override
		//add marker to map using lat and long
		public void onLocationChanged(Location loc) {
			/*
			 * System.out.println("This happened"); String message =
			 * "Longitude:" + loc.getLongitude() + "  Latitude: " +
			 * loc.getLongitude(); Toast.makeText( getBaseContext(),
			 * "Location changed: Lat: " + loc.getLatitude() + " Lng: " +
			 * loc.getLongitude(), Toast.LENGTH_SHORT).show();
			 */
			System.out.println("IN ON LOCATION CHANGE, lat="
					+ loc.getLatitude() + ", lon=" + loc.getLongitude());
			
			LatLng pos=new LatLng(loc.getLatitude(),loc.getLongitude());
			mMap.moveCamera(CameraUpdateFactory.newLatLng(pos));
			mMap.moveCamera(CameraUpdateFactory.zoomTo(16));
			MarkerOptions mopt=new MarkerOptions();
			mopt.position(pos);
			mopt.title("YOU");
			mopt.visible(true);
			mMap.addMarker(mopt);
			
			}
		
		//not used
		@Override
		public void onProviderDisabled(String arg0) {
			// TODO Auto-generated method stub

		}
		//not used
		@Override
		public void onProviderEnabled(String arg0) {
			// TODO Auto-generated method stub

		}
		//not used
		@Override
		public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
			// TODO Auto-generated method stub

		}
		

			
		

	}

}
