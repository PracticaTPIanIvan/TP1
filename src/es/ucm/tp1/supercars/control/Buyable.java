package es.ucm.tp1.supercars.control;


import es.ucm.tp1.supercars.control.exceptions.NotEnoughCoinsException;
import es.ucm.tp1.supercars.logic.Game;

public interface Buyable {
	
	public int cost();
	
	public default boolean buy(Game game) throws NotEnoughCoinsException{
		if (game.getPlayersCoins() >= cost()) {
			game.substractPlayerCoins(cost());
			return true;
		} else {
			throw new NotEnoughCoinsException();
		}
		
	}

}