<%@ include file="header.jsp"%>

<input type="hidden" name="item_id"
	value="<c:out value="${myItem.itemId}" />" />
<div class="panel panel-default">
	<div class="panel-heading">
		<c:out value="${myItem.description}" />
	</div>
	<div class="panel-body">
		<table class="table">
			<thead>
				<th>Status</th>
				<th>Priority Level</th>
				<th>Due Date</th>
				<th>Start Date</th>
				<th>Complete Date</th>
			</thead>
			<tbody>

				<tr>
					<td><c:out value="${myItem.status.description}" /><br>
					<form action="superServlet?action=changeStatus&item_id=${myItem.itemId}"
							method="POST">
							<select class="form-control" name="status" required>
								<option value="">Select</option>
								<option value="1">Completed</option>
								<option value="2">In Progress</option>
								<option value="3">In Completed</option>

							</select>
							<button>Change?</button>
						</form></td>
					<td><c:out value="${myItem.priority.description}" /></td>
					<td><c:out value="${myItem.dueDate}" /><br>
					<form action="superServlet?action=change&item_id=${myItem.itemId}"
							method="POST">
							<input type="date" class="form-control" name="newDate"
								id="exampleInputname" placeholder="MM/DD/YYYY" required>
							<button>Change?</button>
						</form></td>
					<td><c:choose>
							<c:when test="${myItem.dateStarted != null}">
								<c:out value="${myItem.dateStarted}" />
							</c:when>
							<c:otherwise>
								<a href="superServlet?action=start&item_id=${myItem.itemId}">Start?</a>
							</c:otherwise>
						</c:choose></td>
					<td><c:choose>
							<c:when test="${myItem.dateCompleted != null}">
								<c:out value="${myItem.dateCompleted}" />
							</c:when>
							<c:otherwise>
								<a href="superServlet?action=completed&item_id=${myItem.itemId}">Done?</a>
							</c:otherwise>
						</c:choose></td>
				</tr>
			</tbody>
		</table>
	</div>
</div>

<a class="btn btn-warning" href="superServlet?action=homepage">
	To-Do List </a>

<%@ include file="footer.jsp"%>