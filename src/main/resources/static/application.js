import Login from './login.js'
import Connection from "./connection.js";

var Application = Application || {};

Application.TicToe = (function () {
    function TicToe() {
        this.connection = new Connection(this)
        this.login = new Login(this)
        this.hide = (elementDOM) => elementDOM.addClass('d-none')
    }

    return TicToe
})()
$(function () {
    new Application.TicToe();
})

