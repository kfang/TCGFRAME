package org.tcgframework.resource;

import java.util.ArrayList;

public class DominionGameState implements GameState{
	
	public String gameID;
	public ArrayList<String> players;
	
	public DominionGameState(String gameID){
		this.gameID = gameID;
		this.players = new ArrayList<String>();
	}
	
}
