package com.rs.game.player.actions;

import com.rs.game.Animation;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.utils.Misc;
 
 
 
public class Farming {
 
		public static int[] Patch = {8552, 7848, 8151, 8553};
		public static int[] Seed = {5318};
		
		
		public static boolean isPatch(int j){
			for (int i: Patch){
				if (i == j){
					return true;
				}
				}
				return false;
		}
		public static void Planter(int n, Player p){
		if (p.getInventory().containsOneItem(5318)){
			if (n == 8552){
				if ((p.Planted == false)){
					p.Planted = true;
					p.farmtim = 60;
					p.sm("Your potatoes will be ready in 1 minute!");
					p.setNextAnimation(new Animation(2286));
					p.getInventory().deleteItem(5318, 1);
					p.farmob = n;
				} else if (p.Planted == true && p.farmtim == 0){
					if(p.farmob == n){
						p.Planted = false;
						p.getSkills().addXp(Skills.FARMING, 5000);
						int o = (Misc.random(4)+1);
						p.getInventory().addItem(1942, o);
						p.sm("You have picked "+o+" potatoe's and gained some xp!");
						p.setNextAnimation(new Animation(2286));
						p.farmob = 0;
					}
				}
			} else {
				p.sm("Potato goes on the allotment patch only!");
			}
		} else if (p.getInventory().containsOneItem(5291)){
			if (p.getSkills().getLevel(Skills.FARMING) >= 10){
			if (n == 8151){
				if ((p.Planted == false)){
					p.Planted = true;
					p.farmtim = 90;
					p.sm("Your guams will be ready in 90 seconds!");
					p.setNextAnimation(new Animation(2286));
					p.getInventory().deleteItem(5291, 1);
					p.farmob = n;
				} else if (p.Planted == true && p.farmtim == 0){
					if(p.farmob == n){
						p.Planted = false;
						p.getSkills().addXp(Skills.FARMING, 10000);
						int o = (Misc.random(2)+1);
						p.getInventory().addItem(249, o);
						p.sm("You have picked "+o+" guam's and gained some xp!");
						p.setNextAnimation(new Animation(2286));
						p.farmob = 0;
					}
				}
			} else {
				p.sm("Guam goes on the herb patch only!");
			}
			} else {
			p.sm("You need a farming level of at least 10 to plant these seeds.");
			}
		} else if (p.getInventory().containsOneItem(5295)){
		if (p.getSkills().getLevel(Skills.FARMING) >= 35){
			if (n == 8151){
				if ((p.Planted == false)){
					p.Planted = true;
					p.farmtim = 130;
					p.sm("Your rannar will be ready in 130 seconds!");
					p.setNextAnimation(new Animation(2286));
					p.getInventory().deleteItem(5295, 1);
					p.farmob = n;
				} else if (p.Planted == true && p.farmtim == 0){
					if(p.farmob == n){
						p.Planted = false;
						int o = (Misc.random(2)+1);
						p.getSkills().addXp(Skills.FARMING, 15000);
						p.getInventory().addItem(257, o);
						p.setNextAnimation(new Animation(2286));
						p.sm("You have picked "+o+" ranarr's and gained some xp!");
						p.farmob = 0;
					}
				}
			} else {
				p.sm("Rannar goes on the herb patch only!");
			}
			} else {
			p.sm("You need a farming level of at least 35 to plant these seeds.");
			}
		} else if (p.getInventory().containsOneItem(5299)){
			if (p.getSkills().getLevel(Skills.FARMING) >= 50){
			if (n == 8151){
				if ((p.Planted == false)){
					p.Planted = true;
					p.farmtim = 190;
					p.sm("Your Kwuarm will be ready in 190 seconds!");
					p.setNextAnimation(new Animation(2286));
					p.getInventory().deleteItem(5299, 1);
					p.farmob = n;
				} else if (p.Planted == true && p.farmtim == 0){
					if(p.farmob == n){
						p.Planted = false;
						int o = (Misc.random(2)+1);
						p.getSkills().addXp(Skills.FARMING, 35000);
						p.getInventory().addItem(263, o);
						p.setNextAnimation(new Animation(2286));
						p.sm("You have picked "+o+" kwuarms's and gained some xp!");
						p.farmob = 0;
					}
				}
			} else {
				p.sm("Kwuarm goes on the herb patch only!");
			}
			} else {
			p.sm("You need at least 50 farming to plant these seeds.");
			}
		} else if (p.getInventory().containsOneItem(5302)){
			if (p.getSkills().getLevel(Skills.FARMING) >= 75){
			if (n == 8151){
				if ((p.Planted == false)){
					p.Planted = true;
					p.farmtim = 240;
					p.sm("Your Lantadyme will be ready in 240 seconds!");
					p.setNextAnimation(new Animation(2286));
					p.getInventory().deleteItem(5302, 1);
					p.farmob = n;
				} else if (p.Planted == true && p.farmtim == 0){
					if(p.farmob == n){
						p.Planted = false;
						int o = (Misc.random(2)+1);
						p.getSkills().addXp(Skills.FARMING, 45000);
						p.getInventory().addItem(2481, o);
						p.setNextAnimation(new Animation(2286));
						p.sm("You have picked "+o+" lantadyme's and gained some xp!");
						p.farmob = 0;
					}
				}
			} else {
				p.sm("Lantadyme goes on the herb patch only!");
			}
			} else {
			p.sm("You need at least 75 farming to plant these seeds.");
			}
		} else if (p.getInventory().containsOneItem(5304)){
			if (p.getSkills().getLevel(Skills.FARMING) >= 90){
			if (n == 8151){
				if ((p.Planted == false)){
					p.Planted = true;
					p.farmtim = 300;
					p.sm("Your Torstol will be ready in 300 seconds!");
					p.setNextAnimation(new Animation(2286));
					p.getInventory().deleteItem(5304, 1);
					p.farmob = n;
				} else if (p.Planted == true && p.farmtim == 0){
					if(p.farmob == n){
						p.Planted = false;
						int o = (Misc.random(2)+1);
						p.getSkills().addXp(Skills.FARMING, 75000);
						p.getInventory().addItem(269, o);
						p.setNextAnimation(new Animation(2286));
						p.sm("You have picked "+o+" Torstol's and gained some xp!");
						p.farmob = 0;
					}
				}
			} else {
				p.sm("Torstol goes on the herb patch only!");
			}
			} else {
			p.sm("You need at least 90 farming to plant these seeds.");
			}
		}
		}
		}