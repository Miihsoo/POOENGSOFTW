async function carregarQuizzes() {
    try {
        const response = await fetch('http://localhost:8080/quiz', {
            headers: {
                'Accept': 'application/json'
            }
        });

        if (!response.ok) {
            throw new Error('Erro ao buscar quizzes: ' + response.status);
        }

        const quizzes = await response.json();

        const container = document.getElementById('quizes');
        container.innerHTML = '';

        // Agrupar quizzes por tema
        const quizzesPorTema = {};
        quizzes.forEach(quiz => {
            const tema = quiz.tema || 'Sem tema definido';
            if (!quizzesPorTema[tema]) {
                quizzesPorTema[tema] = [];
            }
            quizzesPorTema[tema].push(quiz);
        });

        // Para cada tema, criar um título e listar os quizzes
        for (const tema in quizzesPorTema) {
            const sectionTema = document.createElement('section');
            sectionTema.style.marginBottom = '30px';

            const tituloTema = document.createElement('h2');
            tituloTema.textContent = tema.charAt(0).toUpperCase() + tema.slice(1);
            tituloTema.style.borderBottom = '2px solid #000';
            tituloTema.style.paddingBottom = '5px';

            sectionTema.appendChild(tituloTema);

            quizzesPorTema[tema].forEach(quiz => {
                const divQuiz = document.createElement('div');
                divQuiz.style.border = '1px solid #ccc';
                divQuiz.style.padding = '10px';
                divQuiz.style.marginBottom = '10px';

                const titulo = document.createElement('h3');
                titulo.textContent = quiz.titulo;

                const descricao = document.createElement('p');
                descricao.textContent = quiz.descricao;

                const btnJogar = document.createElement('button');
                btnJogar.textContent = 'Jogar Quiz';
                btnJogar.style.cursor = 'pointer';

                btnJogar.addEventListener('click', () => {
                    window.location.href = `html/quiz.html?id=${quiz.id}`;
                });

                divQuiz.appendChild(titulo);
                divQuiz.appendChild(descricao);
                divQuiz.appendChild(btnJogar);

                sectionTema.appendChild(divQuiz);
            });

            container.appendChild(sectionTema);
        }

    } catch (error) {
        console.error(error);
        document.getElementById('quizes').innerHTML = '<p>Nenhum quiz disponível.</p>';
    }
}

window.onload = carregarQuizzes;