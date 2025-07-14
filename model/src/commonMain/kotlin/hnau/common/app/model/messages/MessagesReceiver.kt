package hnau.common.app.model.messages

interface MessagesReceiver<T> {

    fun sendMessage(message: T)
}
