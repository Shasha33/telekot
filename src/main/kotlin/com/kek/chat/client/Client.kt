package com.kek.chat.client

import com.rabbitmq.client.*
import kotlinx.serialization.toUtf8Bytes


class Client(private val name: String = "Petr", host: String = "localhost") {
    private var connection: Connection
    private var channels: MutableList<Channel> = mutableListOf()
    private val queues: MutableList<String> = mutableListOf()

    init {
        val factory = ConnectionFactory()
        factory.host = host
        connection = factory.newConnection()
    }

    fun openChat(name: String, messageHandler: (String) -> Unit) {
        addChatChannel(name)
        bindChatChannel(name, messageHandler)
    }

    fun addChatChannel(name: String) {
        val channel = connection.createChannel()
        channel.exchangeDeclare(name, BuiltinExchangeType.FANOUT)
        channels.add(channel)
    }

    fun bindChatChannel(channelName: String, messageHandler: (String) -> Unit) {
        val channel = connection.createChannel()
        val queueName: String = channel.queueDeclare(channelName, true, true, true, emptyMap()).queue
        channels.add(channel)
        queues.add(queueName)

        channel.queueBind(queueName, channelName, queueName) //??

        val deliverCallback = DeliverCallback { consumerTag: String?, delivery: Delivery ->
            val message = String(delivery.body, Charsets.UTF_8)
            messageHandler(message)
//            println(" [x] Received '$message'")
        }
        channel.basicConsume(queueName, true, deliverCallback, CancelCallback { consumerTag: String? -> })
    }

    fun sendMessage(channelName: String, message: String) {
        val index = queues.indexOf(channelName)
        val channel = channels[index]

        channel.basicPublish(channelName, channelName, null, "$name : $message".toUtf8Bytes())
//        channel.basicAck()
    }

    fun unbindChannel(name: String) {
        val index = queues.indexOf(name)
        val chanel = channels[index]
        if (chanel.isOpen) {
            chanel.close()
        }
        channels.removeAt(index)
        queues.removeAt(index)
    }

    fun close() {
        connection.close()
    }
}

fun main() {
    val client = Client()
    client.openChat("new_one") {
        println(it)
    }
    client.sendMessage("new_one", "aaaaaaaaa")
}