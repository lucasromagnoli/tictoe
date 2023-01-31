var Login = Login || {};

Login = (function () {
    function Login(tictoe) {
        this.tictoe = tictoe
        registryDomElements.call(this)
        tictoe.connection.handlers['SIGN_IN_SUCCESS'] = onSignInSuccess.bind(this)
    }

    function registryDomElements() {
        this.loginContainer = $('#login-container')
        this.loginInputUsername = $('#inputPlayerName')
        this.loginSubmit = $('#login-submit')
        this.loginSubmit.on('click', (event) => {
            event.preventDefault()
            console.log('login-subit-click')
            this.tictoe.connection.send({
                command: 'SIGN_IN',
                payload: {
                    username: this.loginInputUsername.val()
                }
            })
        })
    }

    function onSignInSuccess(payload) {
        this.tictoe.player = {
            ...this.tictoe.player,
            username: payload.username
        }
        this.tictoe.hide(this.loginContainer)
    }

    return Login
})()


export default function (tictoe) {
    return new Login(tictoe)
}