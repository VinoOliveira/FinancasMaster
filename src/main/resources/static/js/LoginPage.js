$(document).ready(function () {
    $('#loginForm').submit(async function (event) {
        event.preventDefault();

        var email = $('#email').val();
        var password = $('#password').val();

        if (!isValidEmail(email) || password.trim() === '') {
            alert("Please enter a valid email address and password.");
            return;
        }

        try {
            const response = await fetch('/user/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json;charset=UTF-8',
                },
                body: JSON.stringify({ email, password }),
            });

            if (!response.ok) {
                if (response.status === 401) {
                    alert("Incorrect password. Please try again.");
                } else if (response.status === 404) {
                    alert("Email not found. Please try again.");
                }
            }
            const userId = await response.json();
            localStorage.setItem('userId', userId);
            window.location.href = '/home';
        } catch (error) {

        }
    });
});

// Função para validar o formato do e-mail
function isValidEmail(email) {
    var emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return emailPattern.test(email);
}