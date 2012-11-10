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
    	    cometd.publish('/broadcast/waiting', get_username());
    		cometd.subscribe('/broadcast/waiting', update_users);
    	}
    	else
    	{
    		console.log('<div>CometD handshake failed</div>');
    	}
    });
    
    cometd.handshake();
    fill_in_user();
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
	var ul = document.getElementById("user_list");
	ul.innerHTML = "\n";
	for (i in pplList) {
		ul.innerHTML += "<li>" + pplList[i] + "</li>\n";
	}
}

