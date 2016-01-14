<%-- 
    Author     : Alexander Patras
     
   Car View
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
        <title>CarMechanic - Προβολή Αυτοκινήτου</title>
    </head>
    <body>

        <%
            //If logged in, get usrname
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

        <div class="panel panel-default center-block" style="width: 50%;">
            <ol class = "breadcrumb">
                <li><a href = "index.jsp">Home</a></li>
                <li class="active">Car :
                    <c:if test="${not empty result}">
                        ${carinfo.maker} ${carinfo.model} ${carinfo.year} ${carinfo.engine}       
                    </c:if>
                </li>

            </ol>
            <div class="panel-body container-fluid">
                <div class="row">
                    <div class="col-md-6 center-block">
                        <h1 style="text-align: center;">Πληροφορίες μοντέλου</h1>
                        <div class="col-md-3 pull-left">
                            <a href="index.jsp"><button type="button" class="btn  center-block btn-default">Πίσω</button>
                            </a>
                        </div>
                        <div class="col-md-6">
                            <div class="panel panel-default">
                                <div class="panel-body">
                                    <c:if test="${not empty result}">
                                        ${carinfo.maker} ${carinfo.model} ${carinfo.year} ${carinfo.engine}       
                                    </c:if>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-3">

                            <img src="img/image.png" alt=""/>
                        </div>
                    </div>
                </div>

                <%if (role != null && role.equals("1")) { %>
                <div class="row">
                    <div class="col-md-6 center-block">
                        <div class="center-block col-md-3">
                            <a href="insertproblem?type=solved&mid=${carinfo.model_id}"><button action="insertproblem" type="button" class="btn   btn-default">Προσθήκη Προβλήματος</button></a>
                        </div>

                    </div>
                </div>
                <% }%>

            </div>
            <hr/>
            <div class="panel-body container-fluid">
                <div class="row center-block" style="width: 75%">
                    <% boolean first = true; %>
                    <!-- Nav tabs -->
                    <ul class="nav nav-tabs" role="tablist">
                        <c:forEach items="${searchresult}" var="entry">

                            <li role="presentation" 
                                <% if (first) {
                                        first = false;
                                %>class="active"<%}%>>
                                <a href="#${entry.value.name}" aria-controls="${entry.value.name}" role="tab" data-toggle="tab">${entry.value.name}</a>
                            </li>

                        </c:forEach>
                    </ul>
                    <% first = true;%>
                    <!-- Tab panes -->
                    <div class="tab-content">
                        <c:forEach items="${searchresult}" var="entry2">

                            <div role="tabpanel" class="tab-pane 
                                 <% if (first) {
                                          first = false; %>
                                 active <%}%>" id="${entry2.value.name}">


                                <div class="col-md-6 center-block">
                                    <h3 style="text-align: center;">Προβλήματα ${entry2.value.name}</h3>
                                    <table id="problemtable" class="table table-hover">
                                        <thead>
                                        <th>ID</th><th>Περιγραφή</th><th>Λύση</th><th>Κατάσταση</th>
                                        </thead>
                                        <tbody>


                                            <c:forEach var="problem" items="${entry2.value.problems}">
                                                <tr>
                                                    <td>${problem.p_id}</td>
                                                    <td>${problem.description}</td>
                                                    <td>${problem.solution}</td>
                                                    <td>${problem.status}</td>
                                                    <td><a href="problemview?s=${problem.p_id}">Details</a></td>
                                                </tr>


                                            </c:forEach>

                                            <%-- <tr><td>Test</td><td>Test</td><td>Test</td><td>Test</td></tr> --%>
                                        </tbody>
                                    </table>

                                </div>
                            </div>

                        </c:forEach> 
                    </div>

                </div>
            </div>
        </div>



        <%-- Include nessecary scripts --%>
        <%@ include file="static/endbody.jsp" %>
    </body>
</html>
