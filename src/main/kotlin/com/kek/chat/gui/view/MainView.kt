package com.kek.chat.gui.view

import com.kek.chat.gui.MainController
import com.kek.chat.gui.fragment.ChatFragment
import javafx.beans.property.SimpleStringProperty
import tornadofx.*

class MainView : View("Main menu") {

    val controller: MainController by inject()
    val input = SimpleStringProperty("general")

    override val root = vbox {
        form {
            fieldset {
                field("Chat ID") {
                    textfield(input)
                }
            }
            button("Connect") {
                action {
                    find<ChatFragment>(mapOf("name" to (input.value ?: ""))).openWindow()
                }
            }
        }
    }
}


