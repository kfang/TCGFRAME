package org.tcgframework.dominion.cards;

import org.tcgframework.dominion.DominionGameState;
import org.tcgframework.resource.Card;
import org.tcgframework.resource.GameState;
import org.tcgframework.resource.Player;

public class CopperCard extends Card {
	
	public CopperCard(){
		this.name = "copper";
	}
	
	public void doCard(GameState state) {
		DominionGameState Dstate = (DominionGameState) state;
		Player player = Dstate.players.get(Dstate.currentPlayer);
		
		((DominionGameState) state).money++;
	}
	
	public String canPlay(GameState state){
		DominionGameState Dstate = (DominionGameState) state;
		if(Dstate.currentPhase != 1){
			return "Wrong Phase, must play in Buy Phase";
		} else {
			return Card.VALID;
		}
	}

}
