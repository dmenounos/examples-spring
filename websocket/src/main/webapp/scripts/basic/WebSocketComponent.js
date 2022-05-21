/**
 * Abstract class that consists of a simple panel and demonstrates communication over WebSocket.
 *
 * Concrete classes shall provide the following properties:
 * - title
 * - url
 */
function WebSocketComponent(root, props) {
	$.extend(this, props || {});
	this.root = $(root);

	// State
	this.messages = [];

	this.render();
	this.update();

	this.$('.connectButton').on('click', $.proxy(this.connect, this));
	this.$('.disconnectButton').on('click', $.proxy(this.disconnect, this));
	this.$('form').on('submit', $.proxy(this.sendMessage, this));
};

WebSocketComponent.prototype.$ = function(selector) {
	return $(selector, this.root);
};

WebSocketComponent.prototype.render = function() {
	this.root.html(`
		<form>
			<div class="my-2 overflow-hidden">
				<h3 class="float-start">${this.title}</h3>
				<div class="float-end">
					<input type="button" class="connectButton btn btn-primary" value="Connect">
					<input type="button" class="disconnectButton btn btn-primary" style="display: none;" value="Disconnect">
				</div>
			</div>
			<div class="mb-2">
				<div class="form-floating">
					<input type="text" name="message" class="messageField form-control" placeholder="Message">
					<label for="message" class="form-label">Message</label>
				</div>
			</div>
			<div class="mb-3">
				<input type="submit" class="sendButton btn btn-primary" value="Send">
			</div>
			<div class="mb-3">
				<ul class="messages"></ul>
			</div>
		</form>
	`);
};

WebSocketComponent.prototype.update = function() {
	if (!this.ws || this.ws.readyState === 3) {
		this.$('.disconnectButton').hide();
		this.$('.connectButton').show();
		this.$('.messageField').prop('disabled', true);
		this.$('.sendButton').prop('disabled', true);
	}
	else {
		this.$('.connectButton').hide();
		this.$('.disconnectButton').show();
		this.$('.messageField').prop('disabled', false);
		this.$('.sendButton').prop('disabled', false);
	}

	let messagesHtml = this.messages.reduce((html, messageObject) => {
		return html + `<li>${messageObject.message}</li>`;
	}, '');

	this.$('.messages').html(messagesHtml);
};

WebSocketComponent.prototype.connect = function() {
	this.ws = new WebSocket(this.url);

	this.ws.onopen = $.proxy(function(e) {
		this.update();
	}, this);

	this.ws.onclose = $.proxy(function(e) {
		this.messages = [];
		this.update();
	}, this);

	this.ws.onmessage = $.proxy(function(e) {
		let messageObject = JSON.parse(e.data);
		this.messages.push(messageObject);
		this.update();
	}, this);
};

WebSocketComponent.prototype.disconnect = function() {
	this.ws.close();
};

WebSocketComponent.prototype.sendMessage = function() {
	let messageField = this.$('.messageField');
	let messageValue = messageField.val();
	messageField.val('');

	let messageObject = {
		message: messageValue
	};
	this.ws.send(JSON.stringify(messageObject));
	return false;
};
