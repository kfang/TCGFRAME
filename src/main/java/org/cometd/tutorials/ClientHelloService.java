package org.cometd.tutorials;

import java.util.HashMap;

import javax.inject.Inject;

import org.cometd.annotation.Listener;
import org.cometd.annotation.Service;
import org.cometd.annotation.Session;
import org.cometd.bayeux.server.BayeuxServer;
import org.cometd.bayeux.server.ServerChannel;
import org.cometd.bayeux.server.ServerMessage;
import org.cometd.bayeux.server.ServerSession;
import org.tcgframework.dominion.DominionGameState;
import org.tcgframework.resource.GameState;

@Service
public class ClientHelloService {
	
	HashMap<String, GameState> games = new HashMap<String, GameState>();
	
	@Session
	private ServerSession session;
	
	@Inject
	private BayeuxServer bayeux;
	
    @Listener("/service/hello")
    public void processClientHello(ServerSession sender, ServerMessage message)
    {	
    	games.put(sender.getId(), new DominionGameState(0, null));
        System.out.printf("Received greeting '%s' from remote client %s%n", message.getData(), sender.getId());
    }
    
    @Listener("/service/games")
    public void getGames(ServerSession sender, ServerMessage message) {
    	System.out.println("There are " + games.size() + " games available");
    	String toSend = games.keySet().toString();
    	
    	if(this.bayeux == null){
    		System.out.println("Bayeux is null");
    	}
    	bayeux.createIfAbsent("/broadcast/games");
  
    	ServerChannel broadcastChannel = this.bayeux.getChannel("/broadcast/games");
    	if(broadcastChannel == null){
    		System.out.println("broadcastChannel is null");
    	}
    	
    	broadcastChannel.publish(this.session, toSend, null);
    }
    
}
