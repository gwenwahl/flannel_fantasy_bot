package flannel

import discord4j.core.DiscordClient
import discord4j.core.DiscordClientBuilder
import discord4j.core.event.domain.lifecycle.ReadyEvent
import discord4j.core.event.domain.message.MessageCreateEvent
import flannel.config.COMMAND_PREFIX
import flannel.config.FLANNEL_BOT_TOKEN
import flannel.handler.GayHandler
import flannel.helpers.sendMessage

// Where the magic happens
// TODO Dependency Injection would be lovely
class FlannelClient(
    private val discord: DiscordClient = DiscordClientBuilder(FLANNEL_BOT_TOKEN).build(),
    private val gayHandler: GayHandler = GayHandler()
) {


    fun initialize() {
        // Register event handlers
        // TODO componentize these, maybe a DSL?
        discord.eventDispatcher.on(ReadyEvent::class.java).subscribe {
            ready -> println("Logged in as ${ready.self.username}" )
        }

        discord.eventDispatcher.on(MessageCreateEvent::class.java).subscribe { event ->
            val message = event.message
            val content = event.message.content.get()

            if (content.startsWith(COMMAND_PREFIX)) {
                // Check first word for command
                when (content.split(" ")[0]) {
                    // TODO Message name enums?
                    "%gay" -> gayHandler.handleMessage(event)
                    else -> message.channel.sendMessage("Command not recognized")
                }
            }
        }

        // Login and wait for requests
        discord.login().block()
    }
}