let visivel = localStorage.getItem("saldoVisivelPoupanca") !== "false";

function mostrarSaldo(botao) {
    const valor = botao.dataset.saldo;
    const valorSaldo = document.getElementById("saldo-valor");

    const icone = document.getElementById("mostrar-saldo-icone");
    const textoIcone = document.getElementById("texto-icone");

    if (!visivel) {
        valorSaldo.innerText = "••••••••";
        icone.innerText = "visibility_off";
        textoIcone.innerText = "Mostrar";
    } else {
        valorSaldo.innerText = "R$ " + Number(valor).toFixed(2).replace(".", ",");
        icone.innerText = "visibility";
        textoIcone.innerText = "Ocultar";
    }
}

function alterarEstado(botao) {
    visivel = !visivel;
    localStorage.setItem("saldoVisivelPoupanca", visivel);
    mostrarSaldo(botao);
}

window.addEventListener("DOMContentLoaded", () => {
    const botaoSaldo = document.getElementById("botao-mostrar-saldo");

    if (botaoSaldo) {
        mostrarSaldo(botaoSaldo);

        botaoSaldo.addEventListener("click", () => {
            alterarEstado(botaoSaldo);
        });
    }
}); 