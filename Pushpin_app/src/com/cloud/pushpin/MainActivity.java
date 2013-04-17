package com.cloud.pushpin;


import java.io.IOException;
import java.util.Map;
import android.content.Intent;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;

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
		Httpclass http=new Httpclass();
		Map m;
		String sessionid;
		boolean result=false;
		final EditText edit =  (EditText) findViewById(R.id.EditText01);
		final EditText edit2 =  (EditText) findViewById(R.id.message);
		String name=edit.getText().toString().trim();
		String pass=edit2.getText().toString().trim();
		
		System.out.println("This is the name:" + name);
		
		if(name.equals("") || pass.equals(""))
		{
			Toast.makeText( getBaseContext(),"No information entered!", Toast.LENGTH_SHORT).show();
		}
		 
		else
		{
			m=http.login(name, pass);
			result=(Boolean) m.get("boolean");
			
			if(result==true)
			{
				Intent intent = new Intent(this, MapActivity.class);
				intent.putExtra("access_token", m.get("access_token").toString());
				startActivity(intent);
				finish(); 
			}
			else
			{
				Toast.makeText( getBaseContext(),"Login information is incorrect!", Toast.LENGTH_SHORT).show();
			}
		}
		
	}
	
	
	public void newacc(View view) throws IOException
	{
		Intent intent = new Intent(this, NewAccount.class);
		startActivity(intent);
		finish();
		
	}
	

}
