package com.rs.game.npc.combat.impl;

import java.util.ArrayList;

import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.Graphics;
import com.rs.game.Hit;
import com.rs.game.Hit.HitLook;
import com.rs.game.npc.NPC;
import com.rs.game.npc.combat.CombatScript;
import com.rs.game.npc.combat.NPCCombatDefinitions;
import com.rs.game.npc.dungeonnering.AsteaFrostweb;
import com.rs.game.npc.jad.Harken;
import com.rs.game.player.Kilner;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Utils;

public class HarkenCombat extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 15211 };
	}
public void run(){
	
}
	@Override
	public int attack(final NPC npc, final Entity target) {
		if (target != null)
		if (Utils.getRandom(1) == 0) {
			Kilner.Harken boss = (Kilner.Harken) npc;
			boss.spawnTents();
			System.out.println("Spawned a tentacle!");
			return 50;
		}
		return 5;
	}
}
