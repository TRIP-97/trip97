<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>내 정보 조회</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .container {
            width: 350px;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            text-align: center;
        }
        h1 {
            color: #333;
            margin-bottom: 20px;
        }
        p {
            line-height: 1.6;
            color: #666;
            margin-bottom: 10px;
            text-align: left;
        }
        .label {
            font-weight: bold;
        }
        button {
            width: 100%;
            padding: 10px;
            margin-top: 10px;
            font-size: 16px;
            cursor: pointer;
            border: none;
            border-radius: 5px;
            color: white;
            background-color: #007BFF;
        }
        button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>내 정보</h1>
        <p><span class="label">아이디:</span></p>
        <p><span class="label">이름:</span> </p>
        <p><span class="label">이메일:</span></p>
        <p><span class="label">휴대전화:</span></p>
        <p><span class="label">당신은 관리자입니다.</span> </p>
        <button onclick="location.href='/'">내 정보 수정</button>
        <button onclick="location.href='/'">홈으로</button>
        
    </div>
</body>
</html>