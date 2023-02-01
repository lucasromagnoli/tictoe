var Lobby = Lobby || {};

Lobby = (function () {
    function Lobby(tictoe) {
        this.tictoe = tictoe
        registryDomElements.call(this)
        tictoe.connection.handlers['SEND_LOBBY_MESSAGE_SUCCESS'] = onSendLobbyMessageSuccess.bind(this)
    }

    function registryDomElements() {
        this.lobbyMainDiv = $('#lobbyMainDiv')
        this.lobbyMessages = $('#lobbyMessages')
        this.inputLobbyMessage = $('#inputLobbyMessage')
        this.lobbyMessageSubmit = $('#submitLobbyMessage')
        this.lobbyMessageSubmit.on('click', (event) => {
            event.preventDefault()
            console.log('lobbyMessageSubmit')
            this.tictoe.connection.send({
                command: 'SEND_LOBBY_MESSAGE',
                payload: {
                    message: this.inputLobbyMessage.val()
                }
            })
        })
    }

    function onSendLobbyMessageSuccess(payload) {
        console.log(`Mensagem Recebida!: ${payload}`)

        let messageHtml = `<div class="d-flex text-muted pt-3">
            <svg class="bd-placeholder-img flex-shrink-0 me-2 rounded" width="48" height="48"
                 xmlns="http://www.w3.org/2000/svg" role="img">
                <rect width="100%" height="100%" fill="#007bff"/>
                <text x="40%" y="50%" fill="#FFFFFF" dy=".3em">${payload.player[0].toUpperCase()}</text>
            </svg>
            <p class="pb-3 mb-0 small lh-sm border-bottom">
                <strong class="d-block text-gray-dark">@${payload.player}</strong>
                <span>${payload.message}</span>
            </p>
        </div>`

        this.lobbyMessages.append(messageHtml)
    }

    return Lobby
})()


export default function (tictoe) {
    return new Lobby(tictoe)
}