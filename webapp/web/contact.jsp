<%-- 
    Author     : Alexander Patras
     
    Contact
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
        <title>CarMechanic - Επικοινωνία</title>
    </head>
    <%
        //If logged in, get username
        String user = null;
        String uName = null;
        String userId = null;
        if (session.getAttribute("user") != null) {
            user = (String) session.getAttribute("user");
            uName = (String) session.getAttribute("uName");
            userId = "" + (int) session.getAttribute("userId");
    %>
    <%@ include file="static/navbarloggedin.jsp" %>
    <% } else { %>
    <%@ include file="static/navbar.jsp" %>
    <% }%>
    <div class="panel panel-default center-block" style="width: 50%;">
        <ol class = "breadcrumb">
            <li><a href = "index.jsp">Αρχική</a></li>
            <li class="active">Επικοινωνία </li>

        </ol>
        <div class="panel-body container-fluid">
            <c:if test="${empty result}">
                <div class="row heading">
                    <h3  style="text-align:center"> Επικοινωνία </h3>
                    <hr>
                    <form class="form-horizontal" action="SendMessage" method="post">
                        <div class="col-md-6 center-block">
                            <div class="form-group">
                                <label for="contactEmail" class="col-sm-2 control-label">Email</label>
                                <div class="col-sm-8">
                                    <input type="email" name="contactEmail" class="form-control" id="contactEmail" placeholder="Email">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="contactName" class="col-sm-2 control-label">Όνομα</label>
                                <div class="col-sm-8">
                                    <input type="text" name="contactName" class="form-control" id="contactName" placeholder="Όνομα">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="contactSubject" class="col-sm-2 control-label">Θέμα</label>
                                <div class="col-sm-8">
                                    <input type="text" name="contactSubject" class="form-control" id="contactSubject" placeholder="Θέμα">
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6 center-block">
                            <div class="form-group">
                                <label for="contactName" class="col-sm-2 control-label">Μήνυμα</label>
                                <div class="col-sm-8">
                                    <textarea name="contactMessage" class="form-control" rows="3"></textarea>
                                </div>
                            </div>

                        </div>

                        <div class="row">
                            <div class="col-md-3 center-block">
                                <button type="submit" class="center-block btn btn-default">Αποστολή</button></a>
                            </div>
                        </div>
                    </form> 

                </div>
            </c:if>
            <c:if test="${not empty result}">
                <c:if test="${result == "OK"}">
                    <div class="alert alert-success" role="alert">
                        ${resultMessage}
                    </div>
                </c:if>
                <c:if test="${result == "ERROR"}">
                    <div class="alert alert-warning" role="alert">
                        ${resultMessage}
                    </div>   
                </c:if>
            </c:if>
        </div>
    </div>
    <%-- Include nessecary scripts --%>
    <%@ include file="static/endbody.jsp" %>

</html>
