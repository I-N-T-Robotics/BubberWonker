package com.rambots4571.rampage.function

data class Pair<E>(var first: E, var second: E) {
    fun swap() {
        val temp = first
        first = second
        second = temp
    }
}

