$(document).ready(function () {
    $('#SingUpForm').submit(function (event) {
        event.preventDefault();

        const firstName = $('#firstName').val();
        const lastName = $('#lastName').val();
        const email = $('#email').val();
        const password = $('#password').val();

        const userData = {firstName : firstName, lastName: lastName, email: email, password: password};
        $.ajax({
            url: 'user/singUp',
            type: 'POST',
            contentType: 'application/json;charset=UTF-8',
            data: JSON.stringify(userData),
            success: function (newUser) {
                alert("Registration successfully completed.")
                window.location.href = '/login';
            },
            error: function (xhr) {
             alert(xhr.responseText)
            }
        });
    });
});
