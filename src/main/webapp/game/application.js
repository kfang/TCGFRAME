var subscribed = false;
var ismyturn = false;
var changePhase;
var doCard;
require(['dojox/cometd', 'dojo/dom', 'dojo/domReady!'], function(cometd, dom)
{
	doCard = function(card) {
		cometd.publish('/game/' + gameid, {
			do_card: card,
			user: userid
		});
	}
    changePhase = function(phase) {
        cometd.publish('/game/' + gameid, {
        	phase_change: phase,
        	user: userid
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

function get_username() {
	var kvpairs = document.cookie.split("; ");
	for (i in kvpairs) {
		kvpair = kvpairs[i].split("=");
		if (kvpair[0] === "uName") {
			return kvpair[1];
		}
	}
}
var userid = get_username();
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
		phdiv.innerHTML = "Currently in: " + data.phases[data.currentPhase] + "\n";
		for (i in data.phases) {
			var butt = "<button ";
			if (i <= data.currentPhase) {
				butt += "disabled='disabled'";
			}
			butt += " onclick='changePhase(" + i + ");'>" + data.phases[i] + "</button>\n";
			phdiv.innerHTML += butt;
		}
		var supplyDiv = document.getElementById("supply");
		var statsDiv = document.getElementById("stats");
		statsDiv.innerHTML = "<div>Actions: " + data.actions + "</div>\n";
		statsDiv.innerHTML += "<div>Buys: " + data.buys + "</div>\n";
		statsDiv.innerHTML += "<div>Money: " + data.money + "</div>\n";
		var handDiv = document.getElementById("hand");
		handDiv.innerHTML = "in hand:\n";
		for (i in data.hand) {
			handDiv.innerHTML += "<img onclick='doCard(\"" + data.hand[i] + "\");' width='148px' height='237px' border='0' src='cards/" + data.hand[i] + ".jpg' alt='" + data.hand[i] + "'/>";
		}
		var playDiv = document.getElementById("play");
		playDiv.innerHTML = "in play:\n"
		for (i in data.inPlay) {
			playDiv.innerHTML += "<img width='148px' height='237px' border='0' src='cards/" + data.inPlay[i] + ".jpg' alt='" + data.inPlay[i] + "'/>";
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