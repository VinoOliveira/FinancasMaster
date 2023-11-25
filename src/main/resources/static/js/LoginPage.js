$(document).ready(function () {
    $('#loginForm').submit(function (event) {
        event.preventDefault();

        var email = $('#email').val();
        var password = $('#password').val();

        if (!isValidEmail(email)) {
            alert("Please enter a valid email address.");
            return;
        }

        $.ajax({
            url: '/user/login',
            type: 'POST',
            contentType: 'application/json;charset=UTF-8',
            data: JSON.stringify({email: email, password: password}),
            success: function(response) {
                console.log("Login bem-sucedido!", response);
                window.location.href = '/home';
            },
            error: function(xhr) {
                console.error("Erro durante o login:", xhr.responseText);

                var errorMessage = xhr.responseText;
                alert(errorMessage);
            }
        });
    });
})
// Função para validar o formato do e-mail
function isValidEmail(email) {
    var emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return emailPattern.test(email);
}