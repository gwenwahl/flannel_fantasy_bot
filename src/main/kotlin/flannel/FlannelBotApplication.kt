@file:JvmName("FlannelBotApplication")
package flannel

import discord4j.core.DiscordClientBuilder
import discord4j.core.event.domain.lifecycle.ReadyEvent
import discord4j.core.event.domain.message.MessageCreateEvent

fun main(args : Array<String>) {
    // TODO obviously don't hard code token, load in a config file or something?
    val token = if (args.isNotEmpty()) args[0]  else ""
    val client = DiscordClientBuilder(token).build()
    client.eventDispatcher.on(ReadyEvent::class.java).subscribe {
            ready -> println("Logged in as ${ready.self.username}" )
    }
    client.eventDispatcher.on(MessageCreateEvent::class.java).subscribe { event ->
        val message = event.message
        if (message.content.map("!ping"::equals).orElse(false)) {
            message.channel.block()?.createMessage("Pong!")?.block()
        }
    }
    client.login().block()
}