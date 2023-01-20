function addContact() {
    const email = document.getElementById("email").value;
    const user_id = localStorage.getItem('user-id');

    let headers = new Headers();
    headers.append('Content-Type', 'application/json');
    headers.append('Accept', '*/*');
    headers.append('Access-Control-Allow-Origin', '*/*');

    url = "http://localhost:8080/" + user_id + "/contact";
    
    fetch(url, {
        method: 'POST',
        mode: 'cors',
        headers: headers,
        body: email
    }).then(res => res.json())
        .then(res => {
            console.log(res);
        });
}