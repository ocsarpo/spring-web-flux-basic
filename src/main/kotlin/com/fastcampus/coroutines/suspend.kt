package com.fastcampus.coroutines

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

suspend fun doSomething() {
    printHello()
}

// suspend 함수에서 코루틴 빌더(launch async 등 사용하려면 코루틴스코프를 사용)
// 현재 스레드가 블로킹되지 않고 코루틴이 동작함. (runBlocking과 차이)
suspend fun usingCoroutineBuilder() = coroutineScope {
    launch {
        delay(200)
        println("world")
    }

    launch {
        printHello()
    }
}

fun printHello() = println("Hello")

suspend fun suspendPrintHello() = doSomething()

fun main() {
    // 일시 중단이 가능한 함수.
    // suspend 함수에서는 일반함수 호출 가능.
    // 그 반대는 바로 호출 불가.
    // doSomething()

    runBlocking {
        doSomething()
        usingCoroutineBuilder()
    }
}
