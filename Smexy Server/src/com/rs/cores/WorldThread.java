package com.rs.cores;

import com.rs.Settings;
import com.rs.game.World;
import com.rs.game.npc.NPC;
import com.rs.game.player.Player;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Misc;
import com.rs.utils.PlayersOnline;
import com.rs.utils.Utils;

public final class WorldThread extends Thread {

	protected WorldThread() {
		setPriority(Thread.MAX_PRIORITY);
		setName("World Thread");
	}
public String[] news = {"Please use ::commands or ::cmd to see the commands!"
		,"Dont forget to vote every 24 hours, do ::vote to go to site!"
		,"The more you vote the more players we get, so do ::vote!"
		,"Check out the Updates on ::wiki to see the most recent game updates!"
		,"Have recently added new bosses and Slayer Tower do ::commands to see!"
		,"If you find a bug, please report it on forums, Along with reporting staff or players!"};
private int NewsTimer;
private int newsnum = 0;
private int ponline;
	@Override
	public final void run() {
		while (!CoresManager.shutdown) {
			long currentTime = Utils.currentTimeMillis();
			try {
				// long debug = Utils.currentTimeMillis();
				WorldTasksManager.processTasks();
				// System.out.print("TASKS: "+(Utils.currentTimeMillis()-debug));
				// debug = Utils.currentTimeMillis();
				for (Player player : World.getPlayers()) {
					if (player == null || !player.hasStarted() || player.hasFinished())
						continue;
					if (currentTime - player.getPacketsDecoderPing() > Settings.MAX_PACKETS_DECODER_PING_DELAY
							&& player.getSession().getChannel().isOpen())
						player.getSession().getChannel().close();
					player.processEntity();
				}
				if (ponline == 0){
			//	PlayersOnline.updatePlayers();
				ponline = 45;
				}
				if (ponline > 0){
					ponline --;
				}
				/*TODO ----- News*/
				if (NewsTimer == 0){
					if (newsnum  == 6)
						newsnum = 0;
					World.sendWorldWideMessage("<shad=4CC417><col=59E817>[NEWS]: <col=59E817>"+news[newsnum]);
					NewsTimer = 240;
					newsnum++;
				}
				if (NewsTimer > 0){
					NewsTimer--;
				}
			//	System.out.print(" ,PLAYERS PROCESS: "+(Utils.currentTimeMillis()-debug));
			//	 debug = Utils.currentTimeMillis();
				for (NPC npc : World.getNPCs()) {
					if (npc == null || npc.hasFinished())
						continue;
					npc.processEntity();
				}
				//	 System.out.print(" ,NPCS PROCESS: "+(Utils.currentTimeMillis()-debug));
				//	 debug = Utils.currentTimeMillis();

				for (Player player : World.getPlayers()) {
					if (player == null || !player.hasStarted() || player.hasFinished())
						continue;
					player.getPackets().sendLocalPlayersUpdate();
					player.getPackets().sendLocalNPCsUpdate();
				}
				// System.out.print(" ,PLAYER UPDATE: "+(Utils.currentTimeMillis()-debug)+", "+World.getPlayers().size()+", "+World.getNPCs().size());
				// debug = Utils.currentTimeMillis();
				for (Player player : World.getPlayers()) {
					if (player == null || !player.hasStarted()
							|| player.hasFinished())
						continue;
					player.resetMasks();
				}
				for (NPC npc : World.getNPCs()) {
					if (npc == null || npc.hasFinished())
						continue;
					npc.resetMasks();
				}
			} catch (Throwable e) {
				e.printStackTrace();
			}
			// System.out.println(" ,TOTAL: "+(Utils.currentTimeMillis()-currentTime));
			LAST_CYCLE_CTM = Utils.currentTimeMillis();
			long sleepTime = Settings.WORLD_CYCLE_TIME + currentTime
					- LAST_CYCLE_CTM;
			if (sleepTime <= 0)
				continue;
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static long LAST_CYCLE_CTM;

}
