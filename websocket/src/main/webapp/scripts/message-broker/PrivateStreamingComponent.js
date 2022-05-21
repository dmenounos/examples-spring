/**
 * A simple panel that communicates over a private (P2P) WebSocket channel. 
 */
PrivateStreamingComponent = function(el, baseUrl) {
	StreamingComponent.call(this, el, {
		title       : 'Private',
		mode        : 'P2P',
		endpointUrl : baseUrl + '/trig-service',
		requestUrl  : '/app/trig/next-values-user',
		responseUrl : '/user/topic/trig/next-values-user'
	});
};

PrivateStreamingPrototype = function() {};
PrivateStreamingPrototype.prototype = StreamingComponent.prototype;
PrivateStreamingComponent.prototype = new PrivateStreamingPrototype();
