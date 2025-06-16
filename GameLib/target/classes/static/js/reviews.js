function fetchReviews() {
    console.log('Fetching reviews...');
    fetch('/api/reviews')
        .then(async res => {
            if (!res.ok) throw new Error('Failed to fetch reviews: ' + res.status);
            const text = await res.text();
            try {
                return JSON.parse(text);
            } catch (e) {
                console.error('Failed to parse reviews JSON:', text);
                throw e;
            }
        })
        .then(reviews => {
            console.log('Reviews:', reviews);
            const container = document.getElementById('reviewsContainer');
            container.innerHTML = ''; // Limpar o "Placeholder"

            // Tables de review
            const table = document.createElement('table');
            table.id = 'reviewsTable';
            const thead = document.createElement('thead');
            thead.innerHTML = `
                <tr>
                    <th>Jogo</th>
                    <th>Comentário</th>
                    <th>Feita em</th>
                    <th>Média</th>
                    <th>Ações</th>
                </tr>
            `;
            table.appendChild(thead);

            const tbody = document.createElement('tbody');

            if (!reviews || reviews.length === 0) {
                const row = document.createElement('tr');
                row.innerHTML = `<td colspan="5" style="text-align:center;">Nenhuma review disponível.</td>`;
                tbody.appendChild(row);
            } else {
                const now = new Date();

                reviews.forEach(review => {
                    const createdAt = new Date(review.createdAt);
                    const diffHours = (now - createdAt) / (1000 * 60 * 60);
                    const canRemove = diffHours <= 24;

                    let ratingClass = '';
                    if (review.rating >= 4.0 && review.rating <= 5.0) {
                        ratingClass = 'rating-positive';
                    } else if (review.rating >= 2.5 && review.rating < 3.9) {
                        ratingClass = 'rating-average';
                    } else if (review.rating >= 0 && review.rating < 2.5) {
                        ratingClass = 'rating-negative';
                    }

                    const row = document.createElement('tr');
                    row.innerHTML = `
                        <td>${review.gameName || 'N/A'}</td>
                        <td>${review.comment || ''}</td>
                        <td>${createdAt.toLocaleString() || ''}</td>
                        <td><span class="${ratingClass}">${review.rating.toFixed(2)}</span></td>
                        <td>
                            ${canRemove ? `<button class="remove-btn" data-id="${review.id}">Remover</button>` : ''}
                        </td>
                    `;
                    tbody.appendChild(row);
                });
            }

            table.appendChild(tbody);
            container.appendChild(table);

            // Add event listeners (Remove)
            const removeButtons = document.querySelectorAll('.remove-btn');
            removeButtons.forEach(button => {
                button.addEventListener('click', () => {
                    const reviewId = button.getAttribute('data-id');
                    if (confirm('Tem certeza que deseja remover esta review?')) {
                        fetch(`/api/reviews/${reviewId}`, {
                            method: 'DELETE'
                        }).then(response => {
                            if (response.ok) {
                                alert('Review removida com sucesso.');
                                fetchReviews();
                            } else if (response.status === 403) {
                                alert('A review só pode ser removida dentro de 24 horas após a criação.');
                            } else {
                                alert('Erro ao remover a review.');
                            }
                        }).catch(error => {
                            console.error('Error removing review:', error);
                            alert('Erro ao remover a review.');
                        });
                    }
                });
            });
        })
        .catch(error => {
            console.error('Error fetching reviews:', error);
        });
}

// Add input event listener to clamp rating input between 0 and 5
const ratingInput = document.getElementById('ratingInput');
if (ratingInput) {
    ratingInput.addEventListener('input', (event) => {
        let value = parseFloat(event.target.value);
        if (isNaN(value)) return;
        if (value < 0) event.target.value = 0;
        else if (value > 5) event.target.value = 5;
    });
}

function addReview() {
    console.log('Adicionando review...');
    const inputGameName = document.getElementById('gameNameInput');
    const inputRating = document.getElementById('ratingInput');
    const inputComment = document.getElementById('commentInput');

    const gameName = inputGameName.value.trim();
    let rating = parseFloat(inputRating.value);
    const comment = inputComment.value.trim();

    // Clamp rating between 0 and 5 before sending
    if (!isNaN(rating)) {
        rating = Math.min(Math.max(rating, 0), 5);
    }

    if (gameName === '' || isNaN(rating) || comment === '') {
        alert('Preencha os campos corretamente.');
        return;
    }

    const review = { gameName, rating, comment };
    fetch('/api/reviews', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(review)
    }).then(response => {
        if (!response.ok) {
            alert('Algo deu errado...');
        }
        else{
            alert('Review adicionada com sucesso');
        }
        
        inputGameName.value = '';
        inputRating.value = '';
        inputComment.value = '';
        fetchReviews();
    }).catch(error => {
        console.error('Error adding review:', error);
    });
}

// Initial fetch of reviews and average rating
fetchReviews();
