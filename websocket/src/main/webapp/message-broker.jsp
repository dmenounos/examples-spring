<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>

<t:template>
	<div class="container">
		<div class="row">
			<div id="private-streaming-component" class="col"></div>
			<div id="broadcast-streaming-component" class="col"></div>
		</div>
	</div>
	<script src="scripts/message-broker/StreamingComponent.js"></script>
	<script src="scripts/message-broker/PrivateStreamingComponent.js"></script>
	<script src="scripts/message-broker/BroadcastStreamingComponent.js"></script>
	<script>
		var baseHttpUrl = '${scheme}://${serverName}:${serverPort}${contextPath}';
		var baseWsUrl = baseHttpUrl.replaceAll('http', 'ws');

		let privateStreamingComponent = new PrivateStreamingComponent('#private-streaming-component', baseWsUrl);
		let broadcastStreamingComponent = new BroadcastStreamingComponent('#broadcast-streaming-component', baseWsUrl);
	</script>
</t:template>
