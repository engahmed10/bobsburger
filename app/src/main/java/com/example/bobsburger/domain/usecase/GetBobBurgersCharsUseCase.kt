package com.example.bobsburger.domain.usecase

import com.example.bobsburger.data.response.BobBurgerResponse
import com.example.bobsburger.domain.BobBurgerRepository
import com.example.bobsburger.presentation.util.BobBurgerState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetBobBurgersCharsUseCase @Inject constructor(
    private val repository: BobBurgerRepository
) {
    operator fun invoke(): Flow<BobBurgerState<List<BobBurgerResponse>>> = flow {
        emit(BobBurgerState.Loading)
        val result = repository.getCharacters()
        if (result.isSuccessful) {
            emit(BobBurgerState.Success(result.body()!!))
        } else {
            emit(BobBurgerState.Error(result.errorBody().toString() ?: "Unknown error"))
        }
    }
}