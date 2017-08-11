package com.example.securesms;
import java.security.Key;
import java.util.ArrayList;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import android.app.Activity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.sql.ResultSet;


 public class Welcome extends Activity {
	 SQLiteDatabase db=null;

	 EditText ed1;
	 EditText ed2;
	 EditText ed3;
	 Button b1;
	 Button b2;
	 ResultSet rs=null;

 @Override

    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.welcome);
    	ed1=(EditText)findViewById(R.id.nmbr);
    	ed2=(EditText)findViewById(R.id.pwd);
    	ed3=(EditText)findViewById(R.id.txt);
    	b1=(Button)findViewById(R.id.snd);
    	b2=(Button)findViewById(R.id.cncl);
    	db=openOrCreateDatabase("mydb", MODE_PRIVATE, null);

    b2.setOnClickListener(new View.OnClickListener() {
    			public void onClick(View v) {
    						finish();
            }
        });

    		b1.setOnClickListener(new View.OnClickListener() {
    				public void onClick(View v) {
    				
    		String number=ed1.getText().toString();
    		String seckey=ed2.getText().toString();
    		String msgg=ed3.getText().toString();
    		

    		if(number.length()>0  && msgg.length()>0 && seckey.length()==16) {

 

                  byte[] encmsg=encryption(seckey,msgg);
                  String msgx = b2h(encmsg);
                  messagesend(number, msgx);
                 
                  Toast.makeText(getBaseContext(),"Message Successfuly Sent",Toast.LENGTH_SHORT).show();
                  finish();

    		} 
    		
    		else
Toast.makeText(getBaseContext(),"Receiver/Message Field Empty/Key must be 16 digit",Toast.LENGTH_SHORT).show();
    					
    				}

    		});


 }

 public static void messagesend(String number, String encmsg) {

	 try {

 SmsManager s1=SmsManager.getDefault();
 ArrayList<String> parts = s1.divideMessage(encmsg);
s1.sendMultipartTextMessage(number,null,parts,null,null);
} 
	 catch (Exception e) 
	 {

		 e.printStackTrace();
		 }
	 }

 public static String b2h(byte[] b) {
	 String hex = "";
	 String stmp= "";
	 for (int  l= 0; l<b.length; l++) {
		 stmp = Integer.toHexString(b[l] & 0xFF);
		 if (stmp.length() == 1)
			 hex += ("0" + stmp);
		 else
			 hex += stmp;
 }


	 return hex.toUpperCase();
 

    }

 public static byte[] encryption(String seckey,String msgg) {
try
{

	byte[] Array;
	Key k1= generateKey(seckey);
	Cipher c = Cipher.getInstance("AES");
	c.init(Cipher.ENCRYPT_MODE,k1);
	Array = c.doFinal(msgg.getBytes());
	return Array;
} 
catch (Exception e) 
{

	e.printStackTrace();
	byte[] Array = null;
	return Array;

}


 }

 private static Key generateKey(String seckey) throws Exception {
	 Key k1=new SecretKeySpec(seckey.getBytes(), "AES");
 return k1;

    }

}