package es.ucm.tp1.supercars.view;

import java.text.DecimalFormat;

import es.ucm.tp1.supercars.logic.Game;
import es.ucm.tp1.supercars.logic.gameobjects.Coin;
import es.ucm.tp1.supercars.logic.gameobjects.Obstacle;
import es.ucm.tp1.supercars.utils.*;


public class GamePrinter {

	private static final String SPACE = " ";

	private static final String VERTICAL_DELIMITER = "|";

	private static final String ROAD_BORDER_PATTERN = "═";

	private static final String LANE_DELIMITER_PATTERN = "─";

	private static final int CELL_SIZE = 7;

	private static final int MARGIN_SIZE = 2;

	private String indentedRoadBorder;

	private String indentedLlanesSeparator;

	private String margin;

	private static final String CRASH_MSG = String.format("Player crashed!%n");

	private static final String WIN_MSG = String.format("Player wins!%n");

	private static final String DO_EXIT_MSG = "Player leaves the game"; 
	
	private static final String GAME_OVER_MSG = "[GAME OVER] "; 
	
	public String newLine; 

	protected Game game;
	
	private long initialTime;
	
	private DecimalFormat df;

	public GamePrinter(Game game) {
		this.game = game;
		df = new DecimalFormat("#.##");

		margin = StringUtils.repeat(SPACE, MARGIN_SIZE);

		String roadBorder = ROAD_BORDER_PATTERN + StringUtils.repeat(ROAD_BORDER_PATTERN, (CELL_SIZE + 1) *  game.getVisibility());
		indentedRoadBorder = String.format("%n%s%s%n", margin, roadBorder);

		String laneDelimiter = StringUtils.repeat(LANE_DELIMITER_PATTERN, CELL_SIZE);
		String lanesSeparator = SPACE + StringUtils.repeat(laneDelimiter + SPACE,  game.getVisibility() - 1) + laneDelimiter + SPACE;

		indentedLlanesSeparator = String.format("%n%s%s%n", margin, lanesSeparator);
		newLine =  System.getProperty("line.separator");
		

		newLine =  System.getProperty("line.separator");
	}
	
	public void reset() {
		initialTime = System.currentTimeMillis();
		
		margin = StringUtils.repeat(SPACE, MARGIN_SIZE);

		String roadBorder = ROAD_BORDER_PATTERN + StringUtils.repeat(ROAD_BORDER_PATTERN, (CELL_SIZE + 1) *  game.getVisibility());
		indentedRoadBorder = String.format("%n%s%s%n", margin, roadBorder);
		String laneDelimiter = StringUtils.repeat(LANE_DELIMITER_PATTERN, CELL_SIZE);
		String lanesSeparator = SPACE + StringUtils.repeat(laneDelimiter + SPACE,  game.getVisibility() - 1) + laneDelimiter + SPACE;
		indentedLlanesSeparator = String.format("%n%s%s%n", margin, lanesSeparator);
		
	}

	private String getInfo() {
		double seconds;
		String res = "";
		
		if (game.getFirstLoop()) {
			initialTime = System.currentTimeMillis();
			game.setFirstLoop(false);
		}
		seconds = (System.currentTimeMillis() - initialTime) / 1000.0;
		
		res += "Distance: " + (game.getRoadLength() - game.getPlayerPositionX()) + "\n" +
				"Coins: " + game.getPlayersCoins() + "\n" +
				"Cycle: " + game.getCycle() + "\n" +
				"Total Obstacles: " + Obstacle.counter + "\n" +
				"Total Coins: " + Coin.counter ;
		
		if (!game.getTest()) {
			res +=  "\n" + "Ellapsed time: ";
			
			if (seconds == 0) {
				res += "0,00 s";
			} else {
				res += df.format(seconds) + " s";
			}
		}
		
		return res;
	}

	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();

		// Game Status
		
		str.append(getInfo());
		
		// Paint game

		str.append(indentedRoadBorder);

		String verticalDelimiter = SPACE;

		for (int y = 0; y < game.getRoadWidth(); y++) {
			str.append(this.margin).append(verticalDelimiter);
			for (int x = 0; x < game.getVisibility(); x++) {
				
				str.append(StringUtils.centre(game.positionToString(x + game.getPlayerPositionX(), y), CELL_SIZE))
						.append(verticalDelimiter);
			}
			if (y <  game.getRoadWidth() - 1) {
				str.append(this.indentedLlanesSeparator);
			}
		}
		str.append(this.indentedRoadBorder);

		return str.toString();
	}

	
	public String endMessage(){
		
		String s = GAME_OVER_MSG;
		
		if (game.isFinished()) {
			s += " " + WIN_MSG;
		} else if (!game.playerIsAlive()) {
			s+= " " + CRASH_MSG;
		} else {
			s+= " " + DO_EXIT_MSG;
		}
		
		
		return s;
	}
}