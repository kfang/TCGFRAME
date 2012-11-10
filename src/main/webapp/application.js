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
    	    cometd.subscribe('/broadcast/waiting', function(message){
    	    	console.log(message);
    	    });
    	    
    	}
    	else
    	{
    		dom.byId('status').innerHTML += '<div>CometD handshake failed</div>';
    	}
    });

    cometd.handshake();
});

function get_a_group() {
	document.cookie = "uName=" + document.getElementById("username").value
}

console.log('hello world');
console.log('meh');
