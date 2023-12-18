<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>    

    <h4>Welcome Page</h4>
    
    <security:authorize access="isAuthenticated()">		<!-- authorize = if문처럼 쓰인다. (로그인이 되어 있으면) -->
    	<security:authentication property="principal" var="authMember"/>
    	${authMember }
    </security:authorize>