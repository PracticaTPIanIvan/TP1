package es.ucm.tp1.supercars.logic;

import java.util.Random;

import es.ucm.tp1.supercars.control.Level;
import es.ucm.tp1.supercars.control.Record;
import es.ucm.tp1.supercars.logic.gameobjects.GameObject;
import es.ucm.tp1.supercars.logic.gameobjects.GameObjectContainer;
import es.ucm.tp1.supercars.logic.gameobjects.Player;
import es.ucm.tp1.supercars.logic.actions.InstantAction;

public class Game {
	private Player player;
	
	private Level level;
	
	private Random random;
	
	private GameObjectContainer objectContainer;
	
	private boolean exit;
	
	private boolean test;
	
	private boolean reset;
	
	private long seed;
	
	private boolean firstLoop;
	
	private double ellapsedTime;
	
	private int lastDeletedColumn;
	
	private Record record;
	
	private int cycle;
	
	public Game(long seed, Level level) {
		this.level = level;
		this.seed = seed;
		this.player = new Player(this);
		this.random = new Random(seed);
		test = (level == Level.TEST);
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
	
	public void setRecord(Record record) {
		this.record = record;
	}
	
	public long getRecord(String level) {
		return record.getRecord(level);
	}
	
	public boolean recordIsValid() {
		return record.isValid();
	}
	public void setFirstLoop(boolean state) {
		firstLoop = state;
	}
	
	public void setReset(boolean reset) {
		this.reset = reset;
	}
	
	public void setReset(boolean reset, Level newLevel, long newSeed) {
		this.reset = reset;
		level = newLevel;
		seed = newSeed;
	}
	
	public void killObject(int x, int y) {
		objectContainer.setAlive(x, y, false);
	}
	
	public void killObjectsInPos(int x, int y) {
		objectContainer.killObjectsInPos(x, y);
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
	
	public void updatePlayer() {
		player.update();
	}
	
	public void giveCoins(int num) {
		player.addCoins(num);
	}
	public void update() {
		objectContainer.updateObjects();
		updatePlayer();
	}
	public int StartingPos() {
		return getRoadWidth() / 2;
	}
	
	public boolean existsObj(int x, int y) {
		return objectContainer.existsObj(x, y);
	}
	
	public void playerMoveDown() {
		player.moveDown();
	}
	
	public void playerMoveUp() {
		player.moveUp();
	}
	
	public boolean advancePlayer() {
		return player.advance();
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
		return objectContainer.getFirstObjSymbol(x, y);
	}
	
	public void resetContainer() {
		objectContainer.reset();
		GameObjectGenerator.reset(level);
	}
	
	public void toggleTest() {
		test = true;
	}
	
	public boolean doCollision() {
		return player.doCollision();
	}
	
	public boolean doFinalCollision() {
		return player.doFinalCollision();
	}
	
	public void killPastObjects() {
		if (getPlayerPositionX() > 0) {
			for (int i = lastDeletedColumn; i < getPlayerPositionX(); i++) {
				for (int j = 0; j < getRoadWidth(); j++) {
					objectContainer.setObjectsToDead(i, j);
				}
			}
		}
		lastDeletedColumn = getPlayerPositionX() - 1;
	}
	
	public void removeDead() {
		objectContainer.removeDead();
	}
	
	public void deleteObjects() {
		killPastObjects();
		removeDead();
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
	
	public boolean playerIsInPosition(int x, int y) {
		return (getPlayerPositionX() == x && getPlayerPositionY() == y);
	}
	
	public String getPlayerSymbol() {
		return player.getSymbol();
	}
	
	public String positionToString(int x, int y) { 
		String icons = "";
		
		if(playerIsInPosition(x, y)) {
			icons += player.getSymbol() + " ";
		}
		
		int numObjChecked = 0;
		int numObjInPos = objectContainer.numObjectsInPosition(x, y);
		
		while (numObjInPos > numObjChecked) {
			icons += objectContainer.nextObjToString(x, y, numObjChecked) + " ";
			numObjChecked++;
		}
		
		return icons;
	}

	public int getPlayersCoins() {
		return player.getCoins();
	}

	public void forceAdvancedObject(Game game, int id, int x) {
		GameObjectGenerator.forceAdvancedObject(game, id, x);
	}

	public void forceAddObject(GameObject o) {
		objectContainer.add(o);
		o.onEnter();
	}

	public GameObject getFirstObjInPos(int x, int y) {
		return objectContainer.getFirstObjectInPosition(x, y);
	}
	
	public Collider getFirstColliderInPos(int x, int y) {
		return objectContainer.getFirstObjectInPosition(x, y);
	}
	
	public GameObject getNextObject(int x, int y, int numObjChecked) {
		return objectContainer.getNextObj(x, y, numObjChecked);
	}
	
	public double getEllapsedTime() {
		return ellapsedTime;
	}

	public void setEllapsedTime(double time) {
		ellapsedTime = time;
		
	}
}