<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, inital-scale=1.0">
<title>로그인</title>
<link rel="stylesheet" type="text/css" href="./css/bootstrap.min_4.5.3.css">
<style>
    body {
        background-color: #f8f9fa;
    }
    .login-container {
        max-width: 400px;
        margin: 100px auto;
        background-color: #fff;
        border-radius: 8px;
        padding: 20px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }
    .login-container h2 {
        text-align: center;
        margin-bottom: 30px;
    }
    .form-group {
        margin-bottom: 20px;
    }
    .form-group label {
        font-weight: bold;
    }
    .form-control {
        border-radius: 4px;
    }
    .btn-primary {
        width: 100%;
    }
</style>
</head>
<body>
<div class="login-container">
    <h2>로그인</h2>
    <form action="./login.in" method="post">
        <div class="form-group">
            <label for="username">사용자명:</label>
            <input type="text" class="form-control" id="user_id" name="user_id" required>
        </div>
        <div class="form-group">
            <label for="password">비밀번호:</label>
            <input type="password" class="form-control" id="user_password" name="user_password" required>
        </div>
        <button type="submit" class="btn btn-primary">로그인</button>
    </form>
</div>
</body>
</html>
