package com.windhunter.hunterhome.utils;

import java.util.HashMap;

public class LoginManager {

	private static HashMap<String, String> mymap = new HashMap<String, String>();

	public static synchronized void AddLoginflag(String userid, String token) {
		if (token != null && userid != null) {
			mymap.put(userid, token);
		}
	}

	public static synchronized void DelLoginflag(String userid) {
		boolean contains = mymap.containsKey(userid);
		if (contains) {
			mymap.remove(userid);
		}
	}

	public static synchronized String getLoginflag(String userid) {
		if (userid == null) {
			return null;
		}
		return mymap.get(userid);
	}
	
	public static void clearLoginflag() {
		mymap.clear();
	}
}
