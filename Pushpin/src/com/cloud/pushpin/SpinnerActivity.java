package com.cloud.pushpin;

import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class SpinnerActivity extends FragmentActivity implements OnItemSelectedListener {
	GoogleMap map;
	//passes the map object
	public SpinnerActivity(GoogleMap mapp)
	{
		map=mapp;
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
				Location loc=map.getMyLocation();
				LatLng pos1=new LatLng(loc.getLatitude(),loc.getLongitude());
				MarkerOptions mopt=new MarkerOptions();
				mopt.position(pos1);
				mopt.title("YOU");
				mopt.visible(true);
				map.addMarker(mopt);
				
				break;
			//about us option selected
			case 2:
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
