package com.fastcampus.coroutines

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

fun main() {
    // 리액터의 Flux 처럼 여러 개의 값을 반환.
    runBlocking {
        val flow = simple()

        // 최종연산자.
        flow.collect {
            println(it)
        }
    }
}

fun simple(): Flow<Int> = flow {
    println("Flow started")

    for (i in 1..3) {
        delay(100)
        emit(i) // 메시지 통지.
    }
}
