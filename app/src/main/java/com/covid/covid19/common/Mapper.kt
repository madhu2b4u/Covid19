package com.covid.covid19.common

interface Mapper<T, E> {

    fun from(e: E): T

    fun to(t: T): E

}