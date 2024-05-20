<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> 글 쓰기 </title>
</head>
<body>
<c:if test="${inquiryDTO.inquiry_num != 0}"></c:if>
<script type="text/javascript">
alert("답변 글을 등록하였습니다.");
location.href="./InquirySelect.in?inquiry_num=${inquiryDTO.inquiry_num}"
</script>
</body>
</html>