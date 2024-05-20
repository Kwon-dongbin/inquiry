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
<title> 글 제목 </title>
<link rel="stylesheet" type="text/css" href="./css/bootstrap.min_4.5.3.css">
<script src="./js/jquery-3.5.1.min.js" type="text/javascript"></script>
<script src="./js/bootstrap.min_4.5.3.js" type="text/javascript"></script>
</head>
<body>
<header id="main-header" class="py-2 btn-dark text-white">
<div class="container">
<div class="row">
<div class="col-md-6">
<h1> 고객 센터 </h1>
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
<h5> 문의 </h5>
</div>
<div class="card-body">
<table class="table table-hover">
<thead class="thead-light">
<tr>
<th class="text-center" style="width: 20%;"> 글 제목 </th>
<td>${inquiryDTO.inquiry_title}</td>
 
</tr>
<tr >
<th class="text-center" style="vertical-align:top;"> 글 내용 </th>
<td style="height: 200px;">${inquiryDTO.inquiry_content}</td>
</tr>
</thead>
</table>
<table class="table table-hover">
<thead class="thead-light">
<tr>
<th class="text-center" style="width: 20%; vertical-align: top;"> 답변 </th>
<td style="height: 100px;">
    <div id="answer">
        ${inquiryDTO.inquiry_answer}
    </div>
</td>
</tr>
</thead>
</table>
<div class="row justify-content-end">
<div class="col-md-2">
<a href="./InquiryList.in" class="btn btn-primary btn-block"> 글 목록 </a>
</div>
<div class="col-md-2">
<a href="./InquiryUpdate.in?inquiry_num=${inquiryDTO.inquiry_num}" class="btn btn-warning btn-block"> 글 수정 </a>
</div>
<div class="col-md-2">
<a id="answerButton" class="btn btn-warning btn-block"> 글 답변 </a>
</div>
<div class="col-md-2">
<a href="./InquiryDeleteView.in?inquiry_num=${inquiryDTO.inquiry_num}" class="btn btn-danger btn-block"> 글 삭제 </a>
</div>
</div>
</div>
</div>
</div>
</div>
</div>
</section>
<script>
$(document).ready(function() {
    var answer = $("#answer").text().trim();
    var answerButton = $("#answerButton");

    if (answer === "") {
        answerButton.text("글 답변").attr("href", "./InquiryAnswerView.in?inquiry_num=${inquiryDTO.inquiry_num}");
    } else {
        answerButton.text("답변 수정").attr("href", "./InquiryAnswerView.in?inquiry_num=${inquiryDTO.inquiry_num}");
        answerButton.css({"min-width": "60px", "padding-top": "8px", "padding-bottom": "9px", "font-size": "smaller"});
    }
});
window.addEventListener('DOMContentLoaded', (event) => {
    const content = document.querySelector('#inquiryContent');
    const maxLength = 10; // 최대 길이 설정

    // 텍스트의 길이 측정
    const contentLength = content.innerText.length;

    // 최대 길이를 초과하는 경우 줄 바꿈 추가
    if (contentLength > maxLength) {
        content.style.whiteSpace = 'pre-wrap'; // 줄 바꿈 추가
    }
});
</script>
</body>
</html>