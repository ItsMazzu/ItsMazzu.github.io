document.addEventListener('DOMContentLoaded', () => {
    const detailsElements = document.querySelectorAll('details');
    detailsElements.forEach((detail) => {
        detail.addEventListener('toggle', (event) => {
            if (detail.open) {
                detailsElements.forEach((otherDetail) => {
                    if (otherDetail !== detail && otherDetail.open) {
                        otherDetail.open = false;
                    }
                });
                // Scroll the opened details element to top of viewport
                detail.scrollIntoView({ behavior: 'smooth', block: 'start' });
            }
        });
    });
});
