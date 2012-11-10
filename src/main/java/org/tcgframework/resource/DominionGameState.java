package org.tcgframework.resource;

import java.util.ArrayList;
import java.util.HashSet;

public class DominionGameState implements GameState{
	
	public String gameID;
	public HashSet<String> usernames;
	
	public DominionGameState(String gameID, HashSet<String> usernames){
		this.gameID = gameID;
		this.usernames = usernames;
	}
	
}
