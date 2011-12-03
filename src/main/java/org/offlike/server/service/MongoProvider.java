package org.offlike.server.service;

import java.net.UnknownHostException;

import com.google.common.base.Strings;
import com.mongodb.DB;
import com.mongodb.Mongo;

public class MongoProvider {
	private String hostname = getProperty("mongodb.hostname", "localhost");
	private int port = Integer.parseInt(getProperty("mongodb.port", "27017"));
	private String dbname = getProperty("mongodb.dbname", "offlike");
	private String username = getProperty("mongodb.username");
	private String password = getProperty("mongodb.password");
	private String url;
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public void setDbname(String dbname) {
		this.dbname = dbname;
	}
	
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setPort(int port) {
		this.port = port;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public DB get() {
		try {

			
			System.out.println("********************");
			System.out.println("uri" + url);
			System.out.println("db-url: mongodb://" + username + ":" + password + "@"+ hostname + ":" + port+"/"+dbname);
			System.out.println("********************");
			final Mongo m = new Mongo(hostname, port);
			final DB db = m.getDB(dbname);
			
			if (username != null && password != null) {
				db.authenticate(username, password.toCharArray());
			}

			return db;
		} catch (UnknownHostException e) {
			throw new RuntimeException(e);
		}
	}
	

	private static String getProperty(String propname) {
		return getProperty(propname, null);
	}
	
	private static String getProperty(String propname, String defaultValue) {
		// self-implemented version of System.getProperty(name, default);
		// because we want to treat empty strings as missing values.
		final String v = System.getProperty(propname);
		if (Strings.isNullOrEmpty(v)) {
			return defaultValue;
		}
		return v;
	}
	
}
