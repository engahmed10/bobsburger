package com.example.bobsburger.presentation

import androidx.lifecycle.ViewModel
import com.example.bobsburger.data.response.BobBurgerResponse
import com.example.bobsburger.domain.usecase.GetBobBurgersCharsUseCase
import com.example.bobsburger.presentation.util.BobBurgerState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class BobBurgersViewModel @Inject
constructor(val getBobBurgersCharsUseCase: GetBobBurgersCharsUseCase):
    ViewModel()  {

    private var _burgerCharsStateFlow = MutableStateFlow<BobBurgerState<List<BobBurgerResponse>>>(
        BobBurgerState.Loading
    )
    val bobBurgerCharsStateFlow: StateFlow<BobBurgerState<List<BobBurgerResponse>>> = _burgerCharsStateFlow

    suspend fun getBobBurgersChars(){
       getBobBurgersCharsUseCase()
           .collect{
               _burgerCharsStateFlow.value =  it
       }
    }

}