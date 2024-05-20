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
<title> 고객 센터 </title>
<link rel="stylesheet" type="text/css" href="./css/bootstrap.min_4.5.3.css">
<script src="./js/jquery-3.5.1.min.js" type="text/javascript"></script>
<script src="./js/bootstrap.min_4.5.3.js" type="text/javascript"></script>
<style>
.table th.sticky {
	position: sticky;
    top: 0;
    z-index: 999;
    background-color: white;
}
.table th:nth-child(1) {
    width: 15%; /* 첫 번째 셀 너비 */
}

.table th:nth-child(2) {
    width: 35%; /* 두 번째 셀 너비 */
}

.table th:nth-child(3) {
    width: 20%; /* 세 번째 셀 너비 */
}
.table th:nth-child(4) {
    width: 20%; /* 세 번째 셀 너비 */
}
.table th:nth-child(5) {
    width: 10%; /* 세 번째 셀 너비 */
}

</style>
<script>
$(document).ready(function() {
    // 버튼을 클릭했을 때 해당 글 내용으로 이동
    $('.title').click(function() {
        var inquiryNum = $(this).data('inquiry-num'); // 클릭한 버튼의 글 번호 가져오기
        window.location.href = "./InquirySelect.in?inquiry_num=" + inquiryNum; // 해당 글 내용으로 이동
    });

    // 글 제목이 너무 길 경우 최대 글자수 제한
    var max_length = 30; // 최대 글자수
    $('.title').each(function() {
        var text = $(this).text();
        if (text.length > max_length) {
            $(this).text(text.substring(0, max_length) + '...'); // 넘어가는 부분을 ...으로 대체
        } else {
        	$(this).text(text);
        }
    });
    $('.btn-write').click(function() {
        // 로그인 상태 확인
        var loggedIn = "${not empty user_id}";
        if (!loggedIn) {
            alert("로그인 해 주세요");
            return false; // 클릭 이벤트 중단
        }
    });
});


</script>
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
<tr class="text-center">
<th> 글 번호 </th> <th> 글 제목 </th> <th>작성자</th> <th> 등록일 </th> <th> 답글 </th>
</tr>
</thead>
<tbody>
<c:forEach var="arrayList" items="${arrayList}" varStatus="loop">
<tr class="text-center">
    <td>${listSize - loop.index}</td> <!-- 역순으로 번호 부여 -->
    <td>
        <button class="btn btn-link title" data-inquiry-num="${arrayList.inquiry_num}">
            ${arrayList.inquiry_title}
        </button>
    </td>
    <td>${arrayList.user_id}</td>
    <td>${arrayList.inquiry_date}</td>
    <td>
            <c:choose>
                <c:when test="${arrayList.inquiry_status == '1'}">
                    O
                </c:when>
                <c:when test="${arrayList.inquiry_status == '0'}">
                    X
                </c:when>
                <c:otherwise>
                    Unknown <!-- null 또는 비정상적인 값일 경우 -->
                </c:otherwise>
            </c:choose>
        </td>
</tr>
</c:forEach>

<c:if test="${empty arrayList}">
<tr>
<td colspan="4"> 문의 글이 없습니다. </td>
</tr>
</c:if>
</tbody>
</table>
<div>
<a href="./InquiryWriteView.in" class="btn btn-success btn-block btn-write">글 쓰기</a>
</div>
</div>
</div>
</div>
</div>
</div>
</section>
</body>
</html>