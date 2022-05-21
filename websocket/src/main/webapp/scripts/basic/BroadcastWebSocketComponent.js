/**
 * A message panel that communicates over a broadcasting (PubSub) WebSocket channel. 
 */
BroadcastWebSocketComponent = function(el, baseUrl) {
	WebSocketComponent.call(this, el, {
		title	: 'Broadcast',
		url		: baseUrl + '/broadcast'
	});
};

BroadcastWebSocketPrototype = function() {};
BroadcastWebSocketPrototype.prototype = WebSocketComponent.prototype;
BroadcastWebSocketComponent.prototype = new BroadcastWebSocketPrototype();
