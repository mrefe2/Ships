var gamesData;

function dataCall () {
    fetch("http://localhost:8080/api/game", {
        method: "GET"
    }).then(function (response) {
        if (response.ok) {
            return response.json();

        }
        throw new Error(response.statusText);
    }).then(function (json) {

        gamesData = json;
        crearLista();
        tablaResultados();
        formulario()

    }).catch(function (error) {
        console.log("Request failed: " + error.message);
    });

}

dataCall ();


function crearLista() {
        console.log(gamesData);
    document.getElementById("ferTbody").innerHTML = gamesData.games
        .map(game => {
            let tabla1 = `<tr>
                            <td>${game.id}</td>
                            <td>${game.created}</td>
                            <td>${game.gamePlayers.map(gp => gp.player.email)}</td>`

           if (!gamesData.player) {
               return  tabla1
           }
           else if (game.gamePlayers.find(gp => gp.player.id == gamesData.player.id)) {
                let gameplayersid = game.gamePlayers.find(gp => gp.player.id == gamesData.player.id).id;
               return tabla1 + `<td><button onclick="backToGame(${gameplayersid})">Return to Game</button> </td></tr>`
           }
           else if (game.gamePlayers.length === 1){
              let gameId = game.gamePlayers.find(gp => gp.player.id != gamesData.player.id).id
              return tabla1 + `<td><button onclick="joinToGame(${gameId})">Join Game</button> </td></tr>`
           }
           else {
               return tabla1
           }
    }).join("")
}

function tablaResultados () {
    let jugadoresID = gamesData.games.flatMap(pg => pg.gamePlayers.map(py => py.player.id));


   function uniq(jugadoresID) {
        return Array.from(new Set(jugadoresID));
    }
   uniq();

   let jugadoresUniqIDs = [... new Set(jugadoresID)];


   let uniqPlayers = jugadoresUniqIDs.map(id => gamesData.games.flatMap(game => game.gamePlayers).map(gamePlayer => gamePlayer.player).find(player => player.id === id));


  document.getElementById("tablaResultados").innerHTML = uniqPlayers
        .map(uniqPlayer => {
            return `<tr>
                        <td>${uniqPlayer.email}</td>
                        <td>${totalPoints(uniqPlayer)}</td>
                        <td>${getWins(uniqPlayer)}</td>
                        <td>${getLost(uniqPlayer)}</td>
                        <td>${getTies(uniqPlayer)}</td>
                </tr>`
            }).join("")

}



function getWins(player) {
    return player.score.filter(score => score.score === 1).length;
}

function getLost(player) {
    return player.score.filter(score => score.score === 0).length;
}
function getTies(player) {
    return player.score.filter(score => score.score === 0.5).length;
}

function totalPoints(player) {
    return player.score.map(score => score.score).reduce((a,b) => a + b);
}

function backToGame(id) {
    window.location.href = `http://localhost:8080/web/game.html?gp=${id}`
}

function crearPartida() {
    fetch("http://localhost:8080/api/games", {
        method: "POST"
}).then(function (response) {
        if (response.ok) {
            return response.json();
        }
        throw new Error(response.statusText)
    }).then(function (json) {
        console.log(json);
        window.location.href = `http://localhost:8080/web/game.html?gp=${json.gpId}`

    }).catch(function (error) {
        console.log("Request failed: " + error.message);
    });

}

function joinToGame() {
    fetch("http://localhost:8080/api/game", {
        method: "POST"
    }).then(function (response) {
        if (response.ok) {

            return response.json();
        }
        throw new Error(response.statusText)
    }).then(function (json) {

        window.location.href = `http://localhost:8080/web/game.html?gp=${json.gameId}`

    }).catch(function (error) {
        console.log("Request failed: " + error.message);
    });

}
