/**
 * A message panel that communicates over a private (P2P) WebSocket channel. 
 */
PrivateWebSocketComponent = function(el, baseUrl) {
	WebSocketComponent.call(this, el, {
		title	: 'Private',
		url		: baseUrl + '/private'
	});
};

PrivateWebSocketPrototype = function() {};
PrivateWebSocketPrototype.prototype = WebSocketComponent.prototype;
PrivateWebSocketComponent.prototype = new PrivateWebSocketPrototype();
