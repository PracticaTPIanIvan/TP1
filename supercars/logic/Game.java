package es.ucm.tp1.supercars.logic;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.ArrayList;

import es.ucm.tp1.supercars.control.Level;
import es.ucm.tp1.supercars.logic.gameobjects.GameObject;
import es.ucm.tp1.supercars.logic.gameobjects.GameObjectContainer;
import es.ucm.tp1.supercars.logic.gameobjects.Player;

public class Game {
	private Player player;
	
	private Level level;
	
	private Random random;
	
	private GameObjectContainer objectContainer;
	
	private static final String PLAYER_ICON = ">";
	
	private static final String OBSTACLE_ICON = "░";
	
	private static final String COIN_ICON = "¢";
	
	private static final String DEAD_PLAYER = "@";
	
	private static final String FINISH_LINE = "¦";
	
	//private ObstacleCointainer container;
	
	private boolean win;
	
	private boolean exit;
	
	private boolean test;
	
	private int obstacleCounter;
	
	private int initialCoins;
	
	private long initialTime;
	
	private boolean firstLoop;
	
	private boolean advance;
	
	private int cycle;

	private DecimalFormat df;
	
	public Game(long seed, Level level) {
		this.level = level;
		this.player = new Player(this);
		this.random = new Random(seed);
		test = false;
		win = false;
		exit = false;
		firstLoop = true;
		objectContainer = new GameObjectContainer();
		df = new DecimalFormat("#.##");
		cycle = 0;
		initialCoins = 0;
		obstacleCounter = 0;
		GameObjectGenerator.generateGameObjects(this, level);
		
	}
	
	public int getPlayerPositionX() {
		return player.getPositionX();
	}
	
	public int getPlayerPositionY() {
		return player.getPositionY();
	}
	
	public int getRoadWidth() {
		return level.getWidth();
	}
	
	public int getRoadLength() {
		return level.getLength();
	}
	
	public int getVisibility() {
		return level.getVisibility();
	}
	
	public Level getLevel() {
		return level;
	}
	
	public int StartingPos() {
		if(getRoadWidth() == 5)
			return 2;
		else
			return 1;
	}
	
	public int objectPosition(int j, int i) {
		int pos = -1;
		for(int k = 0; k < objectContainer.getObjectCounter(); k++) {
			if(objectContainer.getObjectPositionX(k) == j 
					&& objectContainer.getObjectPositionY(k) == i) {
				pos = k;
			}
		}
		return pos;
	}

	public void moveUp() {
		player.positionUp();
	}

	public void moveDown() {
		player.positionDown();
	}
	
	public void update() {
		player.update();
	}
	
	public void addObstacleCounter() {
		obstacleCounter++;
	}
	
	public void addInitialCoins() {
		initialCoins++;
	}
	
	public boolean emptyPos(int x, int y) {
		boolean res = true;
		
		if (objectPosition(x, y) != -1 || 
				(getPlayerPositionX() == x && getPlayerPositionY() == y)) {
			res = false;
		} 
		return res;
	}

	public boolean isFinished() {
		return (player.getPositionX() > getRoadLength());
	}
	
	public boolean playerIsAlive() {
		return player.isAlive();
	}
	
	public double getRandomNumber() {
		return random.nextDouble();
	}
	
	public int getRandomLane() {
		return (int) (getRandomNumber() * getRoadWidth());
	}
	
	public String getGameStatus() {
		double seconds;
		String res = "";
		
		if (firstLoop) {
			initialTime = System.currentTimeMillis();
			firstLoop = false;
		}
		seconds = (System.currentTimeMillis() - initialTime) / 1000.0;
		
		res += "Distance: " + (level.getLength() - getPlayerPositionX()) + "\n" +
				"Coins: " + player.getCoins() + "\n" +
				"Cycle: " + cycle + "\n" +
				"Total Obstacles: " + obstacleCounter + "\n" +
				"Total Coins: " + initialCoins ;
		
		if (!test) {
			res +=  "\n" + "Ellapsed time: ";
			
			if (seconds == 0) {
				res += "0.00 s";
			} else {
				res += df.format(seconds) + " s";
			}
		}
		
		return res;
	}
	
	public boolean tryToAddObject(GameObject object, double frequency) {
		if(random.nextDouble() < frequency && emptyPos(object.getX(), 
				object.getY())) {
			objectContainer.setNewObject(object);
			return true;
		}
		else {
			return false;
		}
	}
	
	public String positionToString(int j, int i) { 
		
		if(j == player.getPositionX() && i == player.getPositionY()) {
			if (player.isAlive()) {
				return PLAYER_ICON;
			} else {
				return DEAD_PLAYER;
			}
		}
		else if(objectPosition(j, i) != -1) {
			return  objectContainer.toString(objectPosition(j, i));
		}
		else if (j == getRoadLength()) {
			return FINISH_LINE;
		}
		else {
			return "";
		}
	}
}
