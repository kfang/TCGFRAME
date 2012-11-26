package org.tcgframework.dominion.cards;

import org.tcgframework.dominion.DominionGameState;
import org.tcgframework.resource.Card;
import org.tcgframework.resource.GameState;

public class CellarCard extends Card{

	public CellarCard(){
		this.name = "cellar";
	}
	
	public void doCard(GameState state) {
		// TODO Auto-generated method stub
		
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
