package com.rs.net.decoders.handlers;

import com.rs.Settings;
import com.rs.game.World;
import com.rs.game.npc.NPC;
import com.rs.game.npc.familiar.Familiar;
import com.rs.game.npc.slayer.Strykewyrm;
import com.rs.game.player.CoordsEvent;
import com.rs.game.player.Player;
import com.rs.game.player.actions.Fishing;
import com.rs.game.player.actions.PickPocketAction;
import com.rs.game.player.actions.PickPocketableNPC;
import com.rs.game.player.actions.Slayer;
import com.rs.game.player.actions.Fishing.FishingSpots;
import com.rs.game.player.actions.Slayer.SlayerMonsters;
import com.rs.game.player.content.PlayerLook;
import com.rs.game.player.content.exchange.GrandExchange;
import com.rs.game.player.dialogues.FremennikShipmaster;
import com.rs.io.InputStream;
import com.rs.utils.ShopsHandler;

public class NPCHandler {

	public static void handleOption1(final Player player, InputStream stream) {
		@SuppressWarnings("unused")
		boolean unknown = stream.readByte128() == 1;
		int npcIndex = stream.readUnsignedShort128();
		final NPC npc = World.getNPCs().get(npcIndex);
		if (npc == null || npc.isCantInteract() || npc.isDead()
				|| npc.hasFinished()
				|| !player.getMapRegionsIds().contains(npc.getRegionId()))
			return;
		player.stopAll(false);
		if (npc.getDefinitions().name.contains("Banker")
				|| npc.getDefinitions().name.contains("banker")) {
			player.faceEntity(npc);
			if (!Slayer.checkRequirement(player,SlayerMonsters.forId(npc.getId()))){
				player.stopAll();
				return;
			}
			if (!player.withinDistance(npc, 2))
				return;
			npc.faceEntity(player);
			player.getDialogueManager().startDialogue("Banker", npc.getId());
			return;
		}
		player.setCoordsEvent(new CoordsEvent(npc, new Runnable() {
			@Override
			public void run() {
				npc.resetWalkSteps();
				player.faceEntity(npc);
				FishingSpots spot = FishingSpots.forId(npc.getId() | 1 << 24);
				if (spot != null) {
					player.getActionManager().setSkill(new Fishing(spot, npc));
					return; // its a spot, they wont face us
				}
				npc.faceEntity(player);
				if (!player.getControlerManager().processNPCClick1(npc))
					return;
				if(npc.getId() == 1419 || npc.getId() == 2240 || npc.getId() == 2241 || npc.getId() == 2593 || npc.getId() == 15529 || npc.getId() == 15530) {
				    GrandExchange.openGE(player);
					}
				if (npc.getId() == 659){
					ShopsHandler.openShop(player, 76);
					player.sm("You have "+player.VotePoints+" vote points. Go vote everyday for more.");
					return;
				}
				if (npc.getId() == 1569)
					player.getDialogueManager().startDialogue("Veliaf", 
							npc.getId());
                                if (npc.getId() == 12379)
					player.getDialogueManager().startDialogue("GrimReaper", 
							npc.getId());
				if (npc.getId() == 3709)
					player.getDialogueManager().startDialogue("MrEx",
							npc.getId());
				if (npc.getId() == 8443)
					player.getDialogueManager().startDialogue("Lucien",
							npc.getId());
				else if (npc.getId() == 949)
					player.getDialogueManager().startDialogue("QuestGuide",
							npc.getId(), null);
				if (npc.getId() == 3374)
					player.getDialogueManager().startDialogue("Max",
							npc.getId());
				if (npc.getId() == 14057){
					player.getDialogueManager().startDialogue("Velio",
							npc.getId());
							player.sm("Starting Velio Dialogue");
							return;
							}
				if (npc.getId() == 14078){
					player.getDialogueManager().startDialogue("Varnis",
							npc.getId());
							player.sm("Starting Varnis Dialogue");
							return;
							}
				else if (npc.getId() == 9462)
					Strykewyrm.handleStomping(player, npc);
				else if (npc.getId() == 9707)
					player.getDialogueManager().startDialogue(
							"FremennikShipmaster", npc.getId(), true);
				else if (npc.getId() == 9708)
					player.getDialogueManager().startDialogue(
							"FremennikShipmaster", npc.getId(), false);
				if (npc.getId() == 8461)
					player.getDialogueManager().startDialogue("Turael", npc.getId());
				else if (npc.getId() == 6537)
					ShopsHandler.openShop(player, 19);
				else if (npc.getId() == 6537)
					ShopsHandler.openShop(player, 22);
				else if (npc.getId() == 564)
                    ShopsHandler.openShop(player, 30);
				else if (npc.getId() == 2253)
                    ShopsHandler.openShop(player, 26);
				else if (npc.getId() == 2830)
					ShopsHandler.openShop(player, 29);
				else if (npc.getId() == 576)
					ShopsHandler.openShop(player, 22);	
				else if (npc.getId() == 948)
					ShopsHandler.openShop(player, 23);	
				else if (npc.getId() == 445)
					ShopsHandler.openShop(player, 23);	
				else if (npc.getId() == 637)
					ShopsHandler.openShop(player, 25);	
				else if (npc.getId() == 2732)
					ShopsHandler.openShop(player, 26);	
				else if (npc.getId() == 4906)
					ShopsHandler.openShop(player, 27);						
				else if (npc.getId() == 2676)
					player.getDialogueManager().startDialogue("MakeOverMage",
							npc.getId(), 0);
				else {
					player.getPackets().sendGameMessage(
							"Nothing interesting happens.");
					if (Settings.DEBUG)
						System.out.println("cliked 1 at npc id : "
								+ npc.getId() + ", " + npc.getX() + ", "
								+ npc.getY() + ", " + npc.getPlane());
				}
			}
		}, npc.getSize()));
	}

