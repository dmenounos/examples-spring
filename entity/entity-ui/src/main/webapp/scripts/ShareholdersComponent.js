/**
 * Displays the Shareholders table.
 */
function ShareholdersComponent(shareholders) {
	this.shareholders = shareholders;
}

ShareholdersComponent.prototype.render = function() {

	$('#shareholders').html(`
		<h2>Shareholders</h2>
		<table id='shareholders' class='table table-bordered'>
			<thead>
				<tr>
					<th>Name</th>
					<th>Shares</th>
				</tr>
			</thead>
			<tbody></tbody>
		</table>
	`);

	for (let shareholder in this.shareholders) {
		let shareholderRow = `
			<tr>
				<td>${shareholder}</td>
				<td>${this.shareholders[shareholder]}</td>
			</tr>
		`;

		$(shareholderRow).appendTo('#shareholders tbody')
	}
};
