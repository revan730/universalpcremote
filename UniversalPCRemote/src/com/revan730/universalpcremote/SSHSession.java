package com.revan730.universalpcremote;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.util.Properties;

import android.util.Log;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelShell;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class SSHSession {
	//String ip;
	//int port;
	//String user;
	//String password;
	JSch jsch;
	Session session;

	SSHSession (String ip, int port, String user,String password ) throws Exception {
		Log.d("myLogs", "user =" + user + "p=" + password);
		JSch.setLogger(new MyLogger());
		 jsch = new JSch();
		 session = jsch.getSession("revan730", ip, port);
		session.setPassword(password.toString());
		 Properties prop = new Properties();
		  prop.put("StrictHostKeyChecking", "no");
		  prop.put("PreferredAuthentications", "password");
		  session.setConfig(prop);

		  session.connect();
		
	}
	
	public String execute(String command) throws Exception{
		 ChannelExec channelssh = (ChannelExec) session.openChannel("exec");
		 	        
ByteArrayOutputStream baos = new ByteArrayOutputStream();
channelssh.setOutputStream(baos);
channelssh.setInputStream(null);
InputStream iaos = channelssh.getInputStream();
channelssh.setCommand("env DISPLAY=:0.0 /bin/rhythmbox-client --no-start --next");
channelssh.connect();
channelssh.disconnect();
session.disconnect();

return "id =" + channelssh.getExitStatus();
	}
	
	 public static class MyLogger implements com.jcraft.jsch.Logger {
		 static java.util.Hashtable name=new java.util.Hashtable();
		 static{
		 name.put(new Integer(DEBUG), "DEBUG: ");
		 name.put(new Integer(INFO), "INFO: ");
		 name.put(new Integer(WARN), "WARN: ");
		 name.put(new Integer(ERROR), "ERROR: ");
		 name.put(new Integer(FATAL), "FATAL: ");
		 }
		 public boolean isEnabled(int level){
		 return true;
		 }
		 public void log(int level, String message){
		 Log.d("myLogs",name.get(new Integer(level)).toString());
		 Log.d("myLogs",message);
		 }
		 }
}
