var Connection = Connection || {};

Connection = (function () {
    function Connection(tictoe) {
        createClientWebSocket.call(this)
        this.send = send.bind(this)
        this.handlers = {}
    }

    function createClientWebSocket() {
        this.clientWebSocket = new WebSocket(`ws://${location.host}/game`)
        this.clientWebSocket.onmessage = onReceiveMessage.bind(this)
        this.clientWebSocket.onopen = onOpenConnection.bind(this)
        this.clientWebSocket.onclose = onCloseConnection.bind(this)
    }

    function onReceiveMessage(message) {
        const data = JSON.parse(message.data)
        console.log("clientWebSocket.onmessage", data);

        this.handlers[`${data.command}_${data.status}`](data.payload)
    }

    function onOpenConnection() {
        console.log('clientWebSocket.onopen', this.clientWebSocket)
    }

    function onCloseConnection(error) {
        console.log("clientWebSocket.onclose", this.clientWebSocket, error);
    }

    function send(payload) {
        this.clientWebSocket.send(JSON.stringify(payload))
    }

    return Connection
})()


export default function (tictoe) {
    return new Connection(tictoe)
}