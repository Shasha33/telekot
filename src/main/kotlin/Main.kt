

fun main(args: Array<String>) {
    when(args.size) {
        3 -> {
            val name = args[0]
            val address = args[1]
            val port = args[2]
            val client = Client(name, address, port)
            client.runClient()
        }

        2 -> {
            val name = args[0]
            val port = args[1]
            val server = Server(name, port)
            server.runServer()
        }
        else -> {
            print("unsupported argument number")
        }

    }
}