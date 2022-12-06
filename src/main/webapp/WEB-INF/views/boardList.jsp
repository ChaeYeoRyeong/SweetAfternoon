<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@page session="true"%>
<c:set var="loginId" value="${sessionScope.user_id}"/>
<c:set var="loginOutLink" value="${loginId=='' ? '/login/login':'/login/logout'}"/>
<c:set var="loginOut" value="${loginId=='' ? '로그인' : '로그아웃'}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>SweetAfternoon</title>
    <link rel="stylesheet" href="<c:url value='/resources/css/menu.css'/>">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
    <style>
        * {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
            font-family: "Noto Sans KR", sans-serif;
        }
        a {
        	box-sizing: border-box;
            color: black;
        }
        button, input {
            border: none;
            outline: none;
        }
        .left_nav {
            position: fixed;
            top: 0;
            left: 0;
            background-color: lightgray;
            width: 225px;
            height: 100%;
            padding: 20px 0;
        }
        .left_nav ol {
            list-style-type: none;
        }
        .left_nav li:first-child {
            margin-top: 50px;
        }
        .left_nav li {
            margin-top: 10px;
        }
        .left_nav li a {
            font-size: 18px;
            font-weight: bold;
        }
        .board-container {
            width: 60%;
            height: 1300px;
            margin: 0 auto;
        }
        table {
            border-collapse: collapse;
            width: 100%;
            border-top: 2px solid black;
        }
        th, td {
            width: 300px;
            text-align: center;
            padding: 10px 13px;
            border-bottom: 1px solid #ddd;
        }
        td{
            color: rgb(53, 53, 53);
            text-align: center;
        }
        .no { width: 150px;}
        .title { width: 50%;}
        td.title {text-align: left;}
        td.writer {text-align: left;}
        td.viewCnt{ text-align: right;}
        td.title:hover {
            text-decoration: underline;
        }
        .paging {
            color: black;
            width: 100%;
            align-items: center;
        }
        .page {
            color: black;
            padding: 6px;
            margin-right: 10px;
        }
        .paging-active {
            background-color: beige;
            border-radius: 5px;
            color: black;
        }
        .paging-container {
            width: 100%;
            height: 70px;
            display: flex;
            margin-top: 50px;
            margin: auto;
        }
        .search-container {
            background-color: lightgoldenrodyellow;
            width: 100%;
            height: 110px;
            border: 1px solid #ddd;
            margin-top: 10px;
            margin-bottom: 30px;
            display: flex;
            justify-content: center;
            align-items: center;
        }
        .search-form {
            height: 37px;
            display: flex;
        }
        .search-option {
            width: 100px;
            height: 100%;
            outline: none;
            margin-right: 5px;
            border: 1px solid #ccc;
            color: gray
        }
        .search-option > option {
            text-align: center;
        }
        .search-input {
            color: gray;
            background-color: white;
            border: 1px solid #ccc;
            height: 100%;
            width: 300px;
            font-size: 15px;
            padding: 5px 8px;
        }
        .search-input::placeholder {
            color: lightgray;
        }
        .search-button {
            /* 메뉴바 검색 버튼 아이콘 */
            width: 20%;
            height: 100%;
            background-color: #5c5c5c;
            color: white;
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 15px;
        }
        .search-button:hover {
            color: darkgray;
        }
        .btn-write {
            background-color: rgb(236, 236, 236); /* Blue background */
            border: none;
            color: black;
            padding: 6px 12px;
            font-size: 16px;
            cursor: pointer;
            border-radius: 5px;
            margin-left: 30px;
        }
        .btn-write:hover {
            text-decoration: underline;
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

<script>
    let msg = "${msg}"
    if(msg=="List_Error") alert("게시물 목록을 가져오는데 실패했습니다. 다시 시도해 주세요.");
    if(msg=="Read_Error") alert("삭제되었거나 없는 게시물입니다.");
    if(msg=="Delete_Error") alert("삭제에 실패하였습니다.");
    if(msg=="Delete_Done") alert("성공적으로 삭제되었습니다.");
    if(msg=="Write_Done") alert("성공적으로 등록되었습니다.");
    if(msg=="Modify_Done") alert("성공적으로 수정되었습니다.");
</script>

<div style="text-align:center">
    <!-- div class="left_nav">
        <ol>
            <li><a href="">공지사항</a></li>
            <li><a href="">1:1 문의</a></li>
            <li><a href="">FAQ</a></li>
        </ol>
    </div -->
    <div class="board-container">
        <table>
            <tr>
                <th class="no">번호</th>
                <th class="title">제목</th>
                <th class="writer">작성자</th>
                <th class="regDate">등록일</th>
                <th class="viewCnt">조회수</th>
            </tr>
            <c:forEach var="boardDto" items="${list}">
                <tr>
                    <td class="no">
                        ${boardDto.board_no}
                    </td>
                    <td class="title">
                        <a href="<c:url value="/board/read${ph.sc.queryString}&board_no=${boardDto.board_no}"/>">
                            <c:out value="${boardDto.board_title}"/>
                        </a>
                    </td>
                    <td class="writer">
                        ${boardDto.board_writer}
                    </td>
                    <c:choose>
						<c:when test="${boardDto.board_reg_date.time >= startOfToday}">
							<td class="regDate"><fmt:formatDate value="${boardDto.board_reg_date}" pattern="HH:mm" type="time"/></td>
						</c:when>
						<c:otherwise>
							<td class="regDate"><fmt:formatDate value="${boardDto.board_reg_date}" pattern="yyyy-MM-dd" type="date"/></td>
						</c:otherwise>
                    </c:choose>
                    <td class="viewCnt">${boardDto.board_view_cnt}</td>
                </tr>
            </c:forEach>
        </table>
        <br>
        <div class="paging-container">
        	<div class="paging">
        		<c:if test="${totalCnt==null || totalCnt==0}">
        			<div> 게시물이 없습니다. </div>
        		</c:if>
        		<c:if test="${totalCnt!=null && totalCnt!=0}">
        			<c:if test="${ph.showPrev}">
        				<a class="page" href="<c:url value="/board/list${ph.sc.getQueryString(ph.beginPage-1)}"/>">&lt;</a>
        			</c:if>
        			<c:forEach var="i" begin="${ph.beginPage}" end="${ph.endPage}">
        				<a class="page ${i==ph.sc.page? "paging-active" : ""}" href="<c:url value="/board/list${ph.sc.getQueryString(i)}"/>">${i}</a>
        			</c:forEach>
        			<c:if test="${ph.showNext}">
        				<a class="page" href="<c:url value="/board/list${ph.sc.getQueryString(ph.endPage+1)}"/>">&gt;</a>
        			</c:if>
        		</c:if>
        	</div>
        </div>
        <div class="search-container">
            <form action="<c:url value="/board/list"/>" class="search-form" method="get">
                <select class="search-option" name="option">
                    <option value="A" ${ph.sc.option=='A' || ph.sc.option== '' ? "selected" : ""}>제목+내용</option>
                    <option value="T" ${ph.sc.option=='T' ? "selected" : ""}>제목</option>
                    <option value="W" ${ph.sc.option=='W' ? "selected" : ""}>작성자</option>
                </select>
                <input type="text" name="keyword" class="search-input" value="${ph.sc.keyword}" placeholder="검색어를 입력해주세요">
                <input type="submit" class="search-button" value="검색">
            </form>
            <button id="writeBtn" class="btn-write" onclick="location.href='<c:url value="/board/write"/>'"><i class="fa fa-pencil"></i>글쓰기<button>
        </div>
    </div>
</div>
</body>
</html>
