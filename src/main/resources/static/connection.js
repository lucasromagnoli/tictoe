var Connection = Connection || {};

Connection = (function () {
    function Connection(tictoe) {
        this.tictoe = tictoe
        createClientWebSocket.call(this)
        this.send = send.bind(this)
        this.handlers = {}
    }

    function createClientWebSocket() {
        this.clientWebSocket = new WebSocket(`ws://${location.host}/game`)
        this.clientWebSocket.onmessage = onReceiveMessage.bind(this)
        this.clientWebSocket.onopen = onOpenConnection.bind(this)
        this.clientWebSocket.onclose = onCloseConnection.bind(this)
        this.webSocketStatus = $('#ws-status')
    }

    function onReceiveMessage(message) {
        const data = JSON.parse(message.data)
        const payload = JSON.parse(data.payload)
        console.log("clientWebSocket.onmessage", data);

        this.handlers[`${data.command}_${data.status}`](payload)
    }

    function onOpenConnection() {
        console.log('clientWebSocket.onopen', this.clientWebSocket)
        this.tictoe.value(this.webSocketStatus, 'Connected')
    }

    function onCloseConnection(error) {
        console.log("clientWebSocket.onclose", this.clientWebSocket, error);
        this.tictoe.value(this.webSocketStatus, 'Disconnected')
        this.tictoe.hide(this.tictoe.login.footerPlayerDiv)
    }

    function send(payload) {
        this.clientWebSocket.send(JSON.stringify(payload))
    }

    return Connection
})()


export default function (tictoe) {
    return new Connection(tictoe)
}