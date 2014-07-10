package com.revan730.universalpcremote;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class LinuxDashboardActivity extends Activity implements OnClickListener {
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.linuxdashboard);
		//Intent intent = getIntent();
		//try {
			//SSHSession ses = new SSHSession(intent.getStringExtra("ip"), intent.getIntExtra("ssh_port", 22), intent.getStringExtra("user"), intent.getStringExtra("pass"));
			//String response = ses.execute("rhythmbox-client --no-start --play-pause");
			//Log.d("myLogs", "Response =" + response);
		//} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			//Log.d("myLogs",e.toString());
		//}
		
		
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.ldsh_btn_rhythmbox:
			Intent dashintent = getIntent();
			Intent rtmintent = new Intent(this,RhythmboxCtlActivity.class);
			rtmintent.putExtra("ip", dashintent.getStringExtra("ip"));
			rtmintent.putExtra("ssh_port", dashintent.getIntExtra("ssh_port",22));
			rtmintent.putExtra("user", dashintent.getStringExtra("user"));
			rtmintent.putExtra("pass", dashintent.getStringExtra("pass"));
			startActivity(rtmintent);
			
			
		}
		
	}

}
