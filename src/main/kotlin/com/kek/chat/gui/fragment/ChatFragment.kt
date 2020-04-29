package com.kek.chat.gui.fragment

import com.kek.chat.gui.MainController
import tornadofx.*

class ChatFragment() : Fragment("") {
    private var name  = ""
    val controller: MainController by inject()

    private val textFlow = textflow {}
    override val root = vbox {
        add(textFlow)
        prefWidth = 568.0
        prefHeight = 320.0
    }
    override fun onDock() {

        name = params["name"] as String? ?: ""
        title = name
        controller.bindToChat(name) { addMessage(it) }
    }

    override fun onUndock() {
        super.onUndock()
        controller.unbind(name)
    }

    private fun addMessage(newText: String) {
        textFlow.add(text(">> $newText\n"))
    }

}