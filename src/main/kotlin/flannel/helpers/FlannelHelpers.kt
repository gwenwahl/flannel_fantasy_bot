package flannel.helpers

import org.javacord.api.entity.server.Server
import org.javacord.api.entity.user.User

fun User.nameOrNickname(server: Server): String {
    return this.getNickname(server).orElse(this.name)
}

fun List<String>.restAsString(index: Int): String {
    return this.subList(index, this.lastIndex + 1).joinToString(" ").trim()
}
