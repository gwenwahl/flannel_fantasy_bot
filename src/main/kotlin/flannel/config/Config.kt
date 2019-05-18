package flannel.config

import java.lang.IllegalArgumentException

// Constants
const val COMMAND_PREFIX = "%"

val FLANNEL_BOT_TOKEN = System.getenv("FLANNEL_BOT_TOKEN") ?: throw IllegalArgumentException("Discord token is required, please set FLANNEL_BOT_TOKEN")
val ROLE_BLACKLIST  = System.getenv("ACCEPTABLE_ROLES")?.split('|')?.toSet() ?: setOf("@everyone")