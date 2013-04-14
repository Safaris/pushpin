package com.cloud.pushpin;

import java.io.IOException;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class NewAccount extends FragmentActivity
{
	
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.accountinfo);
	}
	
	
	public void submit(View view) throws IOException 
	{
		
		final EditText savedText =(EditText)view.findViewById(R.id.message);
    	final EditText savedText2 =(EditText)view.findViewById(R.id.message1);
    	final EditText savedText3 =(EditText)view.findViewById(R.id.message2);
    	
    	String name = savedText.getText().toString().trim();
    	String pass = savedText2.getText().toString().trim();
    	String repeatPass = savedText3.getText().toString().trim();
    	
    	
    	if(name.equals("") || pass.equals("") || repeatPass.equals(""))
		{
    		Toast.makeText(getBaseContext(),"No information entered!", Toast.LENGTH_SHORT).show();
		}
    	
		if(savedText2.getText().toString().trim().equals(savedText3.getText().toString().trim()))
		{
			if(savedText2.getText().toString().trim().equals("")||savedText3.getText().toString().trim().equals(""))
				Toast.makeText( getBaseContext(),"Please make sure account name and password fields are not blank", Toast.LENGTH_SHORT).show();
			else
			{
				Httpclass test = new Httpclass();
				boolean result = test.newlogin(savedText.getText().toString().trim(), savedText2.getText().toString().trim());
				if(result == true)
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
	
	public void cancel(View view) throws IOException
	{
		finish();
	}

}
