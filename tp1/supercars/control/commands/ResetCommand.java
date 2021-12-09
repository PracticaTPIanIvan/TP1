package es.ucm.tp1.supercars.control.commands;

import es.ucm.tp1.supercars.logic.Game;
import es.ucm.tp1.supercars.control.Level;

public class ResetCommand extends Command{
	private static final String NAME = "reset";

	private static final String DETAILS = "[r]eset";

	private static final String SHORTCUT = "r";

	private static final String HELP = "reset game";
	
	private static final boolean updateGame = false;
	
	private static long newSeed;
	
	private static Level newLevel;
	
	private static boolean hasParameters;

	public ResetCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP, updateGame);
	}
	
	protected Command parse(String[] words) {
		if (matchCommandName(words[0])) {
			if (words.length == 3){
				if (Level.valueOfIgnoreCase(words[1]) != null) {
					newLevel = Level.valueOfIgnoreCase(words[1]);
					newSeed = Integer.parseInt(words[2]);
					hasParameters = true;
					return this;
				}
			} else if (words.length == 1) {
				hasParameters = false;
				return this;
			} else {
				System.out.format("[ERROR]: Command %s: %s%n%n", SHORTCUT, INCORRECT_NUMBER_OF_ARGS_MSG);
				return null;
			}
			
		}
		return null;
	}

	@Override
	public boolean execute(Game game) {
		if (hasParameters) {
			game.setReset(true, newLevel, newSeed);
		} else {
			game.setReset(true);
		}
		
		return true;
	}
}