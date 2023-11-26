$(document).ready(function () {

    const userId = localStorage.getItem('userId');
    if (!userId) {
        window.location.href = '/login';
        return;
    }

    $('#btnLogout').click(function () {
        localStorage.clear();
        window.location.href = '/login';
    });
    $.ajax({
        url: `/user/getUser/` + userId,
        type: 'GET',
        success: function (userData) {
            var welcomeMessage = $('#welcomeMessage p');
            var username = userData.user.firstName + " " + userData.user.lastName;
            welcomeMessage.text(username);
        }
    })
});