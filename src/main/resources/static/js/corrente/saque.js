const saldoInput = document.getElementById("saldo");
let visivel = localStorage.getItem("saldoVisivel") !== "false";

function adicionarValor(valor) {
    let formato = saldoInput.value.replace(",", ".");
    let valorAtual = parseFloat(formato) || 0;
    let resultado = valorAtual + parseFloat(valor);

    saldoInput.value = resultado.toFixed(2).replace(".", ",");
}

function validarSaque() {
    const valor = document.getElementById("saldo").value;

    if (valor <= 0) {
        alert("O valor tem que ser maior de R$ 0,00");
        return false;
    }

    return true;
}

function mostrarNotificacao() {
    const notificacao = document.getElementById("notificacao");

    if (notificacao) {
        notificacao.show();
    
        setTimeout(() => {
            notificacao.classList.add("notificacao-fechar");
            notificacao.addEventListener('animationend', function() {
                notificacao.close();
                notificacao.classList.remove("notificacao-fechar");
            }, {once: true});
        }, 5000);
    }
}

saldoInput.addEventListener("keypress", function (evento) {
    if (!/[0-9.,]/.test(evento.key)) {
        evento.preventDefault();
    }

    const valorAtual = this.value;
    const posicaoCursor = this.selectionStart;
    const indiceSeparador = valorAtual.search(/[.,]/);

    if (indiceSeparador !== -1) {
        if (/[.,]/.test(evento.key)) {
            evento.preventDefault();
            return;
        }

        if (posicaoCursor > indiceSeparador) {
            const casasDecimais = valorAtual.substring(indiceSeparador + 1);

            if (casasDecimais.length >= 2) {
                evento.preventDefault();
            }
        }
    }
});

function mostrarSaldo(botao) {
    const valor = botao.dataset.saldo;
    const limite = botao.dataset.limite;

    const valorSaldo = document.getElementById("valor-saldo-disponivel");
    const valorLimite = document.getElementById("valor-limite-disponivel");
    const valorTotal = document.getElementById("valor-total");

    const icone = document.getElementById("mostrar-saldo-icone");
    const textoIcone = document.getElementById("texto-icone");

    if (!visivel) {
        valorSaldo.innerText = "••••••••";
        valorLimite.innerText = "••••••••";
        valorTotal.innerText = "••••••••";
        icone.innerText = "visibility_off";
        textoIcone.innerText = "Mostrar";
    } else {
        valorSaldo.innerText = "R$ " + Number(valor).toFixed(2).replace(".", ",");
        valorLimite.innerText = "R$ " + Number(limite).toFixed(2).replace(".", ",");

        if (valor > 0) {
            const total = Number(valor) + Number(limite);
            valorTotal.innerText = "R$ " + total.toFixed(2).replace(".", ",");
        } else {
            valorTotal.innerText = "R$ " + Number(limite).toFixed(2).replace(".", ",");
        }

        icone.innerText = "visibility";
        textoIcone.innerText = "Ocultar";
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