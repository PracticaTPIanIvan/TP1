package es.ucm.tp1.supercars.control.commands;

import es.ucm.tp1.supercars.logic.Game;
import es.ucm.tp1.supercars.logic.actions.Shoot;
import es.ucm.tp1.supercars.control.Buyable;

public class ShootCommand extends Command implements Buyable{
	private static final String NAME = "shoot";

	private static final String DETAILS = "[s]hoot";

	private static final String SHORTCUT = "s";

	private static final String HELP = "shoot bullet";
	
	protected static final String ERROR = "[ERROR]: Failed to shoot \n";
	
	private static final boolean updateGame = true;

	public ShootCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP, updateGame);
	}

	@Override
	public boolean execute(Game game) {
		if (buy(game)) {
			Shoot shoot = new Shoot();
			game.executeInstantAction(shoot);
			game.incrementCycle();
			return true;
		} else {
			System.out.println(ERROR);
			game.incrementCycle();
			return true;
		}
	}

	@Override
	public int cost() {
		return 1;
	}
}