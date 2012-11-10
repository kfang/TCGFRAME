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
    		dom.byId('status').innerHTML += '<div>CometD handshake successful</div>';
    	}
    	else
    	{
    		dom.byId('status').innerHTML += '<div>CometD handshake failed</div>';
    	}
    });
    
    dom.byId('hello_server').onclick = function() {
        cometd.publish('/service/hello', 'Hello, World');
    };
    
    cometd.handshake();
    fill_in_user();
});

function fill_in_user() {
	var username = document.cookie.split(";");
	for (i in username) {
		kvpair = username[i].split("=");
		kvpair[0] = kvpair[0].replace(/\s+/g, '');
		if (kvpair[0] === "uName") {
			document.getElementById("username").innerHTML = kvpair[1];
		}
	}
}

