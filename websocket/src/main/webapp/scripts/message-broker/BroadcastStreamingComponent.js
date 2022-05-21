/**
 * A simple panel that communicates over a broadcast (PubSub) WebSocket channel. 
 */
BroadcastStreamingComponent = function(el, baseUrl) {
	StreamingComponent.call(this, el, {
		title       : 'Broadcast',
		mode        : 'PubSub',
		endpointUrl : baseUrl + '/trig-service',
		requestUrl  : '/app/trig//next-values',
		responseUrl : '/topic/trig/next-values'
	});
};

BroadcastStreamingPrototype = function() {};
BroadcastStreamingPrototype.prototype = StreamingComponent.prototype;
BroadcastStreamingComponent.prototype = new BroadcastStreamingPrototype();
