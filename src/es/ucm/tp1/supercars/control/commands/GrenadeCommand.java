package es.ucm.tp1.supercars.control.commands;

import es.ucm.tp1.supercars.logic.Game;
import es.ucm.tp1.supercars.logic.gameobjects.Grenade;
import es.ucm.tp1.supercars.control.Buyable;
import es.ucm.tp1.supercars.control.exceptions.CommandExecuteException;
import es.ucm.tp1.supercars.control.exceptions.CommandParseException;
import es.ucm.tp1.supercars.control.exceptions.InvalidPositionException;
import es.ucm.tp1.supercars.control.exceptions.NotEnoughCoinsException;

public class GrenadeCommand extends Command implements Buyable{
	private static final String NAME = "grenade";

	private static final String DETAILS = "[g]renade";

	private static final String SHORTCUT = "g";

	private static final String HELP = "add a grenade in position x, y";
	
	private static final boolean updateGame = true;
	
	private static final String POS_IS_NUMBER_MSG = "Position must be a number";
	
	private static final String FAILED_MSG = "Failed to add grenade \n";
	
	private static final String INCORRECT_NUMBER_OF_ARGS_MSG = "Incorrect number of arguments for grenade command: [g]renade <x> <y>";
	
	private int grenadeRelativeX;
	
	private int grenadeY;

	public GrenadeCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP, updateGame);
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException{
		try {
			if (grenadeRelativeX >= game.getVisibility() || grenadeRelativeX < 0 
					|| grenadeY >= game.getRoadWidth() || grenadeY < 0) {
				throw new InvalidPositionException();
			} else {
				if (game.emptyPos(game.getPlayerPositionX() + grenadeRelativeX, grenadeY)) {
					if (buy(game)) {
						game.addObject(new Grenade(game, game.getPlayerPositionX() + grenadeRelativeX, grenadeY));
						game.incrementCycle();
						return true;
					}
				} else {
					throw new InvalidPositionException();
				}
			}
		}
		catch(NotEnoughCoinsException e) {
			System.out.println(e.getMessage());
			throw new CommandExecuteException(String.format("[ERROR]: %s ", FAILED_MSG), e);
		}
		catch(InvalidPositionException e) {
			System.out.println(e.getMessage());
			throw new CommandExecuteException(String.format("[ERROR]: %s ", FAILED_MSG), e);
		}
		
		
		return false;
	}
	
	protected Command parse(String[] words) throws CommandParseException{
		if (matchCommandName(words[0])) {
			if (words.length != 3) {
				throw new CommandParseException("[ERROR]: " + INCORRECT_NUMBER_OF_ARGS_MSG + "\n");
			} else {
				try {
					grenadeRelativeX = Integer.parseInt(words[1]);
					grenadeY = Integer.parseInt(words[2]);
					return this;
				}
				catch(NumberFormatException nfe) {
					System.out.println(POS_IS_NUMBER_MSG);
				} 
			}
		}
		return null;
	}

	@Override
	public int cost() {
		return 3;
	}

}