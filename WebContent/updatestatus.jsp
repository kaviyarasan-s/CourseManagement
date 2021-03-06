<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update status</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<script>
	$(document).ready(
			function() {

				$("#course").change(
						function() {

							$.post('ViewTopicServlet', {
								courseId : $("#course").val()
							},
									function(response) {

										var list = response.split(',');

										for ( var item in list) {
											$('#topic').append(
													"<option value="+list[item]+">"
															+ list[item]
															+ "</option>");
										}

									});

						});

				$("#topic").change(function() {

					console.log($("#topic").val());
					$.ajax({
						type : "POST",
						url : "UpdateStatusServlet",
						data : {
							topicname : $("#topic").val(),
							courseId : $("#course").val(),
							button : "status"
						},
						success : function(data) {

							$('#status').val(data);

							console.log(data)
						},

					});

				});

			});
</script>
</head>
<body>
	
	<form method="post" action="UpdateStatusServlet">
		Course List: <select name="coursename" id="course">
			<option value="">Select</option>
			<c:forEach var="courseList" items="${COURSELIST}">
				<option value="${courseList.id}">${courseList.name}</option>
			</c:forEach>




		</select> Topic List: <select name="topicname" id="topic">
			<option value="">Select</option>
		</select> Status:<select name="statusname" id="status">
			<option value="">Select</option>
			<c:forEach var="statusList" items="${STATUSLIST}">
				<option value="${statusList.id}">${statusList.name}</option>
			</c:forEach>
		</select>

		<button type="submit" value="update" name='button'>Update
			status</button>
	</form>

	<%
		if (request.getAttribute("message") != null) {
			out.print(request.getAttribute("message"));
		}
	%>

	<a href="statusoperation.html">Back</a>
</body>
</html>