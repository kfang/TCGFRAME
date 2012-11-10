package org.tcgframework.resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class DominionGameState implements GameState{
	
	public int gameID;
	private int currentPlayer;
	private int currentPhase;
	public ArrayList<String> phases = new ArrayList<String>();
	public ArrayList<Card> playset = new ArrayList<Card>();
	
	public DominionGameState(int gameID, HashSet<String> usernames){
		this.gameID = gameID;
		
		//add all the users
		for (String name : usernames){
			this.players.add(name);
		}
		currentPlayer = 0;
		
		//add all the phases
		currentPhase = 0;
		phases.add("Action Phase");
		phases.add("Buy Phase");
		phases.add("Cleanup Phase");
		
		//create the playset
	}
	
	public String getCurrentPlayer(){
		return players.get((currentPlayer % players.size()));
	}
	
	public String getNextPlyaer(){
		return players.get(((currentPlayer + 1) % players.size()));
	}
	
	public void nextPhase(){
		currentPhase++;
		currentPhase = currentPhase % phases.size();
	}
	

	@Override
	public String toString() {
		HashMap<String, Object> toReturn = new HashMap<String, Object>();
		toReturn.put("gameID", this.gameID);
		toReturn.put("currentPlayer", this.currentPlayer);
		toReturn.put("currentPhase", this.currentPhase);
		toReturn.put("phases", this.phases);
		
		//TODO: make toString for Cards
		toReturn.put("playset", this.playset);
		
		return toReturn.toString();
	}
	
}
