<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원 정보 수정</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
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
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            text-align: center;
        }
        h1 {
            color: #333;
            margin-bottom: 20px;
        }
        form {
            display: flex;
            flex-direction: column;
            gap: 10px;
        }
        input[type="text"], input[type="password"], input[type="email"], input[type="tel"] {
            padding: 10px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }
        button {
            padding: 10px;
            border: none;
            border-radius: 4px;
            background-color: #007BFF;
            color: white;
            cursor: pointer;
            transition: background-color 0.2s;
        }
        button:hover {
            background-color: #4cae4c;
        }
        input[readonly] {
            background-color: #e9ecef;
            opacity: 1;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>회원 정보 수정</h1>
        <form action="" method="post">
            <input type="text" name="memberId" value="">
            <input type="password" name="password" value="" required placeholder="비밀번호">
            <input type="text" name="memberName" value="" placeholder="새로운 이름" required>
            <input type="email" name="email" value="" placeholder="새로운 이메일" required>
            <input type="tel" name="mobile" value="" placeholder="새로운 휴대전화 번호" required>
            <button type="submit">업데이트</button>
        </form>
    </div>
</body>
</html>
