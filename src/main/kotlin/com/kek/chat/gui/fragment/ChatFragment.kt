package com.kek.chat.gui.fragment

import com.kek.chat.gui.MainController
import javafx.beans.property.SimpleStringProperty
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import tornadofx.*

class ChatFragment() : Fragment("") {
    private var name  = ""
    private val controller: MainController by inject()
    private val input = SimpleStringProperty("")

    private val textFlow = textflow {}
    override val root = vbox {
        prefWidth = 568.0
        prefHeight = 320.0

        form {
            fieldset {
                field("send") {
                    textfield(input) {
                        addEventHandler(KeyEvent.KEY_PRESSED) {
                            if (it.code == KeyCode.ENTER) {
//                                println(input.value)
                                controller.sendMessage(name, input.value ?: "")
                                input.value = ""
                            }
                        }
                    }
                }
            }
        }
        add(textFlow)
    }
    override fun onDock() {

        name = params["name"] as String? ?: ""
        title = name
        controller.bindToChat(name) {
//            println("get")
            runLater{ addMessage(it) }
        }
    }

    override fun onUndock() {
        super.onUndock()
        controller.unbind(name)
    }

    private fun addMessage(newText: String) {
//        println("get >> $newText")
        textFlow.add(text(">> $newText\n"))
    }

}