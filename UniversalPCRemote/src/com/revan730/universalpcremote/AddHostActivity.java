// Activity that gathers information about host from user and adds it to database


package com.revan730.universalpcremote;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class AddHostActivity extends Activity implements OnClickListener {
	
	TextView tvName;
	TextView tvOS;
	TextView tvIP;
	TextView tvSSHPort;
	TextView tvUser;
	TextView tvPassword;
	EditText etName;
	EditText etIP;
	EditText etSSHPort;
	EditText etUser;
	EditText etPassword;
	Spinner spnOS;
	Button btnSave;
	String spnsel;

	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addhost);
		 String [] items = {"Linux","Windows"}; 
		tvName = (TextView) findViewById(R.id.ah_tv_name);
		tvOS = (TextView) findViewById(R.id.ah_tv_os);
		tvIP = (TextView) findViewById(R.id.ah_tv_ip);
		tvSSHPort = (TextView) findViewById(R.id.ah_tv_sp);
		tvUser = (TextView) findViewById(R.id.ah_tv_usr);
		tvOS = (TextView) findViewById(R.id.ah_tv_passwd);
		etName = (EditText) findViewById(R.id.ah_et_name);
		etIP = (EditText) findViewById(R.id.ah_et_ip);
		etSSHPort = (EditText) findViewById(R.id.ah_et_sp);
		etUser = (EditText) findViewById(R.id.ah_et_usr);
		etPassword = (EditText) findViewById(R.id.ah_et_passwd);
		spnOS = (Spinner) findViewById(R.id.ah_spn_os);
		btnSave = (Button) findViewById(R.id.ah_btn_save);
		btnSave.setOnClickListener(this);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnOS.setAdapter(adapter);
        spnOS.setPrompt("Select OS");
        spnOS.setSelection(0);
        spnOS.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                int position, long id) {
              switch (position){
              case 0:
            	  spnsel = "linux";
            	  break;
            	  
              case 1:
            	  spnsel = "win";
            	  break;
              }
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
          });
		
		
}

	

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ah_btn_save:
			HostsDatabase sqh = new HostsDatabase(this);
			SQLiteDatabase sqdb = sqh.getWritableDatabase();
			ContentValues cv = new ContentValues();
			cv.put(sqh.HOSTNAME, etName.getText().toString());
			cv.put(sqh.OSTYPE, spnsel); //HARDCODE for backward database compatibility
			cv.put(sqh.IPADRESS, etIP.getText().toString());
			cv.put(sqh.SSH_PORT, etSSHPort.getText().toString());
			cv.put(sqh.USERNAME, etUser.getText().toString());
			cv.put(sqh.PASSWORD, etPassword.getText().toString());
			sqdb.insert(sqh.TABLE_NAME, sqh.HOSTNAME, cv);
			sqdb.close();
			sqh.close();
		Toast toast =	Toast.makeText(getApplicationContext(),"added",Toast.LENGTH_SHORT);
			toast.show();
			
		}
		
	}
}
