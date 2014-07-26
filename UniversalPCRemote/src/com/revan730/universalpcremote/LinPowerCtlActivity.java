package com.revan730.universalpcremote;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class LinPowerCtlActivity extends Activity implements OnClickListener {
	
	SSHSession ses;
	Button rbt;
	Button shtd;
	private String rbtcmd = "/bin/reboot";
	private String shtdcmd = "/bin/shutdown -h now";
	
	public void onCreate(Bundle savedInstanceState){
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.linpwrctl);
		Intent intent = (Intent) getIntent();
		rbt = (Button) findViewById(R.id.lpwrcl_btn_rboot);
		shtd = (Button) findViewById(R.id.lpwrcl_btn_poff);
		try {
			ses = new SSHSession(intent.getStringExtra("ip"), intent.getIntExtra("ssh_port", 22), intent.getStringExtra("user"), intent.getStringExtra("pass"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void onClick(View v) {
		
		switch (v.getId()) {
		
		case R.id.lpwrcl_btn_poff:
			try {
				ses.execute(shtdcmd + " -n");
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
			
		case R.id.lpwrcl_btn_rboot:
			try {
				ses.execute(rbtcmd);
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		}
		
	}

}
