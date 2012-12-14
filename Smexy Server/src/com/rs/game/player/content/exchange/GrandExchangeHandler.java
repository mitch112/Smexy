package com.rs.game.player.content.exchange;

import com.rs.game.World;
import com.rs.net.decoders.*;
import com.rs.game.player.Player;
import com.rs.io.InputStream;
import com.rs.io.Stream;

/**
 * 
 * @author 'Mystic Flow
 * @author `Discardedx2
 */
public class GrandExchangeHandler{

	public void decodeLogicPacket(Player player, Stream packet) {
		System.out.println("Packet: "+packet.getId());
		switch(packet.getId()) {
		case 139:
			int id = packet.readShortl();
			World.getGrandExchange().buyItem(player, id);
			break;
		}
	}

}
