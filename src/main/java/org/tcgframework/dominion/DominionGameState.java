package org.tcgframework.dominion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import org.tcgframework.dominion.cards.CellarCard;
import org.tcgframework.dominion.cards.CopperCard;
import org.tcgframework.dominion.cards.SmithyCard;
import org.tcgframework.resource.Card;
import org.tcgframework.resource.GameState;
import org.tcgframework.resource.Player;

import com.google.gson.Gson;

public class DominionGameState extends GameState{
	
	public HashMap<String, Card> cardObjSet = new HashMap<String, Card>();
	
	public int actions;
	public int buys;
	public int money;
	
	public DominionGameState(int gameID, HashSet<String> usernames){
		//put cards into the cardObjSet--------------
		cardObjSet.put("copper", new CopperCard());
		cardObjSet.put("cellar", new CellarCard());
		cardObjSet.put("smithy", new SmithyCard());
		//-------------------------------------------
		
		this.gameID = gameID;
		this.players = new ArrayList<Player>();
		//add all the users
		for (String name : usernames){
			this.players.add(new DominionPlayer(name));
		}
		currentPlayer = 0;
		
		//add all the phases
		currentPhase = 0;
		phases.add("Action Phase");
		phases.add("Buy Phase");
		phases.add("Cleanup Phase");
		actions = 1;
		buys = 1;
		money = 0;
	}
	
	public void passTurn() {
		cleanup();
		currentPhase = 0;
		currentPlayer = (currentPlayer + 1) % players.size();
		actions = 1;
		buys = 1;
		money = 0;
	}
	
	public void cleanup() {
		DominionPlayer player = (DominionPlayer) players.get(currentPlayer);
		player.discard.addAll(player.hand);
		player.discard.addAll(player.play);
		player.hand.clear();
		player.play.clear();
		player.draw(5);
	}
	
	@Override
	public String toString() {
		HashMap<String, Object> toReturn = new HashMap<String, Object>();
		toReturn.put("gameID", this.gameID);
		toReturn.put("currentPlayer", this.currentPlayer);
		toReturn.put("currentPhase", this.currentPhase);
		toReturn.put("phases", this.phases);
		toReturn.put("players", this.players);
		toReturn.put("inPlay", getCurrentPlayer().play);
		toReturn.put("actions", this.actions);
		toReturn.put("buys", this.buys);
		toReturn.put("money", this.money);
		
		//TODO: make toString for Cards
		toReturn.put("hand", getCurrentPlayer().hand);
		Gson gson = new Gson();
		return gson.toJson(toReturn);
	}
	
	public static void main(String[] args){
		HashSet<String> usernames = new HashSet<String>();
		usernames.add("jfkdla");
		usernames.add("fjkdlaja");
		DominionGameState state = new DominionGameState(0, usernames);
		System.out.println(state.toString());
	}
}
