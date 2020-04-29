package com.kek.chat.client

import com.rabbitmq.client.*

class Client(host: String = "localhost") {
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
        val queue = channel.queueDeclare(channelName, true, true, true, emptyMap()).queue
        channels.add(channel)
        queues.add(queue)

        channel.exchangeBind(channelName, channelName, queue) //??

        val consumer = object : DefaultConsumer(channel) {
            override fun handleDelivery(consumerTag: String,
                                        envelope: Envelope,
                                        properties: AMQP.BasicProperties,
                                        body: ByteArray) {
                val message = body.toString()
                messageHandler.invoke(message)
            }
        }

        channel.basicConsume(queue, true, consumer)
    }

    fun close() {
        connection.close()
    }
}