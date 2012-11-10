package org.tcgframework.dominion;

import org.tcgframework.resource.Card;
import org.tcgframework.resource.DominionGameState;
import org.tcgframework.resource.GameState;
import org.tcgframework.resource.Player;

public class CopperCard extends Card {
	
	public CopperCard(){
		this.name = "copper";
	}
	
	public void doCard(GameState state) {
		DominionGameState Dstate = (DominionGameState) state;
		Player player = Dstate.playerObj.get(Dstate.currentPlayer);
		int treasure = (Integer) player.states.get("treasure");
		treasure += 1;
		player.states.put("treasure", treasure);
		
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
