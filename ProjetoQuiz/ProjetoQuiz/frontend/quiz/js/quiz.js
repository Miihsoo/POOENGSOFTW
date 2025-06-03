const urlParams = new URLSearchParams(window.location.search);
const idQuiz = urlParams.get('id');
let resultado = 0;
let idPergunta = 0;

async function carregarPergunta() {
    document.getElementById('mensagem').textContent = '';
    try {
    const res = await fetch(`http://localhost:8080/quiz/${idQuiz}/${idPergunta}`);
    if (res.status === 404) {
        document.getElementById('perguntaTexto').textContent = "Fim do quiz!";
        const resultadoTexto = document.createElement('p');
        resultadoTexto.textContent = `Você acertou ${resultado} perguntas!`;
        document.body.appendChild(resultadoTexto);

        // Cria botão para voltar à página principal
        const botaoVoltar = document.createElement('button');
        botaoVoltar.textContent = "Voltar à tela principal";
        botaoVoltar.style.marginTop = '10px';
        botaoVoltar.onclick = function () {
            window.location.href = "../index.html";
        };
        document.body.appendChild(botaoVoltar);

        document.getElementById('formResposta').style.display = 'none';
        return;
    }

    const pergunta = await res.json();

    document.getElementById('perguntaTexto').textContent = pergunta.pergunta;
    const opcoesDiv = document.getElementById('opcoes');
    opcoesDiv.innerHTML = '';

    pergunta.escolhas.forEach((escolha, index) => {
        const label = document.createElement('label');
        label.className = 'opcao';
        label.innerHTML = `
        <input type="radio" name="resposta" value="${escolha}" required /> ${escolha}
        `;
        opcoesDiv.appendChild(label);
    });

    } catch (err) {
    console.error('Erro ao carregar pergunta:', err);
    document.getElementById('perguntaTexto').textContent = "Erro ao carregar a pergunta.";
    }
}

document.getElementById('formResposta').addEventListener('submit', async function (e) {
    e.preventDefault();

    const resposta = document.querySelector('input[name="resposta"]:checked').value;

    try {
    const res = await fetch(`http://localhost:8080/quiz/${idQuiz}/${idPergunta}`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: `${resposta}`
    });


    const texto = await res.text();
    document.getElementById('mensagem').textContent = texto;

    if(texto == "Resposta correta!") {
        resultado++;
    }

    setTimeout(() => {
        idPergunta++;
        carregarPergunta();
    }, 1500);

    } catch (err) {
    console.error('Erro ao enviar resposta:', err);
    }
});

carregarPergunta();