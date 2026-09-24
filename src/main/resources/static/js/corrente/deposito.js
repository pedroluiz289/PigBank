const saldoInput = document.getElementById("saldo");
let visivel = localStorage.getItem("saldoVisivel") !== "false";

function adicionarValor(valor) {
    let formato = saldoInput.value.replace(",", ".");
    let valorAtual = parseFloat(formato) || 0;
    let resultado = valorAtual + parseFloat(valor);

    saldoInput.value = resultado.toFixed(2).replace(".", ",");
}

saldoInput.addEventListener("keypress", function(evento) {
    if (!/[0-9.,]/.test(evento.key)) {
        evento.preventDefault();
    }
});

function mostrarSaldo(botao) {
    const valor = botao.dataset.saldo;
    const saldoValor = document.getElementById("saldo-valor");

    const icone = document.getElementById("mostrar-saldo-icone");
    const textoIcone = document.getElementById("texto-icone");

    if (!visivel) {
        saldoValor.innerText = "••••••••";
        icone.innerText = "visibility_off";
        textoIcone.innerText = "Mostrar";
    } else {
        saldoValor.innerText = "R$ " + Number(valor).toFixed(2).replace(".", ",");
        icone.innerText = "visibility";
        textoIcone.innerText = "Ocultar";
    }
}

function mostrarNotificacao() {
    const notificacao = document.getElementById("notificacao");

    if (notificacao) {
        notificacao.show();
    
        setTimeout(() => {
            notificacao.classList.add("notificacao-fechar");
            notificacao.addEventListener("animationend", function() {
                notificacao.close();
                notificacao.classList.remove("notificacao-fechar");
            }, {once: true});
        }, 5000);
    }
}

function alterarEstado(botao) {
    visivel = !visivel;
    localStorage.setItem("saldoVisivel", visivel);
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

    mostrarNotificacao();
}); 