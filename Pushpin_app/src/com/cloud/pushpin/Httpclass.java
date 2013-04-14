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
	            System.out.println(finalResult.get("access_token"));
	           
	         
	            m.put("boolean", finalResult.get("logged_in"));
	         	m.put("access_token", finalResult.get("access_token"));
	          
	          
	           

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
	
	public Map newlogin (String name, String pass, String email)
	{
		boolean result=false;
		Map m=new HashMap();
		try {
			 JSONObject json = new JSONObject();
			 JSONObject json2= new JSONObject();
			 json.put("username",name);
			 json.put("password", pass);
			 json.put("email", email);
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
		     System.out.println(finalResult.get("created"));
		     m.put("boolean", finalResult.get("created"));
	         m.put("access_token", finalResult.get("access_token"));
	           

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

	public void pushpin(double lat, double longi,String access_token, String message)
	{
		try {
			JSONObject json4= new JSONObject();
            json4.put("lat", lat);
            json4.put("long", longi);
            json4.put("access_token", access_token);
            json4.put("message", message);
            System.out.println(json4);
            StringEntity input2 = new StringEntity(json4.toString());
    		input2.setContentType("application/json");
    		
            
          

            
            HttpPost httppost2 = new HttpPost("http://ec2-54-235-20-117.compute-1.amazonaws.com:3000/pushpin");
            httppost2.addHeader("Content-Type","application/json");
    	    
            httppost2.setEntity(input2);
            HttpResponse response2 = httpclient.execute(httppost2);
            BufferedReader rd2 = new BufferedReader(new InputStreamReader(response2.getEntity().getContent()));
            StringBuilder content2 = new StringBuilder();
            String line2;
            while (null != (line2 = rd2.readLine()) ){
                content2.append(line2);
            }
       
        
            
            System.out.println(content2.toString());
		    
	           

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
	}

}
