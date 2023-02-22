package com.fastcampus.springwebflux

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class HelloHandler {
    /*
    ServerRequest, ServerResponse : 둘다 불변 객체.
    내부 프로퍼티 변경 불가.

    핸들러 함수 작성 시 리액터의 퍼블리셔인 Mono 혹은 Flux 로 응답 본문을 작성하게 된다.
     */

    fun sayHello(req: ServerRequest): Mono<ServerResponse> {
        return ServerResponse.ok().bodyValue("Hello WebFlux")
    }
}
