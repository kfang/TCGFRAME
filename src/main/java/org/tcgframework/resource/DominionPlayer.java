package org.tcgframework.resource;

import java.util.ArrayList;

import org.tcgframework.dominion.CopperCard;

public class DominionPlayer implements Player{
	
	public String name;
	public ArrayList<String> deck;
	public ArrayList<String> discard;
	public ArrayList<String> hand;
	
	public DominionPlayer(String name){
		this.name = name;
		this.deck = new ArrayList<String>();
		this.discard = new ArrayList<String>();
		this.hand = new ArrayList<String>();
		
		Player.states.put("actions", 1);
		Player.states.put("treasure", 0);
		Player.states.put("buys", 1);
		
		//TOOD: this shouldn't actually be how you populate your deck,
		//but it works for now
		for (int i = 0; i < 10; i++){
			deck.add("copper");
		}
		
		draw(5);
	}
	
	public void draw(int draws){
		for(int i = 0; i < draws; i++){
			if (deck.size() == 0) {
				deck.addAll(discard);
				discard.clear();
			}
			hand.add(deck.remove(0));
		}
	}

	public void cleanup() {
		if (!hand.isEmpty()){
			this.discard.addAll(this.hand);
			this.hand.clear();
			draw(5);
		} else {
			draw(5);
		}
	}
	
}
