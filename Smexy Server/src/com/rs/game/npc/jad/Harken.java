/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rs.game.npc.jad;

import com.rs.cores.CoresManager;
import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.World;
import com.rs.game.player.content.Magic;
import com.rs.game.WorldTile;
import com.rs.game.npc.NPC;
import com.rs.game.npc.combat.NPCCombatDefinitions;
import com.rs.game.player.Player;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Misc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Owner
 */
public class Harken extends NPC {
	private static NPC[] tents;
	private int spawnedtents;
	public Player killer;
	public static NPC ntp;
	public static NPC tenta;
	public static NPC tenta1;
	public static NPC tenta2;
	public WorldTile[] tentspawns = {new WorldTile(2849,5924,0), new WorldTile(2850,5939,0), new WorldTile(2853,5932,0)};

    public Harken(Player p, int id, int mapAreaNameHash,
            boolean canBeAttackFromOutOfArea, boolean spawned) {
    	super(id, new WorldTile(2846,5929,0), mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
            this.setNextFaceWorldTile(new WorldTile(2850, 5932, 0));
            this.setNextAnimation(new Animation(16232));
            this.killer = p;
            this.ntp = this;
            this.getCombatDefinitions().setMaxHit(500);
            this.getCombatDefinitions().setAttackStyle(getRandomAttack());
            this.getCombatDefinitions().setHitpoints(19500);
            this.setCantFollowUnderCombat(true);
            WorldTasksManager.schedule(new WorldTask() {

                int loop;

                @Override
                public void run() {
                    if (loop == 1600){
                    	destroying();
                    	stop();  
                    }
                    loop ++;
                }
            }, 0, 1);
            
    }
    public int randomTent(){
    	int j = Misc.random(1);
    	switch (j){
    	case 0:
    	return 15209;
    	case 1:
    	return 15210;
    	}
    	return 0;
    	}
	public void spawnTents() {
			switch (Misc.random(tentspawns.length)){
			case 0:
				tenta = new NPC(randomTent(), tentspawns[0],
							-1, true, true);
					tenta.setCantFollowUnderCombat(true);
					tenta.setForceAgressive(true);
					tenta.setAtMultiArea(true);
					tenta.setNextFaceWorldTile(new WorldTile(2850,5924,0));
					tenta.setNextAnimation(new Animation(16241));
					tenta.getCombatDefinitions().setHitpoints(1450);
					tenta.getCombatDefinitions().setMaxHit(475+Misc.random(50));
					tenta.getCombatDefinitions().setAttackStyle(getRandomAttack());
					tenta.getCombatDefinitions().setAttackEmote(16239);
					tenta.getCombatDefinitions().setDeathEmote(16240);
					break;
			case 1:
				tenta1 = new NPC(randomTent(), tentspawns[1],
						-1, true, true);
				tenta1.setCantFollowUnderCombat(true);
				tenta1.setForceAgressive(true);
				tenta1.setAtMultiArea(true);
				tenta1.setNextFaceWorldTile(new WorldTile(2850,5937,0));
				tenta1.setNextAnimation(new Animation(16241));
				tenta1.getCombatDefinitions().setHitpoints(1450);
				tenta1.getCombatDefinitions().setMaxHit(475+Misc.random(50));
				tenta1.getCombatDefinitions().setAttackStyle(getRandomAttack());
				tenta1.getCombatDefinitions().setAttackEmote(16239);
				tenta1.getCombatDefinitions().setDeathEmote(16240);
				break;
			case 2:
				tenta2 = new NPC(randomTent(), tentspawns[2],
						-1, true, true);
				tenta2.setCantFollowUnderCombat(true);
				tenta2.setForceAgressive(true);
				tenta2.setAtMultiArea(true);
				tenta2.setNextFaceWorldTile(new WorldTile(2852,5932,0));
				tenta2.setNextAnimation(new Animation(16241));
				tenta2.getCombatDefinitions().setHitpoints(1450);
				tenta2.getCombatDefinitions().setMaxHit(475+Misc.random(50));
				tenta2.getCombatDefinitions().setAttackStyle(getRandomAttack());
				tenta2.getCombatDefinitions().setAttackEmote(16239);
				tenta2.getCombatDefinitions().setDeathEmote(16240);
				break;
		}
	}
	public int getRandomAttack(){
		int ui = Misc.random(100);
		if (ui > 70)
			return 1;
		else if (ui > 20)
			return 2;
		else
			return 0;
		
	}
	public void spawnBlocks() {
		switch (Misc.random(3)){
		case 0:
			NPC tenta = tents[spawnedtents++] = new NPC(randomTent(), new WorldTile(2850,5292,0),
						-1, true, true);
				tenta.setCantFollowUnderCombat(true);
				tenta.setForceAgressive(true);
				tenta.setAtMultiArea(true);
				tenta.getCombatDefinitions().setMaxHit(275);
				break;
		case 1:
			NPC tenta1 = tents[spawnedtents++] = new NPC(randomTent(), new WorldTile(2853,5292,0),
					-1, true, true);
			tenta1.setCantFollowUnderCombat(true);
			tenta1.setForceAgressive(true);
			tenta1.setAtMultiArea(true);
			tenta1.getCombatDefinitions().setMaxHit(275);
			break;
		case 2:
			NPC tenta2 = tents[spawnedtents++] = new NPC(randomTent(), new WorldTile(2850,5292,0),
					-1, true, true);
			tenta2.setCantFollowUnderCombat(true);
			tenta2.setForceAgressive(true);
			tenta2.setAtMultiArea(true);
			tenta2.getCombatDefinitions().setMaxHit(275);
			break;
	}
}
    @Override
    public ArrayList<Entity> getPossibleTargets() {
        ArrayList<Entity> possibleTarget = new ArrayList<Entity>();
        for (int regionId : getMapRegionsIds()) {
            List<Integer> playerIndexes = World.getRegion(regionId).getPlayerIndexes();
            if (playerIndexes != null) {
                for (int npcIndex : playerIndexes) {
                    Player player = World.getPlayers().get(npcIndex);
                    if (player == null
                            || player.isDead()
                            || player.hasFinished()
                            || !player.isRunning()
                            || !player.withinDistance(this, 64)
                            || ((!isAtMultiArea() || !player.isAtMultiArea())
                            && player.getAttackedBy() != this && player.getAttackedByDelay() > System.currentTimeMillis())
                            || !clipedProjectile(player, false)) {
                        continue;
                    }
                    possibleTarget.add(player);
                }
            }
        }
        return possibleTarget;
    }

    /*
     * gotta override else setRespawnTask override doesnt work
     */
    public static void destroying(){
    if (tenta != null){
         	tenta.setNextAnimation(new Animation(tenta.getCombatDefinitions().getDeathEmote()));
         	tenta.removeTarget();
         	World.removeNPC(tenta);
         }
    if (tenta1 != null){
          	tenta1.setNextAnimation(new Animation(tenta1.getCombatDefinitions().getDeathEmote()));
          	tenta1.removeTarget();
          	World.removeNPC(tenta1);
          }
    if (tenta2 != null){
           	tenta2.setNextAnimation(new Animation(tenta2.getCombatDefinitions().getDeathEmote()));
           	tenta2.removeTarget();
           	World.removeNPC(tenta2);
           }
    	ntp.setNextAnimation(new Animation(ntp.getCombatDefinitions().getDeathEmote()));
      	ntp.removeTarget();
      	World.removeNPC(ntp);
    }
    @Override
    public void sendDeath(Entity source) {
        final NPCCombatDefinitions defs = getCombatDefinitions();
        resetWalkSteps();
        getCombat().removeTarget();
        setNextAnimation(null);
        if (tenta != null){
         	tenta.setNextAnimation(new Animation(tenta.getCombatDefinitions().getDeathEmote()));
         	tenta.removeTarget();
         	World.removeNPC(tenta);
         }
        if (tenta1 != null){
          	tenta1.setNextAnimation(new Animation(tenta1.getCombatDefinitions().getDeathEmote()));
          	tenta1.removeTarget();
          	World.removeNPC(tenta1);
          }
        if (tenta2 != null){
           	tenta2.setNextAnimation(new Animation(tenta2.getCombatDefinitions().getDeathEmote()));
           	tenta2.removeTarget();
           	World.removeNPC(tenta2);
           }
		killer.getInventory().addItem(23659, 1);
		Magic.sendNormalTeleportSpell(killer, 0, 0, new WorldTile(2862, 5933, 0));                               
        WorldTasksManager.schedule(new WorldTask() {

            int loop;

            @Override
            public void run() {
                if (loop == 0) {
                    setNextAnimation(new Animation(16235));
                } else if (loop >= defs.getDeathDelay()) { 
                  //  drop();
                    reset();
                   // setLocation(getRespawnTile());
                    finish();
                   // setRespawnTask();
                    stop();
                }
                loop++;
            }
        }, 0, 1);
    }
    
    @Override
    public void setRespawnTask() {
        if (!hasFinished()) {
            reset();
            setLocation(getRespawnTile());
            finish();
        }
        final NPC npc = this;
        CoresManager.slowExecutor.schedule(new Runnable() {

            @Override
            public void run() {
                setFinished(false);
                World.addNPC(npc);
                npc.setLastRegionId(0);
                World.updateEntityRegion(npc);
                loadMapRegions();
                checkMultiArea();
            }
        }, getCombatDefinitions().getRespawnDelay() * 1200,
                TimeUnit.MILLISECONDS);
    }
}

