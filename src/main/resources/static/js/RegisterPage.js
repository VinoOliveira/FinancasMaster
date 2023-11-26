$(document).ready(function () {
    $('#transactionForm').submit(function (event) {
        event.preventDefault();

        const userId = localStorage.getItem('userId');

        if (!userId) {
            // Se não houver ID de usuário, redirecionar para a página de login
            window.location.href = '/login';
            return;
        }

        // Move a lógica de verificação do tipo de transação para dentro da função de envio do formulário
        var transactionType;
        var selectedValue = $("input[name='movementType']:checked").val();

        // Verifica se um botão de rádio está selecionado
        if (selectedValue === "PROFIT") {
            transactionType = "PROFIT";
        } else if (selectedValue === "EXPENSE") {
            transactionType = "EXPENSE";
        } else {
            alert("Please select a transaction type.");
            return;
        }

        var amount = $('#amount').val();

        const transactionData = {userId: userId, amount: amount, transactionType: transactionType};
        $.ajax({
            url: `transaction/register`,
            type: "POST",
            contentType: 'application/json',
            data: JSON.stringify(transactionData),
            success: function (newData) {
                alert("Transaction successfully registered.");
                window.location.reload();
            },
            error: function (xhr) {
                alert(xhr.responseText);
            }
        });
    });
});
