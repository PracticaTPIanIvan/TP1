package es.ucm.tp1.supercars.control.commands;

import es.ucm.tp1.supercars.logic.Game;

public class GoUpCommand extends Command {
	
	private static final String NAME = "up";

	private static final String DETAILS = "[q]up";

	private static final String SHORTCUT = "q";

	private static final String HELP = "go up";
	
	protected static final String ERROR = "Player can't move up";
	
	private static final boolean updateGame = true;
			
	public GoUpCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP, updateGame);
	}
	
	public boolean execute(Game game) {
		game.moveUp();
		game.update();
		game.incrementCycle();
		return true;
	}
}