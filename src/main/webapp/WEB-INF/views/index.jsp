<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Main Page</title>
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
        form, .info {
            display: flex;
            flex-direction: column;
            gap: 10px;
        }
        input[type="text"], input[type="password"], button, a {
            padding: 10px;
            margin: 5px;
            border-radius: 5px;
            border: 2px solid #ccc;
        }
        button, a {
            color: white;
            background-color: #007BFF;
            text-decoration: none;
            border: none;
            cursor: pointer;
        }
        button:hover, a:hover {
            background-color: #0056b3;
        }
        a {
            text-align: center;
            display: block;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Main Page</h1>

                <form action="" method="post">
                    <input type="text" id="userId" name="memberId" placeholder="ID" required>
                    <input type="password" name="password" placeholder="Password" required>
                    <button type="submit">Login</button>
                    <a href="">회원가입</a>
                </form>

                <div class="info">
                   님 (), 로그인하였습니다.<br/>
                    <a href="">내 정보 조회</a>
                    <a href="">로그인 로그 보기</a>
                    <a href="">로그아웃</a>
                </div>

    </div>
</body>
</html>
