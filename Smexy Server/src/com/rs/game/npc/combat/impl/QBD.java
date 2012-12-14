package com.rs.game.npc.combat.impl;

import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.EntityList;
import com.rs.game.Graphics;
import com.rs.game.World;
import com.rs.game.npc.NPC;
import com.rs.game.npc.combat.CombatScript;
import com.rs.game.npc.combat.NPCCombatDefinitions;
import com.rs.game.player.Player;
import com.rs.game.player.content.Combat;
import com.rs.utils.Misc;
import com.rs.utils.Utils;

public class QBD extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 15507, 15506, 15454 };
	}
	@Override
	public int attack(final NPC npc, final Entity target) {
		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
		int attackStyle = Utils.getRandom(5);
		int size = npc.getSize();
		int meleehit = Misc.random(450);

		if (attackStyle == 0) {
				attackStyle = Utils.getRandom(4) + 1;
				delayHit(
						npc,
						0,
						target,
						getMeleeHit(npc,getRandomMaxHit(npc, meleehit,NPCCombatDefinitions.MELEE, target)));
				npc.setNextAnimation(new Animation(16717));
				return defs.getAttackDelay();
		} else if (attackStyle == 1 || attackStyle == 2) {
			int damage = Utils.getRandom(450);
			final Player player = target instanceof Player ? (Player) target
					: null;
			if (Combat.hasAntiDragProtection(target)
					|| (player != null && (player.getPrayer()
							.usingPrayer(0, 17) || player.getPrayer()
							.usingPrayer(1, 7))))
				damage = 0;
			if (player != null
					&& player.getFireImmune() > Utils.currentTimeMillis()) {
				if (damage != 0)
					damage = Utils.getRandom(264);
			} else if (damage == 0)
				damage = Utils.getRandom(134);
			else if (player != null)
			npc.setNextAnimation(new Animation(16745));
			npc.setNextGraphics(new Graphics(3152));
				player.getPackets().sendGameMessage(
						"You are hit by the QBD's flamming breath!", true);
			delayHit(npc, 2, player, getRegularHit(npc, damage));

		} else if (attackStyle == 3) {
			int damage;
			final Player player = target instanceof Player ? (Player) target
					: null;
			if (Combat.hasAntiDragProtection(target)) {
				damage = getRandomMaxHit(npc, 164, NPCCombatDefinitions.MAGE,
						target);
				if (player != null)
					player.getPackets()
							.sendGameMessage(
									"Your shield absorbs most of the dragon's poisonous breath!",
									true);
			} else if (player != null
					&& (player.getPrayer().usingPrayer(0, 17) || player
							.getPrayer().usingPrayer(1, 7))) {
				damage = getRandomMaxHit(npc, 164, NPCCombatDefinitions.MAGE,
						target);
				if (player != null)
					player.getPackets()
							.sendGameMessage(
									"Your prayer absorbs most of the dragon's poisonous breath!",
									true);
			} else {
				damage = Utils.getRandom(550);
			
			}
			if (Utils.getRandom(5) == 0)
				target.getPoison().makePoisoned(180);
			delayHit(npc, 2, player, getRegularHit(npc, damage));	if (player != null)
					player.getPackets().sendGameMessage(
							"You are hit by the QBD's poisonous breath!",
							true);
			npc.setNextAnimation(new Animation(16746));
			npc.setNextGraphics(new Graphics(3157));
			npc.setNextAnimation(new Animation(16748));
		} else if (attackStyle == 4) {
			int damage;
			final Player player = target instanceof Player ? (Player) target
					: null;
				damage = Utils.getRandom(550);
					player.getPackets().sendGameMessage(
							"You are hit by the dragon's Swipping Swing!",
							true);
			if (Utils.getRandom(2) == 0)
				player.addFreezeDelay(15000);
			delayHit(npc, 2, player, getRegularHit(npc, damage+200));
			npc.setNextAnimation(new Animation(16718));
		} else {
			int damage = Misc.random(550);
			final Player player = target instanceof Player ? (Player) target
					: null;
			delayHit(npc, 2, player, getRegularHit(npc, damage));
			npc.setNextAnimation(new Animation(16743));
		}
		return defs.getAttackDelay();
	}
}
