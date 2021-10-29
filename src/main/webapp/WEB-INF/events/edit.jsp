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
		<h1><c:out value="${event.getName()}"/></h1>
		<div class="col-lg-6 offset-lg-3">
                   <form action="/updateEvent" method="POST" class="p-4 bg-primary text-white">
            				<div class="mb-3">
            					<h3>Edit Event</h3>
            				</div>
							<p><c:out value="${error}"></c:out></p>
							<input type="hidden" class="form-control" name="id" value="${event.getId()}">
							
                            <div class="form-group mb-3">
                                <label for="name" class="fs-4 mb-2">Name:</label>
                                <input type="text" class="form-control" name="name" value="${event.getName()}">
                            </div>
            
                            <div class="form-group mb-3">
                                <label for="date" class="fs-4 mb-2">Date:</label>
                                <input type="date" class="form-control" name="date" value="${event.getDate()}">
                            </div>

                            <div class="form-group row g-3 mb-3">
                            	<div class="col-auto">
                                	<label for="location" class="fs-4">Location:</label>
                                </div>
                                <div class="col">
                                	<input name="location" class="form-control" value="${event.getLocation()}"/>
                                </div>
                        		
                        		<div class="col-auto">
	                        		<select name="state" class="form-select">
	                        			<option value="${event.getState()}" selected><c:out value="${event.getState()}"></c:out></option>
										<c:forEach items="${states}" var="state">
											<c:choose>
												<c:when test="${state != event.getState()}">
	    											<option value="${state}"><c:out value="${state}"></c:out></option>
	    										</c:when>
	    									</c:choose>
	    								</c:forEach>
									</select>
                                </div>
                            </div>
            
                            
                            <div class="form-group mt-5 px-5 mx-5">
                                <input type="submit" class="form-control" value="Edit">
                            </div>
                            
                   	</form>
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