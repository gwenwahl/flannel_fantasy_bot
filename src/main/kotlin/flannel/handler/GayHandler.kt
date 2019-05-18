package flannel.handler

import org.javacord.api.event.message.MessageCreateEvent
import java.util.*


class GayHandler {
    fun handleMessage(event: MessageCreateEvent) {
        val rand = Random()
        val high = 100
        val low = 80
        event.channel.sendMessage("You are ${rand.nextInt(high - low) + low}% gay!")
    }
}
