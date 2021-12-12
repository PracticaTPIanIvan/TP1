package es.ucm.tp1.supercars.logic.gameobjects;

import java.util.ArrayList;
import java.util.List;

public class GameObjectContainer {
	private List<GameObject> gameobjects;
	
	public GameObjectContainer() {
		gameobjects = new ArrayList<>();
	}
	
	public int getObjectCounter() {
		return gameobjects.size();
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
		for (GameObject o : gameobjects) {
			if (o.getX() == x && o.getY() == y) {
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
	
	public GameObject getFirstObj(int x, int y) {
		int ind = indFirstObj(x, y);
		if (ind != -1) {
			return gameobjects.get(ind);
		} else {
			return null;
		}
		
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
		gameobjects.clear();
	}
	
	public void killEveryObject() {
		for(int i = 0; i < getObjectCounter(); i++) {
			gameobjects.get(i).kill();
		}
	}
	
	public void killObjectsInPos(int x, int y) {
		int numObjChecked = 0;
		int index;
		
		while (existsAliveObj(x, y)) {
			index = indNextObjInPos(x, y, numObjChecked);
			gameobjects.get(index).kill();
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
	
	public void setObjectsToDead(int x, int y) {
		int numObjChecked = 0;
		int index;
		
		while (existsAliveObj(x, y)) {
			index = indNextObjInPos(x, y, numObjChecked);
			gameobjects.get(index).setAlive(false);
			numObjChecked++;
		}
	}
	
	public void removeDead() {
		List<GameObject> aux = new ArrayList<>();
		
		for (GameObject o: gameobjects) {
			if (o.isAlive()) {
				aux.add(o); 
			} else {
				o.onDelete();
			}
		}
		gameobjects = aux;
	}
}