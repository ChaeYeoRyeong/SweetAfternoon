<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
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
	<link rel="stylesheet" href="<c:url value='/resources/css/coffee.css'/>">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"/>
	<script src="https://code.jquery.com/jquery-1.11.3.js"></script>
    <style>
        #submitBtn {
            width: 80%;
            height: 35px;
            background-color: gold;
            border-radius: 3px;
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
<div class="productFinder_list">
    <div class="findWrap">
        <div class="find_frame">
            <form:form modelAttribute="recommendcoffeedto" method="get" action="/coffee/result">
            <div class="find_section find_section01">
                <p class="find_con find_con01">1. 어떤 풍미를 좋아하세요?</p>
                <ul class="find_taste find_taste01">
                    <li class="ft_t01">
                        <dl style="opacity: 1; top: 0px;">
                            <dt>
                                <input type="radio" name="radio1" id="btn1" value="1">
                                <label class="salty" for="btn1"></label>
                            </dt>
                            <dd>짭짤한 맛</dd>
                        </dl>
                    </li>
                    <li class="last ft_t02">
                        <dl style="opacity: 1; top: 0px;">
                            <dt>
                                <input type="radio" name="radio1" id="btn2" value="2">
                                <label class="fruit" for="btn2"></label>
                            </dt>
                            <dd>과일 맛</dd>
                        </dl>
                    </li>
                </ul>
            </div>
            <div class="find_section find_section02">
                <p class="find_con find_con02">2. 어떤 느낌을 좋아하세요?</p>
                <ul class="find_taste find_taste02">
                    <li class="ft2_t01">
                        <dl style="opacity: 1; top: 0px;">
                            <dt>
                                <input type="radio" name="radio2" id="btn3" value="1">
                                <label class="fresh" for="btn3"></label>
                            </dt>
                            <dd>상쾌한 느낌</dd>
                        </dl>
                    </li>
                    <li class="last ft2_t02">
                        <dl style="opacity: 1; top: 0px;">
                            <dt>
                                <input type="radio" name="radio2" id="btn4" value="2">
                                <label class="smooth" for="btn4"></label>
                            </dt>
                            <dd>부드러운 느낌</dd>
                        </dl>
                    </li>
                </ul>
            </div>
            <div class="find_section find_section03">
                <p class="find_con find_con03">3. 어느 정도의 강도를 원하세요?</p>
                <ul class="find_taste find_taste03">
                    <li class="ft3_t01">
                        <dl style="opacity: 1; top: 0px;">
                            <dt>
                                <input type="radio" name="radio3" id="btn5" value="1">
                                <label class="soft" for="btn5"></label>
                            </dt>
                            <dd>은은하고 부드러움</dd>
                        </dl>
                    </li>
                    <li class="last ft2_t02">
                        <dl style="opacity: 1; top: 0px;">
                            <dt>
                                <input type="radio" name="radio3" id="btn6" value="2">
                                <label class="intensity" for="btn6"></label>
                            </dt>
                            <dd>무게감을 주는 강렬함</dd>
                        </dl>
                    </li>
                </ul>
            </div>
            <div class="find_section find_section04">
                <p class="find_con find_con04"></p>
                <ul class="find_taste find_taste04">
                    <li class="ft_t04">
                        <dl style="opacity: 1; top: 0px;">
                            <dt>
                                <input type="submit" method="post" id="submitBtn" value="나와 어울리는 커피는?">
                            </dt>
                        </dl>
                    </li>
                </ul>
            </div>
            </form:form>
        </div>
    </div>
</div>
</body>
</html>