function fetchConsoles() {
    fetch('/api/consoles')
        .then(response => response.json())
        .then(consoles => {
            const table = document.getElementById('consolesTable');
            const tableBody = table.querySelector('tbody');
            tableBody.innerHTML = '';
            if (consoles.length === 0) {
                table.style.display = 'none';
            } else {
                table.style.display = 'table';
                consoles.forEach(console => {
                    const row = document.createElement('tr');
                    row.innerHTML = `
                        <td>${console.name}</td>
                        <td>${console.description}</td>
                        <td>${console.releaseDate}</td>
                        <td>${console.evaluation}</td>
                        <td><button onclick="deleteConsole(this, '${encodeURIComponent(console.name)}')">Remover</button></td>
                    `;
                    tableBody.appendChild(row);
                });
            }
        });
}

function deleteConsole(button, name) {
    fetch(`/api/consoles/${name}`, {
        method: 'DELETE'
    }).then(() => {
        // Remove only the row of the clicked button
        const row = button.closest('tr');
        if (row) {
            row.remove();
        }
        // Hide table if no rows left
        const table = document.getElementById('consolesTable');
        const tbody = table.querySelector('tbody');
        if (tbody.children.length === 0) {
            table.style.display = 'none';
        }
    });
}

function addConsole() {
    const inputName = document.getElementById('consoleNameInput');
    const inputDescription = document.getElementById('consoleDescriptionInput');
    const inputReleaseDate = document.getElementById('consoleReleaseDateInput');
    const inputEvaluation = document.getElementById('consoleEvaluationInput');

    const name = inputName.value.trim();
    const description = inputDescription.value.trim();
    const releaseDate = inputReleaseDate.value.trim();
    let evaluation = parseFloat(inputEvaluation.value);

    // Clamp evaluation between 0 and 5
    if (!isNaN(evaluation)) {
        evaluation = Math.min(Math.max(evaluation, 0), 5);
    }

    if (name && description && releaseDate && !isNaN(evaluation)) {
        const console = { name, description, releaseDate, evaluation };
        fetch('/api/consoles', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(console)
        }).then(() => {
            inputName.value = '';
            inputDescription.value = '';
            inputReleaseDate.value = '';
            inputEvaluation.value = '';
            fetchConsoles();
        });
    } else {
        alert('Preencha os campos corretamente');
    }
}

// Initially hide the table
document.getElementById('consolesTable').style.display = 'none';

const evaluationInputConsole = document.getElementById('consoleEvaluationInput');
if (evaluationInputConsole) {
    evaluationInputConsole.addEventListener('input', (event) => {
        let value = parseFloat(event.target.value);
        if (isNaN(value)) return;
        if (value < 0) event.target.value = 0;
        else if (value > 5) event.target.value = 5;
    });
}

// Initial fetch of consoles
fetchConsoles();
