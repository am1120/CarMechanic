<%-- 
    Document   : carmodels.jsp
    Created on : Jan 14, 2016, 2:18:36 PM
    Author     : Alexander
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:forEach var="engine" items="${engines}">
    <option value="${engine.key}:${engine.value}"><c:out value="${engine.value}"/></option>
</c:forEach>