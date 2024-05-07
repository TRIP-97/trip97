<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원 가입</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 40px;
            padding: 0;
        }
        .container {
            width: 300px;
            margin: auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h2 {
            text-align: center;
            color: #333;
        }
        form {
            display: flex;
            flex-direction: column;
        }
        label {
            margin-bottom: 5px;
            color: #666;
        }
        input[type="text"],
        input[type="email"],
        input[type="password"],
        input[type="tel"] {
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ddd;
            border-radius: 4px;
        }
        input[type="submit"] {
            background-color: #5cb85c;
            color: white;
            padding: 10px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #4cae4c;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>회원 가입</h2>
        <form action="" method="post">
            <label for="memberId">아이디</label>
            <input type="text" id="memberId" name="memberId" required>

            <label for="memberName">이름</label>
            <input type="text" id="memberName" name="memberName" required>

            <label for="password">비밀번호</label>
            <input type="password" id="password" name="password" required>

            <label for="email">이메일</label>
            <input type="email" id="email" name="email" required>

            <label for="mobile">휴대전화</label>
            <input type="tel" id="mobile" name="mobile">

            <label for="isAdmin">관리자 여부</label>
            <input type="checkbox" id="isAdmin" name="isAdmin" value="true">

            <input type="submit" value="가입하기">
        </form>
    </div>
</body>
</html>