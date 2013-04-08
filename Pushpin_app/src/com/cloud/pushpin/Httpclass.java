package com.cloud.pushpin;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class Httpclass {
	private HttpClient httpclient;

	public Httpclass(){
		httpclient=new DefaultHttpClient();

	}
	
	public Map login (String name, String pass)
	{
		Map m=new HashMap();
		try {
			 boolean result=false;
			 JSONObject json = new JSONObject();
			 JSONObject json2= new JSONObject();
			 json.put("username",name);
			 json.put("password", pass);
			 
			 StringEntity input = new StringEntity(json.toString());
	    	 input.setContentType("application/json");
	    	
			 HttpPost httppost = new HttpPost("http://ec2-54-235-20-117.compute-1.amazonaws.com:3000/login");
	            httppost.setEntity(input);
	            HttpResponse response = httpclient.execute(httppost);
	            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
	            StringBuilder content = new StringBuilder();
	            String line;
	            while (null != (line = rd.readLine()) ){
	                content.append(line);
	            }
	         
	            Object obj=JSONValue.parse(content.toString());
	            System.out.println(content.toString());
	            JSONObject finalResult=(JSONObject)obj;
	            JSONObject json3 = (JSONObject)finalResult.get(0);
	            System.out.println(finalResult.get("logged_in"));
	            System.out.println(finalResult.get("session_key"));
	           
	         
	            m.put("boolean", finalResult.get("logged_in"));
	         	m.put("sessionid", finalResult.get("session_key"));
	          
	          
	           

		 } catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
	         // When HttpClient instance is no longer needed,
	         // shut down the connection manager to ensure
	         // immediate deallocation of all system resources
	         httpclient.getConnectionManager().shutdown();
     }
		
		return m;
	}
	
	public boolean newlogin (String name, String pass)
	{
		boolean result=false;
		
		try {
			 JSONObject json = new JSONObject();
			 JSONObject json2= new JSONObject();
			 json.put("username",name);
			 json.put("password", pass);
			 json2.put("user",json);
			 
			 HttpClient httpclient=new DefaultHttpClient();
		     System.out.println(json2);
		     
		     StringEntity input = new StringEntity(json.toString());
		     input.setContentType("application/json");
		    		
		    		
		     HttpPost httppost = new HttpPost("http://ec2-54-235-20-117.compute-1.amazonaws.com:3000/users");
		     httppost.setEntity(input);
		     HttpResponse response = httpclient.execute(httppost);
		     BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		     StringBuilder content = new StringBuilder();
		     String line;
		     while (null != (line = rd.readLine()) ){
		            content.append(line);
		          }
		            
		            
		            
		     Object obj=JSONValue.parse(content.toString());
		     System.out.println(content.toString());
		     JSONObject finalResult=(JSONObject)obj;
		            //System.out.println(finalResult.get(0));
		     JSONObject json3 = (JSONObject)finalResult.get(0);
		     System.out.println(finalResult.get("created"));
		     if(finalResult.get("created").equals(true))
		     {
		    	 result=true;
		     }
	           

		 } catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
	         // When HttpClient instance is no longer needed,
	         // shut down the connection manager to ensure
	         // immediate deallocation of all system resources
	         httpclient.getConnectionManager().shutdown();
     }
		
		return result;
	}


}
