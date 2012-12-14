package com.rs.game.npc.combat.impl;

import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.ForceTalk;
import com.rs.game.Graphics;
import com.rs.game.npc.NPC;
import com.rs.game.npc.combat.CombatScript;
import com.rs.game.npc.combat.NPCCombatDefinitions;
import com.rs.game.player.Player;
import com.rs.utils.Utils;

public class Tentacles extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 15209, 15210 };
	}

	@Override
	public int attack(final NPC npc, final Entity target) {
		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
		npc.setNextAnimation(new Animation(defs.getAttackEmote()));
			npc.setNextForceTalk(new ForceTalk("GLARGHHHHHHHH"));
		target.setNextGraphics(new Graphics(337));
		delayHit(
				npc,
				0,
				target,
				getMagicHit(
						npc,
						getRandomMaxHit(npc, defs.getMaxHit(),
								defs.getAttackStyle(), target)));
		return defs.getAttackDelay();
	}
}
