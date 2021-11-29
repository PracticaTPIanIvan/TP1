package es.ucm.tp1.supercars.control;


import es.ucm.tp1.supercars.logic.Game;

public interface Buyable {
	
	public int cost();
	
	public default boolean buy(Game game){
		if (game.getPlayersCoins() >= cost()) {
			game.substractPlayerCoins(cost());
		}
		return game.getPlayersCoins() >= cost();
	};

}