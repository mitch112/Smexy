package com.rs.game.player.starter;

import com.rs.game.player.Player;

/**
 * This class handles the giving of a starter kit.
 * 
 * @author Emperial
 * 
 */
public class Starter {

	public static final int MAX_STARTER_COUNT = 3;

	public static void appendStarter(Player player) {
		String ip = player.getSession().getIP();
		int count = StarterMap.getSingleton().getCount(ip);
		player.starter = 1;
		if (count >= MAX_STARTER_COUNT) {
			return;
		}

		player.getCutscenesManager().play(5);
		player.getInventory().addItem(23256, 100); // attack flask
		player.getInventory().addItem(23280, 100); // str flask
		player.getInventory().addItem(23292, 100); // def flask
		player.getInventory().addItem(386, 500); // shark
		player.getInventory().addItem(542, 1); // monk
		player.getInventory().addItem(544, 1); // monk
		player.getInventory().addItem(995, 2000000); // 2m gp
		player.getInventory().addItem(884, 10000); // iron arrows
		player.getInventory().addItem(841, 1); // short bow
		player.getInventory().addItem(556, 1000); // air rune
		player.getInventory().addItem(1323, 1); // iron scim
		player.getInventory().addItem(1333, 1); // rune scim
		player.getInventory().addItem(558, 1000); // mind rune
		player.getInventory().addItem(554, 1000); // fire rune
		player.softreset = true; 

		player.getHintIconsManager().removeUnsavedHintIcon();
		player.getMusicsManager().reset();
		player.getCombatDefinitions().setAutoRelatie(false);
		player.getCombatDefinitions().refreshAutoRelatie();
		player.getCutscenesManager().play(5);
		StarterMap.getSingleton().addIP(ip);
	}

}
