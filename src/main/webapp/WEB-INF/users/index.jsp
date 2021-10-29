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
        </nav>

        <section class="my-3 py-4"></section>

        <section>
            <div class="container">
                <div class="row g-5">
                    <div class="col">
                        <div class="mb-3">
                            <div class="h1 text-secondary">Register</div>
                            <p><form:errors path="user.*"/></p>
                        </div>
                        <form:form method="POST" action="/registration" class="p-4 bg-secondary text-white" modelAttribute="user"> 

            
                            <div class="form-group mb-3">
                                <form:label path="firstName" class="fs-4 mb-2" >First Name:</form:label>
                                <form:input path="firstName" class="form-control"/>
                            </div>
                            
                            <div class="form-group mb-3">
                                <form:label path="lastName" class="fs-4 mb-2">Last Name:</form:label>
                                <form:input path="lastName" class="form-control"/>
                            </div>
            
                            <div class="form-group mb-3">
                                <form:label path="email" class="fs-4 mb-2">Email:</form:label>
                                <form:input type= "email" path="email" class="form-control"/>
                            </div>
                            
                            <div class="form-group row g-3 mb-3">
                            	<div class="col-auto">
                                	<form:label path="location" class="fs-4">Location:</form:label>
                                </div>
                                <div class="col">
                                	<form:input path="location" class="form-control"/>
                                </div>
                        		
                        		<div class="col-auto">
	                        		<form:select path="state" class="form-select">
	    									<form:options items="${states}"></form:options>
									</form:select>
                                </div>
                            </div>
            

                            <div class="form-group mb-3">
                                <form:label path="password" class="fs-4 mb-2">Password:</form:label>
                                <form:input type="password" class="form-control"  path="password"/>
                            </div>
            
                            <div class="form-group mb-3">
                                <form:label path="passwordConfirmation" class="fs-4 mb-2">Confirm Password:</form:label>
                                <form:input type="password" class="form-control" path="passwordConfirmation"/>
                            </div>
            


                            <div class="form-group mt-5 px-5 mx-5">
                                <input type="submit" class="form-control" value="Register"/>
                            </div>
            
                       </form:form>
            
                        
                    </div>
            
            
                    <div class="col">
                        <div class="mb-3">
                            <span class="h1 text-primary">Login</span>
                            <p><c:out value="${error}" /></p>
                            <p><form:errors path="userLogin.*"/></p>
                        </div>
                        <form:form method="POST" action="/login" class="p-4 bg-primary text-white" modelAttribute="userLogin">
                        

                            <div class="form-group mb-3">
                                <form:label type="email" path="email" class="fs-4 mb-2">Email:</form:label>
                                <form:input path="email" class="form-control"/>
                            </div>
            
                            <div class="form-group mb-3">
                                <form:label path="password" class="fs-4 mb-2">Password:</form:label>
                                <form:input type="password" path="password" class="form-control"/>
                            </div>
            
                            
                            <div class="form-group mt-5 px-5 mx-5">
                                <input type="submit" class="form-control" value="Login"/>
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