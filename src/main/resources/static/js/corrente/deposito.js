const saldoInput = document.getElementById("saldo");

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