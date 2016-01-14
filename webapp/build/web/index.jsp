<%-- 
    Author     : Alexander Patras
     
    Home Page Or aka Search Page
--%>
<%@ page errorPage="errorPage.jsp" %>
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

    <sql:setDataSource var="snapshot" driver="com.mysql.jdbc.Driver"
                       url="jdbc:mysql://localhost/car_mechanic"
                       user="root"  password=""/>

    <sql:query dataSource="${snapshot}" var="result">
        SELECT * from car_maker;
    </sql:query>





    <div class="panel panel-default center-block" style="width: 50%;">
        <ol class = "breadcrumb">
            <li class="active">Αρχική</li>

        </ol>
        <div class="panel-body container-fluid">
            <div class="row">
                <div class="col-md-6 center-block">

                    <h1 style="text-align: center;">Αναζήτηση αυτοκινήτου</h1>


                    <form class="form-horizontal" action="searchservlet" method="post">
                        <div class="form-group">
                            <label for="inputSearch" class="col-sm-2 control-label">Αναζήτηση: </label>
                            <div class="col-sm-10">
                                <input type="hidden" name="formtype" value="textform" />
                                <input type="text" name="searchString" class="form-control" id="inputSearch" placeholder="Μάρκα & Μοντέλο Αυτοκινήτου">
                            </div>
                        </div>


                        <button type="submit" class="btn  center-block btn-default">Αναζήτηση</button>
                    </form>
                </div>
            </div>
            <form method="post" action="searchservlet">
                <div class="row">
                    <input type="hidden" name="formtype" value="selectform" />
                    <div class="col-md-12 center-block">
                        <h2 style="text-align: center;"> Ή </h2>
                        <div class="first-row">

                            <label for="inputSearch" class="col-sm-2 control-label">Κατασκευαστής: </label>

                            <div class="col-sm-4">
                                <select name="maker" class="form-control" onchange="fetchModels(value)">
                                    <option id="makerTip" value="%:%">Επιλέξτε Κατασκευαστή</option>
                                    <c:forEach var="row" items="${result.rows}">
                                        <option value="${row.m_id}:${row.maker}"><c:out value="${row.maker}"/></option>
                                    </c:forEach>
                                </select>
                            </div>
                            <label for="inputSearch" class="col-sm-2 control-label">Μοντέλο: </label>

                            <div class="col-sm-4">
                                <select id="modelSelect" name="model" class="form-control" onchange="fetchYears(value)">
                                    <option id="modelTip" value="%:%">Επιλέξτε Κατασκευαστή</option>

                                </select>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12 center-block">
                        <div style="margin-top: 25px;" class="second-row">
                            <label for="inputSearch" class="col-sm-2 control-label">Έτος κατασκευής: </label>
                            <div class="col-sm-4">
                                <select id="yearSelect" name="year" class="form-control" onchange="fetchEngine(value)">
                                    <option id="yearTip" value="%:%">Επιλέξτε Κατασκευαστή</option>
                                </select>
                            </div>
                            <label for="inputSearch" class="col-sm-2 control-label">Κωδικός κινητήρα: </label>

                            <div class="col-sm-4">
                                <select id="engineSelect" name="engine" class="form-control">
                                    <option id="engineTip" value="%:%">Επιλέξτε Κατασκευαστή</option>
                                </select>
                            </div>
                        </div>

                    </div>

                    <div class="form-group center-block">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" class="btn  center-block btn-default">Αναζήτηση</button>
                        </div>
                    </div>

                </div>

            </form>
        </div>
    </div>
    <div class="panel panel-default center-block" style="width: 50%;">
        <div class="panel-body container-fluid">
            <div class="row">
                <div class="col-md-12 center-block">
                    <h1 style="text-align: center;">Αποτελέσματα αναζήτησης</h1>
                    <table class="table table-hover">
                        <th>Κατασκευαστής</th><th>Μοντέλο</th><th>Έτος</th><th>Κινητήρας</th>
                            <c:if test="${not empty result}">

                            <c:forEach var="car" items="${searchresult}">
                                <tr>
                                    <td>${car.maker}</td>
                                    <td>${car.model}</td>
                                    <td>${car.year}</td>
                                    <td>${car.engine}</td>
                                    <td><a href="carview?s=${car.model_id}">Problems</a></td>
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

</html>
