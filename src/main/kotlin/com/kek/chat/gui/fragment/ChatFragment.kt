package com.kek.chat.gui.fragment

import com.kek.chat.gui.MainController
import javafx.beans.property.SimpleStringProperty
import javafx.scene.Parent
import javafx.scene.text.TextFlow
import tornadofx.*

class ChatFragment() : Fragment("") {
    private val name = SimpleStringProperty("")
    val controller: MainController by inject()

    private val textFlow = textflow { text("init\n") }
    override val root = vbox {
        add(textFlow)
        prefWidth = 568.0
        prefHeight = 320.0
    }
    override fun onDock() {
        name.value = params["name"] as String?
        title = name.value ?: ""

        addText("heh")
        addText("kek")
    }

    private fun addText(newText: String) {
        textFlow.add(text(newText + "\n"))
    }

}