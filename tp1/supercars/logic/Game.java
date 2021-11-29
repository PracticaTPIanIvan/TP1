package es.ucm.tp1.supercars.logic;

import java.util.Random;
import java.util.ArrayList;

import es.ucm.tp1.supercars.control.Level;
import es.ucm.tp1.supercars.logic.gameobjects.GameObject;
import es.ucm.tp1.supercars.logic.gameobjects.GameObjectContainer;
import es.ucm.tp1.supercars.logic.gameobjects.Player;
import es.ucm.tp1.supercars.logic.actions.InstantAction;

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
	
	private boolean exit;
	
	private boolean test;
	
	private boolean reset;
	
	private long seed;
	
	private boolean firstLoop;
	
	private boolean advance;
	
	private int cycle;
	
	public Game(long seed, Level level) {
		this.level = level;
		this.seed = seed;
		this.player = new Player(this);
		this.random = new Random(seed);
		if (level == Level.TEST) {
			test = true;
		} else {
			test = false;
		}
		exit = false;
		reset = false;
		firstLoop = true;
		objectContainer = new GameObjectContainer();
		cycle = 0;
		GameObjectGenerator.generateGameObjects(this, level);
	}
	
	public void generateRuntimeObjects() {
		GameObjectGenerator.generateRuntimeObjects(this, level);
	}
	
	public long getSeed() {
		return seed;
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
	
	public boolean getReset() {
		return reset;
	}
	
	public int getCycle() {
		return cycle;
	}
	
	public boolean getFirstLoop() {
		return firstLoop;
	}
	
	public void setReset(boolean bool, Level newLevel, long newSeed) {
		reset = bool;
		level = newLevel;
		seed = newSeed;
	}
	
	public void setFirstLoop(boolean bool) {
		firstLoop = bool;
	}
	
	public void killObject(int x, int y) {
		objectContainer.setAlive(x, y, false);
	}
	
	public boolean receiveExplosion(int x, int y) {
		return objectContainer.receiveExplosion(x, y, numObjInPos(x, y));
	}
	
	public boolean receiveShoot(int x, int y) {
		boolean res = false;
		
		for (int i = 0; i < numObjInPos(x, y); i++) {
			if (!objectContainer.receiveShoot(x, y, i)) {
				return false;
			} else {
				res = true;
			}
		}
		return res;
	}
	
	public void executeInstantAction(InstantAction action) {
		action.execute(this);
	}
	
	public void resetGame(Level level, long seed) {
		this.level = level;
		this.seed = seed;
		this.random = new Random(seed);
		System.out.println("Level: " + level.name());
		System.out.println("Random generator initialized with seed: " + seed);
		if (level == Level.TEST ) {
			test = true;
		} 
		player.reset();
		cycle = 0;
		GameObjectGenerator.generateGameObjects(this, level);
	}
	
	public void incrementCycle() {
		cycle++;
	}
	
	public void updateObjects() {
		objectContainer.updateObjects();
	}
	
	public int StartingPos() {
		if(this.level == Level.HARD)
			return 2;
		else
			return 1;
	}
	
	public boolean objectInPosition(int x, int y) {
		return objectContainer.objectInPosition(x, y);
	}
	
	public void advanceLastObj(int x, int y) {
		(objectContainer.advanceLastObj(x, y)).advance();
	}
	
	public int getObjectX(int i) {
		return objectContainer.getObjectPositionX(i);
	}
	
	public int getObjectY(int i) {
		return objectContainer.getObjectPositionY(i);
	}

	public boolean moveUp() {
		if(getPlayerPositionY() > 0 && getPlayerPositionX() <= getRoadLength()) {
			player.positionUp();
			return true;
		}
		
		return false;
	}

	public boolean moveDown() {
		if(getPlayerPositionY() < getRoadWidth() - 1 && getPlayerPositionX() <= getRoadLength()) {
			player.positionDown();
			return true;
		} 
		return false;
		
	}
	
	public boolean update() {
		if(getPlayerPositionX() <= getRoadLength()) {
			player.update();
			return true;
		}
		return false;
	}
	
	public void substractPlayerCoins(int numCoins) {
		player.substractCoins(numCoins);
	}
	
	public int numObjInPos(int x, int y) {
		return objectContainer.numObjectsInPosition(x, y);
	}
	
	public boolean emptyPos(int x, int y) {
		return objectContainer.emptyPos(x, y, getPlayerPositionX(), getPlayerPositionY());
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
	
	public int getRandomColumn() {
		return (int) (getRandomNumber() * getVisibility());
	}
	
	public boolean getExit() {
		return exit;
	}
	
	public String getFirstObjSymbol(int x, int y) {
		return objectContainer.getSymbol(x, y, numObjInPos(x, y));
	}
	
	public void resetContainer() {
		objectContainer.reset();
		deleteObjects();
		GameObjectGenerator.reset(level);
	}
	
	public void toggleTest() {
		test = true;
	}
	public boolean doCollision() {
		return player.doCollision();
	}
	
	public void deleteObjects() {
		GameObject object;
		GameObjectContainer aux = new GameObjectContainer();
		objectContainer.killPastObjects(getPlayerPositionX(), getRoadWidth());
		int numObjChecked = 0;
	
		for (int i = getPlayerPositionX(); i < getRoadLength(); i++) {
			for (int j = 0; j < getRoadWidth(); j++) {
				while (objectContainer.AliveObjectInPosition(i, j, numObjChecked)) {
					aux.add(objectContainer.getObject(i, j, numObjChecked));
					numObjChecked++;
				}
				numObjChecked = 0;
			}
		}
		
		objectContainer = aux;
		
	}
	
	public int getContainerSize() {
		return objectContainer.getObjectCounter();
	}
	
	public void setExit() {
		exit = true;
	}
	
	
	public boolean tryToAddObject(GameObject object, double frequency) {
		if(random.nextDouble() < frequency ) {
			if (emptyPos(object.getX(), object.getY())) {
				objectContainer.add(object);
				object.onEnter();
				return true;
			}
			return false;
		}
		else {
			return false;
		}
	}
	
	public void addObject(GameObject object) {
		objectContainer.add(object);
		object.onEnter();
	}
	
	public String positionToString(int x, int y) { 
		String icon = "";
		
		int numObj = 0;
		int numObjInPos = objectContainer.numObjectsInPosition(x, y);
		
		if(x == player.getPositionX() && y == player.getPositionY()) {
			if (player.isAlive()) {
				icon += PLAYER_ICON;
			} else {
				icon += DEAD_PLAYER;
			}
		}
		
		while (numObjInPos > numObj) {
			icon += objectContainer.nextObjToString(x, y, numObjInPos - numObj);
			numObj++;
		}
		
		if (x == getRoadLength()) {
			icon += FINISH_LINE;
		}
		
		return icon;
	}

	public int getPlayersCoins() {
		return player.getCoins();
	}

	public void forceAdvanceObject(Game game, int id, int x) {
		GameObjectGenerator.forceAdvanceObject(game, id, x);
		
	}

	public void forceAddObject(GameObject o) {
		objectContainer.add(o);
	}

	public GameObject getFirstObjectInPosition(int x, int y) {
		return objectContainer.getFirstObjectInPosition(x, y);
	}
}