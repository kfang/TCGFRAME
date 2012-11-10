package org.tcgframework.resource;

public abstract class Card {
	
	public static final String VALID = "valid";
	public String name;
	
	public abstract void doCard(GameState state);
	public abstract String canPlay(GameState state);
}
