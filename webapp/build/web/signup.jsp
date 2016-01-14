<%-- 
    Author     : AAA Team
--%>

<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
         <%-- Include head section --%>
        <%@ include file="static/header.jsp" %>
        <title>CarMechanic - Εγγραφή Χρήστη</title>
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
                
                <c:if test="${result == "ERROR"}">
                    <div class="alert alert-warning" role="alert">
                        ${resultMessage}
                    </div>   
                </c:if>
           
                <div class="col-sm-8 center-block">
                    <div class="jumbotron">
                        <div class="page-header"><h2> Καλώς ορίσατε! </h2></div>
                        <form method="post" action="signup" class="form-horizontal">
                            <div class="form-group">
                                <label for="inputUsername" class="col-sm-4 control-label">Αναγνωριστικό </label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="inputUsername" name="username" placeholder="Username" value="" required />
                                </div>
                            </div>
                             <div class="form-group">
                                <label for="inputName" class="col-sm-4 control-label">Όνομα</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="inputName" name="uname" placeholder="Name" value="" required />
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputPassword" class="col-sm-4 control-label">Κωδικός</label>
                                <div class="col-sm-8">
                                    <input type="password" class="form-control" id="inputPassword" placeholder="Password" name="pass" value="" required />
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputPasswordVerify" class="col-sm-4 control-label">Επαλήθευση Κωδικού</label>
                                <div class="col-sm-8">
                                    <input type="password" class="form-control" id="inputPasswordVerify" placeholder="Password" name="passVerify" value="" required />
                                </div>
                            </div>
                             <div class="form-group">
                                <label for="inputEmail" class="col-sm-4 control-label">Email</label>
                                <div class="col-sm-8">
                                    <input type="email" class="form-control" id="inputEmail" placeholder="youremail@example.com" name="email" value="" required />
                                </div>
                            </div>
                            
                            <div class="form-group">
                                <div class="col-sm-offset-4 col-sm-10">
                                    <button type="submit" class="btn btn-default">Εγγραφή</button>
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
