package flannel.handler

import discord4j.core.event.domain.message.MessageCreateEvent

interface MessageHandler {

    fun handleMessage(event: MessageCreateEvent)

}