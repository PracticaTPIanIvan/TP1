package es.ucm.tp1.supercars.control.commands;

import es.ucm.tp1.supercars.logic.Game;

public class UpdateCommand extends Command {

	private static final String NAME = "update";

	private static final String DETAILS = "[n]one | []";

	private static final String SHORTCUT = "n";

	private static final String HELP = "update";

	public UpdateCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}

	@Override
	public boolean execute(Game game) {
		if(game.getPlayerPositionX() < game.getRoadLength()) {
			game.update();
			return true;
		}
		else
			return false;
	}

	@Override
	protected Command parse(String[] commandWords) {
		if ("".equalsIgnoreCase(commandWords[0])) {
			commandWords[0] = SHORTCUT;
		}
		return super.parse(commandWords);
	}
}