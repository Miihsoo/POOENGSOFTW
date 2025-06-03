const perguntasContainer = document.getElementById('perguntasContainer');
const adicionarPerguntaBtn = document.getElementById('adicionarPerguntaBtn');

function criarBlocoPergunta(index) {
    const divPergunta = document.createElement('div');
    divPergunta.className = 'pergunta';
    divPergunta.dataset.index = index;

    divPergunta.innerHTML = `
        <label>Pergunta ${index + 1}:</label>
        <input type="text" name="perguntaTexto" placeholder="Digite a pergunta" required />

        <div class="escolhas">
            <label>Escolhas (mínimo 2):</label>
            <input type="text" name="escolha" placeholder="Escolha 1" required />
            <input type="text" name="escolha" placeholder="Escolha 2" required />
        </div>

        <button type="button" class="adicionarEscolhaBtn">Adicionar escolha</button>

        <label>Resposta certa:</label>
        <input type="text" name="respostaCerta" placeholder="Digite a resposta certa" required />
    `;

    const btnAddEscolha = divPergunta.querySelector('.adicionarEscolhaBtn');
    const escolhasDiv = divPergunta.querySelector('.escolhas');

    btnAddEscolha.addEventListener('click', () => {
        const inputNovaEscolha = document.createElement('input');
        inputNovaEscolha.type = 'text';
        inputNovaEscolha.name = 'escolha';
        inputNovaEscolha.placeholder = `Escolha ${escolhasDiv.querySelectorAll('input').length + 1}`;
        inputNovaEscolha.required = true;
        escolhasDiv.appendChild(inputNovaEscolha);
    });

    return divPergunta;
}

let contadorPerguntas = 0;
function adicionarPergunta() {
    const bloco = criarBlocoPergunta(contadorPerguntas++);
    perguntasContainer.appendChild(bloco);
}

adicionarPerguntaBtn.addEventListener('click', adicionarPergunta);
adicionarPergunta();

document.getElementById('quizForm').addEventListener('submit', async e => {
    e.preventDefault();

    const titulo = document.getElementById('titulo').value.trim();
    const descricao = document.getElementById('descricao').value.trim();
    const tema = document.getElementById('tema').value;

    if (!tema) {
        alert('Por favor, selecione um tema para o quiz.');
        return;
    }

    const perguntasDivs = perguntasContainer.querySelectorAll('.pergunta');
    const perguntas = [];

    for (const div of perguntasDivs) {
        const perguntaTexto = div.querySelector('input[name="perguntaTexto"]').value.trim();
        const escolhasInputs = div.querySelectorAll('input[name="escolha"]');
        const respostaCerta = div.querySelector('input[name="respostaCerta"]').value.trim();

        const escolhas = Array.from(escolhasInputs).map(input => input.value.trim()).filter(val => val !== '');

        if (escolhas.length < 2) {
            alert('Cada pergunta deve ter pelo menos 2 escolhas.');
            return;
        }

        if (!escolhas.includes(respostaCerta)) {
            alert('A resposta certa deve estar entre as escolhas.');
            return;
        }

        perguntas.push({
            pergunta: perguntaTexto,
            escolhas: escolhas,
            respostaCerta: respostaCerta
        });
    }

    const quiz = {
        titulo,
        descricao,
        tema,
        perguntas
    };

    try {
        const response = await fetch('http://localhost:8080/quiz', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(quiz)
        });

        if (response.ok) {
            alert('Quiz criado com sucesso!');
            e.target.reset();
            perguntasContainer.innerHTML = '';
            contadorPerguntas = 0;
            adicionarPergunta();
        } else if (response.status === 409) {
            const erro = await response.json();
            alert('Erro: ' + erro.erro); // esperando { "erro": "mensagem aqui" }
        } else {
            const erro = await response.text();
            alert('Erro ao criar quiz: ' + erro);
        }
    } catch (error) {
        alert('Erro na requisição: ' + error.message);
    }
});