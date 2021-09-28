package com.example.cryptocomposeapp.presentation.ui.coinlist

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocomposeapp.common.Resource
import com.example.cryptocomposeapp.domain.model.Coin
import com.example.cryptocomposeapp.domain.usecase.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(val useCase: GetCoinsUseCase): ViewModel() {

    private val _state = mutableStateOf(CoinListState())
    val state: State<CoinListState> = _state
    private val _editTextValue = mutableStateOf(TextFieldValue())
    val editTextValue: State<TextFieldValue> = _editTextValue

    private val _coinList = mutableStateOf(mutableListOf<Coin>())
    val coinList: State<MutableList<Coin>> = _coinList

    private val filteredList = mutableListOf<Coin>()
    private val holderList = mutableListOf<Coin>()

    private val _isHintVisible = mutableStateOf<Boolean>(true)
    val isHintVisible = _isHintVisible

    init {
        getCoins()
    }

    private fun getCoins() {
        useCase().onEach { result ->
            when(result) {
                is Resource.Error -> {
                    _state.value = CoinListState(error = result.message)
                }
                is Resource.Loading ->  _state.value = CoinListState(isLoading = true)
                is Resource.Success -> {
                    _state.value = CoinListState(success = result.data ?: emptyList())
                    filteredList.addAll(result.data?.toMutableList()!!)
                    holderList.addAll(result.data.toMutableList())
                    _coinList.value = result.data.toMutableList()
                }
            }
        }.launchIn(viewModelScope)
    }

    fun onTextChanged(value: TextFieldValue) {
        _editTextValue.value = value
        if (value.text.isEmpty()) {
            _coinList.value = holderList
            _isHintVisible.value = true
        } else {
            _isHintVisible.value = false
            _coinList.value = filteredList.filter { it.name.contains(value.text, true) || it.symbol.contains(value.text, true) }.toMutableList()
        }
    }
}