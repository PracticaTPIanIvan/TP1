package es.ucm.tp1.supercars.control.commands;

import es.ucm.tp1.supercars.logic.Game;
import es.ucm.tp1.supercars.logic.actions.Wave;

public class WaveCommand extends Command{
	
	private static final String NAME = "wave";

	private static final String DETAILS = "[w]ave";

	private static final String SHORTCUT = "w";

	private static final String HELP = "Do wave";
			
	public WaveCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}

	public boolean execute(Game game) {
		Wave wave = new Wave();
		game.executeInstantAction(wave);
		game.incrementCycle();
		return true;
	}
	
}