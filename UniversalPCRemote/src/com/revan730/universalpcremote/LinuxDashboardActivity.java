//Activity with dashboard with shortcuts for all supported Linux applications controls

package com.revan730.universalpcremote;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.Toast;

public class LinuxDashboardActivity extends Activity implements OnClickListener {
	
	ImageButton btnRmb;
	
	public void onCreate(Bundle savedInstanceState){
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.linuxdashboard);
		btnRmb = (ImageButton) findViewById(R.id.ldsh_btn_rhythmbox);
		btnRmb.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {

			switch (v.getId()) {
			case R.id.ldsh_btn_rhythmbox:
			Intent dashintent = getIntent();
			Intent rtmintent = new Intent (this,RhythmboxCtlActivity.class);
			rtmintent.putExtra("ip", dashintent.getStringExtra("ip"));
			rtmintent.putExtra("ssh_port", dashintent.getIntExtra("ssh_port",22));
			rtmintent.putExtra("user", dashintent.getStringExtra("user"));
			rtmintent.putExtra("pass", dashintent.getStringExtra("pass"));
			startActivity(rtmintent);
			break;
			
			case R.id.ldsh_btn_power:
				Intent dshintent = getIntent();
				Intent pwrintent = new Intent (this,LinPowerCtlActivity.class);
				pwrintent.putExtra("ip", dshintent.getStringExtra("ip"));
				pwrintent.putExtra("ssh_port", dshintent.getIntExtra("ssh_port",22));
				pwrintent.putExtra("user", dshintent.getStringExtra("user"));
				pwrintent.putExtra("pass", dshintent.getStringExtra("pass"));
				startActivity(pwrintent);	
			
			}
			
	}

}
