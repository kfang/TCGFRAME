var subscribed = false;

require(['dojox/cometd', 'dojo/dom', 'dojo/domReady!'], function(cometd, dom)
{
    cometd.configure({
        url: location.protocol + '//' + location.host + config.contextPath + '/cometd',
        logLevel: 'info'
    });

    cometd.addListener('/meta/handshake', function(message)
    {
    	if (message.successful)
    	{
    		console.log('<div>CometD handshake successful</div>');
    		if (!subscribed) {
    			cometd.subscribe('/broadcast/waiting', update_users);
    			cometd.publish('/broadcast/waiting', get_username());
    			subscribed = true;
    		}
    	}
    	else
    	{
    		console.log('<div>CometD handshake failed</div>');
    	}
    });
    
    cometd.handshake();
});

function get_username() {
	var username = document.cookie.split(";");
	for (i in username) {
		kvpair = username[i].split("=");
		kvpair[0] = kvpair[0].replace(/\s+/g, '');
		if (kvpair[0] === "uName") {
			return kvpair[1];
		}
	}
}

function update_users(event) {
	var pplList = event.data;
	if (event.data !== undefined) {
		// Then it wasn't our message
		return;
	}
	var ul = document.getElementById("user_list");
	// List all the peeps in the ul.
	ul.innerHTML = "\n";
	for (i in pplList) {
		ul.innerHTML += "<li>" + pplList[i] + "</li>\n";
	}
	// If I am the first person in the list, I get to choose to start the game.
	if (pplList[0] === get_username()) {
		var bDiv = document.getElementById("start_game");
		bDiv.innerHTML = "\n<button>Start Game</button>\n";
	}
}

