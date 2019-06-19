function LogIn() {

        let userName = document.getElementById("email").value;
        let password = document.getElementById("password").value;

        fetch("http://localhost:8080/api/login", {
            method: 'POST',
            credentials: "include",
            headers: {
                'Accept': 'application/json',
                "Content-Type": "application/x-www-form-urlencoded"
            },
            body: "email=" + userName + "&password=" + password
        }).then(function (response) {
            if (response.ok) {
                location.reload();
            }
        }).catch(function (error) {
            alert('Not logged in: ' + error.message);
        });


}

function Logout() {
    fetch("http://localhost:8080/api/logout", {
        method: `POST`,
    }).then(function (response) {
        location.reload();
    }).catch(function (error) {
        alert('Not logged out: ' + error.message);
});
}

function formulario() {
    if (gamesData.player === null){
        document.getElementById("formulario").style.display = "show"
        document.getElementById("logout").style.display = "none"
    }else if (gamesData.player != null){
        document.getElementById("formulario").style.display = "none"
        document.getElementById("logout").style.display = "show"
    }
}

function registro() {
    fetch("http://localhost:8080/api/player", {
        method: 'POST',
        credentials: "include",
        headers: {
            'Accept': 'application/json',
            "Content-Type": "application/json"
        },
        body: JSON.stringify(
            {email: document.getElementById("email").value,
            password: document.getElementById("password").value
        })
    }).then(response => {
        if (response.status == 403){
            alert("ERROR 403: Name in use or empty fields")
        }
        if (response.status == 201){
            LogIn();
        }
    })
}

