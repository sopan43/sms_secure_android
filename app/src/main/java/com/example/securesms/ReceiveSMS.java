package com.example.securesms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

public class ReceiveSMS extends BroadcastReceiver {

 @Override
 public void onReceive(Context context, Intent intent) {

 Bundle b1=intent.getExtras();

 
 Object[] o1 = (Object[]) b1.get("pdus");
 SmsMessage s1[] = new SmsMessage[o1.length]; 
 Intent i1=new Intent(context,show.class);
 i1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
 i1.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
 String mesg = "";
 String nmbrr = "";
 StringBuffer sbuffer = new StringBuffer();

 for (int i=0; i<o1.length; i++) {

 s1[i]=SmsMessage.createFromPdu((byte[]) o1[i]);
 mesg=s1[i].getDisplayMessageBody();
   nmbrr=s1[i].getDisplayOriginatingAddress();
 sbuffer.append(mesg);
  abortBroadcast();

 }
  i1.putExtra("nmbrr", nmbrr);
 i1.putExtra("mesg", new String(sbuffer));
 context.startActivity(i1);
 
 }

}
