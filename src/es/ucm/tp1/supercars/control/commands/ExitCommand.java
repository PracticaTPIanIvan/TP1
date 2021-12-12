package es.ucm.tp1.supercars.control.commands;

import es.ucm.tp1.supercars.logic.Game;

public class ExitCommand extends Command{
	private static final String NAME = "exit";

	private static final String DETAILS = "[e]xit";

	private static final String SHORTCUT = "e";

	private static final String HELP = "exit game";

	private static final boolean updateGame = false;
	
	public ExitCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP, updateGame);
	}

	@Override
	public boolean execute(Game game) {
		game.setExit();
		return false;
	}
}