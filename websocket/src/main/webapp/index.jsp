<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>

<t:template>
	<div class="container">
		<div class="row">
			<div id="private-websocket-component" class="col"></div>
			<div id="broadcast-websocket-component" class="col"></div>
		</div>
	</div>
	<script src="scripts/basic/WebSocketComponent.js"></script>
	<script src="scripts/basic/PrivateWebSocketComponent.js"></script>
	<script src="scripts/basic/BroadcastWebSocketComponent.js"></script>
	<script>
		var baseHttpUrl = '${scheme}://${serverName}:${serverPort}${contextPath}';
		var baseWsUrl = baseHttpUrl.replaceAll('http', 'ws');

		var privateWebSocketComponent = new PrivateWebSocketComponent('#private-websocket-component', baseWsUrl);
		var broadcastWebSocketComponent = new BroadcastWebSocketComponent('#broadcast-websocket-component', baseWsUrl);
	</script>
</t:template>
