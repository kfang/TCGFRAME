package org.tcgframework;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

import org.cometd.annotation.Listener;
import org.cometd.annotation.Service;
import org.cometd.annotation.Session;
import org.cometd.bayeux.server.BayeuxServer;
import org.cometd.bayeux.server.ServerChannel;
import org.cometd.bayeux.server.ServerMessage;
import org.cometd.bayeux.server.ServerSession;
import org.tcgframework.resource.DominionGameState;
import org.tcgframework.resource.GameState;

@Service
public class JoinGameService {
	
	//Instance Variables
	HashMap<String, Object> users = new HashMap<String, Object>();
	HashSet<String> usernames = new HashSet<String>();
	ArrayList<GameState> games = new ArrayList<GameState>();
	
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
		games.add(new DominionGameState(message.getClientId()));
		
		//broadcast to all users that a new game started
		this.bayeux.createIfAbsent("/broadcast/waiting");
		ServerChannel broadcastChannel = this.bayeux.getChannel("/broadcast/waiting");
		broadcastChannel.publish(this.session, message.getClientId(), "start");
		
		//empty out the users list
		users.clear();
		usernames.clear();
		System.out.println("Game has started, Users have been cleared");
	}
	
	//GAME LISTENERS
	
	@Listener("/game/*")
	public void gameHandler(ServerSession session, ServerMessage message){

	}
	
	
	
	
}
