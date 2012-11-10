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
		
		for (int i = 0; i < 10; i++){
			deck.add("Copper");
		}
		
		draw(5);
	}
	
	public void draw(int draws){
		for(int i = 0; i < draws; i++){
			String c = deck.remove(0);
			if (c == null){
				for (String d : discard){
					//TODO: shuffling
					deck.add(d);
				}
				discard.clear();
			} else {
				hand.add(c);
			}
		}
	}
	
}
