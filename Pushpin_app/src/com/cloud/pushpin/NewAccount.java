package com.cloud.pushpin;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class NewAccount extends FragmentActivity implements Serializable
{
	
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.accountinfo);
	}
	
	
	public void submit(View view) throws IOException 
	{
		Map m;
		boolean result;
		final EditText savedText =(EditText)findViewById(R.id.message);
    	final EditText savedText2 =(EditText)findViewById(R.id.message1);
    	final EditText savedText3 =(EditText)findViewById(R.id.message2);
    	final EditText savedText4 =(EditText)findViewById(R.id.email);
    	
    	String name = savedText.getText().toString().trim();
    	System.out.println(name);
    	String pass = savedText2.getText().toString().trim();
    	String repeatPass = savedText3.getText().toString().trim();
    	String email=savedText4.getText().toString().trim();
    	
    	
    	if(name.equals("") || pass.equals("") || repeatPass.equals(""))
		{
    		Toast.makeText(getBaseContext(),"No information entered!", Toast.LENGTH_SHORT).show();
		}
    	
		if(savedText2.getText().toString().trim().equals(savedText3.getText().toString().trim()))
		{
			if(email.equals(""))
			{
				Toast.makeText(getBaseContext(),"No email entered!", Toast.LENGTH_SHORT).show();
			}
			else
			{
				if(savedText2.getText().toString().trim().equals("")||savedText3.getText().toString().trim().equals(""))
					Toast.makeText( getBaseContext(),"Please make sure account name and password fields are not blank", Toast.LENGTH_SHORT).show();
				else
				{
					Httpclass test = new Httpclass();
					m = test.newlogin(savedText.getText().toString().trim(), savedText2.getText().toString().trim(),savedText4.getText().toString());
					result=(Boolean)m.get("boolean");
					System.out.println(m.get("access_token"));
					if(result == true)
					{
						Toast.makeText( getBaseContext(),"Successfully made account", Toast.LENGTH_SHORT).show();
						Intent intent = new Intent(this, MapActivity.class);
						intent.putExtra("access_token", m.get("access_token").toString());
						intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						startActivity(intent);
						finish();
						
					}
					else
						Toast.makeText( getBaseContext(),"Failed to create account", Toast.LENGTH_SHORT).show();
				}
			}
			
		}
		else
		{
			Toast.makeText( getBaseContext(),"Passwords do not match.", Toast.LENGTH_SHORT).show();
		} 
	}
	
	public void cancel(View view) throws IOException
	{
		Intent intent = new Intent(this, MainActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
		finish();
	}

}
