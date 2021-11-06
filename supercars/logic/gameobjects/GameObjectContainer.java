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
	public void setNewObject(GameObject object) {
		gameobjects.add(gameobjects.size(), object);
	}
	public String toString(int ind) {
		return gameobjects.get(ind).toString();
	}
}