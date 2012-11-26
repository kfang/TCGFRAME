package org.tcgframework.resource;

import java.util.ArrayList;
import java.util.List;

public abstract class Card {
	
	/**
	 * A string that denotes that it is valid to play the card given the gamestate.
	 */
	public static final String VALID = "valid";
	public String name;
	public List<TriggeredEff> tEffs = new ArrayList<TriggeredEff>();
	public List<PersistentEff> pEffs = new ArrayList<PersistentEff>();
	public List<ActivatedEff> aEffs = new ArrayList<ActivatedEff>();

	public abstract void playCard(GameState state);
	public abstract String canPlay(GameState state);
}
