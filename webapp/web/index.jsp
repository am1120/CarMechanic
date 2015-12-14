<%-- 
    Author     : Alexander
     
    Home Page Or aka Search Page
--%>

<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%String serverUrl = "http://localhost:8081/searchservlet";%>
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

        %>


        <div class="panel panel-default center-block" style="width: 70%;">

            <div class="panel-body container-fluid">
                <div class="row">
                    <div class="col-md-6 center-block">

                        <h1 style="text-align: center;">Αναζήτηση αυτοκινήτου</h1>


                        <form class="form-horizontal" action="searchservlet" method="post">
                            <div class="form-group">
                                <label for="inputSearch" class="col-sm-2 control-label">Αναζήτηση: </label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control" id="inputSearch" placeholder="Μάρκα & Μοντέλο Αυτοκινήτου">
                                </div>
                            </div>


                            <button type="submit" class="btn  center-block btn-default">Αναζήτηση</button>
                        </form>
                    </div>
                </div>
                <form method="post" action="searchservlet">
                    <div class="row">

                        <div class="col-md-6 center-block">
                            <h2 style="text-align: center;"> Ή </h2>
                            <div class="first-row">

                                <label for="inputSearch" class="col-sm-3 control-label">Κατασκευαστής: </label>

                                <div class="col-sm-3">
                                    <select class="form-control">
                                        <option>1</option>
                                        <option>2</option>
                                        <option>3</option>
                                        <option>4</option>
                                        <option>5</option>
                                    </select>
                                </div>
                                <label for="inputSearch" class="col-sm-3 control-label">Μοντέλο: </label>

                                <div class="col-sm-3">
                                    <select class="form-control">
                                        <option>1</option>
                                        <option>2</option>
                                        <option>3</option>
                                        <option>4</option>
                                        <option>5</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6 center-block">
                            <div style="margin-top: 25px;" class="second-row">
                                <label for="inputSearch" class="col-sm-3 control-label">Έτος κατασκευής: </label>

                                <div class="col-sm-3">
                                    <select class="form-control">
                                        <option>1</option>
                                        <option>2</option>
                                        <option>3</option>
                                        <option>4</option>
                                        <option>5</option>
                                    </select>
                                </div>
                                <label for="inputSearch" class="col-sm-3 control-label">Κωδικός κινητήρα: </label>

                                <div class="col-sm-3">
                                    <select class="form-control">
                                        <option>1</option>
                                        <option>2</option>
                                        <option>3</option>
                                        <option>4</option>
                                        <option>5</option>
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
        <div class="panel panel-default center-block" style="width: 70%;">
            <div class="panel-body container-fluid">
                <div class="row">
                    <div class="col-md-6 center-block">
                        <h1 style="text-align: center;">Αποτελέσματα αναζήτησης</h1>
                        <table class="table table-hover">
                            <th>Col 1</th><th>Col 2</th><th>Col 3</th><th>Col 4</th>
                            <tr><td>Test</td><td>Test</td><td>Test</td><td>Test</td></tr>

                            <tr><td>Test</td><td>Test</td><td>Test</td><td>Test</td></tr>

                            <tr><td>Test</td><td>Test</td><td>Test</td><td>Test</td></tr>

                            <tr><td>Test</td><td>Test</td><td>Test</td><td>Test</td></tr>
                        </table>

                    </div>
                </div>
            </div>
        </div>



        <%-- Include nessecary scripts --%>
        <%@ include file="static/endbody.jsp" %>
    </body>
</html>
