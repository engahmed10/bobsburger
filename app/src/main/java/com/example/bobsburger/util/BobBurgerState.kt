package com.example.bobsburger.util

import com.example.bobsburger.data.response.BobBurgerResponse

sealed class BobBurgerState< out T> {
    object Loading : BobBurgerState<Nothing>()
    data class Success<out T>(val data: T) : BobBurgerState<T>()
    data class Error(val message: String) : BobBurgerState<Nothing>()
}