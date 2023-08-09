document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('signupForm');
    form.addEventListener('submit', function(event) {
        event.preventDefault(); // 기본 동작 중단

        const email = document.getElementById('email').value;
        const password = document.getElementById('password').value;
        const nickname = document.getElementById('nickname').value;



        // 회원 정보 전송 후 성공/실패 메시지
        alert('회원가입이 완료되었습니다.');

    });
});