	public static void handleOption2(final Player player, InputStream stream) {
		@SuppressWarnings("unused")
		boolean unknown = stream.readByte128() == 1;
		int npcIndex = stream.readUnsignedShort128();
		final NPC npc = World.getNPCs().get(npcIndex);
		if (npc == null || npc.isCantInteract() || npc.isDead()
				|| npc.hasFinished()
				|| !player.getMapRegionsIds().contains(npc.getRegionId()))
			return;
		player.stopAll(false);
		if (npc.getDefinitions().name.contains("Banker")
				|| npc.getDefinitions().name.contains("banker")) {
			player.faceEntity(npc);
			if (!player.withinDistance(npc, 2))
				return;
			npc.faceEntity(player);
			player.getBank().openBank();
			return;
		}
		player.setCoordsEvent(new CoordsEvent(npc, new Runnable() {
			@Override
			public void run() {
				npc.resetWalkSteps();
				player.faceEntity(npc);
				FishingSpots spot = FishingSpots.forId(npc.getId() | (2 << 24));
				if (spot != null) {
					player.getActionManager().setSkill(new Fishing(spot, npc));
					return;
				}
				PickPocketableNPC pocket = PickPocketableNPC.get(npc.getId());
				if (pocket != null) {
					player.getActionManager().setSkill(
							new PickPocketAction(npc, pocket));
					return;
				}
				if (npc instanceof Familiar) {
					if (npc.getDefinitions().hasOption("store")) {
						if (player.getFamiliar() != npc) {
							player.getPackets().sendGameMessage(
									"That isn't your familiar.");
							return;
						}
						player.getFamiliar().store();
					} else if (npc.getDefinitions().hasOption("cure")) {
						if (player.getFamiliar() != npc) {
							player.getPackets().sendGameMessage(
									"That isn't your familiar.");
							return;
						}
						if (!player.getPoison().isPoisoned()) {
							player.getPackets().sendGameMessage(
									"Your arent poisoned or diseased.");
							return;
						} else {
							player.getFamiliar().drainSpecial(2);
							player.addPoisonImmune(120);
						}
					}
					return;
				}
				if (npc.getDefinitions().hasPickupOption()
						|| npc.getDefinitions().hasTakeOption()) {
					if (!player.withinDistance(npc, 2)) {
						return;
					}
					player.faceEntity(npc);
					if (player.getPetFollow() != player.getIndex()) {
						player.sendMessage("This isn't your pet!");
						return;
					}
					if (player.getPetId() == 0) {
						return;
					}
					player.getPet().dissmissPet(false);
					return;
				}
				npc.faceEntity(player);
				if (!player.getControlerManager().processNPCClick2(npc))
					return;
				if(npc.getId() == 1419 || npc.getId() == 2240 || npc.getId() == 2241 || npc.getId() == 2593 || npc.getId() == 15529 || npc.getId() == 15530) {
				    GrandExchange.openGE(player);
					}
				if (npc.getId() == 9707)
					FremennikShipmaster.sail(player, true);
				else if (npc.getId() == 9708)
					FremennikShipmaster.sail(player, false);
				else if (npc.getId() == 13455)
					player.getBank().openBank();
				else if (npc.getId() == 9711)
					ShopsHandler.openShop(player, 89);
				else if (npc.getId() == 519)
					ShopsHandler.openShop(player, 1);
				else if (npc.getId() == 520)
					ShopsHandler.openShop(player, 2);
				if (npc.getId() == 14057){
					player.getDialogueManager().startDialogue("Velio",
							npc.getId());
							//player.sm("Starting Velio Dialogue");
							}
				if (npc.getId() == 14078){
					player.getDialogueManager().startDialogue("Varnis",
							npc.getId());
						//	player.sm("Starting Varnis Dialogue");
							}
				else if (npc.getId() == 521)
					ShopsHandler.openShop(player, 3);
				else if (npc.getId() == 522)
					ShopsHandler.openShop(player, 4);
				else if (npc.getId() == 523)
					ShopsHandler.openShop(player, 5);
				else if (npc.getId() == 524)
					ShopsHandler.openShop(player, 6);
				else if (npc.getId() == 525)
					ShopsHandler.openShop(player, 7);
				else if (npc.getId() == 526)
					ShopsHandler.openShop(player, 8);
				else if (npc.getId() == 527)
					ShopsHandler.openShop(player, 9);
				else if (npc.getId() == 528)
					ShopsHandler.openShop(player, 10);
				else if (npc.getId() == 529)
					ShopsHandler.openShop(player, 11);
				else if (npc.getId() == 530)
					ShopsHandler.openShop(player, 12);
				else if (npc.getId() == 531)
					ShopsHandler.openShop(player, 13);
				else if (npc.getId() == 534)
					ShopsHandler.openShop(player, 14);
				else if (npc.getId() == 535)
					ShopsHandler.openShop(player, 15);
				else if (npc.getId() == 551)
					ShopsHandler.openShop(player, 16);
				else if (npc.getId() == 552)
					ShopsHandler.openShop(player, 17);
				else if (npc.getId() == 554)
					ShopsHandler.openShop(player, 18);
				else if (npc.getId() == 555)
					ShopsHandler.openShop(player, 19);
				else if (npc.getId() == 561)
					ShopsHandler.openShop(player, 20);
				else if (npc.getId() == 1699)
					ShopsHandler.openShop(player, 21);
				else if (npc.getId() == 1917)
					ShopsHandler.openShop(player, 22);
				else if (npc.getId() == 11678)
					ShopsHandler.openShop(player, 23);
				else if (npc.getId() == 11679)
					ShopsHandler.openShop(player, 24);
				else if (npc.getId() == 536)
					ShopsHandler.openShop(player, 25);
				else if (npc.getId() == 537)
					ShopsHandler.openShop(player, 26);
				else if (npc.getId() == 538)
					ShopsHandler.openShop(player, 27);
				else if (npc.getId() == 556)
					ShopsHandler.openShop(player, 28);
				else if (npc.getId() == 540)
					ShopsHandler.openShop(player, 29);
				else if (npc.getId() == 541)
					ShopsHandler.openShop(player, 30);
				else if (npc.getId() == 542)
					ShopsHandler.openShop(player, 31);
				else if (npc.getId() == 544)
					ShopsHandler.openShop(player, 32);
				else if (npc.getId() == 545)
					ShopsHandler.openShop(player, 33);
				else if (npc.getId() == 873)
					ShopsHandler.openShop(player, 34);
				else if (npc.getId() == 6893)
					ShopsHandler.openShop(player, 36);
				else if (npc.getId() == 2676)
					PlayerLook.openMageMakeOver(player);
				else {
					player.getPackets().sendGameMessage(
							"Nothing interesting happens.");
					if (Settings.DEBUG)
						System.out.println("cliked 2 at npc id : "
								+ npc.getId() + ", " + npc.getX() + ", "
								+ npc.getY() + ", " + npc.getPlane());
				}
			}
		}, npc.getSize()));
	}
}