package com.example.securesms;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;

public class show extends Activity {

 EditText ed1;
 TextView tv1;
 TextView tv2;
 TextView tv3;
 Button b1;
 Button b2;
 String nmbrr = "";
 String mesg = "";

 @Override
 public void onCreate(Bundle savedInstanceState) {
 super.onCreate(savedInstanceState);
 setContentView(R.layout.receive);

 tv1=(TextView) findViewById(R.id.sndr);
 tv2= (TextView) findViewById(R.id.encmsg);
 tv3=(TextView) findViewById(R.id.decmsg);
 ed1=(EditText) findViewById(R.id.pwd);
 b1=(Button) findViewById(R.id.sbmt);
 b2=(Button) findViewById(R.id.cncl2);

 
 Bundle e1=getIntent().getExtras();
 if (e1!=null) {
 nmbrr=e1.getString("nmbrr");
 mesg=e1.getString("mesg");
 tv1.setText(nmbrr);
 tv2.setText(mesg);
 } 
 else 
 {

 Toast.makeText(getBaseContext(), "Error!",Toast.LENGTH_SHORT).show();
 finish();
 }
 b2.setOnClickListener(new View.OnClickListener() {

 public void onClick(View v) {
 finish();

 }
 });
 b1.setOnClickListener(new View.OnClickListener() {
 public void onClick(View v) {

 
 String seckey=ed1.getText().toString();

 if (seckey.length() == 16) {
 try {
byte[] msg = h2b(mesg.getBytes());

byte[] result = decryptSMS(ed1.getText().toString(), msg);
  tv3.setText(new String(result));

 } catch (Exception e) 
 
 {

	 Toast.makeText(getBaseContext(), "Error!",Toast.LENGTH_SHORT).show();
 }

 } else
 Toast.makeText(getBaseContext(),"Enter correct 16 digit message key",Toast.LENGTH_SHORT).show();
 }
 });

 }

 public static byte[] h2b(byte[] b) {
 if ((b.length % 2) != 0)
 throw new IllegalArgumentException("hello");
byte[] b2 = new byte[b.length / 2];
for (int n = 0; n < b.length; n += 2) 
{
 String item = new String(b, n, 2);
 b2[n / 2] = (byte) Integer.parseInt(item, 16);
 }
 return b2;
 }

 public static byte[] decryptSMS(String seckey, byte[] encmsg)

		 throws Exception {

	 Key k1 = generateKey(seckey);
 Cipher c = Cipher.getInstance("AES");
 c.init(Cipher.DECRYPT_MODE, k1);
 byte[] decValue = c.doFinal(encmsg);
 return decValue;
 }
 private static Key generateKey(String seckey) throws Exception {
 Key k1 = new SecretKeySpec(seckey.getBytes(), "AES");
 return k1;
 }

}
