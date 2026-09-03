let visivel = localStorage.getItem('saldoVisivel') !== 'false';

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
}) 