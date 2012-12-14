package com.rs.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.rs.game.player.Skills;
import com.rs.game.player.*;
import com.rs.utils.Misc;
import com.rs.game.World;

//Heres a command for people who don't know >.>
//Don't forget to change imports and the way your server handles the below methods. 

/*  @ Runeunited
if (cmd[0].equals("::recieve")) {	
	DonationManager.startProcess(player);
}
*/

public class DonationManager {

	public static Connection con = null;
	public static Statement stmt;
	public static boolean connectionMade;

	
	public static void createConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		String IP="";
        String DB="";
        String User="";
        String Pass=""; 
			con = DriverManager.getConnection("jdbc:mysql://"+IP+"/"+DB, User, Pass);
			stmt = con.createStatement();
		System.out.println("Connection to Donation database successful!"); //You can take these system prints out. Get annoying sometimes.
		} catch (Exception e) {
        System.out.println("Connection to Donation database failed");
        e.printStackTrace();
		}
	}
	
	public static void startProcess(final Player player) {  //Choose which payment options give which things.
		createConnection();
		if(checkDonation(player.getUsername())) {
			if(player.getInventory().getFreeSlots() < 10) {
				player.getPackets().sendGameMessage("<col=00FFCC>Create some more space in your inventory Before doing this!");			
				return;
			}
			/*
			*	READ THIS!!!
			*   To Choose which item they would get simple edit the below product id's.
			*   Example: If they chose option number 4 on the website, the product id would be 4.
			*/
			if(checkDonationItem(player.getUsername()) == 1) { //Productid 1 
				player.getInventory().addItem(19780, 1);
				player.getPackets().sendGameMessage("<shad=cc0ff><img=1>You have your recieved your korasi enjoy! :)!");
				if (player.donator == false) {
					player.donator = true;
					for(Player p : World.getPlayers()) {
						if (p ==null) {
							continue; 
						}
					p.getPackets().sendGameMessage("<shad=cc0ff><img=1>Welcome New Donator "+player.getUsername()+"!");
					}
				}
				donationGiven(player.getUsername());			
			}
			else if(checkDonationItem(player.getUsername()) == 2) { //Productid 2
				player.getInventory().addItem(16689, 1);
				player.getInventory().addItem(16711, 1);
				player.getInventory().addItem(17259, 1);
				player.getInventory().addItem(17561, 1);
				player.getPackets().sendGameMessage("<shad=cc0ff><img=1>You recieved your primal set!");
				//player.getPackets().sendGameMessage("<col=00FFCC>The donatorzone can be found by the portal at home or ::donatorzone");
				donationGiven(player.getUsername());
				if (player.donator == false) {
					player.donator = true;
					for(Player p : World.getPlayers()) {
						if (p ==null) {
							continue; 
						}
					p.getPackets().sendGameMessage("<shad=cc0ff><img=1>Welcome New Donator "+player.getUsername()+"!");
					}
				}
			}
			else if(checkDonationItem(player.getUsername()) == 3) { //Productid 3
				player.getInventory().addItem(16909, 1);
				player.getInventory().addItem(16955, 1);
				player.getInventory().addItem(17143, 1);
				player.getInventory().addItem(16425, 1);
				player.getInventory().addItem(16403, 1);
				player.getPackets().sendGameMessage("<shad=cc0ff><img=1>You have recieved your primal weapon set!");
					donationGiven(player.getUsername());
					if (player.donator == false) {
					player.donator = true;
					for(Player p : World.getPlayers()) {
						if (p ==null) {
							continue; 
						}
					p.getPackets().sendGameMessage("<shad=cc0ff><img=1>Welcome New Donator "+player.getUsername()+"!");
					}
				}
			}
			else if(checkDonationItem(player.getUsername()) == 4) { //Productid 4
				player.getInventory().addItem(16403, 1);
				player.getPackets().sendGameMessage("<shad=cc0ff><img=1>You recieved your primal long sword!");
				if (player.donator == false) {
					player.donator = true;
					for(Player p : World.getPlayers()) {
						if (p ==null) {
							continue; 
						}
					p.getPackets().sendGameMessage("<shad=cc0ff><img=1>Welcome New Donator "+player.getUsername()+"!");
					}
				}
				donationGiven(player.getUsername());	
			}
			else if(checkDonationItem(player.getUsername()) == 5) { //Productid 5
				player.getInventory().addItem(16425, 1);
				player.getPackets().sendGameMessage("<shad=cc0ff><img=1>You recieved your primal maul!");
				if (player.donator == false) {
					player.donator = true;
					for(Player p : World.getPlayers()) {
						if (p ==null) {
							continue; 
						}
					p.getPackets().sendGameMessage("<shad=cc0ff><img=1>Welcome New Donator "+player.getUsername()+"!");
					}
				}
				donationGiven(player.getUsername());	
			}	
			else if(checkDonationItem(player.getUsername()) == 6) { //Productid 6
				player.getInventory().addItem(16909, 1);
				player.getPackets().sendGameMessage("<shad=cc0ff><img=1>You recieved your primal 2h!");
				if (player.donator == false) {
					player.donator = true;
					for(Player p : World.getPlayers()) {
						if (p ==null) {
							continue; 
						}
					p.getPackets().sendGameMessage("<shad=cc0ff><img=1>Welcome New Donator "+player.getUsername()+"!");
					}
				}
				donationGiven(player.getUsername());	
			}	
			else if(checkDonationItem(player.getUsername()) == 7) { //Productid 6
				player.getInventory().addItem(16955, 1);
				player.getPackets().sendGameMessage("<shad=cc0ff><img=1>You recieved your primal rapier!");
				if (player.donator == false) {
					player.donator = true;
					for(Player p : World.getPlayers()) {
						if (p ==null) {
							continue; 
						}
					p.getPackets().sendGameMessage("<shad=cc0ff><img=1>Welcome New Donator "+player.getUsername()+"!");
					}
				}
				donationGiven(player.getUsername());	
			}	
			else if(checkDonationItem(player.getUsername()) == 8) { //Productid 6
				player.getInventory().addItem(17143, 1);
				player.getPackets().sendGameMessage("<shad=cc0ff><img=1>You recieved your primal spear!");
				if (player.donator == false) {
					player.donator = true;
					for(Player p : World.getPlayers()) {
						if (p ==null) {
							continue; 
						}
					p.getPackets().sendGameMessage("<shad=cc0ff><img=1>Welcome New Donator "+player.getUsername()+"!");
					}
				}
				donationGiven(player.getUsername());	
			}	
			else if(checkDonationItem(player.getUsername()) == 9) { //Productid 6
				player.getInventory().addItem(1038, 1);
				player.getInventory().addItem(1040, 1);	
				player.getInventory().addItem(1042, 1);
				player.getInventory().addItem(1044, 1);
				player.getInventory().addItem(1046, 1);
				player.getInventory().addItem(1048, 1);
				player.getPackets().sendGameMessage("<shad=cc0ff><img=1>You recieved your party hat set.!");
				if (player.donator == false) {
					player.donator = true;
					for(Player p : World.getPlayers()) {
						if (p ==null) {
							continue; 
						}
					p.getPackets().sendGameMessage("<shad=cc0ff><img=1>Welcome New Donator "+player.getUsername()+"!");
					}
				}
				donationGiven(player.getUsername());	
			}				
			else if(checkDonationItem(player.getUsername()) == 10) { //Productid 6
				int o = Misc.random(17);
				switch(o){
					case 0:
						player.getInventory().addItem(1038, 1);
						player.sm("You have recieved red partyhat");
					break;
					case 1:
						player.getInventory().addItem(1040, 1);
						player.sm("You have recieved yellow partyhat");
					break;
					case 2:
						player.getInventory().addItem(1042, 1);
						player.sm("You have recieved blue partyhat");
					break;
					case 3:
						player.getInventory().addItem(1044, 1);
						player.sm("You have recieved green partyhat");
					break;
					case 4:
						player.getInventory().addItem(1046, 1);
						player.sm("You have recieved purple partyhat");
					break;
					case 5:
						player.getInventory().addItem(1048, 1);
						player.sm("You have recieved white partyhat");
					break;
					case 7:
						player.getInventory().addItem(1053, 1);
						player.sm("You have recieved green haloween mask");
					break;
					case 8:
						player.getInventory().addItem(1055, 1);
						player.sm("You have recieved blue haloween mask");
					break;
					case 9:
						player.getInventory().addItem(1057, 1);
						player.sm("You have recieved red haloween mask");
					break;
					case 10:
						player.getInventory().addItem(20135, 1);
						player.sm("You have recieved torva helmet");
					break;
					case 11:
						player.getInventory().addItem(20139, 1);
						player.sm("You have recieved torva top");
					break;
					case 12:
						player.getInventory().addItem(20143, 1);
						player.sm("You have recieved torva legs");
					break;
					case 13:
						player.getInventory().addItem(20147, 1);
						player.sm("You have recieved pernix hood");
					break;
					case 14:
						player.getInventory().addItem(20151, 1);
						player.sm("You have recieved pernix top");
					break;
					case 15:
						player.getInventory().addItem(20155, 1);
						player.sm("You have recieved pernix chaps");
					break;
					case 16:
						player.getInventory().addItem(20159, 1);
						player.sm("You have recieved virtus robe mask");
					break;
					case 17:
						player.getInventory().addItem(20163, 1);
						player.sm("You have recieved virtus robe top");
					break;
					case 18:
						player.getInventory().addItem(20167, 1);
						player.sm("You have recieved virtus robe legs");
					break;
				}
				if (player.donator == false) {
					player.donator = true;
					for(Player p : World.getPlayers()) {
						if (p ==null) {
							continue; 
						}
					p.getPackets().sendGameMessage("<shad=cc0ff><img=1>Welcome New Donator "+player.getUsername()+"!");
					}
				}
				donationGiven(player.getUsername());	
			}						
		} else {
			player.getPackets().sendGameMessage("<col=00FFCC>You Either have not donated or have claimed your reward already.");
			
	}
		} 
	public static ResultSet query(String s) throws SQLException {
		try {
			if (s.toLowerCase().startsWith("select")) {
				ResultSet rs = stmt.executeQuery(s);
				return rs;
			} else {
				stmt.executeUpdate(s);
			}
			return null;
		} catch (Exception e) {
			destroyConnection();
		}
		return null;
	}

	public static void destroyConnection() {
		try {
			stmt.close();
			con.close();
		} catch (Exception e) {
		}
	}

	public static boolean checkDonation(String playerName) {
   
        try {
			String name2 = playerName.replaceAll("_", " ");
            Statement statement = con.createStatement();
            String query = "SELECT * FROM donation WHERE username = '" + name2 + "'";
            ResultSet results = statement.executeQuery(query);
				while(results.next()) {
                    int tickets = results.getInt("tickets"); //tickets are "Recieved" technically. 0 for claimed, 1 for not claimed.
                        if(tickets == 1) {                     
                         return true;
                         }
                                
                        }
                } catch(SQLException e) {
                        e.printStackTrace();
                }
				
                return false;
				
        }
	
	public static int checkDonationItem(String playerName) {
        
        try {
			String name2 = playerName.replaceAll("_", " ");
			Statement statement = con.createStatement();
            String query = "SELECT * FROM donation WHERE username = '" + name2 + "'";
            ResultSet results = statement.executeQuery(query);
                while(results.next()) {
                    int productid = results.getInt("productid");
                        if(productid >= 1) {				                          
							return productid;
                            }
								                               
                        }
                } catch(SQLException e) {
                        e.printStackTrace();
                }
				
                return 0;
				
        }		
	
	public static boolean donationGiven(String playerName) {       
              
			  try
                {
				String name2 = playerName.replaceAll("_", " ");
				query("DELETE FROM `donation` WHERE username = '"+name2+"';");
                       // query("UPDATE donations SET tickets = 0 WHERE username = '" + playerName + "'");
						//query("UPDATE donations SET productid = 0 WHERE username = '" + playerName + "'");
						
                } catch (Exception e) {
                        e.printStackTrace();
						
                        return false;
                }
                return true;
        }	
	
}	
	
