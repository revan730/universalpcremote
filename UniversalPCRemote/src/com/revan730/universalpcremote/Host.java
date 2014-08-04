/* This class is used to store information about remote host (such as ip or OS)
	 for it's further use,like transfering connection data to ssh client class */

package com.revan730.universalpcremote;

public class Host {
	
	int id;
    String name;
    String os;
    String ip;
    int ssh_port;
    String user;
    String passwd;
    int image;
    
    
    Host (int _id, String _name, String _os, String _ip, int _ssh_port, String _user, String _passwd ) {
    	id= _id;
    	name = _name;
    	os = _os;
    	ip = _ip;
    	ssh_port =  _ssh_port;
    	user = _user;
    	passwd = _passwd;
    	image = R.drawable.ic_launcher;
    }
}
