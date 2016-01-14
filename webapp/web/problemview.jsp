<%-- 
    Author     : Alexander Patras
     
   Problem View
--%>

<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<html>
    <head>
        <%-- Include head section --%>
        <%@ include file="static/header.jsp" %>
        <title>CarMechanic - Προβολή προβλήματος</title>
    </head>
    <body>

        <%
            //If logged in, get username
            String user = null;
            String uName = null;
            String userId = null;
            String role = null;
            if (session.getAttribute("user") != null) {
                user = (String) session.getAttribute("user");
                uName = (String) session.getAttribute("uName");
                userId = "" + (int) session.getAttribute("userId");
                role = (String) session.getAttribute("role");
        %>
        <%@ include file="static/navbarloggedin.jsp" %>
        <% } else { %>
        <%@ include file="static/navbar.jsp" %>
        <% }%>
        <div class="panel panel-default center-block" style="width: 70%;">
            <ol class = "breadcrumb">
                <li><a href = "index.jsp">Αρχική </a></li>
                <li><a href="carview?s=${carinfo.model_id}">Car :
                        <c:if test="${not empty result}">
                            ${carinfo.maker} ${carinfo.model} ${carinfo.year} ${carinfo.engine}</c:if>
                        </a>
                    </li>
                    <li class="active">Problem: ${searchresult.p_id}</li>
            </ol>
            <div class="panel-body container-fluid">
                <div class="row">
                    <div class="col-md-6 center-block">
                        <c:if test="${not empty result}">
                            <h3 style="text-align:center"> Περιγραφή </h3>
                            <p class="bg-info">
                                <c:out value="${searchresult.description}"/>
                            </p>
                        </c:if>
                    </div>
                </div>

            </div>

            <div class="panel-body container-fluid">
                <div class="row">
                    <div class="col-md-6 center-block">

                        <c:if test="${not empty result}">
                            <h3 style="text-align:center"> Φωτογραφίες </h3>
                            <div id="small-img" class="col-xs-12 col-sm-12 col-md-12 col-lg-12 center">
                                <ul>
                                    <li>
                                        <img src="http://placehold.it/150x100" class="img-responsive inline-block" alt="Responsive image" />
                                    </li>
                                    <li>
                                        <img src="http://placehold.it/150x100" class="img-responsive inline-block" alt="Responsive image" />
                                    </li>
                                    <li>
                                        <img src="http://placehold.it/150x100" class="img-responsive inline-block" alt="Responsive image" />
                                    </li>
                                    <li>
                                        <img src="http://placehold.it/150x100" class="img-responsive inline-block" alt="Responsive image" />
                                    </li>
                                    <li>
                                        <img src="http://placehold.it/150x100" class="img-responsive inline-block" alt="Responsive image" />
                                    </li>
                                    <li>
                                        <img src="http://placehold.it/150x100" class="img-responsive inline-block" alt="Responsive image" />
                                    </li>
                                </ul>
                            </div>
                        </c:if>

                    </div>
                </div>
                <%if (role != null && role.equals("1")) { %>
                <div class="panel-body container-fluid">

                    <div class="row">
                        <div class="col-md-6 center-block">
                            <div style="margin-right: 100px;" class="col-md-3">
                                <a href="addsolution"><button type="button" class="btn   btn-default">Προσθήκη Λύσης</button></a>
                            </div>
                            <form action="uploadphoto">
                                <input type="hidden" name="problemId" value="${searchresult.p_id}">
                                <input type="file" name="pic" accept="image/*" >
                                <input type="submit" class="btn btn-default" value="Προσθήκη φωτογραφίας">
                            </form> 

                        </div>
                    </div>
                </div>
                <%} else { %>
                <div class="alert alert-info center-block" role="alert" style="width: 80%;">
                    Πρέπει να είστε συνδεδεμένος για να μπορείτε να προσθέσετε μια λύση ή φωτογραφία.
                </div>

                <%}%>
            </div>

            <div class="panel-body container-fluid">
                <div class="row">
                    <div class="col-md-6 center-block">

                        <c:if test="${not empty result}">
                            <h3 style="text-align:center"> Λύση </h3>
                            <p class="bg-warning">
                                <c:out value="${searchresult.solution}"/>
                            </p>
                        </c:if>

                    </div>
                </div>
            </div>

            <hr/>
            <div class="panel-body container-fluid">
                <%if (role != null && role.equals("1")) { %>
                <div class="row">
                    <div class="col-md-6 center-block">

                        <c:if test="${not empty result}">
                            <h3 style="text-align:center"> Comment Section </h3>


                            <c:forEach var="comment" items="${comments}">


                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h3 class="panel-title">Comment 
                                            ${comment.commentId} Author: ${comment.authorId} Date: ${comment.createdAt}</h3>
                                    </div>
                                    <div class="panel-body">
                                        ${comment.content}
                                    </div>
                                </div>

                            </c:forEach>
                            <!-- Add comment -->
                            <form class="form-horizontal" action="insertcomment?pid=${searchresult.p_id}" method="post">
                                <div class="form-group">
                                    <label for="inputSearch" class="col-sm-2 control-label">Προσθήκη σχολίου: </label>
                                    <div class="col-sm-10">
                                        <input type="hidden" name="formtype" value="textform" />
                                        <textarea name="commenttext" class="form-control" rows="3"></textarea>
                                    </div>
                                </div>


                                <button type="submit" class="btn  center-block btn-default">Προσθήκη</button>
                            </form>

                        </c:if>

                    </div>
                </div>
                <%} else {%>
                <div class="row">
                    <div class="col-md-6 center-block">

                        <div class="alert alert-info center-block" role="alert" style="width: 80%;">
                            Πρέπει να είστε συνδεδεμένος για να μπορείτε να προβάλετε ή να αποστείλετε σχόλια.
                        </div>

                    </div>
                </div>
                <%}%>
            </div>
        </div>



        <%-- Include nessecary scripts --%>
        <%@ include file="static/endbody.jsp" %>
    </body>
</html>
