package com.fastcampus.springwebflux.about_r2dbc.repositories

import com.fastcampus.springwebflux.about_r2dbc.entities.Book
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
interface BookRepository : ReactiveCrudRepository<Book, Long> {

    fun findByName(name: String): Mono<Book>
}
