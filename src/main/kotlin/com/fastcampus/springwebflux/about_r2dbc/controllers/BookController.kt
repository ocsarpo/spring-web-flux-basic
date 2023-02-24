package com.fastcampus.springwebflux.about_r2dbc.controllers

import com.fastcampus.springwebflux.about_r2dbc.entities.Book
import com.fastcampus.springwebflux.about_r2dbc.repositories.BookRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController("/r2dbc/books") // 빈 이름 겹쳐서 지정함.
@RequestMapping("/r2dbc/books") // 기본 패스 지정
class BookController(
    val bookRepository: BookRepository,
) {

    @GetMapping("/{name}")
    fun getByName(@PathVariable name: String): Mono<Book> {
        return bookRepository.findByName(name)
    }

    @PostMapping
    fun create(@RequestBody map: Map<String, Any>): Mono<Book> {
        val book = Book(
            name = map["name"].toString(),
            price = map["price"] as Int
        )

        return bookRepository.save(book)
    }
}
