package org.tcgframework.resource;

import java.util.ArrayList;

import org.tcgframework.dominion.DominionPlayer;

public abstract class GameState {
	
	//player list
	public ArrayList<Player> players;
	public int gameID;
	public int currentPlayer;
	public int currentPhase;
	public final ArrayList<String> phases = new ArrayList<String>();
	
	public DominionPlayer getCurrentPlayer(){
		return (DominionPlayer) players.get(currentPlayer);
	}
	
	public void nextPhase(){
		currentPhase++;
		currentPhase = currentPhase % phases.size();
	}
	
	public void nextPhase(Long long1) {
		currentPhase = (int) (long) long1;
		if (currentPhase == phases.size() - 1) {
			passTurn();
		}
	}
	
	public abstract void passTurn();
}
