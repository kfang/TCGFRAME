package org.tcgframework.resource;

import java.util.ArrayList;
import java.util.HashSet;

public class DominionGameState implements GameState{
	
	public int gameID;
	public HashSet<String> usernames;
	
	public DominionGameState(int gameID, HashSet<String> usernames){
		this.gameID = gameID;
		this.usernames = usernames;
	}
	
}
