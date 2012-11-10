package org.tcgframework.resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import com.google.gson.Gson;

public class DominionGameState implements GameState{
	
	public int actions;
	public int buys;
	public int money;
	
	public int gameID;
	public int currentPlayer;
	public int currentPhase;
	public ArrayList<String> phases = new ArrayList<String>();
	public ArrayList<String> hand = new ArrayList<String>();
	public ArrayList<String> inPlay = new ArrayList<String>();
	public ArrayList<Player> playerObj = new ArrayList<Player>();
	
	public DominionGameState(int gameID, HashSet<String> usernames){
		this.gameID = gameID;
		
		//add all the users
		for (String name : usernames){
			this.players.add(name);
			this.playerObj.add(new DominionPlayer(name));
		}
		currentPlayer = 0;
		
		//add all the phases
		currentPhase = 0;
		phases.add("Action Phase");
		phases.add("Buy Phase");
		phases.add("Cleanup Phase");
		
		this.hand = ((DominionPlayer) playerObj.get(currentPlayer)).hand;
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
	
	public void doCard(String card){
		//add to in play
		inPlay.add(card);
		
		//remove from hand
		this.playerObj.indexOf(card);
		
	}
	

	@Override
	public String toString() {
		HashMap<String, Object> toReturn = new HashMap<String, Object>();
		toReturn.put("gameID", this.gameID);
		toReturn.put("currentPlayer", this.currentPlayer);
		toReturn.put("currentPhase", this.currentPhase);
		toReturn.put("phases", this.phases);
		toReturn.put("players", this.players);
		toReturn.put("inPlay", this.inPlay);
		toReturn.put("actions", this.actions);
		toReturn.put("buys", this.buys);
		toReturn.put("money", this.money);
		
		//TODO: make toString for Cards
		toReturn.put("hand", this.hand);
		Gson gson = new Gson();
		return gson.toJson(toReturn);
	}
	
	public static void main(String[] args){
		HashSet<String> usernames = new HashSet<String>();
		usernames.add("jfkdla");
		usernames.add("fjkdlaja");
		DominionGameState state = new DominionGameState(0, usernames);
		System.out.println(state.toString());
	}

	public void nextPhase(Long long1) {
		currentPhase = (int) (long) long1;
	}
}
