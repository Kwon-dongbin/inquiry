// 텍스트 입력 상자의 길이 제한과 초과 여부를 확인하는 함수
function checkInputLength() {
    // 텍스트 입력 상자의 최대 길이 설정
    var maxTitleLength = 50; // 글 제목의 최대 길이
    var maxContentLength = 300; // 글 내용의 최대 길이

    // 입력 내용 가져오기
    var titleContent = document.getElementById("inquiry_title").value;
    var contentContent = document.getElementById("inquiry_content").value;

    // 입력 내용의 길이 확인
    var titleLength = titleContent.length;
    var contentLength = contentContent.length;

    // 최대 길이를 초과하는 경우
    if (titleLength > maxTitleLength || contentLength > maxContentLength) {
        // 초과 메시지 표시
        if (titleLength > maxTitleLength) {
            alert("글 제목은 " + maxTitleLength + "자를 초과할 수 없습니다.");
        } else {
            alert("글 내용은 " + maxContentLength + "자를 초과할 수 없습니다.");
        }

        // 초과한 부분 삭제
        document.getElementById("inquiry_title").value = titleContent.substring(0, maxTitleLength);
        document.getElementById("inquiry_content").value = contentContent.substring(0, maxContentLength);
    }

    // 현재 입력된 글자 수 표시
    document.getElementById("titleCount").innerText = titleLength + "/" + maxTitleLength;
    document.getElementById("contentCount").innerText = contentLength + "/" + maxContentLength;
}
function goBack() {
    window.history.back();
}