package com.fastcampus.springwebflux.book.controllers

import com.fastcampus.springwebflux.book.services.Book
import org.slf4j.LoggerFactory
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.http.HttpMethod.GET
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux

@RestController
class WebClientExampleController {

    val url = "http://localhost:8080/books"

    val log = LoggerFactory.getLogger(javaClass)

    @GetMapping("/books/block")
    fun getBooksBlockingWay(): List<Book> {
        // RestTemplate: 스프링에서 제공하는 블로킹 방식의 HTTP Client.
        // 스프링 5 부터 deprecated. (WebClient 사용 권고)

        // 블록킹 방식은 순차적으로 실행되기 때문에 코드를 파악하기 쉬운 장점.
        // 다만, 응답을 받을 때 까지 스레드블록킹되어 해당 스레드는 다른 일을 하지 못함.

        log.info("Start RestTemplate")

        val restTemplate = RestTemplate()
        val response = restTemplate.exchange(
            url,
            GET,
            null,
            object : ParameterizedTypeReference<List<Book>>() {}
        )

        val result = response.body!!

        log.info("result : {}", result)
        log.info("Finish RestTemplate")

        return result
    }

    // WebClient: 스프링에서 제공하는 리액티브 기반 논블로킹 HTTP 클라이언트.
    // 스프링 5 부터 restTemplate 을 대체함.
    // 블로킹, 논블로킹 두 방식 모두 사용 가능.
    // 스레드가 별도 응답을 기다리지 않으므로, restTemplate 에 비해 부하가 적다. (context switch 비용 감소)
    // 더 적은 메모리로 처리 가능
    // 동시에 여러 서버에 대한 호출이 필요한 경우 순차적으로 처리하는 restTemplate 에 비해 빠르게 처리할 수 있다.
    @GetMapping("/books/nonblock")
    fun getBooksNonBlockingWay(): Flux<Book> {
        log.info("Start WebClient")
        val flux = WebClient.create()
            .get()
            .uri(url)
            .retrieve()
            .bodyToFlux(Book::class.java)
            .map {
                log.info("result: {}", it)
                it
            }

        log.info("Finish WebClient")

        return flux
    }
}
