package com.kek.chat

import io.grpc.ManagedChannelBuilder


class Client(val name: String, val address: String, val port: Int) {
    private val channel = ManagedChannelBuilder.forAddress(address, port).usePlaintext()
    val stub = ChatCoroutineStub(channel)
    fun runClient() {

    }

}
