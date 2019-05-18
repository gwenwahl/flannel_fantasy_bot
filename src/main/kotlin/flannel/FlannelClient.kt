package flannel

import flannel.config.COMMAND_PREFIX
import flannel.config.FLANNEL_BOT_TOKEN
import flannel.handler.GayHandler
import flannel.handler.RoleHandler
import flannel.helpers.restAsString
import org.javacord.api.DiscordApi
import org.javacord.api.DiscordApiBuilder

// Where the magic happens
// TODO Dependency Injection would be lovely
class FlannelClient(
    private val discord: DiscordApi = DiscordApiBuilder().setToken(FLANNEL_BOT_TOKEN).login().join(),
    private val gayHandler: GayHandler = GayHandler(),
    private val roleHandler: RoleHandler = RoleHandler()
) {

    fun initialize() {
        discord.addMessageCreateListener { event ->
            if (event.messageContent.startsWith(COMMAND_PREFIX)) {
                val contentWords = event.messageContent.split(" ")
                when (contentWords[0].removePrefix(COMMAND_PREFIX)) {
                    "gay" -> gayHandler.handleMessage(event)
                    "role" ->  when (contentWords.getOrNull(1) ?: "") {
                        "add" -> roleHandler.handleAdd(event, contentWords.restAsString(2))
                        "remove" -> roleHandler.handleRemove(event, contentWords.restAsString(2))
                        "rm" -> roleHandler.handleRemove(event, contentWords.restAsString(2))
                        "list" -> roleHandler.handleList(event)
                        else -> event.channel.sendMessage("Command not recognized") // This duplication is annoying, new DSL approach should help to avoid it
                    }
                    else -> event.channel.sendMessage("Command not recognized")
                }
            }
        }
    }

}