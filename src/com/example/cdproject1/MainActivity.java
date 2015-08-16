package com.example.cdproject1;



import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.*;









public class MainActivity extends Activity {
	Button send;
	EditText content;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		send=(Button) findViewById(R.id.button1);
		content=(EditText) findViewById(R.id.editText1);
		send.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String contnt=content.getText().toString();
			if(contnt.matches("send message(.*) to (.*)"))
			{	
			     Pattern p = Pattern.compile(".*to ([0-9]{10}$)");
				 Matcher m = p.matcher(contnt);
				// int x=contnt.indexOf(m.group(1));
				 String msg="send message ";
				 int len=msg.length();
				
				if(m.find())
				{	
					int x=contnt.indexOf(m.group(1));
				 String body=contnt.substring(len, x-3);
			//	 Toast.makeText(getApplicationContext(), body, Toast.LENGTH_SHORT).show();
				 String num=m.group(1);
			//		Toast.makeText(getApplicationContext(), num, Toast.LENGTH_SHORT).show();
					String text=body;
			//		Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
                    try{
						
						SmsManager mng=SmsManager.getDefault();
						mng.sendTextMessage(num, null, text, null, null);
						
						Toast.makeText(getApplicationContext(), "message sent!!", Toast.LENGTH_SHORT).show();
						
						
						
					}
					catch(Exception e){
						
						
						
						Toast.makeText(getApplicationContext(), "message not sent exception!!", Toast.LENGTH_SHORT).show();
					}
					
					
					
					
					
					
					
					
					
					

				}
				else
				{
					 Toast.makeText(getApplicationContext(),"Enter Valid Number", Toast.LENGTH_SHORT).show();

				}
			}

			else if(contnt.matches("show directions from (.*) to (.*)"))
			{
			//	int x=contnt.indexOf("from");
			//	int y=contnt.indexOf("to");
				String str=m.group(0);
			//	System.out.println(str);
				String str1=m.group(1)
			//		System.out.println(str1);
				Intent intent = new Intent(android.content.Intent.ACTION_VIEW, 
						  Uri.parse("http://maps.google.com/maps?saddr="+str+"&daddr="+str1));
				intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
					startActivity(intent);

			}
			else if(contnt.matches("search for (.*)"))
			{
				int x=contnt.indexOf("for");
				String str=contnt.substring(x+4,contnt.length());
				Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
				intent.putExtra(SearchManager.QUERY, str); // query contains search string
				startActivity(intent);
				
			}
		
			else if(contnt.matches("make a call to (.*)"))
			{
				Pattern p = Pattern.compile(".*to ([0-9]{10})");
				 Matcher m = p.matcher(contnt);
				// int x=contnt.indexOf(m.group(1));
			//	 String msg="send message ";
			//	 int len=msg.length();
				 if(m.find()){
					 String num=m.group(1);
				Intent callIntent = new Intent(Intent.ACTION_CALL);
				callIntent.setData(Uri.parse("tel:"+num));
				startActivity(callIntent);}
				 else
				 {
					 Toast.makeText(getApplicationContext(),"Enter Valid Number", Toast.LENGTH_SHORT).show();
				 
				 }
			}
			else if(contnt.matches("send email (.*) to (.*)"))
			{
				 Pattern p = Pattern.compile(".*to (.*@.*\\.com)");
				 Matcher m = p.matcher(contnt);
					 String msg="send email ";
					 int len=msg.length();
					 if(m.find())
						{	
						//	int x=contnt.indexOf(m.group(1));
						 String body=m.group(0);
					//	 Toast.makeText(getApplicationContext(), body, Toast.LENGTH_SHORT).show();
						 String email1=m.group(1);
						 Intent email = new Intent(Intent.ACTION_SEND);  
		                  email.putExtra(Intent.EXTRA_EMAIL, new String[]{ email1});  
		                  email.putExtra(Intent.EXTRA_SUBJECT, " ");  
		                  email.putExtra(Intent.EXTRA_TEXT, body);  
		       
		                  //need this to prompts email client only  
		                  email.setType("message/rfc822");  
		       
		                  startActivity(Intent.createChooser(email, "Choose an Email client :"));  
		                  //startActivity(Intent.createChooser(email, gmail));  
 
						

						 
												
						}
						else
						{
							 Toast.makeText(getApplicationContext(),"Enter Valid Email ID", Toast.LENGTH_SHORT).show();

						}

					 
			}
			else{
				 Toast.makeText(getApplicationContext(),"look out help for the proper format", Toast.LENGTH_SHORT).show();

			}
			}
		});
		
	}
	
	  public boolean onCreateOptionsMenu(Menu menu)
	    {
	        MenuInflater menuInflater = getMenuInflater();
	        menuInflater.inflate(R.layout.menu1, menu);
	        return true;
	    }
	    
	    /**
	     * Event Handling for Individual menu item selected
	     * Identify single menu item by it's id
	     * */
	    @Override
	    public boolean onOptionsItemSelected(MenuItem item)
	    {
	        
	        switch (item.getItemId())
	        {
	        case R.id.menu_bookmark:
	        	
	        	Intent intent3=new Intent(MainActivity.this,Help.class);
				startActivity(intent3);
	            return true;

	        default:
	            return super.onOptionsItemSelected(item);
	        }
	    }


}
