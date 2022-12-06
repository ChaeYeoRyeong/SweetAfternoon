<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false"%>
<c:set var="loginId" value="${pageContext.request.getSession(false)==null ? '':pageContext.request.session.getAttribute('user_id')}"/>
<c:set var="loginOutLink" value="${loginId=='' ? '/login/login' : '/login/logout'}"/>
<c:set var="loginOut" value="${loginId=='' ? '로그인' : '로그아웃'}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>SweetAfternoon</title>
	<link rel="stylesheet" href="<c:url value='/resources/css/menu.css'/>">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"/>
</head>
<body>
<div id="menu">
	<ul>
		<li id="logo"><a href="<c:url value='/home'/>">SweetAfternoon</a></li>
		<li><a href="<c:url value='${loginOutLink}'/>">${loginOut}</a></li>
		<li><a href="<c:url value='/register/add'/>">회원가입</a></li>
		<li><a href="<c:url value='/board/list'/>">고객센터</a></li>
	</ul>
</div>
<div class="navigation">
    <ul class="sub-menu">
        <li>
            <h4><a href="<c:url value='/coffee/recommend'/>">나와 어울리는 커피</a></h4>
        </li>
    </ul>
</div>
<div style="text-align:center">
	<h1>홈 화면(jsp)</h1>
</div>
</body>
</html>