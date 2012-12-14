package com.rs.game.npc.normal;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.rs.cores.CoresManager;
import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.item.Item;
import com.rs.game.npc.Drop;
import com.rs.game.npc.NPC;
import com.rs.game.npc.combat.NPCCombatDefinitions;
import com.rs.game.player.Player;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Misc;
import com.rs.utils.NPCDrops;
import com.rs.utils.Utils;

@SuppressWarnings("serial")
public class QBD extends NPC {

	public QBD(int id, WorldTile tile, int mapAreaNameHash,
			boolean canBeAttackFromOutOfArea, boolean spawned) {
		super(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		this.setCantFollowUnderCombat(true);
		this.setForceMultiArea(true);
		this.setForceTargetDistance(10);
		this.setForceMultiAttacked(true);
		this.setAtMultiArea(true);
		this.setRandomWalk(false);
		this.setCapDamage(1050);
		this.getCombatDefinitions().setAttackStyle(2);
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
							|| !player.withinDistance(this, 84)
							|| ((!isAtMultiArea() || !player.isAtMultiArea())
									&& player.getAttackedBy() != this && player
									.getAttackedByDelay() > Utils
									.currentTimeMillis())
							|| !clipedProjectile(player, false))
						continue;	possibleTarget.add(player);
				}
			}
		}
		return possibleTarget;
	}

	/*
	 * gotta override else setRespawnTask override doesnt work
	 */
	@Override
	public void sendDeath(Entity source) {
		final NPCCombatDefinitions defs = getCombatDefinitions();
		resetWalkSteps();
		getCombat().removeTarget();
		setNextAnimation(null);
		World.removeNPC(this);
		World.QBD = false;
		WorldTasksManager.schedule(new WorldTask() {
			int loop;

			@Override
			public void run() {
				if (loop == 0) {
					setNextAnimation(new Animation(defs.getDeathEmote()));
					World.sendWorldWideMessage("NEWS: The QBD has recently been slayed, we have a hero amongst us!");
				} else if (loop >= defs.getDeathDelay()) {
					dropQBD();
					reset();
					finish();
					stop();
				}
				loop++;
			}
		}, 0, 1);
	}

	public void dropQBD() {
		try {
			Drop[] drops = NPCDrops.getDrops(getId());
			if (drops == null)
				return;
			Player killer = getMostDamageReceivedSourcePlayer();
			if (killer == null)
				return;
		int [] charms = {12159, 12160, 12158, 12163};
		int li = Misc.random(3);
		Drop dr = new Drop(charms[li],85,11,31,false);
		sendDropQBD(killer,dr);
			Drop[] possibleDrops = new Drop[drops.length];
			int possibleDropsCount = 0;
			for (Drop drop : drops) {
				if (drop.getRate() == 100)
					sendDropQBD(killer, drop);
				else {
					if ((Utils.getRandomDouble(99) + 1) <= drop.getRate() * 1.5)
						possibleDrops[possibleDropsCount++] = drop;
				}
			}
			if (possibleDropsCount > 0)
		sendDropQBD(killer,
						possibleDrops[Utils.getRandom(possibleDropsCount - 1)]);
		} catch (Exception e) {
			e.printStackTrace();
		} catch (Error e) {
			e.printStackTrace();
		}
	}

	public void sendDropQBD(Player player, Drop drop) {
		int size = getSize();
		World.addGroundItem(new Item(drop.getItemId(), drop.getMinAmount()
				+ Utils.getRandom(drop.getExtraAmount())), new WorldTile(
				3536, 5191, getPlane()), player,
				false, 180, false);
	}
	@Override
	public void setRespawnTask() {
		if (!hasFinished()) {
			reset();
			setLocation(getRespawnTile());
			finish();
		}CoresManager.slowExecutor.schedule(new Runnable() {
			@Override
			public void run() {
				try {
					respawn();
				} catch (Exception e) {
					e.printStackTrace();
				} catch (Error e) {
					e.printStackTrace();
				}
			}
		}, getCombatDefinitions().getRespawnDelay() * 600,
				TimeUnit.MILLISECONDS);
	}

	public void respawn() {
		setFinished(false);
		World.addNPC(this);
		setLastRegionId(0);
		World.updateEntityRegion(this);
		loadMapRegions();
		this.setAtMultiArea(true);
		World.sendWorldWideMessage("NEWS: The QBD has respawned, lets see if it can be killed again!");
	}

}
