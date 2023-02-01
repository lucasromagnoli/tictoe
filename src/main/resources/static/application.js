import Login from './login.js'
import Connection from "./connection.js";

var Application = Application || {};

Application.TicToe = (function () {
    function TicToe() {
        this.connection = new Connection(this)
        this.login = new Login(this)
        this.hide = (elementDOM) => elementDOM.addClass('d-none')
        this.show = (elementDOM) => elementDOM.removeClass('d-none')
        this.value = (elementDOM, message) => elementDOM.html(message)
        this.player = {}
    }

    return TicToe
})()
$(function () {
    new Application.TicToe();
})

