var games;
var ships;
var players;
var gamePlayers;

function dataCall () {
    fetch("http://localhost:8080/api/game", {
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

    document.getElementById("ferTbody").innerHTML = games
        .map(game => `<tr>
                            <td>${game.id}</td>
                            <td>${game.created}</td>
                            <td>${game.gamePlayers.map(gp => gp.player.userName)}</td>
                </tr>`
        ).join("");
}
