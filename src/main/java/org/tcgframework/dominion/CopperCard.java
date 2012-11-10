package org.tcgframework.dominion;

import org.tcgframework.resource.Card;
import org.tcgframework.resource.DominionGameState;
import org.tcgframework.resource.GameState;
import org.tcgframework.resource.Player;

public class CopperCard implements Card {

	public void doCard(GameState state) {
		DominionGameState Dstate = (DominionGameState) state;
		Player player = Dstate.playerObj.get(Dstate.currentPlayer);
		int treasure = (Integer) player.states.get("treasure");
		treasure += 1;
		player.states.put("treasure", treasure);
	}
	
	public boolean canPlay(GameState state){
		DominionGameState Dstate = (DominionGameState) state;
		return Dstate.currentPhase == 1;
	}

}
