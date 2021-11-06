package es.ucm.tp1.supercars.logic;

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
	
	private boolean exit;
	
	private boolean test;
	
	private int obstacleCounter;
	
	private int initialCoins;
	
	private boolean firstLoop;
	
	private boolean advance;
	
	private int cycle;
	
	public Game(long seed, Level level) {
		this.level = level;
		this.player = new Player(this);
		this.random = new Random(seed);
		test = false;
		exit = false;
		firstLoop = true;
		objectContainer = new GameObjectContainer();
		cycle = 0;
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
	
	public boolean getTest() {
		return test;
	}
	
	public int getCycle() {
		return cycle;
	}
	
	public boolean getFirstLoop() {
		return firstLoop;
	}
	
	public void setFirstLoop(boolean bool) {
		firstLoop = bool;
	}
	
	public void incrementCycle() {
		cycle++;
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
	
	public GameObject getObjectInPosition(int x, int y) {
		int ind = objectPosition(x, y);
		return objectContainer.getObject(ind);
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
	
	public boolean getExit() {
		return exit;
	}
	
	public void reset() {
		
	}
	
	public void toggleTest() {
		test = true;
	}
	public boolean doCollision() {
		return player.doCollision();
	}
	
	public void setExit() {
		exit = true;
	}
	
	
	public boolean tryToAddObject(GameObject object, double frequency) {
		if(random.nextDouble() < frequency && emptyPos(object.getX(), 
				object.getY())) {
			objectContainer.setNewObject(object);
			object.onEnter();
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
