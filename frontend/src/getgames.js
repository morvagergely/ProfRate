async function get(page) {
  const response = await fetch(
    'https://u3b6gr4ua3-3.algolianet.com/1/indexes/*/queries?x-algolia-agent=Algolia%20for%20JavaScript%20(3.33.0)%3B%20Browser%20(lite)%3B%20JS%20Helper%202.20.1&x-algolia-application-id=U3B6GR4UA3&x-algolia-api-key=9a20c93440cf63cf1a7008d75f7438bf',
    {
      credentials: 'omit',
      method: 'POST',
      mode: 'cors',
      referrerPolicy: 'no-referrer-when-downgrade',
      referrer: 'https://www.nintendo.com/games/game-guide/',
      headers: {
        accept: 'application/json',
        'content-type': 'application/x-www-form-urlencoded',
        'sec-fetch-dest': 'empty',
        'sec-fetch-mode': 'cors',
        'sec-fetch-site': 'cross-site',
      },
      body: JSON.stringify({
        requests: [
          {
            indexName: 'noa_aem_game_en_us',
            params: new URLSearchParams({
              tagFilters: '',
              query: '',
              page,
              hitsPerPage: 42,
              maxValuesPerFacet: 30,
              facets: [
                'generalFilters',
                'platform',
                'availability',
                'categories',
                'filterShops',
                'virtualConsole',
                'characters',
                'priceRange',
                'esrb',
                'filterPlayers',
              ].toString(),
              facetFilters: [
                'platform:Nintendo Switch',
              ].toString(),
            }).toString(),
          },
        ],
      }),
    },
  );

  return await response.json();
}

console.log(await get(0));

const pages = (await get(1)).results[0].nbPages;

for (let i = 0; i < pages; ++i) {
  console.log();
}
