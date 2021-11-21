package es.ucm.tp1.supercars.control.commands;

import es.ucm.tp1.supercars.logic.Game;

public class GoDownCommand extends Command {
	
	private static final String NAME = "down";

	private static final String DETAILS = "[a]down";

	private static final String SHORTCUT = "a";

	private static final String HELP = "go down";
	
	protected static final String ERROR = "Player can't move down";
	
	public GoDownCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}
	
	public boolean execute(Game game) {
		if(game.getPlayerPositionY() < game.getRoadWidth() - 1
				&& game.getPlayerPositionX() < game.getRoadLength()) {
			game.moveDown();
			game.update();
			game.incrementCycle();
			return true;
		} else {
			System.out.println(ERROR);
			return false;
		}
	}
}
