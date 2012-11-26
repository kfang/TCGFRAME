package org.tcgframework;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

import org.cometd.annotation.Listener;
import org.cometd.annotation.Service;
import org.cometd.annotation.Session;
import org.cometd.bayeux.server.BayeuxServer;
import org.cometd.bayeux.server.ServerChannel;
import org.cometd.bayeux.server.ServerMessage;
import org.cometd.bayeux.server.ServerSession;
import org.tcgframework.dominion.DominionGameState;
import org.tcgframework.resource.Card;
import org.tcgframework.resource.GameState;
import org.tcgframework.resource.Player;

@Service
public class JoinGameService {
	
	//Instance Variables
	HashMap<String, Object> users = new HashMap<String, Object>();
	HashSet<String> usernames = new HashSet<String>();
	HashMap<String, DominionGameState> games = new HashMap<String, DominionGameState>();
	int gameID = 1;
	
	@Session
	private ServerSession session;
	
	@Inject
	private BayeuxServer bayeux;
	
	//whenever a user joins the room
	@Listener("/broadcast/waiting")
	public void addUser(ServerSession session, ServerMessage message){
		//add the owner
		if (users.isEmpty()){
			users.put("OWNER", message.getData().toString());
		}
		
		//add user to map -- assume username not in set
		if(usernames.add(message.getData().toString())){
			users.put("USERS", usernames);	
		}
		//broadcast list to all users who are waiting
		this.bayeux.createIfAbsent("/broadcast/waiting");		  
    	ServerChannel broadcastChannel = this.bayeux.getChannel("/broadcast/waiting");
    	broadcastChannel.publish(this.session, users , null);
    	
    	System.out.println("Broadcasted List of Users");
	}
	
	//whenever owner starts a game
	@Listener("/broadcast/startgame")
	public void startGame(ServerSession session, ServerMessage message){
		//add a new game to games
		games.put("/game/"+ this.gameID, new DominionGameState(this.gameID, (HashSet<String>) usernames.clone()));
		
		//broadcast to all users that a new game started
		this.bayeux.createIfAbsent("/broadcast/waiting");
		ServerChannel broadcastChannel = this.bayeux.getChannel("/broadcast/waiting");
		broadcastChannel.publish(this.session, this.gameID, "start");
		
		//empty out the users list
		users.clear();
		usernames.clear();
		this.gameID++;
		System.out.println("Game has started, Users have been cleared");
	}
	
	//GAME LISTENERS
	
	@Listener("/game/*")
	public void gameHandler(ServerSession sender, ServerMessage message){
		System.out.println("caught a message");
		DominionGameState state = games.get(message.getChannel());
		
		
		if (message.getData().toString().equals("iam_ready")){
			System.out.println("Channel: " + message.getChannel());
			sender.deliver(this.session, message.getChannel(), state, "gamestate");
		} else {
			Map<String, Object> map = message.getDataAsMap();
			if (map.containsKey("do_card")){
				boolean legal = checkUserTurn(state, map);
				Card card = state.cardObjSet.get((String) map.get("do_card"));
				String can = card.canPlay(state);
				if (can.equals(Card.VALID) && legal) {
					state.getCurrentPlayer().hand.remove(card.name);
					state.getCurrentPlayer().play.add(card.name);
					card.doCard(state);
					ServerChannel broadcastChannel = this.bayeux.getChannel(message.getChannel());
					broadcastChannel.publish(this.session, state, "gamestate");
				} else if (legal) {
					sender.deliver(this.session, message.getChannel(), can, "message");
				} else {
					sender.deliver(this.session, message.getChannel(), "It's not your turn", "message");
				}
			} else if (map.containsKey("phase_change")){
				boolean legal = checkUserTurn(state, map);
				if (legal) {
					state.nextPhase((Long) map.get("phase_change"));
					ServerChannel broadcastChannel = this.bayeux.getChannel(message.getChannel());
					broadcastChannel.publish(this.session, state, "gamestate");
				} else {
					sender.deliver(this.session, message.getChannel(), "It's not your turn", "message");
				}
			}
		}
	}

	private boolean checkUserTurn(DominionGameState state,
			Map<String, Object> map) {
		boolean legal = false;
		for (int i = 0; i < state.players.size(); i++) {
			Player p = state.players.get(i);
			if (p.username.equals(map.get("user")) && state.currentPlayer == i) {
				legal = true;
				break;
			}
		}
		return legal;
	}
	
	
	
	
}
