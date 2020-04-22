package com.kek.chat

import io.grpc.stub.StreamObserver

class ChatService(private val name: String) : ChatGrpc.ChatImplBase() {
    override fun chat(responseObserver: StreamObserver<ChatOuterClass.Message>?): StreamObserver<ChatOuterClass.Message> {
        return object : StreamObserver<ChatOuterClass.Message> {
            override fun onNext(value: ChatOuterClass.Message) {
                responseObserver?.onNext(value)
            }

            override fun onError(t: Throwable?) {
                println("error occurred $t")
            }

            override fun onCompleted() {
                responseObserver?.onCompleted()
            }

        }
    }

    override fun connect(
        request: ChatOuterClass.ConnectMessage,
        responseObserver: StreamObserver<ChatOuterClass.ConnectMessage>
    ) {
        //start ui
        val answer = ChatOuterClass.ConnectMessage.newBuilder().setName(name).build()
        responseObserver.onNext(answer)
        responseObserver.onCompleted()
    }
}