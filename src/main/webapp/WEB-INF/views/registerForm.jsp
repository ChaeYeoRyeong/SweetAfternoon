<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.net.URLDecoder"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
    <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <style>
        * { box-sizing:border-box; }
        form {
            width:400px;
            height:600px;
            display : flex;
            flex-direction: column;
            align-items:center;
            position : absolute;
            top:50%;
            left:50%;
            transform: translate(-50%, -50%) ;
            border: 1px solid rgb(89,117,196);
            border-radius: 10px;
        }
        .input-field {
            width: 360px;
            height: 40px;
            border : 1px solid rgb(89,117,196);
            border-radius:5px;
            padding: 0 10px;
            margin-bottom: 10px;
        }
        .idWrap {
            width: 360px;
            height: 40px;
            margin-bottom: 10px;
            display: inline-block;
        }
        .idWrap .input-field1 {
            width: 270px;
            padding-left: 10px;
            border : 1px solid rgb(89,117,196);
            border-radius:5px;
        }
        .idWrap .input-field2 {
            width: 80px;
            border : 1px solid rgb(89,117,196);
            border-radius:5px;
        }
        label {
            width:360px;
            height:30px;
            margin-top :4px;
        }
        button {
            background-color: rgb(89,117,196);
            color : white;
            width:360px;
            height:50px;
            font-size: 17px;
            border : none;
            border-radius: 5px;
            margin : 20px 0 30px 0;
        }
        .title {
            font-size : 50px;
            margin: 40px 0 30px 0;
        }
        .msg {
            height: 30px;
            text-align:center;
            font-size:16px;
            font-weight: bold;
            color:red;
            margin-bottom: 20px;
        }
    </style>
    <title>회원가입</title>
</head>
<body>
    <form method="post" action="<c:url value='/register/add'/>" class="userjoin" onsubmit="return formCheck(this);">
        <div class="title">회원가입</div>
        <div id="msg" class="msg"><form:errors path="user_id"/></div>
            <label for="user_id">아이디</label>
            <div class="idWrap">
                <input class="input-field1" type="text" id="user_id" name="user_id" placeholder="5~10자리의 영어, 숫자의 조합">
                <input class="input-field2" type="button" id="idCheck" name="idCheck" value="중복 체크">
            </div>
            <label for="user_pwd">비밀번호</label>
            <input class="input-field" type="password" id="user_pwd" name="user_pwd" placeholder="5~12자리의 소문자, 대문자, 숫자의 조합">
            <label for="user_pwd2">비밀번호 확인</label>
            <input class="input-field" type="password" id="user_pwd2" name="user_pwd2" placeholder="5~12자리의 소문자, 대문자, 숫자의 조합">
            <label for="user_name">이름</label>
            <input class="input-field" type="text" id="user_name" name="user_name" placeholder="아이유">
            <label for="user_email">이메일</label>
            <input class="input-field" type="text" id="user_email" name="user_email" placeholder="iu@naver.com">
            <label for="user_birth">생년월일</label>
            <input class="input-field" type="date" name="user_birth"  placeholder="yyyyMMdd">
        <button type="submit">회원 가입</button>
    </form>
    <script>
     $('.userjoin').on('submit',()=>{
            let user_id = $("#user_id").val();
            let user_pwd = $("#user_pwd").val();
            let user_pwd2 = $("#user_pwd2").val();
            let user_email = $("#user_email").val();
            let user_name = $("#user_name").val();

            let idReg = /^[a-z0-9]{5,10}$/;
            let pwdReg = /^[a-zA-Z0-9]{5,12}$/;
            let emailReg = /^[A-Za-z0-9_]*[@]{1}[A-Za-z0-9]*[.]{1}[A-Za-z]{3}$/;
            let nameReg = /^[가-힣]{2,6}$/;

            if(!idReg.test(user_id)) {
                $("#msg").text("아이디는 영소문자, 숫자 조합의 5~10글자로 입력해야 합니다.");
                $("#user_id").focus();
                $("#msg").css({"color": "#FA3E3E"})
                return false;
            }

            if(!pwdReg.test(user_pwd)) {
                $("#msg").text("비밀번호는 영대소문자, 숫자 조합의 5~12글자로 입력해야 합니다.");
                $("#user_pwd").focus();
                $("#msg").css({"color": "#FA3E3E"})
                return false;
            }

            if(!nameReg.test(user_name)) {
                $("#msg").text("이름은 2~6글자의 한글로 입력해야 합니다.");
                $("#user_name").focus();
                $("#msg").css({"color": "#FA3E3E"})
                return false;
            }

            if(!emailReg.test(user_email)) {
                $("#msg").text("이메일 양식을 확인해주세요.(ex. hong@naver.com)");
                $("#user_email").focus();
                $("#msg").css({"color": "#FA3E3E"})
                return false;
            }

        })

        function formCheck(frm) {
            let msg ='';
            if(frm.user_pwd.value != frm.user_pwd2.value) {
                setMessage('비밀번호가 일치하지 않습니다.', frm.user_pwd2);
                return false;
            }
            if(frm.user_birth.value=="") {
                setMessage('생년월일을 선택해주세요.', frm.user_birth);
                return false;
            }
            return true;
        }
        function setMessage(msg, element){
            document.getElementById("msg").innerHTML = `<i class="fa fa-exclamation-circle"> ${'${msg}'}</i>`;
            if(element) {
                element.select();
            }
        }
    </script>
    <script type="text/javascript">
        $('#idCheck').click(function() {
            let user_id = $("#user_id").val();

        $.ajax({
            type : "POST",
            data : user_id,
            url : "idCheck.do",
            dataType : "json",
            contentType: "application/json; charset=UTF-8",
            success : function(result) {
                if(result == 1) {
                    $("#msg").text("이미 사용중인 아이디입니다.");
                    $("#msg").css({
                        "color": "#FA3E3E"
                    })
                } else {
                    $("#msg").text("사용 가능한 아이디입니다.");
                    $("#msg").css({
                        "color": "#2c2ff8"
                    })
                }
            },
            error : function() {
                alert("서버 요청 실패");
            }
        });
        });
    </script>
</body>
</html>