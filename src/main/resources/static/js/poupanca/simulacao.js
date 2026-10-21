let grafico = null;

function inicializarGraficoSimulacao() {
    const canvas = document.getElementById("canvas-grafico");

    if (!canvas) {
        return;
    }

    grafico = new Chart(canvas.getContext("2d"), {
        type: "line",
        data: {
            labels: ["Mês 1", "Mês 2", "Mês 3", "Mês 4", "Mês 5", "Mês 6", "Mês 7", "Mês 8", "Mês 9", "Mês 10", "Mês 11", "Mês 12"],
            datasets: [{
                label: "Saldo Simulado",
                borderColor: "#0066b3",
                backgroundColor: "rgba(0, 102, 179, 0.08)",
                fill: true,
                tension: 0.3
            }]
        },
        options: {
            responsive: true,
            maintainAspectRatio: false,
            plugins: { 
                legend: { 
                    display: false 
                } 
            },
            scales: {
                y: { 
                    ticks: { 
                        callback: value => `R$ ${value.toLocaleString("pt-BR", {
                            minimumFractionDigits: 2,
                            maximumFractionDigits: 2
                        })}`
                    } 
                }
            }
        }
    });

    atualizarGraficoSimulacao();
}

function atualizarGraficoSimulacao() {
    const valorSlider = parseInt(document.getElementById("slider-aporte-mensal").value);
    const valorAporteMensal = document.getElementById("valor");
    const valorEstimativa = document.getElementById("valor-estimativa");

    valorAporteMensal.textContent = `R$ ${valorSlider.toLocaleString("pt-BR")}`;

    let base = parseFloat(document.getElementById("slider-aporte-mensal").dataset.saldo);
    let valor = base;
    let novoDado = [];

    for (let i = 1; i <= 12; i++) {
        valor = (valor + valorSlider) * 1.01;
        novoDado.push(parseFloat(valor));
    }

    valorEstimativa.textContent = `R$ ${novoDado[novoDado.length - 1].toLocaleString("pt-BR", {
        minimumFractionDigits: 2,
        maximumFractionDigits: 2
    })}`;

    if (grafico) {
        grafico.data.datasets[0].data = novoDado;
        grafico.update();
    }
}

document.addEventListener('DOMContentLoaded', () => {
    inicializarGraficoSimulacao();
});