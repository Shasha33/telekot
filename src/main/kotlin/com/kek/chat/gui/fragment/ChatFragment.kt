package com.kek.chat.gui.fragment

import javafx.beans.property.SimpleStringProperty
import javafx.scene.Parent
import javafx.scene.text.TextFlow
import tornadofx.*

class ChatFragment() : Fragment("") {
    val name = SimpleStringProperty("")

    override val root = vbox {
        label("aaaaaaaaaaaa")
        prefWidth = 568.0
        prefHeight = 320.0
    }
    override fun onDock() {
        name.value = params["name"] as String?
        title = name.value ?: ""
    }
}