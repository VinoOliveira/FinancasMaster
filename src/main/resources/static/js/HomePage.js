document.addEventListener("DOMContentLoaded", function () {
    const userId = localStorage.getItem('userId');

    if (!userId) {
        // Se não houver ID de usuário, redirecionar para a página de login
        window.location.href = '/login';
        return;
    }

    $.ajax({
        url: `/user/getUser/` + userId,
        type: 'GET',
        success: function (userData) {
            // Preencher informações do usuário(saldo/lucros do dia/ despesas do dia)
            $('#balance p').text(`$${userData.user.balance.toFixed(2)}`);
            $('#item-profit p').text(`$${userData.totalProfitToday.toFixed(2)}`);
            $('#item-expense p').text(`$${userData.totalExpenseToday.toFixed(2)}`);

            //preencher a tabela com as transações
            const transactionTable = $('#transactionTable');

            userData.listTransactions.forEach(transaction => {
                const textColorClass = transaction.transactionType === 'EXPENSE' ? 'text-danger' : 'text-success';
                const row = `<tr>
                                <td>${transaction.date}</td>
                                <td>${transaction.amount.toFixed(2)}</td>
                                <td class="${textColorClass}">${transaction.transactionType}</td>
                            </tr>`;
                transactionTable.find('tbody').append(row);
            });

        }, error: function (xhr) {
            console.error("Erro ao obter informações do usuário:", xhr.responseText);
        }
    });
});
