package com.fastcampus.springwebflux

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions.route
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router

// 요청과 응답을 처리할 수 있는 라우터 클래스.

@Configuration // 라우터 함수는 Configuration 어노테이션을 사용하여 설정으로 등록해야 한다.
class Router {

    // 클라 요청을 라우팅하고 처리할 수 있는 람다기반 프로그래밍 모델: 함수형 엔드포인트를 제공한다.
    // 함수형 엔드포인트는 요청을 분석해 핸들러로 라우팅하는 라우터 함수
    // 요청 객체를 전달받아 응답을 제공하는 핸들러 함수로 구성.

    @Bean
    fun helloRouter(handler: HelloHandler): RouterFunction<ServerResponse> = // RouterFunction 을 반환하는 Bean 으로 등록해야 함.
        route()
            .GET("/", handler::sayHello)
            .build()

    // 빈의 이름을 다르게 하여 여러개 등록 가능.
    @Bean
    fun userRouter(handler: UserHandler): RouterFunction<ServerResponse> {
        /**
         *
         * return route()
            .GET("/users/{id}", handler::getUser)
            .GET("/users", handler::getAll)
            .build()
         */

        // 중첩 라우트 사용
        return router {
            "/users".nest {
                GET("/{id}", handler::getUser)
                GET("", handler::getAll)
            }
        }
    }
}
