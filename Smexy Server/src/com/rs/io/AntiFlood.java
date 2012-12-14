package com.rs.io;

import java.util.ArrayList;

import com.rs.Settings;

/**
 * Anti Flood
 * 
 * @Author Apache Ah64
 */
public class AntiFlood {

	private static ArrayList<String> connections = new ArrayList<String>(
			Settings.PLAYERS_LIMIT * 3);

	public static boolean contains(String ip) {
		if (connections.contains(ip))
			return true;
		return false;
	}

	public static void add(String ip) {
		// if(!connections.contains(ip))
		connections.add(ip);
	}

	public static void remove(String ip) {
		if (connections.contains(ip))
			connections.remove(ip);
	}

	public static int getSessionsIP(String ip) {
		int amount = 1;
		for (int i = 0; i < connections.size(); i++) {
			if (connections.get(i).equalsIgnoreCase(ip))
				amount++;
		}
		return amount;
	}

	public static ArrayList<String> getConnections() {
		return connections;
	}
}