function fetchGames() {
    fetch('/api/games')
        .then(response => response.json())
        .then(games => {
            const table = document.getElementById('gamesTable');
            const tableBody = table.querySelector('tbody');
            tableBody.innerHTML = '';
            if (games.length === 0) {
                table.style.display = 'none';
            } else {
                table.style.display = 'table';
                games.forEach(game => {
                    const row = document.createElement('tr');
                    row.innerHTML = `
                        <td>${game.name}</td>
                        <td>${game.description}</td>
                        <td>${game.releaseDate}</td>
                        <td>${game.evaluation}</td>
                        <td><button onclick="deleteGame(this, '${encodeURIComponent(game.name)}')">Remover</button></td>
                    `;
                    tableBody.appendChild(row);
                });
            }
        });
}

function deleteGame(button, name) {
    fetch(`/api/games/${name}`, {
        method: 'DELETE'
    }).then(() => {
        // Remove only the row of the clicked button
        const row = button.closest('tr');
        if (row) {
            row.remove();
        }
        // Hide table if no rows left
        const table = document.getElementById('gamesTable');
        const tbody = table.querySelector('tbody');
        if (tbody.children.length === 0) {
            table.style.display = 'none';
        }
    });
}

function addGame() {
    const inputName = document.getElementById('gameNameInput');
    const inputDescription = document.getElementById('gameDescriptionInput');
    const inputReleaseDate = document.getElementById('gameReleaseDateInput');
    const inputEvaluation = document.getElementById('gameEvaluationInput');

    const name = inputName.value.trim();
    const description = inputDescription.value.trim();
    const releaseDate = inputReleaseDate.value.trim();
    let evaluation = parseFloat(inputEvaluation.value);

    // Clamp evaluation between 0 and 5
    if (!isNaN(evaluation)) {
        evaluation = Math.min(Math.max(evaluation, 0), 5);
    }

    if (name && description && releaseDate && !isNaN(evaluation)) {
        const game = { name, description, releaseDate, evaluation };
        fetch('/api/games', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(game)
        }).then(() => {
            inputName.value = '';
            inputDescription.value = '';
            inputReleaseDate.value = '';
            inputEvaluation.value = '';
            fetchGames();
        });
    } else {
        alert('Preencha os campos corretamente.');
    }
}

// Initially hide the table
document.getElementById('gamesTable').style.display = 'none';

const evaluationInputGame = document.getElementById('gameEvaluationInput');
if (evaluationInputGame) {
    evaluationInputGame.addEventListener('input', (event) => {
        let value = parseFloat(event.target.value);
        if (isNaN(value)) return;
        if (value < 0) event.target.value = 0;
        else if (value > 5) event.target.value = 5;
    });
}

// Initial fetch of games
fetchGames();
