const aviso = document.getElementById("aviso");
let visivel = localStorage.getItem("saldoVisivelPoupanca") !== "false";

function mostrarSaldo(botao) {
    const valor = botao.dataset.saldo;
    const taxa = botao.dataset.taxa;

    const valorSaldo = document.getElementById("saldo-valor");
    const valorRendimento = document.getElementById("valor-rendimento");

    const icone = document.getElementById("mostrar-saldo-icone");
    const textoIcone = document.getElementById("texto-icone");

    if (!visivel) {
        valorSaldo.innerText = "••••••••";
        valorRendimento.innerText = "••••••••";
        icone.innerText = "visibility_off";
        textoIcone.innerText = "Mostrar";
    } else {
        valorSaldo.innerText = "R$ " + Number(valor).toFixed(2).replace(".", ",");
        valorRendimento.innerText = "R$ " + Number(valor * taxa).toFixed(2).replace(".", ",");
        icone.innerText = "visibility";
        textoIcone.innerText = "Ocultar";
    }
}

function alterarEstado(botao) {
    visivel = !visivel;
    localStorage.setItem("saldoVisivelPoupanca", visivel);
    mostrarSaldo(botao);
}

function abrirAviso() {
    aviso.showModal();
}

function fecharAviso() {
    aviso.close();
}

function confirmarSaida(rota) {
    fecharAviso();
    window.location.href = rota;
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