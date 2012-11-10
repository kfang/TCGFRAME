package org.tcgframework.resource;

public class DominionPlayer implements Player{
	
	public DominionPlayer(){
		this.states.put("actions", 1);
		this.states.put("treasure", 0);
		this.states.put("buys", 1);
	}
}
