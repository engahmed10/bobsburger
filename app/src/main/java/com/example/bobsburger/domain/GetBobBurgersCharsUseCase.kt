package com.example.bobsburger.domain

import android.util.Log
import androidx.compose.material3.BottomAppBarState
import com.example.bobsburger.data.repository.BobBurgerRepository
import com.example.bobsburger.data.response.BobBurgerResponse
import com.example.bobsburger.util.BobBurgerState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class GetBobBurgersCharsUseCase @Inject constructor(
    private val repository: BobBurgerRepository
) {
    operator fun invoke(): Flow<BobBurgerState<List<BobBurgerResponse>>> =  flow{
       emit(BobBurgerState.Loading)
        val result = repository.getCharacters()
        if (result.isSuccessful){
            Log.d("MY_TAG", "invoke: ${result.body()?.get(0)}")
             emit(BobBurgerState.Success(result.body()!!))
        }else{
             emit(BobBurgerState.Error(result.errorBody().toString() ?: "Unknown error"))
        }
    }
}