<%@ include file="header.jsp"%>
<form action="superServlet?action=add" method="post">
	<div class="panel panel-default">
		<div class="panel panel-heading">Add New Task</div>

		<div class="panel-body">
			<table class="table">
				<thead>
					<th>Description</th>
					<th>Status</th>
					<th>Priority Level</th>
					<th>Due Date</th>
					<th>Start Date</th>
					<th>Complete Date</th>
				</thead>
				<tbody>
					<tr>
						<td>
							<div class="form-group">
								<input type="name" class="form-control" name="description"
									id="exampleInputname" placeholder="Task Description" required>
							</div>
						</td>
						<td>
							<div class="form-group">
								<select class="form-control" name="status" required>
									<option value="">Select</option>
									<option value="1">Completed</option>
									<option value="2">In Progress</option>
									<option value="3">In Completed</option>

								</select>
							</div>
						</td>
						<td>
							<div class="form-group">
								<select class="form-control" name="priority" required>
									<option value="">Select</option>
									<option value="1">Urgent</option>
									<option value="2">High</option>
									<option value="3">Medium</option>
									<option value="4">Low</option>
									<option value="5">No Priority</option>
								</select>
							</div>
						</td>
						<td>
							<div class="form-group">

								<input type="date" class="form-control" name="dueDate"
									id="exampleInputUser1" placeholder="MM/DD/YYYY" required>
							</div>
						</td>
						<td>
							<div class="form-group">
								<input type="date" class="form-control" name="startDate"
									id="exampleInputUser1" placeholder="MM/DD/YYYY">
							</div>
						</td>
						<td>
							<div class="form-group">
								<input type="date" class="form-control" name="completedDate"
									id="exampleInputUser1" placeholder="MM/DD/YYYY">
							</div>
						</td>
					</tr>
				</tbody>
			</table>
			<button type="submit" class="btn btn-warning">Add</button>

		</div>
	</div>
</form>


<%@ include file="footer.jsp"%>