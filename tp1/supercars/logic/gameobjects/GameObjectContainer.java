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
	
	public int getObjectPositionX(int k) {
		return gameobjects.get(k).getX();
	}
	
	public int getObjectPositionY(int k) {
		return gameobjects.get(k).getY();
	}
	
	public GameObject getObject(int x, int y) {
		int ind = objectPosition(x, y);
		return gameobjects.get(ind);
	}
	
	public boolean getState(int index) {
		return gameobjects.get(index).isAlive();
	}
	
	public String getSymbol(int index) {
		return gameobjects.get(index).getSymbol();
	}
	
	public void setAlive(int x, int y, boolean alive) {
		int ind = objectPosition(x, y); 
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
		for(int k = 0; k < gameobjects.size(); k++) {
			if(gameobjects.get(k).getX() == x
					&& gameobjects.get(k).getY() == y) {
				res++;
			}
		}
		return res;
	}
	
	public String toString(int x, int y) {
		return toString(objectPosition(x, y));
	}
	
	private String toString(int ind) {
		return gameobjects.get(ind).toString();
	}
	
	public boolean receiveExplosion(int index) {
		return gameobjects.get(index).receiveExplosion();
	}
	
	public GameObject advanceLastObj(int x, int y) {
		int ind = objectPosition(x, y);
		return gameobjects.get(ind);
	}
	
	public void updateObjects() {
		for (int i = 0; i < getObjectCounter(); i++) {
			gameobjects.get(i).update();
		}
	}
	
	private int objectPosition(int x, int y) {
		int pos = -1;
		for(int k = 0; k < gameobjects.size(); k++) {
			if(gameobjects.get(k).getX() == x
					&& gameobjects.get(k).getY() == y) {
				pos = k;
			}
		}
		return pos;
	}
	
	public String nextObjToString(int x, int y, int numObj) {
		int ind = indNextObjInPos(x, y ,numObj);
		if (ind != -1) {
			return toString(ind);
		} else {
			return "";
		}
	}
	
	private int indNextObjInPos(int x, int y, int numObj) {
		int ind = objectPosition(x, y);

		for (int i = 0; i < numObj - 1; i++) {
			ind = nextObjPosition(ind, x, y);
		}
		return ind;
	}
	
	private int nextObjPosition(int index, int x, int y) {
		
		for(int k = gameobjects.size() - 1; k >= 0; k--) {
			if(gameobjects.get(k).getX() == x 
					&& gameobjects.get(k).getY() == y) {
				if (k < index) {
					//newInd = k;
					return k;
				}
			}
		}
		return -1;
		
	} 
	
	public boolean objectInPosition(int x, int y) {
		return objectPosition(x, y) != -1;
	}
	
	public boolean AliveObjectInPosition(int x, int y) {
		int ind = objectPosition(x, y);
		return (ind != -1) && (getState(ind));
	}
	
	public boolean AliveObjectInPosition(int x, int y, int numObjChecked) {
		int ind = indNextObjInPos(x, y, numObjChecked + 1);
		if (ind != -1) {
			return gameobjects.get(ind).isAlive();
		} else {
			return false;
		}
	}
	
	public boolean emptyPos(int x, int y, int playerX, int playerY) {
		return ((objectPosition(x, y) == -1) && !(playerX == x && playerY == y));
	}
	
	public void reset() {
		for(int i = 0; i < getObjectCounter(); i++) {
			setAlive(i, false);
		}	
	}
	
	public void killPastObjects(int playerX, int roadWidth) {
		int numObjChecked = 0;
		if (playerX > 0) {
			for (int i = lastDeletedColumn; i < playerX; i++) {
				for (int j = 0; j < roadWidth; j++) {
					int ind = objectPosition(i, j);
					while(numObjectsInPosition(i, j) > numObjChecked) {
						setAlive(ind, false);
						ind = indNextObjInPos(i, j, numObjChecked);
						numObjChecked++;
					}
				}
			}
		}
		lastDeletedColumn = playerX - 1;
	}
	
	public void killObjectsInPosition(int x, int y) {
		while (objectInPosition(x, y)) {
			setAlive(x, y, false);
		}
	}
	
	public boolean receiveShoot(int x, int y, int numObjChecked) {
		int ind = indNextObjInPos(x, y, numObjChecked);
		return gameobjects.get(ind).receiveShoot();
	}
	
	public boolean receiveExplosion(int x, int y, int numObjChecked) {
		int ind = indNextObjInPos(x, y, numObjChecked);
		if (ind != -1) {
			return gameobjects.get(ind).receiveExplosion();
		} else {
			return false;
		}
	}

	public GameObject getFirstObjectInPosition(int x, int y) {
		int index = indNextObjInPos(x, y, numObjectsInPosition(x, y) - 1);
		if (index != -1) {
			return gameobjects.get(index);
		} else {
			return null;
		}
		
	}

	public String getSymbol(int x, int y, int numObj) {
		int ind = indNextObjInPos(x, y, numObj);
		return gameobjects.get(ind).getSymbol();
	}

	

	public GameObject getObject(int x, int y, int numObjChecked) {
		int ind = indNextObjInPos(x, y, numObjChecked);
		return gameobjects.get(ind);
	}
}