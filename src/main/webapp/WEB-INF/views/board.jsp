<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page session="true"%>
<c:set var="loginId" value="${sessionScope.user_id}"/>
<c:set var="loginOutLink" value="${loginId=='' ? '/login/login' : '/login/logout'}"/>
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
        .container {
            width : 50%;
            margin : auto;
        }
        .writing-header {
            position: relative;
            margin: 20px 0 0 0;
            padding-bottom: 10px;
            border-bottom: 1px solid #323232;
        }
        input {
            width: 100%;
            height: 35px;
            margin: 5px 0px 10px 0px;
            border: 1px solid #e9e8e8;
            padding: 8px;
            background: #f8f8f8;
            outline-color: #e6e6e6;
        }
        textarea {
            width: 100%;
            background: #f8f8f8;
            margin: 5px 0px 10px 0px;
            border: 1px solid #e9e8e8;
            resize: none;
            padding: 8px;
            outline-color: #e6e6e6;
        }
        .frm {
            width:100%;
        }
        .btn {
            background-color: rgb(236, 236, 236);
            border: none;
            color: black;
            padding: 6px 12px;
            font-size: 16px;
            cursor: pointer;
            border-radius: 5px;
        }
        .btn:hover {
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
    let msg = "${msg}";
    if(msg=="Write_Error") alert("게시물 등록에 실패하였습니다. 다시 시도해주세요.");
    if(msg=="Modify_Error") alert("게시물 수정에 실패하였습니다. 다시 시도해 주세요.");
</script>
<div class="container">
    <h2 class="writing-header">게시글 ${mode=="new" ? "작성" : ""}</h2>
    <form id="form" class="frm" action="" method="post">
        <input type="hidden" name="board_no" value="${boardDto.board_no}">
        <input type="text" name="board_title" value="<c:out value='${boardDto.board_title}'/>" placeholder="제목을 입력해주세요." ${mode=="new" ? "" : "readonly='readonly'"}><br>
        <textarea name="board_content" rows="20" placeholder="내용을 입력해주세요." ${mode=="new" ? "" : "readonly='readonly'"}><c:out value='${boardDto.board_content}'/></textarea><br>

        <c:if test="${mode=='new'}">
            <button type="button" id="writeBtn" class="btn btn-write"><i class="fa fa-pencil">글쓰기</i></button>
        </c:if>
        <!--
        <c:if test="${mode!='new'}">
            <button type="button" id="writeNewBtn" class="btn btn-write"><i class="fa fa-pencil"></i>수정</button>
        </c:if>
        -->

        <c:if test="${boardDto.board_writer == loginId}">
            <button type="button" id="modifyBtn" class="btn btn-modify"><i class="fa fa-edit"></i>수정</button>
            <button type="button" id="removeBtn" class="btn btn-remove"><i class="fa fa-trash"></i>삭제</button>
        </c:if>
        <button type="button" id="listBtn" class="btn btn-list"><i class="fa fa-bars"></i>목록</button>
    </form>
</div>

<script>
    $(document).ready(function() {
        let formCheck = function() {
            let form = document.getElementById("form");
            if(form.board_title.value=="") {
                alert("제목을 입력해주세요.");
                form.board_title.focus();
                return false;
            }
            if(form.board_content.value=="") {
                alert("내용을 입력해주세요.");
                form.board_content.focus();
                return false;
            }
            return true;
        }
        $("#writeNewBtn").on("click", function() {
            location.href="<c:url value='/board/write'/>";
        });
        $("#writeBtn").on("click", function(){
            let form = $("#form");
            form.attr("action", "<c:url value='/board/write'/>");
            form.attr("method", "post");
            if(formCheck())
                form.submit();
        });
        $("#modifyBtn").on("click", function() {
            let form = $("#form");
            let isReadOnly = $("input[name=board_title]").attr('readonly');
            // 1. 읽기 상태이면, 수정 상태로 변경
            if(isReadOnly=='readonly') {
                $(".writing-header").html("게시글 수정");
                $("input[name=board_title]").attr('readonly', false);
                $("textarea").attr('readonly', false);
                $("#modifyBtn").html("<i class='fa fa-pencil'></i>등록");
                return;
            }
            // 2. 수정 상태이면, 수정된 내용을 서버로 전송
            form.attr("action", "<c:url value='/board/modify${searchCondition.queryString}'/>");
            form.attr("method", "post");
            if(formCheck())
                form.submit();
        });
        $("#removeBtn").on("click", function(){
            if(!confirm("게시물을 삭제하시겠습니까?")) return;
            let form = $("#form");
            form.attr("action", "<c:url value='/board/remove${searchCondition.queryString}'/>");
            form.attr("method", "post");
            form.submit();
        });
        $("#listBtn").on("click", function(){
            location.href="<c:url value='/board/list${searchCondition.queryString}'/>";
        });
    });
</script>
</body>
</html>
