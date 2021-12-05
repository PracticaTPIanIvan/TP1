package es.ucm.tp1.supercars.control.commands;

import es.ucm.tp1.supercars.logic.Game;
import es.ucm.tp1.supercars.utils.StringUtils;
import es.ucm.tp1.supercars.view.GamePrinter;

public class InfoCommand extends Command {

	private static final String NAME = "info";

	private static final String DETAILS = "[i]nfo";

	private static final String SHORTCUT = "i";

	private static final String HELP = "prints gameobject info";
	
	private static final boolean updateGame = false;

	public InfoCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP, updateGame);
	}

	@Override
	public boolean execute(Game game) {
		System.out.print("Available objects: \n[Coin] gives 1 coin to the player \n[Obstacle] hits car \n" +
				"[GRENADE] Explodes in 3 cycles, harming everyone around \n" + "[WALL] hard obstacle \n" +
				"[TURBO] pushes the car: 3 columns \n[SUPERCOIN] gives 1000 coins \n" +
				"[TRUCK] moves towards the player \n[PEDESTRIAN] person crossing the road up and down \n");
		return false;
	}

}