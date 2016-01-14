<%-- 
    
    Author     : Alexander
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
        <title>CarMechanic - Προφίλ Χρήστη</title>
    </head>
    <body>
        <%
            //If logged in, get usrname
            String user = null;
            String uName = null;
            String uEmail = "NULL";
            String accType = "NULL";
            String otherInfo = "NULL";
            String birthDate = "NULL";
            String userId = "NULL";
            if (session.getAttribute("user") != null) {
                user = (String) session.getAttribute("user");
                uName = (String) session.getAttribute("uName");
                uEmail = (String) session.getAttribute("uEmail");
                accType = (String) session.getAttribute("accType");
                otherInfo = (String) session.getAttribute("otherInfo");
                birthDate = (String) session.getAttribute("birthDate");
                userId = ""+(int)session.getAttribute("userId");
        %>
        <%@ include file="static/navbarloggedin.jsp" %>
        <% } else { %>
        <%@ include file="static/navbar.jsp" %>
        <% }%>


        <div class="panel panel-default center-block" style="width: 70%;">
             <ol class = "breadcrumb">
                <li><a href = "index.jsp">Αρχική</a></li>
                <li class="active">Προφίλ Χρήστη :
                    <%=uName%>
                </li>

            </ol>
            <div class="panel-body container-fluid">
                <div class="row">
                    <div class="col-md-6 center-block">

                        <h1 style="text-align: center;">Προφίλ Χρήστη</h1>


                    </div>
                </div>
                <div class="row">

                    <div class="col-md-4">
                        <div>
                            <img class="center-block" src="http://placehold.it/200x200">
                        </div>
                        <div class="">
                            <div class="col-sm-3 center-block imgButton">
                            <span class="glyphicon glyphicon-edit" aria-hidden="true"></span>
                            <span onclick="removeProfilePic()" class="pull-right glyphicon glyphicon-remove" aria-hidden="true"></span>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-8">
                        <form class="form-horizontal" action="UserProfile" method="post" enctype="multipart/form-data">
                            <div class="form-group">
                                <label for="inputName" class="col-sm-2 control-label">Όνομα:</label>
                                <div class="col-sm-6">
                                    <input id="nameInput" type="hidden" class="form-control" id="inputName" placeholder="Όνοματεπώνυμο" value="<%=uName%>">
                                    <p id="nameText" class="form-control-static"><%=uName%><span onclick="editField('name')" class="glyphicon glyphicon-edit" aria-hidden="true"></span></p>

                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputEmail3" class="col-sm-2 control-label">Email:</label>
                                <div class="col-sm-6">
                                    <input id="emailInput" type="hidden" class="form-control" id="inputEmail3" placeholder="youremail@example.com" value="<%=uEmail%>">
                                    <p id="emailText" class="form-control-static"><%=uEmail%><span onclick="editField('email')" class="glyphicon glyphicon-edit" aria-hidden="true"></span></p>
                                </div>

                            </div>
                            <div class="form-group">
                                <label for="accountType" class="col-sm-2 control-label">Τύπος Λογαριασμού:</label>
                                <div class="col-sm-6">
                                    <input type="hidden" class="form-control" id="accountType" placeholder="">
                                    <p class="form-control-static"><%=accType%></p>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="otherInfo" class="col-sm-2 control-label">Άλλο:</label>
                                <div class="col-sm-6">
                                    <input id="otherInput" type="hidden" class="form-control" id="otherInfo" placeholder="" value="<%=otherInfo%>">
                                    <p id="otherText" class="form-control-static"><%=otherInfo%><span onclick="editField('other')" class="glyphicon glyphicon-edit" aria-hidden="true"></span></p>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="birthDate" class="col-sm-2 control-label">Ημερομηνία Γέννησης</label>
                                <div class="col-sm-6">
                                    <input id="birthInput" type="hidden" class="form-control" id="birthDate" placeholder="DD/MM/YYYY" value="<%=birthDate%>">
                                    <p id="birthText" class="form-control-static"><%=birthDate%><span  onClick="editField('birth')" class="glyphicon glyphicon-edit" aria-hidden="true"></span></p>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-offset-4 col-sm-8">
                                    <button type="submit" class="btn btn-default">Αποθήκευση</button>
                                </div>
                            </div>
                        </form>

                    </div>


                </div>

                <hr>
                <div class="row">
                    <div class="col-md-6 center-block">

                        <h1 style="text-align: center;">Απεσταλμένα προβλήματα</h1>
                        <table id="problemtable" class="table table-hover">
                            <thead>
                                <th>ID</th><th>Περιγραφή</th><th>Λύση</th><th>Κατάσταση</th>
                            </thead>
                            <tbody>
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
                            </tbody>
                        </table>

                    </div>
                </div>
            </div>
        </div>




        <%-- Include nessecary scripts --%>
        <%@ include file="static/endbody.jsp" %>
    </body>
</html>
