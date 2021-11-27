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
	public int getObjectPositionX(int k) {
		return gameobjects.get(k).getX();
	}
	public int getObjectPositionY(int k) {
		return gameobjects.get(k).getY();
	}
	public GameObject getObject(int index) {
		return gameobjects.get(index);
	}
	public boolean getState(int index) {
		return gameobjects.get(index).isAlive();
	}
	public String getSymbol(int index) {
		return gameobjects.get(index).getSymbol();
	}
	public void setState(int index, boolean state) {
		gameobjects.get(index).setState(state);
	}
	public void setNewObject(GameObject object) {
		gameobjects.add(gameobjects.size(), object);
	}
	public String toString(int ind) {
		return gameobjects.get(ind).toString();
	}
	
	public boolean receiveExplosion(int index) {
		return gameobjects.get(index).receiveExplosion();
	}
	
	public void updateObjects() {
		for (int i = 0; i < getObjectCounter(); i++) {
			gameobjects.get(i).update();
		}
	}
}