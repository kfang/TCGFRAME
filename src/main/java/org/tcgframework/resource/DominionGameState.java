package org.tcgframework.resource;

public class DominionGameState implements GameState{
	
	String owner;
	
	public DominionGameState(String owner){
		this.owner = owner;
	}
	
	public String getOwner(){
		return owner;
	}
}
