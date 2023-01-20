window.onload = function () {
    first_name = localStorage.getItem("first-name");
    second_name = localStorage.getItem("second-name");

    document.getElementById("user-name").innerHTML = "Username:  " + first_name + " " + second_name;
}

function goToAddContact() {
    window.open("add-contact.html", "_self");
}

function displayContacts() {
    const user_id = localStorage.getItem('user-id');

    let headers = new Headers();

    headers.append('Content-Type', 'application/json');
    headers.append('Accept', '*/*');
    headers.append('Access-Control-Allow-Origin', '*/*');

    url = "http://localhost:8080/" + user_id + "/contacts";

    
    fetch(url, {
        method: 'GET',
        mode: 'cors',
        headers: headers,
    }).then(res => res.json())
        .then(res => {
            let list = document.createElement("div");
            list.classList.add("btn-group");

            res.forEach(function (item) {
                let button = document.createElement("button");
                button.innerHTML = `${item.contact_name}`;

                button.addEventListener("click", function() {
                    goToConversation(item.id);
                });

                list.appendChild(button);
            });
        
            document.body.appendChild(list);
        });
}

function goToConversation(contact_id) {
    console.log(contact_id);

    let params = new URLSearchParams();
    params.append("id", contact_id);

    const url = "conversation.html?" + params.toString();
    location.href = url;
    window.open(url);
}