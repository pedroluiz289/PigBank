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

let notificacaoTimer = null;

function mostrarNotificacao(sucesso, mensagem) {
    const notificacao = document.getElementById("notificacao");
    const icone = document.getElementById("icone-notificacao");
    const texto = document.getElementById("texto-notificacao");

    if (notificacaoTimer) {
        clearTimeout(notificacaoTimer);
        notificacao.classList.remove("notificacao-sucesso");
        notificacao.classList.remove("notificacao-erro");
    }

    if (sucesso) {
        notificacao.classList.add("notificacao-sucesso");
        icone.innerText = "check";
    } else {
        notificacao.classList.add("notificacao-erro");
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
        }, {once: true});
    }, 5000);
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
}); 

document.getElementById("formulario").addEventListener("submit", function(evento) {
    evento.preventDefault();

    const saldoInput = document.getElementById("saldo").value.replace(",", ".");
    const saldoValor = document.getElementById("saldo-valor");
    const valorSaldo = saldoValor.dataset.saldo;

    const dataFormulario = {
        saldo: parseFloat(saldoInput)
    };

    fetch("/corrente/depositar", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(dataFormulario)
    })
    .then(resposta => resposta.json())
    .then(data => {
        if (data.sucesso) {
            mostrarNotificacao(true, data.mensagem);
            
            saldoValor.innerText = "R$ " + Number(valorSaldo).toFixed(2).replace(".", ",");
            document.getElementById("formulario").reset();
        } else {
            mostrarNotificacao(false, data.mensagem);
        }
    })
    .catch(erro => {
        mostrarNotificacao(false, erro);
    });
});