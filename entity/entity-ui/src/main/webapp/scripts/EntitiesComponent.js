/**
 * Displays the Entities.
 */
function EntitiesComponent(entities) {
	this.entities = entities;
}

EntitiesComponent.prototype.render = function() {

	$('#entities').html(`
		<h2>Entities</h2>
		<table id='entities' class='table table-bordered table-hover'>
			<thead>
				<tr>
					<th>Name</th>
					<th>Country</th>
					<th>Date of Incorporation</th>
					<th>Total Shares</th>
				</tr>
			</thead>
			<tbody></tbody>
		</table>
		<div id='shareholders'></div>
	`);

	for (let entity of this.entities) {
		let entityRow = `
			<tr>
				<td>${entity.name}</td>
				<td>${entity.country}</td>
				<td>${entity.incorporationDate}</td>
				<td>${entity.totalShares}</td>
			</tr>
		`;

		$(entityRow).appendTo('#entities tbody').on('click', () => {
			let shareholdersComponent = new ShareholdersComponent(entity.shareholders);
			shareholdersComponent.render();
		});
	}
};
