package com.rs.game.player.actions;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.Animation;
import com.rs.game.World;
import com.rs.game.WorldObject;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.utils.Utils;

public final class Mining extends Action {

	public static enum RockDefinitions {

		Copper_Ore(1, 17.5, 436, 10, 1, 11552, 5, 0), Tin_Ore(1, 17.5, 438, 15,
				1, 11552, 5, 0), Iron_Ore(15, 35, 440, 15, 1, 11552, 10, 0), Sandstone_Ore(
				35, 30, 6971, 30, 1, 11552, 10, 0), Silver_Ore(20, 40, 442, 25,
				1, 11552, 20, 0), Coal_Ore(30, 50, 453, 50, 10, 11552, 30, 0), Granite_Ore(
				45, 50, 6979, 50, 10, 11552, 20, 0), Gold_Ore(40, 60, 444, 80,
				20, 11554, 40, 0), Mithril_Ore(55, 80, 447, 100, 20, 11552, 60,
				0), Adamant_Ore(70, 95, 449, 130, 25, 11552, 180, 0), Runite_Ore(
				85, 125, 451, 150, 30, 11552, 360, 0);

		private int level;
		private double xp;
		private int oreId;
		private int oreBaseTime;
		private int oreRandomTime;
		private int emptySpot;
		private int respawnDelay;
		private int randomLifeProbability;

		private RockDefinitions(int level, double xp, int oreId,
				int oreBaseTime, int oreRandomTime, int emptySpot,
				int respawnDelay, int randomLifeProbability) {
			this.level = level;
			this.xp = xp;
			this.oreId = oreId;
			this.oreBaseTime = oreBaseTime;
			this.oreRandomTime = oreRandomTime;
			this.emptySpot = emptySpot;
			this.respawnDelay = respawnDelay;
			this.randomLifeProbability = randomLifeProbability;
		}

		public int getLevel() {
			return level;
		}

		public double getXp() {
			return xp;
		}

		public int getOreId() {
			return oreId;
		}

		public int getOreBaseTime() {
			return oreBaseTime;
		}

		public int getOreRandomTime() {
			return oreRandomTime;
		}

		public int getEmptyId() {
			return emptySpot;
		}

		public int getRespawnDelay() {
			return respawnDelay;
		}

		public int getRandomLifeProbability() {
			return randomLifeProbability;
		}
	}

	private WorldObject rock;
	private RockDefinitions definitions;

	private int emoteId;
	private int pickaxeTime;

	public Mining(WorldObject rock, RockDefinitions definitions) {
		this.rock = rock;
		this.definitions = definitions;
	}

	@Override
	public boolean start(Player player) {
		if (!checkAll(player))
			return false;
		player.getPackets().sendGameMessage(
				"You swing your pickaxe at the rock.");
		setActionDelay(player, getMiningDelay(player));
		rock.setLife(definitions.getRandomLifeProbability());
		return true;
	}

	private int getMiningDelay(Player player) {
		int summoningBonus = 0;
		if (player.getFamiliar() != null) {
			if (player.getFamiliar().getId() == 7342
					|| player.getFamiliar().getId() == 7342)
				summoningBonus += 10;
			else if (player.getFamiliar().getId() == 6832
					|| player.getFamiliar().getId() == 6831)
				summoningBonus += 1;
		}
		int mineTimer = definitions.getOreBaseTime()
				- (player.getSkills().getLevel(Skills.MINING) + summoningBonus)
				- Utils.getRandom(pickaxeTime);
		if (mineTimer < 1 + definitions.getOreRandomTime())
			mineTimer = 1 + Utils.getRandom(definitions.getOreRandomTime());
		mineTimer /= player.getAuraManager().getMininingAccurayMultiplier();
		return mineTimer;
	}

	private boolean checkAll(Player player) {
		if (!hasPickaxe(player)) {
			player.getPackets().sendGameMessage(
					"You need a pickaxe to mine this rock.");
			return false;
		}
		if (!setPickaxe(player)) {
			player.getPackets().sendGameMessage(
					"You dont have the required level to use this pickaxe.");
			return false;
		}
		if (!hasMiningLevel(player))
			return false;
		if (!player.getInventory().hasFreeSlots()) {
			player.getPackets().sendGameMessage(
					"Not enough space in your inventory.");
			return false;
		}
		return true;
	}

	private boolean hasMiningLevel(Player player) {
		if (definitions.getLevel() > player.getSkills().getLevel(Skills.MINING)) {
			player.getPackets().sendGameMessage(
					"You need a mining level of " + definitions.getLevel()
							+ " to mind this rock.");
			return false;
		}
		return true;
	}

