package com.rs.game.player.controlers;

import com.rs.Settings;
import com.rs.game.Animation;
import com.rs.game.World;
import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.npc.NPC;
import com.rs.game.player.DominionTower;

public class Kiln extends Controler {

	@Override
	public void start() {

	}

	public int getMode() {
		return (Integer) getArguments()[0];
	}

	private NPC Tentacles;
	private NPC Tentacles1;
	private NPC Tentacles2;
	private static NPC Harken;
	private int harktim;

	@Override
	public boolean processObjectClick1(WorldObject object) {
	/*	if (object.getId() == 62682 || object.getId() == 62683
				|| object.getId() == 62690) {
			player.getDominionTower().destroyArena(false, getMode());
			return false;
		}
		return true;*/
		return true;
	}
	public static void setHark(NPC n){
		Harken = n;
	}
	@Override
	public boolean sendDeath() {
	/*	if (Tentacles != null){
			Tentacles.setNextAnimation(new Animation(Tentacles.getCombatDefinitions().getDeathEmote()));
			Tentacles.removeTarget();
         	World.removeNPC(Tentacles);
         }
    if (Tentacles1 != null){
    	Tentacles1.setNextAnimation(new Animation(Tentacles1.getCombatDefinitions().getDeathEmote()));
    	Tentacles1.removeTarget();
          	World.removeNPC(Tentacles1);
          }
    if (Tentacles2 != null){
    	Tentacles2.setNextAnimation(new Animation(Tentacles2.getCombatDefinitions().getDeathEmote()));
    	Tentacles2.removeTarget();
           	World.removeNPC(Tentacles2);
           }
    	Harken.setNextAnimation(new Animation(16235));
		World.removeNPC(Harken);
    	Harken = null;
		player.setNextWorldTile(Settings.RESPAWN_PLAYER_LOCATION);
		player.sm("You have failed, please try harder next time!");
		player.getKiln().destroyArena(true);
		return false;
	}*/
		return true;
	}
	@Override
	public void process() {
		if (Harken == null){
		if (harktim > 0){
			harktim--;
		}
		if (harktim == 0){
			player.sm("Please click on the floating orb to start the kiln, be carefulle you will lose your fcape!");
			harktim = 15;
		}
		}
		
	}

	@Override
	public boolean processMagicTeleport(WorldTile toTile) {
		return false;
	}

	@Override
	public boolean processItemTeleport(WorldTile toTile) {
		return false;
	}

	@Override
	public boolean login() {
		if (player.isDead() || getArguments().length == 0)
			return true;
		return false;
	}

	@Override
	public boolean logout() {
	//	player.getKiln().destroyArena(true);
		return false;
	}

}
