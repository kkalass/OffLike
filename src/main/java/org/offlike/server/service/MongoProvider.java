package org.offlike.server.service;

import java.net.UnknownHostException;

import com.google.common.base.Strings;
import com.mongodb.DB;
import com.mongodb.Mongo;

public class MongoProvider {
	public DB get() {
		try {

			final Mongo m = new Mongo(getProperty("mongodb.hostname", "localhost"), Integer.parseInt(getProperty("mongodb.port", "27017")));

			// clear the database
			final String dbname = getProperty("mongodb.dbname", "offlike");
			final DB db = m.getDB(dbname);
			final String username = getProperty("mongodb.username");
			final String password = getProperty("mongodb.password");
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
