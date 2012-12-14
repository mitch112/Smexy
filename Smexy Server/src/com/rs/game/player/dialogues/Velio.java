package com.rs.game.player.dialogues;

import com.rs.cache.loaders.NPCDefinitions;
import com.rs.game.WorldTile;
import com.rs.game.minigames.CastleWars;
import com.rs.game.player.content.Magic;
import com.rs.utils.ShopsHandler;

public class Velio extends Dialogue {

private int npcId;

@Override
public void start() {
sendDialogue(SEND_3_OPTIONS, "Armour Galore", "Melee Armour Shop",
"Mage Armour Shop", "Range Armour Shop");
stage = 2;
}

@Override
public void run(int interfaceId, int componentId) {

if (stage == 2) {
if (componentId == 1) {
ShopsHandler.openShop(player, 12);
end();
} else if (componentId == 2) {
ShopsHandler.openShop(player, 13);
end();
} else if (componentId == 3) {
ShopsHandler.openShop(player, 14);
end();
}
}
}

@Override
public void finish() {

}
}