package com.rs.game.player.dialogues;

import com.rs.game.WorldTile;
import com.rs.game.item.Item;
import com.rs.game.player.content.Magic;

public class Transportation extends Dialogue {

	// Ring of duelling
	// Combat bracelet

	public static int EMOTE = 9603, GFX = 1684;

	@Override
	public void start() {
		sendDialogue(SEND_5_OPTIONS, "Where would you like to teleport to",
				(String) parameters[0], (String) parameters[2],
				(String) parameters[4], (String) parameters[6], "Nowhere");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		boolean teleported = false;
		if (componentId == 1)
			teleported = Magic.sendItemTeleportSpell(player, true, EMOTE, GFX,
					4, (WorldTile) parameters[1]);
		else if (componentId == 2)
			teleported = Magic.sendItemTeleportSpell(player, true, EMOTE, GFX,
					4, (WorldTile) parameters[3]);
		else if (componentId == 3)
			teleported = Magic.sendItemTeleportSpell(player, true, EMOTE, GFX,
					4, (WorldTile) parameters[5]);
		else if (componentId == 4)
			teleported = Magic.sendItemTeleportSpell(player, true, EMOTE, GFX,
					4, (WorldTile) parameters[7]);
		if (!teleported) {
			end();
			return;
		}
		Item item = player.getInventory().getItems()
				.lookup((Integer) parameters[8]);
		if (item.getId() >= 3853 && item.getId() <= 3865
				|| item.getId() >= 10354 && item.getId() <= 10361) {
			item.setId(item.getId() + 2);
		} else if (item.getId() == 3867 || item.getId() == 10362) {
			item.setId(-1);
		} else {
			item.setId(item.getId() - 2);
		}
		player.getInventory().refresh(
				player.getInventory().getItems().getThisItemSlot(item));
		end();
	}

	@Override
	public void finish() {
	}

}
