package es.ucm.tp1.logic;

public class ObstacleList {
	private Obstacle [] list;
	private int count;
	
	public ObstacleList() {
		list = new Obstacle [100];
		count = 0;
	}
	
	public int getObstacleCounter() {
		return count;
	}
	
	public Obstacle getObstacle (int ind) {
		return list[ind];
	}
	
	public void setNewObstacle(Obstacle obs) {
		list[count] = obs;
		count++;
	}
}
