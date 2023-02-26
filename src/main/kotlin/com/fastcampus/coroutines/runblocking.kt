package com.fastcampus.coroutines

import kotlinx.coroutines.runBlocking

fun main() {

    // 스레드 차단. 블록 내 코드가 모두 실행될 때 까지 블락.
    // 특정 라이브러리/코드에서 코루틴을 지원하지 않는 경우 (테스트 코드, 스프링 배치 등)
    // 블로킹으로 동작하게 하려는 목적일 때 사용함.
    runBlocking {
        println("Hello")
        println(Thread.currentThread().name) // 실행환경설정 VM Options `-Dkotlinx.coroutines.debug` 추가하면 코루틴 이름 나옴
    }

    println("World")
    println(Thread.currentThread().name)
}
