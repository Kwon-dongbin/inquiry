<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%
    HttpSession sessionmember = request.getSession();
    String userId = (String) sessionmember.getAttribute("user_id");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, inital-scale=1.0">
<title>글 삭제 확인</title>
<link rel="stylesheet" type="text/css" href="./css/bootstrap.min_4.5.3.css">
<script src="./js/jquery-3.5.1.min.js" type="text/javascript"></script>
<script src="./js/inquiry_validity.js" type="text/javascript"></script>
<script src="./js/bootstrap.min_4.5.3.js" type="text/javascript"></script>
</head>
<body>
<header id="main-header" class="py-2 btn-dark text-white">
<div class="container">
<div class="row">
<div class="col-md-6">
<h1>고객 센터</h1>
</div>
<div class="col-md-6 text-right">
    <!-- 여기에 로그인 상태 메시지 또는 로그인 버튼 추가 -->
    <%-- 만약 로그인 상태라면 --%>
    <c:if test="${not empty user_id}">
        <p style="color: white; font-weight: bold;">로그인 중: ${user_id}</p>
    </c:if>
    <%-- 만약 로그인 상태가 아니라면 --%>
    <c:if test="${empty user_id}">
        <a href="./loginView.in" class="btn btn-primary">로그인</a>
    </c:if>
</div>
</div>
</div>
</header>
<section class="py-4 mb-4 bg-light">
</section>
<section id="department">
<div class="container">
<div class="row">
<div class="col-md-12">
<div class="card">
<div class="card-header">
<h5>글 삭제 확인</h5>
</div>
<div class="card-body">
<p>정말로 이 글을 삭제하시겠습니까?</p>
<form action="./InquiryDelete.in" method="post">
  <input type="hidden" name="inquiry_num" value="${param.inquiry_num}">
  <button type="submit" class="btn btn-danger">삭제</button>
  <button type="button" class="btn btn-secondary" onclick="goBack()"> 취소 </button>
</form>
</div>
</div>
</div>
</div>
</div>
</section>
</body>
</html>