function fetchPokemon() {
    const pokemonInput = document.getElementById('pokemon-input');
    const pokemonInfo = document.getElementById('pokemon-info');
    const pokemonName = pokemonInput.value.toLowerCase();
    fetch(`https://pokeapi.co/api/v2/pokemon/# `${pokemonName}`)
        .then(response => response.json())
        .then(data => {
            let altura = data.height*10;
            let peso = data.weight/10;
            let correcaoAltura;
            let correcaoPeso;
            if (altura >= 100) {
                correcaoAltura = (altura / 100).toFixed(2) + "m";
              } else {
                correcaoAltura = altura.toFixed(2) + "cm";
              }
        
              if (peso >= 1000) {
                correcaoPeso = (peso / 1000).toFixed(2) + "t";
              } else {
                correcaoPeso = peso.toFixed(2) + "kg";
              }
            pokemonInfo.innerHTML = `
                <h2>${data.name}</h2>
                <img src="${data.sprites.front_default}" alt="Sprite normal de ${data.name}">
                <img src="${data.sprites.front_shiny}" alt="Sprite shiny de ${data.name}">
                <h3>Tipo(s)</h3>
                <ul>
                    ${data.types.map(type => `<li>${type.type.name}</li>`).join('')}
                </ul>
                <h3>Habilidade(s)</h3>
                <ul>
                    ${data.abilities.map(ability => `<li>${ability.ability.name}</li>`).join('')}
                </ul>
                <h3>Estatísticas Base</h3>
                <ul>
                    ${data.stats.map(stat => `<li>${stat.stat.name}: ${stat.base_stat}</li>`).join('')}
                </ul>
                <h3>Informações Extras</h3>
                <ul>
                <p>Altura: ${correcaoAltura}</p>
                <p>Peso: ${correcaoPeso}</p>
                </ul>
            `;
        })
        .catch(error => {
            pokemonInfo.innerHTML = 'Pokémon não encontrado.';
        });
}
