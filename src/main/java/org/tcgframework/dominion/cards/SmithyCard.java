package org.tcgframework.dominion.cards;

import org.tcgframework.dominion.DominionGameState;
import org.tcgframework.resource.Card;
import org.tcgframework.resource.GameState;

public class SmithyCard extends Card {

	@Override
	public void playCard(GameState state) {
		DominionGameState dstate = (DominionGameState) state;
		state.getCurrentPlayer().draw(3);
		dstate.actions--;
	}

	@Override
	public String canPlay(GameState state) {
		DominionGameState dstate = (DominionGameState) state;
		if (dstate.actions < 1) {
			return "Don't have any actions left.";
		} else if (dstate.currentPhase != 0) {
			return "Must be in action phase.";
		}
		
		return Card.VALID;
	}

}
