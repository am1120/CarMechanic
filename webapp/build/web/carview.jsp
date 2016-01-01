<%-- 
    Author     : Alexander Patras
     
    Home Page Or aka Search Page
--%>

<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%String serverUrl = "http://localhost:8081/searchservlet";%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<html>
    <head>
        <%-- Include head section --%>
        <%@ include file="static/header.jsp" %>
        <title>CarMechanic - Αρχική</title>
    </head>
    <body>

        <%
            //If logged in, get usrname
            String user = null;
            String uName = null;
            if (session.getAttribute("user") != null) {
                user = (String) session.getAttribute("user");
                uName = (String) session.getAttribute("uName");
        %>
        <%@ include file="static/navbarloggedin.jsp" %>
        <% } else { %>
        <%@ include file="static/navbar.jsp" %>
        <% }%>





        <sql:setDataSource var="snapshot" driver="com.mysql.jdbc.Driver"
                           url="jdbc:mysql://localhost/car_mechanic"
                           user="root"  password=""/>

        <sql:query dataSource="${snapshot}" var="result">
            SELECT * from car_maker;
        </sql:query>

        <sql:query dataSource="${snapshot}" var="resultmodel">
            SELECT * from car_model;
        </sql:query>



        <div class="panel panel-default center-block" style="width: 70%;">

            <div class="panel-body container-fluid">
                <div class="row">
                    <div class="col-md-6 center-block">
                       <h1 style="text-align: center;">Πληροφορίες μοντέλου</h1>
                        
                    </div>
                </div>
                
            </div>
        </div>
        <div class="panel panel-default center-block" style="width: 70%;">
            <div class="panel-body container-fluid">
                <div class="row">
                    <div class="col-md-6 center-block">
                        <h1 style="text-align: center;">Προβλήματα</h1>
                        <table class="table table-hover">
                            <th>ID</th><th>Περιγραφή</th><th>Λύση</th><th>Κατάσταση</th>
                                <c:if test="${not empty result}">
                                 
                                    <c:forEach var="problem" items="${searchresult}">
                                    <tr>
                                        <td>${problem.p_id}</td>
                                        <td>${problem.description}</td>
                                        <td>${problem.solution}</td>
                                        <td>${problem.status}</td>
                                        <td><a href="problemview?s=${problem.p_id}">Details</a></td>
                                    </tr>


                                </c:forEach>
                            </c:if>
                            <%-- <tr><td>Test</td><td>Test</td><td>Test</td><td>Test</td></tr> --%>

                        </table>

                    </div>
                </div>
            </div>
        </div>



        <%-- Include nessecary scripts --%>
        <%@ include file="static/endbody.jsp" %>
    </body>
</html>
