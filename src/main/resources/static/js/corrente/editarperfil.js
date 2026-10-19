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

document.getElementById("card-principal").addEventListener("submit", function(evento) {
    evento.preventDefault();

    const novoNome = document.getElementById("nome-input").value;

    const dataFormulario = {
        nome: novoNome
    };

    fetch("/corrente/salvar", {
        method: "PATCH",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(dataFormulario)
    })
    .then(resposta => resposta.json())
    .then(data => {
        if (data.sucesso) {
            mostrarNotificacao(true, data.mensagem);
        } else {
            mostrarNotificacao(false, data.mensagem);
        }
    })
    .catch(erro => {
        mostrarNotificacao(false, erro);
    });
});