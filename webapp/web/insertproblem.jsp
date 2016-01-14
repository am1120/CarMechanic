<%-- 
    Author     : Alexander Patras
     
    Insert Problem
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
        <title>CarMechanic - Προσθήκη προβλήματος</title>
    </head>
    <body>

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
        <div class="panel panel-default center-block" style="width: 70%;">
            <form class="form-horizontal" action="insertproblem" method="post"  enctype="multipart/form-data">

                <ol class = "breadcrumb">
                    <li><a href = "index.jsp">Αρχική </a></li>
                    <li><a href="carview?s=${carinfo.model_id}">Car :
                            <c:if test="${not empty result}">
                                ${carinfo.maker} ${carinfo.model} ${carinfo.year} ${carinfo.engine}
                                <input type="hidden" name="modelId" value="${carinfo.model_id}">
                            </c:if>
                        </a>
                    </li>
                    <li class="active">Προσθήκη Νέου Προβλήματος</li>
                </ol>
                <div class="panel-body container-fluid">
                    <div class="row">
                        <div class="col-md-6 center-block">


                            <h3 style="text-align:center"> Περιγραφή </h3>

                            <textarea name="descriptiontext" class="form-control" rows="3"></textarea>

                        </div>

                    </div>

                </div>

                <div class="panel-body container-fluid">
                    <div class="row">
                        <div class="col-md-6 center-block">


                            <h3 style="text-align:center"> Φωτογραφίες </h3>
                            <div id="small-img" class="col-xs-12 col-sm-12 col-md-12 col-lg-12 center">
                                <ul>

                                </ul>
                            </div>


                        </div>
                    </div>

                    <div class="panel-body container-fluid">
                        <div class="row">
                            <div class="col-md-6 center-block">


                                <input type="file" name="pic" accept="image/*" >

                            </div>
                        </div>
                    </div>
                </div>

                <div class="panel-body container-fluid">
                    <div class="row">
                        <div class="col-md-6 center-block">


                            <h3 style="text-align:center"> Λύση </h3>

                            <textarea name="solutiontext" class="form-control" rows="3"></textarea>

                        </div>

                    </div>
                    <div class="row">
                        <div class="col-md-3 center-block">
                            <a href="insertproblem"><button type="submit" class="center-block btn   btn-default">Αποθήκευση</button></a>
                        </div>
                    </div>
                </div>



            </form>   
        </div>



        <%-- Include nessecary scripts --%>
        <%@ include file="static/endbody.jsp" %>
    </body>
</html>
