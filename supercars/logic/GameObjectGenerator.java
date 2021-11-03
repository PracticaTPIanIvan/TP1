package es.ucm.tp1.supercars.logic;

import es.ucm.tp1.supercars.control.Level;
import es.ucm.tp1.supercars.logic.gameobjects.Coin;
import es.ucm.tp1.supercars.logic.gameobjects.Obstacle;

public class GameObjectGenerator {
	
	private Level level;

	public static void generateGameObjects(Game game, Level level) {

		for (int x = game.getVisibility() / 2; x < game.getRoadLength(); x++) {
			if (game.tryToAddObject(new Obstacle(game, x, game.getRandomLane()), level.getObstacleFrequency())) {
				game.addObstacleCounter();
			}
			int y = game.getRandomLane();
			if (game.tryToAddObject(new Coin(game, x, y), level.getCoinFrequency())) {
				game.addInitialCoins();
			}
		}
	}

	public static void reset(Level level) {
		// TODO add your code
	}

	public static void generateRuntimeObjects(Game game) {
		// TODO add your code
	}
}