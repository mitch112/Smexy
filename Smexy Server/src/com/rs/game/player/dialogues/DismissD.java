package com.rs.game.player.dialogues;

public class DismissD extends Dialogue {

	@Override
	public void start() {
		this.sendDialogue(SEND_2_OPTIONS, new String[] { "Dismiss Familiar?",
				"Yes.", "No." });
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (componentId == 1) {
			if (player.getFamiliar() != null)
				player.getFamiliar().sendDeath(player);
		}
		end();
	}

	@Override
	public void finish() {

	}

}
