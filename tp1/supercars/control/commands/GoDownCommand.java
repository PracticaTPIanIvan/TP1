package es.ucm.tp1.supercars.control.commands;

import es.ucm.tp1.supercars.logic.Game;

public class GoDownCommand extends Command {
	
	private static final String NAME = "down";

	private static final String DETAILS = "[a]down";

	private static final String SHORTCUT = "a";

	private static final String HELP = "go down";
	
	protected static final String ERROR = "Player can't move down";
	
	private static final boolean updateGame = true;
	
	public GoDownCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP, updateGame);
	}
	
	public boolean execute(Game game) {
		game.moveDown();
		game.update();
		game.incrementCycle();
		return true;
	}
}