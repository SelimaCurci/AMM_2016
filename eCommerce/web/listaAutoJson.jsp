<%-- 
    Document   : listaAutoJson
    Created on : 2-giu-2016, 15.31.20
    Author     : selima
--%>

<%@page contentType="application/json" pageEncoding="UTF-8"%>
<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<json:array>
    <c:forEach var="auto" items="${listaAuto}">
        <json:object>
            <json:property name="nomeAuto" value="${auto.nomeAuto}"/>
            <json:property name="url" value="${auto.urlImmagine}"/>
            <json:property name="quantita" value="${auto.quantita}"/>
            <json:property name="prezzo" value="${auto.prezzoUnitario}"/>
            <json:property name="id" value="${auto.id}"/>
            <json:property name="descrizione" value="${auto.descrizione}"/>
        </json:object>
    </c:forEach>
</json:array>
