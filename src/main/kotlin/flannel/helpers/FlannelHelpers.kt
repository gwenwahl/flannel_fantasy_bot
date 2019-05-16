package flannel.helpers

import discord4j.core.`object`.entity.MessageChannel
import reactor.core.publisher.Mono

fun Mono<MessageChannel>.sendMessage(message: String){
    this.block()?.createMessage(message)?.block()
}