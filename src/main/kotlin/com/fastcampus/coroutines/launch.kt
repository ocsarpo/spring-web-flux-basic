package com.fastcampus.coroutines

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis
import kotlin.time.measureTime

fun main() {
    runBlocking<Unit> {
//        // 스레드 차단 없이 새 코루틴 시작, 그 결과로 job 반환
//        launch {
//            delay(500) // 코루틴에서 지원하는 일시중단 함수. Thread.sleep 과 유사하지만, 스레드는 차단하지 않은 채 동작 중단.
//            println("World!")
//        }
//
//        println("Hello")

        // launch 에 있는 코루틴의 상태 확인 또는 코루틴 실행/취소 기능.
        val job1: Job =launch {
            val elapsedTime = measureTimeMillis {
                delay(150)
            }

            println("async task-1 $elapsedTime ms")
        }
        job1.cancel()

        // 실행지연
        val job2: Job = launch(start = CoroutineStart.LAZY) {
            val elapsedTime = measureTimeMillis {
                delay(100)
            }

            println("async task-2 $elapsedTime ms")
        }

        println("start task-2 ")
        job2.start()
    }
}
