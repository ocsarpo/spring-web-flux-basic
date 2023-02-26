package com.fastcampus.coroutines

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun sum(a: Int, b: Int) = a + b

fun main() {

    // 비동기 작업의 결과 받아오기.
    runBlocking {
        val result1: Deferred<Int> = async {
            delay(100)
            sum(1, 3)
        }

        println("result1: ${result1.await()}")

        val result2 = async {
            delay(100)
            sum(2, 5)
        }

        println("result2: ${result2.await()}")
    }
}
