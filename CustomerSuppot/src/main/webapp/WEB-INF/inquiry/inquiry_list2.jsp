<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Comment System</title>
<style type="text/css">
* {
	padding: 0;
	margin: 0;
	color: #333;
}

ul {
	list-style: none;
}

#container {
	padding: 30px 20px;
}

h1 {
	font-size: large;
	border-left: 10px solid #7BAEB5;
	border-bottom: 1px solid #7BAEB5;
	padding: 10px;
	width: auto;
}

#comment_write {
	padding: 20px 15px;
	border-bottom: 1px solid #7BAEB5;
}

#comment_write label {
	display: inline-block;
	width: 80px;
	font-size: 14px;
	font-weight: bold;
	margin-bottom: 10px;
}

#comment_write input[type='text'], #comment_write textarea {
	border: 1px solid #ccc;
	vertical-align: middle;
	padding: 3px 10px;
	font-size: 12px;
	line-height: 150%;
}

#comment_write textarea {
	width: 380px;
	height: 90px;
}

.comment_item {
	font-size: 13px;
	color: #333;
	padding: 15px;
	border-bottom: 1px dotted #ccc;
	line-height: 150%;
}

.comment_item .writer {
	color: #555;
	line-height: 200%;
}

.comment_item .writer input {
	vertical-align: middle;
}

.comment_item .writer .name {
	color: #222;
	font-weight: bold;
	font-size: 14px;
}
</style>

<script src="js/jquery-3.5.1.min.js" type="text/javascript"></script>
<script type="text/javascript">
	$(function() {
		// 기본 댓글 목록 불러오기
		$.ajax({
			url : "CommentList.co",
			type : "GET",
			success : function(data) {
				$("#comment_list").html(data);
			},
			error : function() {
				alert("댓글 목록을 불러오는데 실패하였습니다. 잠시후에 다시 시도해 주세요.");
			}
		});

		
		// 댓글 내용 저장 이벤트
		$("#comment_form").submit(function(event) {
			event.preventDefault(); // 기본 이벤트 제거

			var formData = $(this).serialize(); // 폼 데이터 직렬화

			// AJAX를 통한 댓글 추가
			$.ajax({
				url : "CommentInsert.co",
				type : "POST",
				data : formData,
				success : function() {
					// 댓글 추가 후 목록 다시 불러오기
					$.ajax({
						url : "CommentList.co",
						type : "GET",
						success : function(data) {
							$("#comment_list").html(data);
							$("#comment_content").val(""); // input 요소의 값 비우기
							$("#user_id").val(""); // input 요소의 값 비우기
						},
						error : function() {
							alert("댓글 목록을 다시 불러오는데 실패하였습니다.");
						}
					});
				},
				error : function() {
					alert("댓글 작성에 실패하였습니다. 잠시 후에 다시 시도해 주세요.");
				}
			});
		});

		
		// 삭제 버튼 클릭 이벤트
		$(document).on("click", ".delete_btn", function() {
			//var comment_num = $(this).parents(".comment_item").val();
			var comment_num = $(this).closest(".comment_item").val();
			console.log(comment_num)
			// AJAX를 통한 댓글 삭제
			$.ajax({
				url : "commentDelete.jsp",
				type : "POST",
				data : {
					comment_num : comment_num
				},
				success : function() {
					// 삭제 후 목록 다시 불러오기
					$.ajax({
						url : "commentList.jsp",
						type : "GET",
						success : function(data) {
							$("#comment_list").html(data);
						},
						error : function() {
							alert("댓글 목록을 다시 불러오는데 실패하였습니다.");
						}
					});
				},
				error : function() {
					alert("댓글 삭제에 실패하였습니다. 잠시 후에 다시 시도해 주세요.");
				}
			});
		});
	});
</script>
</head>
<body>
	<div id="container">
		<h1>댓글 시스템</h1>
		<div id="comment_write">
			<form id="comment_form">
				<div>
					<label for="user_name">작성자</label>
					<input type="text" name="user_id" id="user_id" required>
					<p id="user_id"><?php echo $_SESSION['민성님이 작성하시는 세션명 user_id']; ?>
					</p>
				</div>
				<div>
					<label for="comment">댓글 내용</label>
					<textarea name="comment_content" id="comment_content" required></textarea>
				</div>
				<input type="submit" value="저장하기">
			</form>
		</div>
	</div>
	<div id="comment_list"></div>
</body>
</html>
