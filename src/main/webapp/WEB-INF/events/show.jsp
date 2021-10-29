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
	              <a href="/home" class="nav-link">Dashboard</a>
	            </li>
	          </ul>
	        </div>
        </nav>

        <section class="my-3 py-4"></section>

        <section>
            <div class="container">
            	<h1><c:out value="${event.getName()}" /></h1>
                <div class="row g-5">
                    <div class="col">
                        <h4>Host: <c:out value="${event.getOrganizer().getFirstName()} ${event.getOrganizer().getLastName()}" /></h4>
                        <h4>Date: <c:out value="${event.getDate()}" /></h4>
                        <h4>Location: <c:out value="${event.getLocation()}, ${event.getState()}" /></h4>
                        <h4>Attendees: <c:out value="${event.getAttendees().size()}" /></h4>
                        
                        <table class="table table-striped table-hover">
				<thead class="table-dark">
					<tr>
						<td>Name</td>
						<td>Location</td>
					</tr>
				</thead>
				<tbody>					
						<c:forEach items="${event.getAttendees()}" var="att">
								<tr>
									<td><c:out value="${att.getFirstName()} ${att.getLastName()}"/></td>
									<td><c:out value="${att.getLocation()},${att.getState()} "/></td>
								</tr>
						</c:forEach>				
				</tbody>
			</table>       
                    </div>
            
            
                    <div class="col">
                        <div class="mb-3">
                            <h1>Message Wall</h1>
                        </div>
                        
                        <div class="mb-3 overflow-scroll">
                        	<c:forEach items="${event.getMessages()}" var="msg">
                        		<p><c:out value="${msg.getSender().getFirstName()} ${msg.getSender().getLastName()} says: ${msg.getContent()}" /></p>
                        		<p>-----</p>
                        	</c:forEach>
                        </div>
                        
                        <form:form method="POST" action="/sendMessage" class="p-4 bg-secondary text-white" modelAttribute="message">
                        	<input type="hidden" name="eventId" value = "${event.getId()}">
							<div class="form-group mb-3">
                                <form:label path="content" class="fs-4 mb-2" >Add Comment:</form:label>
                                <form:input path="content" class="form-control"/>
                            </div>
            
                            
                            <div class="form-group mt-5 px-5 mx-5">
                                <input type="submit" class="form-control" value="Submit">
                            </div>
                            
                        </form:form>
            
                        
                    </div>




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