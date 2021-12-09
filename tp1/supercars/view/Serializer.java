package es.ucm.tp1.supercars.view;

import es.ucm.tp1.supercars.logic.gameobjects.GameObjectContainer;

public class Serializer {
	
	private GameObjectContainer container;
	
	public Serializer(GameObjectContainer container) {
		this.container = container;
	}
	
	public void serializeContainer() {
		for(int i = 0; i < container.getObjectCounter(); i++) {
			
		}
	}
}
