/**
 * Abstract class that consists of a simple panel and demonstrates communication over WebSocket with Message Broker and STOMP.
 *
 * Concrete classes shall provide the following properties:
 * - title
 * - endpointUrl
 * - requestUrl
 * - responseUrl
 */
function StreamingComponent(root, props) {
	$.extend(this, props || {});
	this.root = $(root);

	this.resetState();

	this.render();
	this.update();

	this.$('.connectButton').on('click', $.proxy(this.connect, this));
	this.$('.disconnectButton').on('click', $.proxy(this.disconnect, this));
};

StreamingComponent.prototype.$ = function(selector) {
	return $(selector, this.root);
};

StreamingComponent.prototype.render = function() {
	this.root.html(`
		<form>
			<div class="my-2 overflow-hidden">
				<h3 class="float-start">${this.title}</h3>
				<div class="float-end">
					<input type="button" class="connectButton btn btn-primary" value="Connect">
					<input type="button" class="disconnectButton btn btn-primary" style="display: none;" value="Disconnect">
				</div>
			</div>
			<div class="mb-3">
				<canvas style="border: 1px solid;"></canvas>
				<textarea rows="3" class="form-control" style="font-size: 0.625em;"></textarea>
			</div>
		</form>
	`);

	this.canvas = this.$('canvas');
	let canvasBorderLeftWidth = parseInt(this.canvas.css('borderLeftWidth'), 10);
	let canvasBorderRightWidth = parseInt(this.canvas.css('borderRightWidth'), 10);
	this.canvas.prop('width', this.canvas.parent().innerWidth() - canvasBorderLeftWidth - canvasBorderRightWidth);

	this.textarea = this.$('textarea');
};

StreamingComponent.prototype.update = function() {
	if (!this.ws || this.ws.readyState === 3) {
		this.$('.disconnectButton').hide();
		this.$('.connectButton').show();
	}
	else {
		this.$('.connectButton').hide();
		this.$('.disconnectButton').show();
	}

	this.clear();

	if (this.values.length) {
		this.drawText('- sin', 10, 10, 'red');
		this.drawText('- cos', 10, 10, 'blue');
	}

	let valuesHtml = this.values.reduce((html, value, index) => {
		let degX = index / (360 / this.canvas.innerWidth());
		let sinY = this.canvas.height() / 2 + value.sin * this.canvas.height() / 2;
		let cosY = this.canvas.height() / 2 + value.cos * this.canvas.height() / 2;

		this.drawPixel(degX, sinY, 'red');
		this.drawPixel(degX, cosY, 'blue');

		return html + `deg: ${index} sin: ${value.sin} cos: ${value.cos}\n`;
	}, '');

	this.textarea.val(valuesHtml);
	this.textarea.scrollTop(this.textarea[0].scrollHeight);
};

StreamingComponent.prototype.resetState = function() {
	this.values = [];
	this.degrees = 0;
	this.size = 10;
}

StreamingComponent.prototype.connect = function() {
	this.ws = new WebSocket(this.endpointUrl);
	this.stompClient = Stomp.over(this.ws);
	this.stompClient.debug = function(str) {};
	
	this.stompClient.connect({}, this.endpointUrl, $.proxy(function() {
		console.log('this.stompClient.connect');
		this.resetState();
		this.update();

		this.subscription = this.stompClient.subscribe(this.responseUrl, $.proxy(function(e) {
			console.log('this.stompClient.subscribe ', e);
			let responseObject = JSON.parse(e.body);
			this.values = this.values.concat(responseObject.nextValues);
			this.degrees += this.size;
			this.update();
		}, this));

		if (this.mode == 'P2P') {
			this.intervalID = setInterval($.proxy(function() {
				let requestObject = { degrees: this.degrees, size: this.size };
				this.stompClient.send(this.requestUrl, {}, JSON.stringify(requestObject));
			}, this), 1000);
		}

	}, this));
};

StreamingComponent.prototype.disconnect = function() {
	clearInterval(this.intervalID);
	this.subscription.unsubscribe({ destination: this.responseUrl });
	this.stompClient.disconnect($.proxy(function() {
		console.log('stompClient.disconnect');
		delete this.ws;
		this.update();
	}, this));
};

StreamingComponent.prototype.clear = function() {
	let ctx = this.canvas[0].getContext("2d");
	ctx.clearRect(0, 0, this.canvas.width(), this.canvas.height());
};

StreamingComponent.prototype.drawText = function(text, x, y, color) {
	let ctx = this.canvas[0].getContext("2d");
	ctx.fillStyle = color || "#000000";
	ctx.fillText(text, x, this.canvas.height() - y);
};

StreamingComponent.prototype.drawPixel = function(x, y, color) {
	let ctx = this.canvas[0].getContext("2d");
	ctx.fillStyle = color || "#000000";
	ctx.fillRect(x, this.canvas.height() - y, 1, 1);
};
