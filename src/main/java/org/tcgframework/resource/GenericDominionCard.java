package org.tcgframework.resource;

public class GenericDominionCard implements Card{
	
	String name;
	
	public GenericDominionCard(String name){
		this.name = name;
	}

	public void doCard(GameState state) {
		DominionGameState DState = (DominionGameState) state;
		
	}
}
