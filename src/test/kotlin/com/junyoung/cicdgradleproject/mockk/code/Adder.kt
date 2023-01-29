package com.junyoung.cicdgradleproject.mockk.code

class Adder {
    fun magnify(a: Int) = a
    fun add(a: Int, b: Int) = a + magnify(b)
}
