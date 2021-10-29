<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Events Core Assignment</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />

    </head>
    <body>
        <nav class="navbar navbar-expand-lg bg-dark navbar-dark py-1 fixed-top">
            <div class="container">
              <a href="#" class="navbar-brand">Events Core Assignment</a>
            </div>
            <button
          class="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navmenu"
        >
	          <span class="navbar-toggler-icon"></span>
	        </button>
	
	        <div class="collapse navbar-collapse" id="navmenu">
	          <ul class="navbar-nav ms-auto">
	            <li class="nav-item">
	              <a href="/logout" class="nav-link">Logout</a>
	            </li>
	          </ul>
	        </div>
        </nav>

        <section class="my-3 py-4"></section>

<section class="py-2">
	<div class="container">
		<h1 class="text-center">Welcome <c:out value="${user.firstName}!"/></h1>
		<p class="text-center" >Here are some of the events in your state:</p>
		<div class="col-lg-6 offset-lg-3">
			<table class="table table-striped table-hover">
				<thead class="table-dark">
					<tr>
						<td>Name</td>
						<td>Date</td>
						<td>Location</td>
						<td>Host</td>
						<td>Action/Status</td>
					</tr>
				</thead>
				<tbody>					
						<c:forEach items="${stateEvents}" var="event">
								<tr>
									<td><a href="/events/${event.getId()}"><c:out value="${event.getName()}"/></a></td>
									<td><c:out value="${event.getDate()}"/></td>
									<td><c:out value="${event.getLocation()}, ${event.getState()}"/></td>
									<td><c:out value="${event.getOrganizer().getFirstName()} ${event.getOrganizer().getLastName()}"/></td>
									<td>
										<c:choose>
											<c:when test="${event.getOrganizer().getId() != user.getId()}">
												<c:choose>
													<c:when test="${event.getAtt_ids().contains(user.getId())}">
														<a href="/cancel/${event.getId()}">Cancel</a>
													</c:when>
													<c:otherwise>
														<a href="/join/${event.getId()}">Join</a>
													</c:otherwise>	
												</c:choose>
											</c:when>
											<c:otherwise>
												<a href="/edit/${event.getId()}">Edit</a>
												<a href="/delete/${event.getId()}">Delete</a>
											</c:otherwise>
										</c:choose>
									</td>
								</tr>
						</c:forEach>				
				</tbody>
			</table>
		</div>
	</div>
</section> 

<section class="py-2">
	<div class="container">
		<p class="text-center">Here are some of the events in other states:</p>
		<div class="col-lg-6 offset-lg-3">
			<table class="table table-striped table-hover">
				<thead class="table-dark">
					<tr>
						<td>Name</td>
						<td>Date</td>
						<td>Location</td>
						<td>Host</td>
						<td>Action/Status</td>
					</tr>
				</thead>
				<tbody>					
						<c:forEach items="${otherStateEvents}" var="event">
								<tr>
									<td><a href="/events/${event.getId()}"><c:out value="${event.getName()}"/></a></td>
									<td><c:out value="${event.getDate()}"/></td>
									<td><c:out value="${event.getLocation()}, ${event.getState()}"/></td>
									<td><c:out value="${event.getOrganizer().getFirstName()} ${event.getOrganizer().getLastName()}"/></td>
									<td>
										<c:choose>
											<c:when test="${event.getOrganizer().getId() != user.getId()}">
												<c:choose>
													<c:when test="${event.getAtt_ids().contains(user.getId())}">
														<a href="/cancel/${event.getId()}">Cancel</a>
													</c:when>
													<c:otherwise>
														<a href="/join/${event.getId()}">Join</a>
													</c:otherwise>	
												</c:choose>
											</c:when>
											<c:otherwise>
												<a href="/edit/${event.getId()}">Edit</a>
												<a href="/delete/${event.getId()}">Delete</a>
											</c:otherwise>
										</c:choose>
									</td>
								</tr>
						</c:forEach>				
				</tbody>
			</table>
		</div>
	</div>
</section> 

<section class="py-2">
	<div class="container">
		<h3 class="text-center">Create an Event</h3>
		<p class="text-center"><form:errors path="event.*"/></p>
		<div class="col-lg-6 offset-lg-3">
			<form:form method="POST" action="/createEvent" class="p-4 bg-secondary text-white" modelAttribute="event">
                            <div class="form-group mb-3">
                                <form:label path="name" class="mb-2" >Name:</form:label>
                                <form:input path="name" class="form-control"/>
                            </div>
                            
                            <div class="form-group mb-3">
                                <form:label path="date" class="mb-2">Date:</form:label>
                                <form:input type="date" path="date" class="form-control"/>
                            </div>
			 
                            <div class="form-group row g-3 mb-3">
                            	<div class="col-auto">
                                	<form:label path="location">Location:</form:label>
                                </div>
                                <div class="col">
                                	<form:input path="location" class="form-control"/>
                                </div>
                        		
                        		<div class="col-auto">
	                        		<form:select path="state" class="form-select">
	    									<form:options items="${states}"></form:options>
									</form:select>
                                </div>
                                
	                            <div class="text-center">
	                                <input  class="btn btn-primary" type="submit" class="form-control" value="Create"/>
	                            </div>
                                
                            </div>
			</form:form>
		</div>
	</div>
</section> 
        

        <section class="py-5 my-5"></section>
        <footer class="col-12 p-1 bg-dark text-white text-center position-fixed bottom-0">
            <div class="container">
              <p class="lead">Coding Dojo Web Dev Bootcamp</p>
            </div>
        </footer>
	<script src="/webjars/jquery/jquery.min.js"></script>
	<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
    </body>
</html>