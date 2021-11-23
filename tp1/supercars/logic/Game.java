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
	
	//private ObstacleCointainer container;
	
	private boolean exit;
	
	private boolean test;
	
	private boolean reset;
	
	private boolean firstLoop;
	
	private boolean advance;
	
	private int cycle;
	
	public Game(long seed, Level level) {
		this.level = level;
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
	
	public void setReset(boolean bool) {
		reset = bool;
	}
	
	public void setFirstLoop(boolean bool) {
		firstLoop = bool;
	}
	
	public boolean receiveShoot(int ind) {
		return objectContainer.getObject(ind).receiveShoot();
	}
	
	public void executeInstantAction(InstantAction action) {
		action.execute(this);
	}
	
	public void resetGame(Level level, long seed) {
		this.level = level;
		this.random = new Random(seed);
		if (level == Level.TEST) {
			test = true;
		} else {
			test = false;
		}
		player.reset();
		cycle = 0;
		GameObjectGenerator.generateGameObjects(this, level);
	}
	
	public void incrementCycle() {
		cycle++;
	}
	
	public int StartingPos() {
		if(this.level == Level.HARD)
			return 2;
		else
			return 1;
	}
	
	public void setAdvance(int ind) {
		objectContainer.getObject(ind).advance();
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
	
	public int getObjectX(int i) {
		return objectContainer.getObjectPositionX(i);
	}
	
	public int getObjectY(int i) {
		return objectContainer.getObjectPositionY(i);
	}
	
	public GameObject getObjectInPosition(int x, int y) {
		int ind = objectPosition(x, y);
		if(ind != -1)
			return objectContainer.getObject(ind);
		else
			return null;
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
	
	public void resetContainer() {
		for(int i = 0; i < objectContainer.getObjectCounter(); i++) {
			objectContainer.setState(i, false);
		}
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
		for(int i = 0; i < getRoadWidth(); i++) {
			object = getObjectInPosition(getPlayerPositionX() - 1, i);
			if(object != null) {
				object.onDelete();
			}
		}
		for(int j = 0; j < objectContainer.getObjectCounter(); j++) {
			if(objectContainer.getState(j)) {
				aux.setNewObject(objectContainer.getObject(j));
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
		String icon = "";
		if(j == player.getPositionX() && i == player.getPositionY()) {
			if (player.isAlive()) {
				icon += PLAYER_ICON;
			} else {
				icon += DEAD_PLAYER;
			}
		}
		if(objectPosition(j, i) != -1) {
			icon += objectContainer.toString(objectPosition(j, i));
		}
		if (j == getRoadLength()) {
			icon += FINISH_LINE;
		}
		return icon;
	}

	public int getPlayersCoins() {
		return player.getCoins();
	}
}
