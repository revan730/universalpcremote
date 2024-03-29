/*Main activity.Used to select remote host from list and call Linux or Windows dashboard activity,
call AddHostActivity to add hosts to database */

package com.revan730.universalpcremote;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnItemClickListener {
	ArrayList<Host> hosts = new ArrayList<Host>();
	HostListAdapter hostAdapter;
	HostsDatabase sqh;
	SQLiteDatabase sqdb;
	ListView lvHosts;
	final String[] options = {"Edit","Remove"};
	int clickeditem;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);		
		HostsDatabase sqh = new HostsDatabase(this);
		SQLiteDatabase sqdb = sqh.getWritableDatabase();
		
		Cursor cursor = sqdb.query(sqh.TABLE_NAME, null , null, null, null, null, null);
		while (cursor.moveToNext()) {
			hosts.add(new Host(cursor.getInt(cursor.getColumnIndex(sqh._ID)),cursor.getString(cursor.getColumnIndex(sqh.HOSTNAME)),cursor.getString(cursor.getColumnIndex(sqh.OSTYPE)),cursor.getString(cursor.getColumnIndex(sqh.IPADRESS)),cursor.getInt(cursor.getColumnIndex(sqh.SSH_PORT)),cursor.getString(cursor.getColumnIndex(sqh.USERNAME)),cursor.getString(cursor.getColumnIndex(sqh.PASSWORD))));
		}
		cursor.close();
		
		hostAdapter = new HostListAdapter(this,hosts);
		ListView lvHosts = (ListView) findViewById(R.id.pcselectlistView);
		lvHosts.setAdapter(hostAdapter);
		lvHosts.setOnItemClickListener(this);
		LongClickL clickl = new LongClickL();
		lvHosts.setOnItemLongClickListener(clickl);

		
		sqdb.close();
		sqh.close();

	}
	
	public boolean onCreateOptionsMenu(Menu menu){
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item){
		switch (item.getItemId()){
		case R.id.ma_mn_ahact_itm:
			Intent intent = new Intent(this, AddHostActivity.class);
			startActivity(intent);
			return true;
		}
		return true;
	}
	

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
		String ip;
		int ssh_port;
		String user;
		String pass;
		if (hosts.get(position).os.equals("win")) {
			Toast.makeText(getApplicationContext(), "Sorry,Windows is not supported yet", Toast.LENGTH_LONG).show();
		} else {
			ip = hosts.get(position).ip.toString();
			ssh_port = hosts.get(position).ssh_port;
			user = hosts.get(position).user.toString();
			pass = hosts.get(position).passwd.toString();
			Intent intent =new Intent(this,LinuxDashboardActivity.class);
			intent.putExtra("ip", ip);
			intent.putExtra("ssh_port", ssh_port);
			intent.putExtra("user", user);
			intent.putExtra("pass", pass);
			startActivity(intent);
		}
	}
	
	private class LongClickL implements OnItemLongClickListener {

		@Override
		public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
				int position, long arg3) {
			clickeditem = hosts.get(position).id;
			AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
			builder.setTitle("Select option").setItems(options, new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int item) {	
					
					switch (item){
					case 0:
						Intent intent = new Intent(getApplicationContext(),AddHostActivity.class);
						intent.putExtra("id", clickeditem);
						startActivity(intent);
					case 1:
						HostsDatabase sqh = new HostsDatabase(getApplicationContext());
						SQLiteDatabase sqdb = sqh.getWritableDatabase();
						Log.i("myLogs","item id = " + clickeditem);
						sqdb.delete(sqh.TABLE_NAME, sqh._ID +" = " + clickeditem, null);
						sqh.close();
						sqdb.close();
					}
					
					
				}
			});
			AlertDialog dialog = builder.create();
			dialog.show();
			

			return false;
		}
		
	}

}
