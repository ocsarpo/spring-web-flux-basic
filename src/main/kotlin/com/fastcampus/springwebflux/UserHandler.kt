package com.fastcampus.springwebflux

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class UserHandler {

    val users = listOf(
        User(id = 1, email = "user1@gmail.com"),
        User(id = 2, email = "user2@gmail.com"),
        User(id = 3, email = "user3@gmail.com"),
    )

    fun getUser(req: ServerRequest): Mono<ServerResponse> {
        return users.find { req.pathVariable("id").toLong() == it.id } // MVC 에서는 어노테이션으로 받아왔었는데~
            ?.let {
                ServerResponse.ok().bodyValue(it)
            } ?: ServerResponse.notFound().build()
    }

    fun getAll(req: ServerRequest): Mono<ServerResponse> {
        return ServerResponse.ok().bodyValue(users)
    }
}

data class User(val id: Long, val email: String)
