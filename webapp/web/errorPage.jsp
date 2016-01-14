<%-- 
    Author     : Alexander Patras
     
    Home Page Or aka Search Page
--%>
<%@page isErrorPage="true" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <%-- Include head section --%>
        <%@ include file="static/header.jsp" %>
        <title>CarMechanic - Error Page</title>
    </head>
    <%
        //If logged in, get username
        String user = null;
        String uName = null;
        String userId = "4";
       
        if (session.getAttribute("user") != null) {
            user = (String) session.getAttribute("user");
            uName = (String) session.getAttribute("uName");
            userId = ""+(int) session.getAttribute("userId");
            %>
    <%@ include file="static/navbarloggedin.jsp" %>
    <% } else { %>
    <%@ include file="static/navbar.jsp" %>
    <% }%>

    <div class="panel panel-default center-block" style="width: 50%;">
         <ol class = "breadcrumb">
             <li class="active"><a href="index.jsp">Πίσω στην Αρχική</a></li>
                
            </ol>
        <div class="panel-body container-fluid">
            <div class="row">
                 <div class="alert alert-danger center-block" role="alert" style="width: 80%;">
                        Oops! Κάτι πήγε στραβά! Έχουμε ενημερωθεί και θα διορθωθεί σύντομα! Ευχαριστούμε για την κατανόηση!
                    </div>
            </div>
            
        </div>
    </div>
  

    <%-- Include nessecary scripts --%>
    <%@ include file="static/endbody.jsp" %>

</html>
