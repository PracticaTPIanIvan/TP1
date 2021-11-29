package es.ucm.tp1.supercars.control.commands;

import es.ucm.tp1.supercars.logic.Game;
import es.ucm.tp1.supercars.logic.gameobjects.Grenade;
import es.ucm.tp1.supercars.control.Buyable;

public class GrenadeCommand extends Command implements Buyable{
	private static final String NAME = "grenade";

	private static final String DETAILS = "[g]renade";

	private static final String SHORTCUT = "g";

	private static final String HELP = "add a grenade in position x, y";
	
	private int grenadeX;
	
	private int grenadeY;

	public GrenadeCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}

	@Override
	public boolean execute(Game game) {
		game.addObject(new Grenade(game, grenadeX, grenadeY));
		buy(game);
		return true;
	}
	
	protected Command parse(String[] words) {
		if (matchCommandName(words[0])) {
			if (words.length != 3) {
				System.out.format("[ERROR]: Command %s: %s%n%n", NAME, INCORRECT_NUMBER_OF_ARGS_MSG);
				return null;
			} else {
				grenadeX = Integer.parseInt(words[1]);
				grenadeY = Integer.parseInt(words[2]);
				return this;
			}
		}
		return null;
	}

	@Override
	public int cost() {
		return 5;
	}

}