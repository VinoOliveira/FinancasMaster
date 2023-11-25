// form validation and login button redirection
document.addEventListener("DOMContentLoaded", function () {
    document.getElementById("btnLogin").addEventListener("click", function () {
        var name = document.getElementById("name").value;
        var password = document.getElementById("password").value;

        var namePattern = /^[A-Za-z\s]+$/;

        if (name.trim() === "" || password.trim() === "") {
            alert("Please fill in all fields.");

        } else if (!namePattern.test(name)) {
            alert("Please enter a valid name without numbers.")
        } else {
            window.location.href = "/profile";
        }
    });




});