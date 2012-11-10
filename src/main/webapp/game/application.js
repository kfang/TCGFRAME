var subscribed = false;
var ismyturn = false;
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
    		console.log('CometD handshake successful');
    		if (!subscribed) {
    			cometd.subscribe('/game/' + get_game_id(), receive_broadcast);
    			cometd.publish('/game/' + get_game_id(), "list_users");
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

function get_game_id() {
	var kvpairs = document.cookie.split(";");
	for (i in kvpairs) {
		kvpair = kvpairs[i].split("=");
		kvpair[0] = kvpair[0].replace(/\s+/g, '');
		if (kvpair[0] === "gameid") {
			return kvpair[1];
		}
	}
}

function receive_broadcast(event) {
	var data = event.data;
	var ul = document.getElementById("user_list");
	ul.innerHTML = "\n";
	for (i in data) {
		ul.innerHTML += "<li>" + pplList[i] + "</li>\n";
	}
}