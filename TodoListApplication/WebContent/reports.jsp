<%@ include file="header.jsp"%>

<div class="panel panel-default">
	<div class="panel panel-heading">Your To-Do List</div>
	<div class="panel-body">
		<div class="list-group">
		<c:choose>
		<c:when test = "${not empty itemList }">
			<c:forEach var="item" items="${itemList}">
			<c:choose>
			<c:when test="${item.status.statusId == '1' }">
				<a
					href="superServlet?action=details&item_id=${item.itemId}"
					class="list-group-item list-group-item-success">
					<h4 class="list-group-item-heading">${item.description}</h4>
					<h5 class="list-group-item-heading">${item.status.description}</h5>
					<p class="list-group-item-text">Get Details?</p>
				</a>
				<br />
				<br />
				</c:when>
				<c:when test="${item.status.statusId == '2' }">
				<a
					href="superServlet?action=details&item_id=${item.itemId}"
					class="list-group-item list-group-item-warning">
					<h4 class="list-group-item-heading">${item.description}</h4>
					<h5 class="list-group-item-heading">${item.status.description}</h5>
					<p class="list-group-item-text">Get Details?</p>
				</a>
				<br />
				<br />
				</c:when>
				<c:when test="${item.status.statusId == '3' }">
				<a
					href="superServlet?action=details&item_id=${item.itemId}"
					class="list-group-item list-group-item-danger">
					<h4 class="list-group-item-heading">${item.description}</h4>
					<h5 class="list-group-item-heading">${item.status.description}</h5>
					<p class="list-group-item-text">Get Details?</p>
				</a>
				<br />
				<br />
				</c:when>
				</c:choose>
			</c:forEach>
			</c:when>
			<c:otherwise><p>No items in your list. Start adding items</p></c:otherwise>
			</c:choose>
		</div>
	</div>
</div>
<a class="btn btn-warning" href="addNewTask.jsp">Add New Task</a>

<%@ include file="footer.jsp"%>