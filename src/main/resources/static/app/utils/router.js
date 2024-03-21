let routersPath = '/config/routers.json';

async function findRouterByName(name) {
    const response = await fetch(routersPath);
    if (!response.ok) {
        throw new Error(`Erro ao carregar rotas: ${response.status}`);
    }
    const routers = await response.json();
    return routers.find(route => route.name == name);
}

function navigateTo(name, auth = (authorized) => authorized) {
    findRouterByName(name)
        .then(route => {
            if (!auth(route.authorized)) {
                throw new Error(`Rota de nome ${name}, não autorizada`);
            }
            window.location.href = route.path;
        })
        .catch(error => {
            throw error;
        });
}

export { navigateTo, routersPath }