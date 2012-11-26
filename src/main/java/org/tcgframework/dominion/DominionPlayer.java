package org.tcgframework.dominion;

import java.util.ArrayList;

import org.tcgframework.resource.Player;

public class DominionPlayer extends Player {
	
	public ArrayList<String> deck;
	public ArrayList<String> discard;
	public ArrayList<String> play;
	public ArrayList<String> hand;
	
	public DominionPlayer(String name){
		this.username = name;
		this.deck = new ArrayList<String>();
		this.discard = new ArrayList<String>();
		this.hand = new ArrayList<String>();
		this.play = new ArrayList<String>();
		
		//TOOD: this shouldn't actually be how you populate your deck,
		//but it works for now
		deck.add("smithy");
		deck.add("copper");
		deck.add("copper");
		deck.add("copper");
		deck.add("copper");
		deck.add("smithy");
		deck.add("copper");
		deck.add("copper");
		deck.add("copper");
		deck.add("copper");
		
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
