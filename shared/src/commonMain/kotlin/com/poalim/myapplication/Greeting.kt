package com.poalim.myapplication

class Greeting {
    private val platform: Platform = getPlatform()

    fun greet(): String {
        return "Hello, ${platform.name}!"
    }

    fun sayHello(): String = "Hello from Kotlin Multiplatform!"
}