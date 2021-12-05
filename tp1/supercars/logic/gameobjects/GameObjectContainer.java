package es.ucm.tp1.supercars.logic.gameobjects;

import java.util.ArrayList;
import java.util.List;

import es.ucm.tp1.supercars.logic.GameObjectGenerator;

public class GameObjectContainer {
	private List<GameObject> gameobjects;
	
	private int lastDeletedColumn;
	
	public GameObjectContainer() {
		gameobjects = new ArrayList<>();
		lastDeletedColumn = 0;
	}
	
	public int getObjectCounter() {
		return gameobjects.size();
	}
	
	public GameObject getFirstObject(int x, int y) { 
		int index = indFirstObj(x, y);
		if (index != -1) {
			return gameobjects.get(index);
		}
		return null;
	}
	
	private boolean getState(int index) {
		return gameobjects.get(index).isAlive();
	}
	
	public String getFirstObjSymbol(int x, int y) {
		int ind = indFirstObj(x, y);
		if (ind != -1) {
			return gameobjects.get(ind).getSymbol();
		}
		return "";
	}
	
	public String getNextObjSymbol(int x, int y, int numObjChecked) {
		int ind = indNextObjInPos(x, y, numObjChecked);
		if (ind != -1) {
			return gameobjects.get(ind).getSymbol();
		}
		return "";
	}
	
	public void setAlive(int x, int y, boolean alive) {
		int ind = indFirstObj(x, y); 
		if (ind != -1) {
			gameobjects.get(ind).setAlive(alive);
		}
	}
	
	private void setAlive(int index, boolean alive) {
		gameobjects.get(index).setAlive(alive);
		
	}
	
	public void add(GameObject gameObject) {
		gameobjects.add(getObjectCounter(), gameObject);
	}
	
	public int numObjectsInPosition(int x, int y) {
		int res = 0;
		for(int k = 0; k < getObjectCounter(); k++) {
			if(gameobjects.get(k).getX() == x && gameobjects.get(k).getY() == y) {
				res++;
			}
		}
		return res;
	}
	
	public String firstObjToString(int x, int y) { 
		return toString(indFirstObj(x, y));
	}
	
	private String toString(int ind) {
		return gameobjects.get(ind).toString();
	}
	
	public boolean receiveExplosion(int x, int y) {
		int ind = indFirstObj(x, y);
		if (ind != -1) {
			return gameobjects.get(ind).receiveExplosion();
		} else {
			return false;
		}
	}
	
	public boolean receiveShoot(int x, int y, int numObjChecked) {
		int ind = indNextObjInPos(x, y, numObjChecked);
		return gameobjects.get(ind).receiveShoot();
	}
	
	
	public GameObject getFirstObj(int x, int y) {
		int ind = indFirstObj(x, y);
		return gameobjects.get(ind);
	}
	
	public void updateObjects() {
		for (int i = 0; i < getObjectCounter(); i++) {
			gameobjects.get(i).update();
		}
	}
	
	private int indFirstObj(int x, int y) {
		int ind = 0;
		boolean found = false;
		
		while(!found && ind < getObjectCounter()) {
			if(gameobjects.get(ind).getX() == x && gameobjects.get(ind).getY() == y) {
				found = true;
			} else {
				ind++;
			}
		}
		if (!found) {
			ind = -1;
		} 
		return ind;
	}
	
	public String nextObjToString(int x, int y, int numObjChecked) {
		int ind = indNextObjInPos(x, y ,numObjChecked);
		if (ind != -1) {
			return toString(ind);
		} else {
			return "";
		}
	}
	
	private int indNextObjInPos(int x, int y, int numObjChecked) {
		int ind = indFirstObj(x, y);

		for (int i = 0; i < numObjChecked; i++) {
			ind = nextObjPosition(ind, x, y);
		}
		return ind;
	}
	
	private int nextObjPosition(int prevObjIndex, int x, int y) {
		
		for(int k = 0; k < getObjectCounter(); k++) {
			if(gameobjects.get(k).getX() == x && gameobjects.get(k).getY() == y 
					&& k > prevObjIndex) {
					return k;
			}
		}
		return -1;
		
	} 
	
	public boolean existsObj(int x, int y) { //Supongo que se puede utilizar el empty pos
		return indFirstObj(x, y) != -1;
	}
	
	public boolean existsAliveObj(int x, int y) {
		int ind = indFirstObj(x, y);
		return (ind != -1) && (getState(ind));
	}
	
	public boolean existsNextAliveObj(int x, int y, int numObjChecked) {
		int ind = indNextObjInPos(x, y, numObjChecked);
		if (ind != -1) {
			return gameobjects.get(ind).isAlive();
		} else {
			return false;
		}
	}
	
	
	public boolean emptyPos(int x, int y, int playerX, int playerY) {
		return ((indFirstObj(x, y) == -1) && !(playerX == x && playerY == y));
	}
	
	public void reset() {
		for(int i = 0; i < getObjectCounter(); i++) {
			setAlive(i, false);
		}	
	}
	
	public void killPastObjects(int playerX, int roadWidth) {
		if (playerX > 0) {
			for (int i = lastDeletedColumn; i < playerX; i++) {
				for (int j = 0; j < roadWidth; j++) {
					killObjectsInPos(i, j);
				}
			}
		}
		lastDeletedColumn = playerX - 1;
	}
	
	public void killObjectsInPos(int x, int y) {
		int numObjChecked = 0;
		int index;
		
		while (existsAliveObj(x, y)) {
			index = indNextObjInPos(x, y, numObjChecked);
			setAlive(index, false);
			numObjChecked++;
		}
	}

	public GameObject getFirstObjectInPosition(int x, int y) {
		int index = indFirstObj(x, y);
		if (index != -1) {
			return gameobjects.get(index);
		} else {
			return null;
		}
		
	}

	public GameObject getNextObj(int x, int y, int numObjChecked) {
		int ind = indNextObjInPos(x, y, numObjChecked);
		if (ind != -1) {
			return gameobjects.get(ind);
		} else {
			return null;
		}
		
	}
	
	public void position() {
		if (getObjectCounter() > 1) {
			System.out.println(gameobjects.get(1).getY());
		}
	}
}