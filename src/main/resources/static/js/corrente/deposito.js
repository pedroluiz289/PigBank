const saldoInput = document.getElementById("saldo");
let visivel = localStorage.getItem('saldoVisivel') !== 'false';

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

    if (visivel) {
        saldoValor.innerText = "*****";
        icone.innerText = "visibility_off";

        visivel = false;
        localStorage.setItem('saldoVisivel', false);
    } else {
        saldoValor.innerText = Number(valor).toFixed(2).replace(".", ",");
        icone.innerText = "visibility";

        visivel = true;
        localStorage.setItem('saldoVisivel', true);
    }
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

window.addEventListener('DOMContentLoaded', () => {
    const botaoSaldo = document.querySelector('[data-saldo]');
    const saldoValor = document.getElementById("saldo-valor");
    const icone = document.getElementById("mostrar-saldo-icone");

    if (!visivel && botaoSaldo) {
        saldoValor.innerText = "*****";
        icone.innerText = "visibility_off";
    } else if (botaoSaldo) {
        const valor = botaoSaldo.dataset.saldo;
        saldoValor.innerText = Number(valor).toFixed(2).replace(".", ",");
        icone.innerText = "visibility";
    }

    mostrarNotificacao()
});