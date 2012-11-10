package org.tcgframework;

import javax.inject.Inject;

import org.cometd.annotation.Service;
import org.cometd.annotation.Session;
import org.cometd.bayeux.server.BayeuxServer;
import org.cometd.bayeux.server.ServerSession;

@Service
public class JoinGameService {

	
	@Session
	private ServerSession session;
	
	@Inject
	private BayeuxServer bayeux;
	
	
	
	
}
