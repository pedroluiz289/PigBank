const saldoInput = document.getElementById("saldo");
const botaoSaldo = document.getElementById("botao-mostrar-saldo");
const valorTotal = document.getElementById("valor-total");

let valor = botaoSaldo.dataset.saldo;
let limite = botaoSaldo.dataset.limite;
let visivel = localStorage.getItem("saldoVisivel") !== "false";

function adicionarValor(valor) {
    let formato = saldoInput.value.replace(",", ".");
    let valorAtual = parseFloat(formato) || 0;
    let resultado = valorAtual + parseFloat(valor);

    saldoInput.value = resultado.toFixed(2).replace(".", ",");
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
    const valorSaldo = document.getElementById("valor-saldo-disponivel");
    const valorLimite = document.getElementById("valor-limite-disponivel");

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
    const saldoValor = document.getElementById("valor-saldo-disponivel");
    const valorLimite = document.getElementById("valor-limite-disponivel");
    const botaoSaque = document.getElementById("botao-saque");
    const boatoLimpar = document.getElementById("botao-limpar");
    const textoBotaoSaque = document.getElementById("texto-botao");

    botaoSaque.disabled = true;
    boatoLimpar.disabled = true;
    textoBotaoSaque.textContent = "Confirmando o Saque...";

    const dataFormulario = {
        saldo: parseFloat(valorInput)
    };

    fetch("/corrente/sacar", {
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
                const novoSaldo = Number(data.novoSaldo);
                const novoLimite = Number(data.novoLimite);

                saldoValor.innerText = "R$ " + novoSaldo.toFixed(2).replace(".", ",");
                valorLimite.innerText = "R$ " + novoLimite.toFixed(2).replace(".", ",");

                if (novoSaldo > 0) {
                    const total = novoSaldo + novoLimite;
                    valorTotal.innerText = "R$ " + total.toFixed(2).replace(".", ",");
                } else {
                    valorTotal.innerText = "R$ " + novoLimite.toFixed(2).replace(".", ",");
                }
            }

            valor = data.novoSaldo;
            limite = data.novoLimite;

            document.getElementById("formulario").reset();
            mostrarNotificacao(true, data.mensagem);
        } else {
            mostrarNotificacao(false, data.mensagem);
        }
    })
    .catch(erro => {
        mostrarNotificacao(false, erro);
    })
    .finally(() => {
        botaoSaque.disabled = false;
        boatoLimpar.disabled = false;
        textoBotaoSaque.textContent = "Confirmar Saque";
    });
});