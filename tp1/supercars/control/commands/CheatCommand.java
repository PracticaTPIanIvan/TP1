package es.ucm.tp1.supercars.control.commands;

import es.ucm.tp1.supercars.logic.Game;

public class CheatCommand extends Command{
	private static final String NAME = "cheat";

	private static final String DETAILS = "Cheat [1..5]";

	private static final String SHORTCUT = "";

	private static final String HELP = "Removes all elements from last visible column,"
			+ " and adds an Advanced object";
	
	private int id;
	
	private static final int numCommands = 5;
			
	public CheatCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}
	
	protected boolean matchCommandName(String name) {
		int provisionalId = Integer.valueOf(name);
		return (provisionalId > 0 && provisionalId <= numCommands);
	}
	
	protected Command parse(String[] words) {
		if (matchCommandName(words[0])) {
			if (words.length != 1) {
				System.out.format("[ERROR]: Command %s: %s%n%n", NAME, INCORRECT_NUMBER_OF_ARGS_MSG);
				return null;
			} else {
				return this;
			}
		}
		return null;
	}
	@Override
	public boolean execute(Game game) {
		int ind;
		for (int i = 0; i < game.getRoadWidth(); i++) {
			if(game.objectInPosition(game.getPlayerPositionX() + game.getVisibility(), i)) {
				game.killObject(game.getPlayerPositionX() + game.getVisibility(), i);
			}
			
			
		}
		game.deleteObjects();
		game.forceAdvanceObject(game, id, game.getPlayerPositionX() + game.getVisibility());
		return true;
	}
}
