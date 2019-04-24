var games;

function dataCall () {
    fetch("http://localhost:8080/api/games", {
        method: "GET"
    }).then(function (response) {
        if (response.ok) {
            return response.json();

        }
        throw new Error(response.statusText);
    }).then(function (json) {

        games = json;
        console.log(games);
        crearLista();

    }).catch(function (error) {
        console.log("Request failed: " + error.message);
    });

}
dataCall ();

function crearLista() {

    var partidas = games.map(game => `<li>${game.id}, ${game.created}</li>`).join("<br>");
    document.getElementById("tabla").innerHTML = partidas;
    console.log(partidas);
}

