package es.ucm.tp1.supercars.control.commands;

import es.ucm.tp1.supercars.logic.Game;

public class CheatCommand extends Command{
	private static final String NAME = "cheat";

	private static final String DETAILS = "Cheat [1..5]";

	private static final String SHORTCUT = "";

	private static final String HELP = "Removes all elements from last visible column,"
			+ " and adds an Advanced object";
	
	private int id;
	
	private static final char numCommands = '5';
	
	private static final boolean updateGame = false;
			
	public CheatCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP, updateGame); //Se puede añadir el updateGame?
	}
	
	protected boolean matchCommandName(String name) { //Mirar si name es mayor que 1
		char charName = name.charAt(0);
		return (charName > '0' && charName <= numCommands); //Se puede?
	}
	
	protected Command parse(String[] words) {
		if (matchCommandName(words[0])) {
			
			if (words.length != 1) {
				System.out.format("[ERROR]: Command %s: %s%n%n", NAME, INCORRECT_NUMBER_OF_ARGS_MSG);
				return null;
			} else {
				id = Character.getNumericValue(words[0].charAt(0));
				return this;
			}
		}
		
		return null;
	}
	@Override
	public boolean execute(Game game) {
		for (int i = 0; i < game.getRoadWidth(); i++) {
			game.killObjectsInPos(game.getPlayerPositionX() + game.getVisibility() - 1, i);
			game.deleteObjects();
		}
		
		game.deleteObjects();
		game.forceAdvanceObject(game, id, game.getPlayerPositionX() + game.getVisibility() - 1);
		return true; //No tiene que hacer update
	}
}
