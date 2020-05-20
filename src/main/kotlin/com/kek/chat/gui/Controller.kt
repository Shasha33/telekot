package com.kek.chat.gui

import com.kek.chat.client.Client
import tornadofx.Controller

class MainController : Controller() {
    private val client = Client()

    fun bindToChat(name: String, handler: (String) -> Unit) {
        client.openChat(name, handler)
    }

    fun unbind(name: String) {
        client.unbindChannel(name)
    }

    fun sendMessage(channel: String, message: String) {
        client.sendMessage(channel, message)
    }

    fun shutdown() {
        client.close()
    }

}
