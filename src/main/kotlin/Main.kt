import com.kek.chat.gui.view.MainView
import javafx.scene.paint.Color
import tornadofx.*

class HelloWorldStyle : Stylesheet() {
    companion object {
        val mainPanel by cssclass()
    }

    init {
        root {
            prefWidth = 400.px
            prefHeight = 400.px
        }

        mainPanel {
            borderWidth += box(4.px)
            borderColor += box(Color.RED)
        }
    }
}

class HelloWorldApp : App(MainView::class, HelloWorldStyle::class)

fun main(args: Array<String>) {
    launch<HelloWorldApp>()
}