package es.ucm.tp1.supercars.control.commands;

import es.ucm.tp1.supercars.logic.Game;
import es.ucm.tp1.supercars.logic.actions.Wave;
import es.ucm.tp1.supercars.control.Buyable;

public class WaveCommand extends Command implements Buyable{
	
	private static final String NAME = "wave";

	private static final String DETAILS = "[w]ave";

	private static final String SHORTCUT = "w";

	private static final String HELP = "Do wave";
	
	private static final boolean updateGame = true;
			
	public WaveCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP, updateGame);
	}

	public boolean execute(Game game) {
		if (buy(game)) {
			Wave wave = new Wave();
			game.executeInstantAction(wave);
			game.incrementCycle();
			return true;
		} else {
			game.incrementCycle();
			return false;
		}
	}

	@Override
	public int cost() {
		return 5;
	}
	
}