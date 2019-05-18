@file:JvmName("FlannelBotApplication")
package flannel

fun main(args : Array<String>) {
    val client = FlannelClient()
    client.initialize()
}