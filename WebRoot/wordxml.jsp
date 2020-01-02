<%@ page language="java" contentType="text/xml; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<words>
    <c:forEach var="word" items="${words}">
        <word>${word}</word>
    </c:forEach>
</words>

