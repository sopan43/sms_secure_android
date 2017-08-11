package com.example.securesms;
import android.os.Bundle;
import android.view.View;
import android.app.Activity;
import android.content.Intent;

public class MainActivity extends Activity {

	Intent i=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void login_sigin(View v)
	{
		switch(v.getId())
		{
		case R.id.log_in:
			i=new Intent(this,Login.class);
			startActivityForResult(i, 500);
			overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left); 
			break;
		case R.id.sign_in:
			i=new Intent(this,Signin.class);
			startActivityForResult(i, 500);
			overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);;
			break;	
		}
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
	} 
}