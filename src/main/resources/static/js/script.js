
// LOGIN PAGE
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
            window.location.href = "ProfilePage.html";
        }
    });
});

// SING-UP PAGE
// validation of the registration form 
document.addEventListener("DOMContentLoaded", function () {
    document.getElementById("btnSingUp").addEventListener("click", function () {
        var email = document.getElementById("email").value;
        var name = document.getElementById("name").value;
        var password = document.getElementById("password").value;

        var namePattern = /^[A-Za-z\s]+$/;
        var emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;

        if (email.trim() === "" || name.trim() === "" || password.trim() === "") {
            alert("Please fill in all fields.");
        } else if (!emailPattern.test(email)) {
            alert("Please enter a valid e-mail address.");
        } else if (!namePattern.test(name)) {
            alert("Please enter a valid name without numbers.")
        } else {
            alert("Registration successful!");
            window.location.href = "LoginPage.html";
        }
    });
});

// REGISTER PAGE
// validation of the registration form
document.addEventListener("DOMContentLoaded", function () {
    document.getElementById("movementForm").addEventListener("submit", function (event) {
        event.preventDefault(); 

        const value = document.getElementById("value").value;
        const profitRadio = document.getElementById("btnProfit");
        const expenseRadio = document.getElementById("btnExpense");
        const successMessage = document.getElementById("successMessage");

        if (value && (profitRadio.checked || expenseRadio.checked)) {
            successMessage.style.display = "block";

            setTimeout(function () {
                successMessage.style.display = "none";
                document.getElementById("movementForm").reset();
            }, 3000);
        } else {
            alert("Please enter a number and select either 'Profit' or 'Expense'.");
        }
    });
});

//TRANSACTION PAGE
//applying date filter
document.addEventListener("DOMContentLoaded", function () {
    document.getElementById("filter").addEventListener("click", function () {
        const dateFilter = document.getElementById("dateFilter").value;
        const rows = document.querySelectorAll("#transactionTable tbody tr");

        rows.forEach(function (row) {
            const dateCell = row.cells[0].textContent; 

            if (dateFilter === "" || dateCell === dateFilter) {
                row.style.display = "table-row";
            } else {
                row.style.display = "none";
            }
        });
    });
});