	private boolean setPickaxe(Player player) {
		int level = player.getSkills().getLevel(Skills.MINING);
		int weaponId = player.getEquipment().getWeaponId();
		if (weaponId != -1) {
			switch (weaponId) {
			case 15259: // dragon pickaxe
				if (level >= 61) {
					emoteId = 12190;
					pickaxeTime = 13;
					return true;
				}
				break;
			case 1275: // rune pickaxe
				if (level >= 41) {
					emoteId = 624;
					pickaxeTime = 10;
					return true;
				}
				break;
			case 1271: // adam pickaxe
				if (level >= 31) {
					emoteId = 628;
					pickaxeTime = 7;
					return true;
				}
				break;
			case 1273: // mith pickaxe
				if (level >= 21) {
					emoteId = 629;
					pickaxeTime = 5;
					return true;
				}
				break;
			case 1269: // steel pickaxe
				if (level >= 6) {
					emoteId = 627;
					pickaxeTime = 3;
					return true;
				}
				break;
			case 1267: // iron pickaxe
				emoteId = 626;
				pickaxeTime = 2;
				return true;
			case 1265: // bronze axe
				emoteId = 625;
				pickaxeTime = 1;
				return true;
			case 13661: // Inferno adze
				if (level >= 61) {
					emoteId = 10222;
					pickaxeTime = 13;
					return true;
				}
				break;
			}
		}
		if (player.getInventory().containsOneItem(15259)) {
			if (level >= 61) {
				emoteId = 12190;
				pickaxeTime = 13;
				return true;
			}
		}
		if (player.getInventory().containsOneItem(1275)) {
			if (level >= 41) {
				emoteId = 624;
				pickaxeTime = 10;
				return true;
			}
		}
		if (player.getInventory().containsOneItem(1271)) {
			if (level >= 31) {
				emoteId = 628;
				pickaxeTime = 7;
				return true;
			}
		}
		if (player.getInventory().containsOneItem(1273)) {
			if (level >= 21) {
				emoteId = 629;
				pickaxeTime = 5;
				return true;
			}
		}
		if (player.getInventory().containsOneItem(1269)) {
			if (level >= 6) {
				emoteId = 627;
				pickaxeTime = 3;
				return true;
			}
		}
		if (player.getInventory().containsOneItem(1267)) {
			emoteId = 626;
			pickaxeTime = 2;
			return true;
		}
		if (player.getInventory().containsOneItem(1265)) {
			emoteId = 625;
			pickaxeTime = 1;
			return true;
		}
		if (player.getInventory().containsOneItem(13661)) {
			if (level >= 61) {
				emoteId = 10222;
				pickaxeTime = 13;
				return true;
			}
		}
		return false;

	}

	private boolean hasPickaxe(Player player) {
		if (player.getInventory().containsOneItem(15259, 1275, 1271, 1273,
				1269, 1267, 1265, 13661))
			return true;
		int weaponId = player.getEquipment().getWeaponId();
		if (weaponId == -1)
			return false;
		switch (weaponId) {
		case 1265:// Bronze PickAxe
		case 1267:// Iron PickAxe
		case 1269:// Steel PickAxe
		case 1273:// Mithril PickAxe
		case 1271:// Adamant PickAxe
		case 1275:// Rune PickAxe
		case 15259:// Dragon PickAxe
		case 13661: // Inferno adze
			return true;
		default:
			return false;
		}

	}

	@Override
	public boolean process(Player player) {
		player.setNextAnimation(new Animation(emoteId));
		return checkRock(player);
	}

	private boolean usedDeplateAurora;

	@Override
	public int processWithDelay(Player player) {
		addOre(player);
		rock.decrementObjectLife();
		if (!usedDeplateAurora
				&& (1 + Math.random()) < player.getAuraManager()
						.getChanceNotDepleteMN_WC()) {
			usedDeplateAurora = true;
		} else if (rock.getLife() == 0) {
			World.spawnTemporaryObject(
					new WorldObject(definitions.getEmptyId(), rock.getType(),
							rock.getRotation(), rock.getX(), rock.getY(), rock
									.getPlane()),
					definitions.respawnDelay * 600, false);
			player.setNextAnimation(new Animation(-1));
			return -1;
		}
		if (!player.getInventory().hasFreeSlots()
				&& definitions.getOreId() != -1) {
			player.setNextAnimation(new Animation(-1));
			player.getPackets().sendGameMessage(
					"Not enough space in your inventory.");
			return -1;
		}
		return getMiningDelay(player);
	}

	private void addOre(Player player) {
		double xpBoost = 0;
		int idSome = 0;
		if (definitions == RockDefinitions.Granite_Ore) {
			idSome = Utils.getRandom(2) * 2;
			if (idSome == 2)
				xpBoost += 10;
			else if (idSome == 4)
				xpBoost += 25;
		} else if (definitions == RockDefinitions.Sandstone_Ore) {
			idSome = Utils.getRandom(3) * 2;
			xpBoost += idSome / 2 * 10;
		} else if (player.getFamiliar() != null
				&& (player.getFamiliar().getId() == 7342 || player
						.getFamiliar().getId() == 7342))
			xpBoost += 40;
		player.getSkills().addXp(Skills.MINING, definitions.getXp() + xpBoost);
		if (definitions.getOreId() != -1) {
			player.getInventory().addItem(definitions.getOreId() + idSome, 1);
			String oreName = ItemDefinitions
					.getItemDefinitions(definitions.getOreId() + idSome)
					.getName().toLowerCase();
			player.getPackets().sendGameMessage(
					"You mine some " + oreName + ".", true);
		}
	}

	private boolean checkRock(Player player) {
		return World.getRegion(rock.getRegionId()).containsObject(rock.getId(),
				rock);
	}

	@Override
	public void stop(Player player) {
		setActionDelay(player, 3);
	}

}
