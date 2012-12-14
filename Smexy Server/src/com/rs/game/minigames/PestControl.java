package com.rs.game.minigames;

import com.rs.Settings;
import com.rs.game.EntityList;
import com.rs.game.World;
import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.npc.NPC;
import com.rs.game.player.Player;

/**
 * 
 * @author Raghav <Raghav_ftw@hotmail.com>
 * 
 */
public class PestControl {

	/**
	 * List to store players in waiting room.
	 */
	private EntityList<Player> waitingPlayers = new EntityList<Player>(
			Settings.PLAYERS_LIMIT);

	/**
	 * List to store players in game.
	 */
	public static EntityList<Player> playersInGame = new EntityList<Player>(25);

	/**
	 * List to store pc npcs.
	 */
	@SuppressWarnings("unused")
	private static final EntityList<NPC> pestMonsters = new EntityList<NPC>(
			Settings.NPCS_LIMIT);

	/**
	 * Points players will receive for fighting pests and the portals.
	 */
	private int points;

	/**
	 * Area to teleport the player when he leaves.
	 */
	private WorldTile OUTSIDE_AREA = new WorldTile(2657, 2639, 0);

	/**
	 * Area to telport all the monsters.
	 */
	public final WorldTile PESTCONTROL_AREA = new WorldTile(2657, 2639, 0);

	/**
	 * An array to store all the pests. Thanks to 'Sheikyy'
	 */
	public int[] PESTS = { 3772, 3762, 3742, 3732, 3747, 3727, 3752, 3773,
			3764, 3743, 3734, 3748, 3728, 3754, 3774, 3766, 3744, 3736, 3749,
			3729, 3756, 3775, 3768, 3745, 3738, 3750, 3730, 3758, 3776, 3770,
			3746, 3740, 3751, 3731, 3760 };

	/**
	 * Boolean array to check if portal is dead or alive. 0 = 6142, 1 = 6144, 2
	 * = 6145, 3 = 6143
	 */
	private transient static boolean[] PORTALS = new boolean[3];

	/**
	 * The player.
	 */
	private Player player;

	/**
	 * Used in spawning of monsters.
	 */
	public int lastIndex;

	public PestControl(Player player) {
		this.player = player;
	}

	/**
	 * 
	 * @param player
	 *            The player.
	 * @param object
	 *            The object.
	 * @return {@code true} if the object action got handled, {@code false} if
	 *         not.
	 */
	public boolean handleObjectClick1(Player player, WorldObject object) {
		switch (object.getId()) {
		case 14315:
			if (player.getSkills().getCombatLevel() < 40) {
				player.getDialogueManager()
						.startDialogue("SimpleMessage",
								"You need a combat level of 40 or more to enter in boat.");
				return false;
			}
			enterInBoat();
			return true;
		case 14314:
			leaveBoat();
			return true;
		}
		return false;
	}

	/**
	 * Entering in boat.
	 * 
	 * @param player
	 *            The player to add.
	 */
	private void enterInBoat() {
		player.getTemporaryAttributtes().put("priority1", false);
		player.getTemporaryAttributtes().put("priority2", false);
		if (waitingPlayers.size() > 25)
			player.getTemporaryAttributtes().put("priority1", true);
		if (waitingPlayers.size() > 50)
			player.getTemporaryAttributtes().put("priority2", true);
		waitingPlayers.add(player);
		player.setNextWorldTile(new WorldTile(2661, 2639, 0));
		player.getPackets().sendGameMessage("You board the lander.", true);
		sendInterfaces();
		startGame();
	}

	/**
	 * Leaving the boat.
	 * 
	 * @param player
	 *            the player to remove.
	 */
	private void leaveBoat() {
		if (waitingPlayers.contains(player))
			waitingPlayers.remove(player);
		player.closeInterfaces();
		player.setNextWorldTile(OUTSIDE_AREA);
	}

	/**
	 * Departure interface.
	 * 
	 * @param player
	 *            the player to send interface
	 */
	private void sendInterfaces() {
		player.getPackets().sendIComponentText(407, 3, "");
		player.getPackets().sendIComponentText(407, 14,
				"" + waitingPlayers.size());
		player.getPackets().sendIComponentText(407, 16,
				"" + Integer.toString(player.getPestPoints()));
		player.getInterfaceManager().sendTab(
				player.getInterfaceManager().hasRezizableScreen() ? 10 : 19,
				407);
	}

	/**
	 * Starting the game and controler.
	 */
	public void startGame() {
		playersInGame.addAll(waitingPlayers);
		waitingPlayers.remove(playersInGame);
		for (Player players : waitingPlayers) {
			if ((Boolean) players.getTemporaryAttributtes().get("priority1"))
				players.getPackets()
						.sendGameMessage(
								"You have been given priority level 1 over other players in joining the next game.");
			if ((Boolean) players.getTemporaryAttributtes().get("priority2"))
				players.getPackets()
						.sendGameMessage(
								"You have been given priority level 2 over other players in joining the next game.");

		}
		//player.getControllerManager().startControler("PestControler");
	}

	/**
	 * Spawning of pests. Should call this every 7-9 seconds.
	 */
	public void spawnMonsters() {
		if (!getPortals(0))
			World.spawnNPC(PESTS[lastIndex], new WorldTile(2634, 2593, 0), -1,
					false);
		if (!getPortals(1))
			World.spawnNPC(PESTS[lastIndex], new WorldTile(2670, 2576, 0), -1,
					false);
		if (!getPortals(2))
			World.spawnNPC(PESTS[lastIndex], new WorldTile(2646, 2574, 0), -1,
					false);
		if (!getPortals(2))
			World.spawnNPC(PESTS[lastIndex], new WorldTile(2676, 2589, 0), -1,
					false);
		lastIndex++;
	}

	/**
	 * 
	 * @return waitingPlayers
	 */
	public EntityList<Player> getWaitingPlayers() {
		return waitingPlayers;
	}

	/**
	 * 
	 * @return playersInGame
	 */
	public EntityList<Player> getPlayersInGame() {
		return playersInGame;
	}

	/**
	 * 
	 * @param points
	 *            the points to set.
	 */
	public void setPoints(int points) {
		this.points = points;
	}

	/**
	 * 
	 * @return points
	 */
	public int getPoints() {
		return points;
	}

	/**
	 * 
	 * @param portal
	 *            The portal id.
	 * @param b
	 *            {@code true} if the portal is dead, {@code false} if not.
	 */
	public static boolean setPortals(int portal, boolean b) {
		return PORTALS[portal] = b;
	}

	/**
	 * 
	 * @param portal
	 *            The portal.
	 * @return PORTALS
	 */
	public boolean getPortals(int portal) {
		return PORTALS[portal];
	}
}
