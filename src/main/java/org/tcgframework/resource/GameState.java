package org.tcgframework.resource;

import java.util.ArrayList;

public abstract class GameState {
	
	//player list
	public ArrayList<Player> players;
	public int gameID;
	public int currentPlayer;
	public int currentPhase;
	public final ArrayList<String> phases = new ArrayList<String>();
}
