<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>로그인 로그 조회</title>
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
            width: 80%;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            text-align: center;
        }
        table {
            border-collapse: collapse;
            width: 100%;
            margin-top: 20px;
        }
        th, td {
            padding: 8px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #4CAF50;
            color: white;
        }
        tr:hover {
            background-color: #f5f5f5;
        }
        input[type="date"], button {
            padding: 8px;
            margin-top: 5px;
            border-radius: 5px;
            border: 2px solid #ccc;
            width: 30%;
        }
        button {
            width: auto;
            padding: 10px 20px;
            font-size: 16px;
            cursor: pointer;
            background-color: #007BFF;
            color: white;
            border: none;
            border-radius: 5px;
            margin-top: 20px;
        }
        button:hover {
            background-color: #0056b3;
        }
        .search-form {
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>로그인 로그 조회</h1>
        <div class="search-form">
            <form action="" method="get">
                <input type="date" name="startDate" required>
                <input type="date" name="endDate" required>
                <button type="submit">검색</button>
            </form>
        </div>
        <table>
            <tr>
                <th>로그 ID</th>
                <th>회원 ID</th>
                <th>로그인 시간</th>
                <th>로그아웃 시간</th>
            </tr>
            <c:forEach var="" items="">
                <tr>
                    <td><c:out value=""/></td>
                    <td><c:out value=""/></td>
                    <td><c:out value=""/></td>
                    <td><c:out value=""/></td>
                </tr>
            </c:forEach>
        </table>
        <button type="button" onclick="location.href='/'">홈으로</button>
    </div>
</body>
</html>
