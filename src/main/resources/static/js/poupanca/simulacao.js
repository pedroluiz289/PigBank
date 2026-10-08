let grafico = null;

function inicializarGraficoSimulacao() {
    const canvas = document.getElementById("canvas-grafico");

    if (!canvas) {
        return;
    }

    grafico = new Chart(canvas.getContext("2d"), {
        type: "line",
        data: {
            labels: ["Mês 1", "Mês 3", "Mês 6", "Mês 9", "Mês 12"],
            datasets: [{
                label: "Saldo Simulado",
                data: [13000, 14200, 16000, 17800, 19120],
                borderColor: "#0066b3",
                backgroundColor: "rgba(0, 102, 179, 0.08)",
                fill: true,
                tension: 0.3
            }]
        },
        options: {
            responsive: true,
            maintainAspectRatio: false,
            plugins: { legend: { display: false } },
            scales: {
                x: { grid: { display: false } },
                y: { grid: { color: "#f1f5f9" } }
            }
        }
    });

    atualizarGraficoSimulacao();
}

function atualizarGraficoSimulacao() {
    const valorSlider = parseInt(document.getElementById("slider-aporte-mensal").value);
    document.getElementById("valor").textContent = `R$ ${valorSlider.toLocaleString("pt-BR")}`;

    let base = document.getElementById("slider-aporte-mensal").dataset.saldo;
    let novoDado = [base];

    for (let i = 1; i <= 12; i++) {
        base = (base + valorSlider) * 1.005;

        if ([2, 5, 8, 11].includes(i)) {
            novoDado.push(Math.round(base));
        }
    }

    document.getElementById("valor-estimativa").textContent = `R$ ${novoDado[novoDado.length - 1].toLocaleString("pt-BR", {minimumFractionDigits: 2})}`;

    if (grafico) {
        grafico.data.datasets[0].data = novoDado;
        grafico.update();
    }
}

document.addEventListener('DOMContentLoaded', () => {
    inicializarGraficoSimulacao();
});