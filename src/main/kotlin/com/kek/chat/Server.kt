package com.kek.chat

import io.grpc.ServerBuilder

class Server(private val port: Int, private val name: String) {
    fun run() {
        val server = ServerBuilder.forPort(port).addService(ChatService(name)).build().start()
    }

    fun startChat(message: ChatOuterClass.ConnectMessage): Client {
        return Client(message.host, message.port)
    }
}