package com.fastcampus.springwebflux.about_r2dbc.entities

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

//
@Table("books")
data class Book(
    @Id
    val id: Long? = null,

    @Column
    val name: String,

    @Column
    val price: Int,
)
