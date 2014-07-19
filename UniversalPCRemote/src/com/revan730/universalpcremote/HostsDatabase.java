// This class is used to access database with information about remote hosts 

package com.revan730.universalpcremote;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class HostsDatabase extends SQLiteOpenHelper implements BaseColumns {

	private static final String DATABASE_NAME = "hosts.db";
    private static final int DATABASE_VERSION = 1;
    public static final String HOSTNAME = "hostname";
    public static final String OSTYPE = "OS";
    public static final String IPADRESS = "ip";
    public static final String SSH_PORT = "ssh_p";
    public static final String USERNAME = "login";
    public static final String PASSWORD = "paswd";
    public static final String TABLE_NAME = "hosts_table";
    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE "
			+ TABLE_NAME + " (" + HostsDatabase._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ HOSTNAME + " VARCHAR(255)," + OSTYPE + " VARCHAR(10)," + IPADRESS + " VARCHAR(20)," + SSH_PORT +" INT(5)," + USERNAME + " VARCHAR(255)," + PASSWORD +" VARCHAR(255)" + ");";
	private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS "
			+ TABLE_NAME;
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(SQL_CREATE_ENTRIES);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(SQL_DELETE_ENTRIES);
		onCreate(db);

	}



    public HostsDatabase (Context context) {
	    super(context,DATABASE_NAME,null,DATABASE_VERSION);
	    
    }
}
