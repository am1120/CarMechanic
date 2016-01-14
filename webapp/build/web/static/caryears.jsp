<%-- 
    Document   : carmodels.jsp
    Created on : Jan 14, 2016, 2:18:36 PM
    Author     : Alexander
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:forEach var="year" items="${years}">
    <option value="${year.key}:${year.value}"><c:out value="${year.value}"/></option>
</c:forEach>