<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="row heading">
    <div class="col-md-4 logo center-block">
        <div class="page-header center-block">
            <h1 class='center-block'>CarMechanic <br><small>Car Mechanic</small></h1>
        </div>
    </div>

</div>

<nav class="navbar navbar-default center-block mainnav">
    <div class="container-fluid">

        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>

        </div>

        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li><a href="index.jsp">Αρχική <span class="sr-only">(current)</span></a></li>
                <li><a href="about.jsp">Πληροφορίες</a></li>
                <li><a href="contact.jsp">Επικοινωνία</a></li>

            </ul>

            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Καλώς ορίσατε, <%=uName%> <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="UserProfile?id=<%=userId%>">Προφίλ</a></li>
                        <li role="separator" class="divider"></li>
                        <li>
                            <form action="logout" method="post">
                                <input  id="logoutid" class="btn btn-default center-block" type="submit" value="Αποσύνδεση" >
                            </form>

                    </ul>
                </li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>