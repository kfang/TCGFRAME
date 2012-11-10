package org.tcgframework.resource;

import java.util.ArrayList;
import java.util.HashSet;

public class DominionGameState implements GameState{
	
	public int gameID;
	private int currentPlayer;
	public ArrayList<Card> playset = new ArrayList<Card>();
	
	public DominionGameState(int gameID, HashSet<String> usernames){
		this.gameID = gameID;
		
		for (String name : usernames){
			this.players.add(name);
		}
		
		currentPlayer = 0;
	}
	
	public String getCurrentPlayer(){
		return players.get((currentPlayer % players.size()));
	}
	
	public String getNextPlyaer(){
		return players.get(((currentPlayer + 1) % players.size()));
	}
	
	public void nextPhase(){
		
	}
	
}
