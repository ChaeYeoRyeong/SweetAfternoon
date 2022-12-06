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
	<script src="https://code.jquery.com/jquery-1.11.3.js"></script>
	<style>
	    .result {
            background-color: antiquewhite;
            margin: 0 auto;
        }
        .h2 {
            margin-left: 170px;
            line-height: 100px;
        }
        .content {
            max-width: 900px;
            margin: 0 auto;
        }
        .content article {
            padding: 15px;
            text-align: center;
        }
        .content article img {
            width: auto;
            height: auto;
            max-width: 450px;
            max-height: 470px;

        }
        .content article div h2 {
            font-size: 25px;
            margin-bottom: 15px;
        }
        .content article div p {
            border-top: 1px solid #333;
            padding-top: 15px;
            width: 45%;
            margin:0 auto;
        }
	</style>
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
<div class="result">
    <h2 class="h2">나와 어울리는 커피
</div>
<c:forEach var="recommendcoffeedto" items="${list}">
<section class="content">
    <article>
        <div>
            <img src="https://sweetafternoon.s3.ap-northeast-2.amazonaws.com/${recommendcoffeedto.coffeeImg}">
        </div>
    <article>
    <article>
        <div>
            <h2>${recommendcoffeedto.coffee}</h2>
            <p>${recommendcoffeedto.description}</p>
        </div>
    </article>
</section>
</c:forEach>
</body>
</html>