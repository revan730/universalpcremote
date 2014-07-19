// Adapter class for listview on main activity

package com.revan730.universalpcremote;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.ImageView;

public class HostListAdapter extends BaseAdapter {
	Context ctx;
	LayoutInflater linflater;
	ArrayList<Host> objects;
	final String LOG_TAG = "myLogs";
	
	HostListAdapter (Context context,ArrayList<Host> host){
		ctx = context;
		objects = host;
		linflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return objects.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return objects.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		if (view == null) {
			view =linflater.inflate(R.layout.hostlistitem, parent, false);
		}
		
		Host h = getHost(position);
		
		((TextView) view.findViewById(R.id.ma_lvi_tvName)).setText(h.name);
		Log.d(LOG_TAG,"OS Type : " + h.os);
	if (h.os.equals("win")){((ImageView) view.findViewById(R.id.ma_lvi_ivIcon)).setImageResource(R.drawable.ic_win);
	    Log.d(LOG_TAG,"Win icon set");
	}
	else if (h.os.equals("linux")) {
		((ImageView) view.findViewById(R.id.ma_lvi_ivIcon)).setImageResource(R.drawable.ic_arch);
		Log.d(LOG_TAG, "Linux icon set");
	}
		
		return view;
	}
	
	Host getHost(int position){
		return ((Host) getItem(position));
	}

}
