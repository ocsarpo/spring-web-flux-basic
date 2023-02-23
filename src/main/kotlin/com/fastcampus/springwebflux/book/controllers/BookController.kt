package com.fastcampus.springwebflux.book.controllers

import com.fastcampus.springwebflux.book.services.Book
import com.fastcampus.springwebflux.book.services.BookService
import org.springframework.web.bind.annotation.*
import org.springframework.web.reactive.function.server.ServerRequest
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
class BookController(
    private val bookService: BookService,
) {

    // 컨트롤러 함수 반환타입이 Flux, Mono 인 경우
    // WebFlux 에서 자동으로 subscribe()  (리액터: 터미널연산자: 중간연산자들을 모두 실행시킴) 를 호출한다.
    @GetMapping("/books")
    fun getAll(): Flux<Book> {
        return bookService.getAll()
    }

    @GetMapping("/books/{id}")
    fun get(@PathVariable id: Int): Mono<Book> {
        return bookService.get(id)
    }

    @PostMapping("/books")
    fun add(@RequestBody request: Map<String, Any>): Mono<Book> = bookService.add(request)

    @DeleteMapping("/books/{id}")
    fun delete(@PathVariable id: Int): Mono<Void> { // java 의 void
        return bookService.delete(id)
    }
}
