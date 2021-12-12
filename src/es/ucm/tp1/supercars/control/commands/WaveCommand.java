package es.ucm.tp1.supercars.control.commands;

import es.ucm.tp1.supercars.logic.Game;
import es.ucm.tp1.supercars.logic.actions.Wave;
import es.ucm.tp1.supercars.control.Buyable;
import es.ucm.tp1.supercars.control.exceptions.CommandExecuteException;
import es.ucm.tp1.supercars.control.exceptions.NotEnoughCoinsException;

public class WaveCommand extends Command implements Buyable{
	
	private static final String NAME = "wave";

	private static final String DETAILS = "[w]ave";

	private static final String SHORTCUT = "w";

	private static final String HELP = "Do wave";
	
	private static final String ERROR = "Failed to execute wave";
	
	private static final boolean updateGame = true;
			
	public WaveCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP, updateGame);
	}

	public boolean execute(Game game) throws CommandExecuteException{
		try {
			if (buy(game)) {
				Wave wave = new Wave();
				game.executeInstantAction(wave);
				game.incrementCycle();
				return true;
			} else {
				game.incrementCycle();
				return false;
			}
		} catch(NotEnoughCoinsException e) {
			System.out.println(e.getMessage());
			throw new CommandExecuteException(String.format("[ERROR]: %s", ERROR), e);
		}
	}

	@Override
	public int cost() {
		return 5;
	}
	
}