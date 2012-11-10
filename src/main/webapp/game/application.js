var subscribed = false;
var ismyturn = false;
var changePhase;
require(['dojox/cometd', 'dojo/dom', 'dojo/domReady!'], function(cometd, dom)
{
    changePhase = function(phase) {
        cometd.publish('/game/' + gameid, {
        	phase_change: phase
        });
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
    			cometd.subscribe('/game/' + gameid, receive_broadcast);
    			cometd.publish('/game/' + gameid, "iam_ready");
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
	var kvpairs = document.cookie.split("; ");
	for (i in kvpairs) {
		kvpair = kvpairs[i].split("=");
		if (kvpair[0] === "gameid") {
			return kvpair[1];
		}
	}
}
var gameid = get_game_id();

function receive_broadcast(event) {
	if (event.id === "gamestate") {
		// Display the gamestate stuff
		var data = JSON.parse(event.data);
		var phdiv = document.getElementById("phases");
		phases.innerHTML = "\n";
		for (i in data.phases) {
			var butt = "<button ";
			if (i <= data.currentPhase) {
				butt += "disabled='disabled'";
			}
			butt += " onclick='changePhase(" + i + ");'>" + data.phases[i] + "</button>\n";
			phases.innerHTML += butt;
		}
	} else if (event.id === "message") {
		var data = event.data;
		var ms = document.getElementById("message");
		ms.innerHTML = data;
	} else {
		// Bad ids (some sort of response packet)
		return;
	}
}