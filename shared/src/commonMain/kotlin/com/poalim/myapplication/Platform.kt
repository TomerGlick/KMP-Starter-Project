package com.poalim.myapplication

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform