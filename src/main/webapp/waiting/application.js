var subscribed = false;
var send_start;
require(['dojox/cometd', 'dojo/dom', 'dojo/domReady!'], function(cometd, dom)
{
	send_start = function() {
		cometd.publish('/broadcast/startgame', 'start');
	}
    cometd.configure({
        url: location.protocol + '//' + location.host + config.contextPath + '/cometd',
        logLevel: 'info'
    });

    cometd.addListener('/meta/handshake', function(message)
    {
    	if (message.successful)
    	{
    		console.log('CometD handshake successful');
    		if (!subscribed) {
    			cometd.subscribe('/broadcast/waiting', receive_broadcast);
    			cometd.publish('/broadcast/waiting', get_username());
    			subscribed = true;
    		}
    	}
    	else
    	{
    		console.log('CometD handshake failed');
    	}
    });
    
    cometd.handshake();
});

function get_username() {
	var kvpairs = document.cookie.split("; ");
	for (i in kvpairs) {
		kvpair = kvpairs[i].split("=");
//		kvpair[0] = kvpair[0].replace(/\s+/g, '');
		if (kvpair[0] === "uName") {
			return kvpair[1];
		}
	}
}

function receive_broadcast(event) {
	if (event.id === "start") {
		document.cookie = "gameid=" + event.data + "; path=/";
		document.getElementById("game_form").submit();
		
	}
	else if (event.id !== undefined) {
		// Then it wasn't our message
		return;
	}
	var pplList = event.data["USERS"];
	var ul = document.getElementById("user_list");
	// List all the peeps in the ul.
	ul.innerHTML = "\n";
	for (i in pplList) {
		ul.innerHTML += "<li>" + pplList[i] + "</li>\n";
	}
	// If I am the first person in the list, I get to choose to start the game.
	if (event.data["OWNER"] === get_username()) {
		var bDiv = document.getElementById("start_game");
		bDiv.innerHTML = "\n<button onclick='send_start();'>Start Game</button>\n";
	}
}
