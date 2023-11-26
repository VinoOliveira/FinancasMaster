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
                throw new Error(`HTTP error! Status: ${response.status}`);
            }

            const userId = await response.json();
            localStorage.setItem('userId', userId);

            window.location.href = '/home';
        } catch (error) {
            console.error("Error during login:", error.message);
            alert("An error occurred during login. Please try again.");
        }
    });
});

// Função para validar o formato do e-mail
function isValidEmail(email) {
    var emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return emailPattern.test(email);
}