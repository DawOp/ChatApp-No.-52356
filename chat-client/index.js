function submitForm() {
    const email = document.getElementById("username").value;
    const password = document.getElementById("password").value;

    let headers = new Headers();
    headers.append('Content-Type', 'application/json');
    headers.append('Accept', '*/*');
    headers.append('Access-Control-Allow-Origin', '*/*');

    fetch('http://localhost:8080/login', {
        method: 'POST',
        mode: 'cors',
        headers: headers,
        body: JSON.stringify({ "email": email, 
        "password": password })
    }).then(res => res.json())
        .then(res => {
            let response = res;

            goToMainPanel(res['id'],res['first_name'], res['second_name']);
        });
}

function submitRegistration() {
    const first_name = document.getElementById("first-name").value;
    const second_name = document.getElementById("second-name").value;
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;

    let headers = new Headers();
    headers.append('Content-Type', 'application/json');
    headers.append('Accept', '*/*');
    headers.append('Access-Control-Allow-Origin', '*/*');

    fetch('http://localhost:8080/signup', {
        method: 'POST',
        mode: 'cors',
        headers: headers,
        body: JSON.stringify({ "first_name": first_name, 
        "second_name": second_name, 
        "email": email, 
        "password": password })
    }).then(res => res.json())
        .then(res => {
            console.log(res);
        });
}


function goToRegistration() {
    window.location.href = "registration.html";
}

function goToMainPanel(user_id, first_name, second_name) {
    localStorage.setItem('user-id', user_id);
    localStorage.setItem('first-name', first_name);
    localStorage.setItem('second-name', second_name);

    window.open("main-panel.html", "_self");
}

