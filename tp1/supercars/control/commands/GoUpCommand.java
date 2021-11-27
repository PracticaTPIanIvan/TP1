package es.ucm.tp1.supercars.control.commands;

import es.ucm.tp1.supercars.logic.Game;

public class GoUpCommand extends Command {
	
	private static final String NAME = "up";

	private static final String DETAILS = "[q]up";

	private static final String SHORTCUT = "q";

	private static final String HELP = "go up";
	
	protected static final String ERROR = "Player can't move up";
			
	public GoUpCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}
	
	public boolean execute(Game game) {
		if(game.getPlayerPositionY() > 0 && game.getPlayerPositionX() < game.getRoadLength()) {
			game.moveUp();
			game.update();
			game.incrementCycle();
			return true;
		} else {
			System.out.println(ERROR);
			return false;
		}
	}
}