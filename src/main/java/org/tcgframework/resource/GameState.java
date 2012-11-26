package org.tcgframework.resource;

import java.util.ArrayList;

import org.tcgframework.dominion.DominionPlayer;

public abstract class GameState {
	
	/** A list of the players. */
	public ArrayList<Player> players;
	/** Id for game. Used for socket listener. */
	public final int gameID;
	/** The index of the player whose turn it is in the players list. */
	public int currentPlayer;
	/** The index of the phase we are currently in in the phases list. */
	public int currentPhase;
	/** A list of all the names of the phases in one turn.
	 * The order is the order in which the phases happen. */
	public final ArrayList<String> phases = new ArrayList<String>();
	/** The current turnNumber (First turn is turn 0). */
	public int turnNumber;

	/** Call super with this constructor (always). */
	public GameState(int id) {
		gameID = id;
		turnNumber = 0;
		currentPlayer = 0;
		currentPhase = 0;
	}

	/**
	 * Returns the player whose turn it is.
	 * @return
	 */
	public DominionPlayer getCurrentPlayer(){
		return (DominionPlayer) players.get(currentPlayer);
	}
	
	/**
	 * Advances to the next phases. If in the last phase, will pass the turn.
	 */
	public void nextPhase(){
		currentPhase++;
		if (currentPhase >= phases.size()) {
			currentPhase -= phases.size();
			passTurn();
		}
	}

	/**
	 * Skips to the given phase index. If in the last phase, will pass the turn.
	 */
	public void skipToPhase(Long l) {
		if (currentPhase == phases.size() - 1) {
			passTurn();
		} else {
			currentPhase = (int) (long) l;
		}
	}
	
	/**
	 * Passing the turn does different things in different games.
	 * Implement this method to define the behavior.
	 * Note that you have to change the player index and the phase index
	 * as well as whatever else you might want to happen when you pass the turn.
	 */
	public abstract void passTurn();
}
