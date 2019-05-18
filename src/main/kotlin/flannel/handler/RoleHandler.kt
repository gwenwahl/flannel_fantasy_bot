package flannel.handler

import flannel.config.ROLE_BLACKLIST
import flannel.helpers.nameOrNickname
import org.javacord.api.event.message.MessageCreateEvent
import org.javacord.api.util.logging.ExceptionLogger
import java.lang.IllegalArgumentException

class RoleHandler {

    fun handleAdd(event: MessageCreateEvent, requestedRole: String) {
        if (availableRoles(event).contains(requestedRole)) {
            val user = event.messageAuthor.asUser().get()
            val role = event.server.get().roles.find { it.name == requestedRole }
            user.addRole(role).thenAcceptAsync {
                event.channel.sendMessage("Role `$requestedRole` added to ${user.nameOrNickname(event.server.get())}").join()
            }.exceptionally(ExceptionLogger.get())
        } else {
            event.channel.sendMessage("Role $requestedRole not recognized")
        }
    }

    fun handleList(event: MessageCreateEvent) {
        val roles = availableRoles(event)
        if (roles.isNotEmpty()) {
            event.channel.sendMessage("The roles you can set with this bot are: ```${roles.joinToString(", ")}```")
        } else {
            event.channel.sendMessage("There are no roles configured for FlannelBot to administer")
        }
    }

    fun handleRemove(event: MessageCreateEvent, requestedRole: String) {
        if (availableRoles(event).contains(requestedRole)) {
            val user = event.messageAuthor.asUser().get()
            val role = event.server.get().roles.find { it.name == requestedRole }
            user.removeRole(role).thenAcceptAsync {
                event.channel.sendMessage("Role $requestedRole removed from ${user.nameOrNickname(event.server.get())}").join()
            }.exceptionally(ExceptionLogger.get())
        } else {
            event.channel.sendMessage("Role $requestedRole not recognized")
        }
    }

    private fun availableRoles(event: MessageCreateEvent): Set<String> {
        val roles = event.server.get().roles
        val botRole = roles.find { role -> role.users.any { it.id == event.api.clientId } && role.isManaged } ?: throw IllegalArgumentException("Bot role is missing on server")
        return roles.filter { it.position < botRole.position && !ROLE_BLACKLIST.contains(it.name) && !it.isManaged}.map { it.name }.toSet()
    }
}