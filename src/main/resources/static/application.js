var Application = Application || {};

Application.TicToe = (function() {
    function TicToe() {
        registryWebSocket.call(this)
    }

    function registryWebSocket() {
        this.clientWebSocket = new WebSocket(`ws://${location.host}/game`)
        this.clientWebSocket.onReceiveMessage = onReceiveMessage.bind(this)
        this.clientWebSocket.onopen = onOpenConnection.bind(this)
        this.clientWebSocket.onclose = onCloseConnection.bind(this)
    }

    function onReceiveMessage(message) {
        console.log("clientWebSocket.onmessage", message.data);
    }

    function onOpenConnection() {
        console.log('clientWebSocket.onopen', this.clientWebSocket)
    }

    function onCloseConnection(error) {
        console.log("clientWebSocket.onclose", this.clientWebSocket, error);
    }

    return TicToe
})()
$(function () {
    new Application.TicToe()
})

