

window.onload = function () {
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);

    const contact_id = urlParams.get("id");
    const user_id = localStorage.getItem("user-id");
    
    addConversation(user_id, contact_id);
    getConversation(user_id, contact_id);


    loadMessage():
    // listner
    fetchMessages();

}

function addConversation(user_id, contact_id) {
    let headers = new Headers();

    headers.append('Content-Type', 'application/json');
    headers.append('Accept', '*/*');
    headers.append('Access-Control-Allow-Origin', '*/*');

    url = "http://localhost:8080/" + user_id + "/conversation";
    
    fetch(url, {
        method: 'POST',
        mode: 'cors',
        headers: headers,
        body: JSON.stringify({ "contact_id": contact_id, 
        "name": "custom"})
    }).then(res => res.json())
        .then(res => {
        });
}

function getConversation(user_id, contact_id) {
    let headers = new Headers();

    headers.append('Content-Type', 'application/json');
    headers.append('Accept', '*/*');
    headers.append('Access-Control-Allow-Origin', '*/*');

    url = "http://localhost:8080/" + user_id + "/conversation/" + contact_id;

    fetch(url, {
        method: 'GET',
        mode: 'cors',
        headers: headers
    }).then(res => res.json())
        .then(res => {
            document.getElementById("chat-name").innerHTML = res.name;
        });
}

function receiveMessage(message) {
    const messageHistory = document.getElementById("message-history");

    // create a new element to display the message
    const messageElement = document.createElement("div");
    messageElement.innerHTML = message;
    messageElement.classList.add("received-message");

    // add the message to the message history
    messageHistory.appendChild(messageElement);
}

function sendMessage() {
    const message = document.getElementById("message-input").value;
    const messageHistory = document.getElementById("message-history");

    // create a new element to display the message
    const messageElement = document.createElement("div");
    messageElement.innerHTML = message;
    messageElement.classList.add("sent-message");

    // add the message to the message history
    messageHistory.appendChild(messageElement);

    // clear the message input field
    document.getElementById("message-input").value = "";
}

function fetchMessages  () {
    // Send a GET request to the server
    fetch(`/messages?since=${lastMessageTimestamp}`)
        .then(response => response.json())
        .then(data => {
            // Iterate through the messages and add them to the chat interface
            data.messages.forEach(message => {
                // update the timestamp of last received message
                lastMessageTimestamp = message.timestamp;
                // Add the message to the chat interface
                addMessageToChat(message);
            });
            // schedule next fetch after interval
            setTimeout(fetchMessages,3000);
        });
};