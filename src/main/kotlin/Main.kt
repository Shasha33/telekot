import javafx.scene.paint.Color
import tornadofx.*

class HelloWorld : View() {
    override val root = gridpane {
        button("North") {
            useMaxWidth = true
            gridpaneConstraints {
                columnRowIndex(0,0)
                marginBottom = 10.0
                columnSpan = 2
            }
        }
        button("West").gridpaneConstraints {
            columnRowIndex(0,1)
        }
        button("East").gridpaneConstraints {
            columnRowIndex(1,1)
        }

        button("South") {
            useMaxWidth = true
            gridpaneConstraints {
                columnRowIndex(0,2)
                marginTop = 10.0
                columnSpan = 2
            }
        }
    }
}

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

class HelloWorldApp : App(HelloWorld::class, HelloWorldStyle::class)

fun main(args: Array<String>) {
    launch<HelloWorldApp>()
}