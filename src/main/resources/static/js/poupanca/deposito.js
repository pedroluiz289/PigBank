const saldoInput = document.getElementById("saldo");
const botaoSaldo = document.getElementById("botao-mostrar-saldo");

let valor = botaoSaldo.dataset.saldo;
let visivel = localStorage.getItem("saldoVisivelPoupanca") !== "false";

function adicionarValor(valor) {
    let formato = saldoInput.value.replace(",", ".");
    let valorAtual = parseFloat(formato) || 0;
    let resultado = valorAtual + parseFloat(valor);

    saldoInput.value = resultado.toFixed(2).replace(".", ",");
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

function mostrarSaldo() {
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

let notificacaoTimer = null;

function mostrarNotificacao(sucesso, mensagem) {
    const notificacao = document.getElementById("notificacao");
    const icone = document.getElementById("icone-notificacao");
    const texto = document.getElementById("texto-notificacao");

    if (notificacaoTimer) {
        clearTimeout(notificacaoTimer);
        notificacao.classList.remove("notificacao-sucesso");
        notificacao.classList.remove("notificacao-erro");
        icone.classList.remove("icone-notificacao-sucesso");
        icone.classList.remove("icone-notificacao-erro");
    }

    if (sucesso) {
        notificacao.classList.add("notificacao-sucesso");
        icone.classList.add("icone-notificacao-sucesso");
        icone.innerText = "check";
    } else {
        notificacao.classList.add("notificacao-erro");
        icone.classList.add("icone-notificacao-erro");
        icone.innerText = "close";
    }

    texto.textContent = mensagem;
    notificacao.show();

    notificacaoTimer = setTimeout(() => {
        notificacao.classList.add("notificacao-fechar");

        notificacao.addEventListener("animationend", function() {
            notificacao.close();

            notificacao.classList.remove("notificacao-fechar");
            notificacao.classList.remove("notificacao-sucesso");
            notificacao.classList.remove("notificacao-erro");
            icone.classList.remove("icone-notificacao-sucesso");
            icone.classList.remove("icone-notificacao-erro");
        }, {once: true});
    }, 5000);
}

function alterarEstado() {
    visivel = !visivel;
    localStorage.setItem("saldoVisivelPoupanca", visivel);
    mostrarSaldo();
}

window.addEventListener("DOMContentLoaded", () => {
    if (botaoSaldo) {
        mostrarSaldo();

        botaoSaldo.addEventListener("click", () => {
            alterarEstado();
        });
    }
}); 

document.getElementById("formulario").addEventListener("submit", function(evento) {
    evento.preventDefault();

    const valorInput = saldoInput.value.replace(",", ".");
    const saldoValor = document.getElementById("saldo-valor");

    const dataFormulario = {
        saldo: parseFloat(valorInput)
    };

    fetch("/poupanca/depositar", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(dataFormulario)
    })
    .then(resposta => resposta.json())
    .then(data => {
        if (data.sucesso) {
            if (visivel) {
                const novoSaldo = Number(data.novoSaldo).toFixed(2).replace(".", ",");
                saldoValor.innerText = "R$ " + novoSaldo;
            }

            mostrarNotificacao(true, data.mensagem);
            valor = data.novoSaldo;
            document.getElementById("formulario").reset();
        } else {
            mostrarNotificacao(false, data.mensagem);
        }
    })
    .catch(erro => {
        mostrarNotificacao(false, erro);
    });
});