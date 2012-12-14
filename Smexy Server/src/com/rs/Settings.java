package com.rs;

import com.rs.game.WorldTile;

public final class Settings {

	// client/server settings
	public static final String SERVER_NAME = "Smexy";
	public static final String WEBSITE_LINK = "http://SmexyServer.com";
	public static final String ITEMLIST_LINK = "http://www.itemdb.biz/";
	public static final String ITEMDB_LINK = "http://itemdb.biz/";
	public static final String VOTE_LINK = "http://smexyserver.com/vote/";
	public static final String HIGHSCORES_LINK = "http://smexyserver.com/highscores/";
	public static final String WIKI_LINK = "http://smexyserver.com/wiki/";
	public static final String DONATE_LINK = "http://smexyserver.com/don/";
	public static final String LASTEST_UPDATE = "You can help us gain more players using the ::vote command daily.";
	public static final int PORT_ID = 43594;
	public static final String CACHE_PATH = "data/cache/";
	public static final int RECEIVE_DATA_LIMIT = 7500;
	public static final int PACKET_SIZE_LIMIT = 7500;
	public static final int CLIENT_BUILD = 667;
	public static final int CUSTOM_CLIENT_BUILD = 1;


	public static boolean DEBUG;
	public static boolean HOSTED;

	// GUI Settings
	public static final String GUI_SIGN = "V 1.0";
	public static final String LINE_SEPARATOR = System
			.getProperty("line.separator");
	// world settings
	public static final int START_PLAYER_HITPOINTS = 100;
	public static final WorldTile START_PLAYER_LOCATION = new WorldTile(5888,4705,0);
	//old location below
//	public static final WorldTile START_PLAYER_LOCATION = new WorldTile(2849,10222,0);//new WorldTile(3182, 5713, 0);//new WorldTile(2966, 3392, 0); 2649, 9393, 0);// - I
						// got an amazing idea for this area
	public static final String START_CONTROLER = "StartTutorial"; // "NewHomeControler";//
	public static final WorldTile RESPAWN_PLAYER_LOCATION = new WorldTile(5888,4705, 0); 
	//old location below
//	public static final WorldTile RESPAWN_PLAYER_LOCATION = new WorldTile(2849,10219, 0); //new WorldTile(2966, 3387, 0);// //
	public static final long MAX_PACKETS_DECODER_PING_DELAY = 30000; // 30seconds
	public static final int WORLD_CYCLE_TIME = 600; // the speed of world in ms
	public static final int XP_RATE = 1; // x75 for combat, x50 for skills
	public static final int SKILLING_XP_RATE = 1;
	public static final int COMBAT_XP_RATE = 1;
	public static final int AIR_GUITAR_MUSICS_COUNT = 50;
	// mem settings
	public static final int PLAYERS_LIMIT = 2000;
	public static final int LOCAL_PLAYERS_LIMIT = 250;
	public static final int NPCS_LIMIT = Short.MAX_VALUE;
	public static final int LOCAL_NPCS_LIMIT = 1000;
	public static final int MIN_FREE_MEM_ALLOWED = 30000000; // 30mb
	// game constants
	public static final int[] MAP_SIZES = { 104, 120, 136, 168 };
	public static final int[] GRAB_SERVER_KEYS = { 1362, 77448, 44880, 39771,
			24563, 363672, 44375, 0, 1614, 0, 5340, 142976, 741080, 188204,
			358294, 416732, 828327, 19517, 22963, 16769, 1244, 11976, 10, 15,
			119, 817677, 1624243};

	public static String[] DONATOR_ITEMS = {};

	public static String[] EARNED_ITEMS = { "castle wars ticket", "(class",
			"sacred clay", "dominion" };

	private Settings() {

	}
}
