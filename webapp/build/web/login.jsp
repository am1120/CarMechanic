<%-- 
    Author     : AAA Team
--%>

<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
         <%-- Include head section --%>
        <%@ include file="static/header.jsp" %>
        <title>CarMechanic - Σύνδεση Χρήστη</title>
    </head>
    <body>
        
        <%@ include file="static/navbar.jsp" %>
        <%
            //invalidate the session if exists
            session = request.getSession(false);
            if (session.getAttribute("user") != null) {
                response.sendRedirect("index.jsp");
            }
            //no session? clear cookies
            Cookie[] cook = request.getCookies();

            if (cook != null) {
                for (int i = 0; i < cook.length; i++) {
                    cook[i].setMaxAge(0);
                    response.addCookie(cook[i]);
                }
            }
        %>
        <div class="container">
            <div class="row">

                <div class="col-sm-6 center-block">
                    <div class="jumbotron">
                        <div class="page-header"><h2> Καλώς ορίσατε! </h2></div>
                        <form method="post" action="login" class="form-horizontal">
                            <div class="form-group">
                                <label for="inputUsername" class="col-sm-2 control-label">Όνομα χρήστη</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="inputUsername" name="uname" placeholder="Username" value="" required />
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputPassword3" class="col-sm-2 control-label">Κωδικός</label>
                                <div class="col-sm-8">
                                    <input type="password" class="form-control" id="inputPassword3" placeholder="Password" name="pass" value="" required />
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-offset-2 col-sm-10">
                                    <div class="checkbox">
                                        <label>
                                            <input type="checkbox"> Να με θυμάσαι
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-offset-4 col-sm-10">
                                    <button type="submit" class="btn btn-default">Σύνδεση</button>
                                    <button type="reset" class="btn btn-default">Επαναφορά</button>
                                </div>
                            </div>
                        </form>
                    </div>

                </div>

            </div>
        </div>
        
        <%-- Include nessecary scripts --%>
        <%@ include file="static/endbody.jsp" %>
    </body>
</html>
