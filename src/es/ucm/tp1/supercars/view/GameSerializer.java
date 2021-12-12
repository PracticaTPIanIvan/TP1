package es.ucm.tp1.supercars.view;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import es.ucm.tp1.supercars.logic.Game;
import es.ucm.tp1.supercars.logic.gameobjects.Player;
import es.ucm.tp1.supercars.logic.gameobjects.GameObjectContainer;

public class GameSerializer {
	
	private GameObjectContainer container;
	
	private Game game;
	
	public GameSerializer(Game game) {
		this.game = game;
	}
	
	public String serializeGame() {
		String res = ("---- ROAD FIGHTER SERIALIZED ----");
		res += "\n Level: ";
		res += game.getLevel().name();
		res += "\n Cycles: ";
		res += game.getCycle();
		res += "\n Coins: ";
		res += game.getPlayersCoins();
		res += "\n";
		if (!game.getTest()) {
			res += "\n Ellapsed Time: ";
			res += game.getEllapsedTime();
			res += "\n";
		}
		return res;
	}
	
	public String serializeContainer() {
		String res = "Game Objects: ";
		res += "\n" + game.getPlayerSymbol();
		res += " (" + game.getPlayerPositionX() + ", " + game.getPlayerPositionY() + ")\n"; 
		for(int x = 0; x < game.getRoadLength(); x++) {
			for (int y = 0; y < game.getRoadWidth(); y++) {
				for (int z = 0; z < game.numObjInPos(x, y); z++) {
					res += game.getNextObject(x, y, z).getSerialInfo();
					res += "\n";
				}
			}
		}
		return res;
	}
	
	public void printSerialInfo() {
		System.out.print(serializeGame());
		System.out.print(serializeContainer());
	}
	
	public void saveSerialInfo(Writer writer) throws IOException{
	      writer.write(serializeGame());
	      writer.write(serializeContainer());
	      writer.close();
	      
	}
}