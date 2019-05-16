@file:JvmName("FlannelBotApplication")
package flannel

import flannel.config.FLANNEL_BOT_TOKEN
import java.lang.IllegalArgumentException


fun main(args : Array<String>) {
    if (FLANNEL_BOT_TOKEN.isBlank()) throw IllegalArgumentException("No discord bot token configured.")

    val client = FlannelClient()
    client.initialize()
}