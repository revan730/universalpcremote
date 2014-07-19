package com.revan730.universalpcremote;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class RhythmboxCtlActivity extends Activity implements OnClickListener {
	
	Button prev;
	Button plps;
	Button next;
	SSHSession ses;
	private String com = "env DISPLAY=:0.0 /bin/rhythmbox-client --no-start";
	
	public void onCreate (Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.rhythmboxctl);
		Intent intent = (Intent) getIntent();
		prev = (Button) findViewById(R.id.rtmcl_btn_prev);
		plps = (Button) findViewById(R.id.rtmcl_btn_plps);
		next = (Button) findViewById(R.id.rtmcl_btn_next);
		prev.setOnClickListener(this);
		plps.setOnClickListener(this);
		next.setOnClickListener(this);
		try {
			ses = new SSHSession(intent.getStringExtra("ip"), intent.getIntExtra("ssh_port", 22), intent.getStringExtra("user"), intent.getStringExtra("pass"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}
	
	public void onPause(){
		super.onPause();
		ses.disconnect();
	}
	
	public void onDestroy(){
		super.onDestroy();
		ses.disconnect();
	}
	
	public void onResume(){
		super.onResume();
		ses.reconnect();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.rtmcl_btn_prev:
			try {
				ses.execute(com + " --previous");
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case R.id.rtmcl_btn_plps:
			try {
				ses.execute(com + " --play-pause");
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case R.id.rtmcl_btn_next:
			try {
				ses.execute(com + " --next");
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		}
	}

}
