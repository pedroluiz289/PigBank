let tipoAtual = "todos";

function aplicarFiltro(tipo, elemento) {
    document.querySelectorAll(".tag").forEach(
        botao => botao.classList.remove("ativo")
    );

    elemento.classList.add("ativo");

    tipoAtual = tipo;
    filtrarTransacoes();
}

function filtrarTransacoes() {
    const campoValor = document.getElementById("campo-busca").value.toLowerCase();
    const transacao = document.querySelectorAll(".trasacao");

    transacao.forEach(item => {
        const tipo = item.querySelector("h4").innerText.toLowerCase();
        const valorItem = item.querySelector("strong").innerText.toLowerCase();
        const valor = tipo.includes(campoValor);
        let filtroAchado = false;

        if (tipoAtual === "todos") {
            filtroAchado = true;
        } else if (tipoAtual === "saque" && tipo === "saque") {
            filtroAchado = true;
        } else if (tipoAtual === "deposito" && tipo === "deposito") {
            filtroAchado = true;
        }

        if ((valor || valorItem.includes(campoValor)) && filtroAchado) {
            item.style.display = "flex";
        } else {
            item.style.display = "none";
        }
    });
}