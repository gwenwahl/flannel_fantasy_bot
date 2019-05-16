package flannel.handler

import discord4j.core.event.domain.message.MessageCreateEvent
import flannel.helpers.sendMessage
import java.util.*


class GayHandler : MessageHandler {
    override fun handleMessage(event: MessageCreateEvent) {
        val rand = Random()
        val high = 100
        val low = 80
        event.message.channel.sendMessage("You are ${rand.nextInt(high - low) + low}% gay!")
    }
}