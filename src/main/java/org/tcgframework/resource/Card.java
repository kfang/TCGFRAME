package org.tcgframework.resource;

public interface Card {
	
	public static final String VALID = "valid";
	
	public void doCard(GameState state);
	public String canPlay(GameState state);
}
