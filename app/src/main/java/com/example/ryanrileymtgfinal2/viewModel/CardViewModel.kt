package com.example.ryanrileymtgfinal2.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ryanrileymtgfinal2.api.CardRepository
import com.example.ryanrileymtgfinal2.view.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

const val TAG = "CardViewModel"
@HiltViewModel
class CardViewModel @Inject constructor(
    private val repository: CardRepository,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val viewModelSafeScope by lazy {
        viewModelScope + coroutineExceptionHandler
    }

    private val coroutineExceptionHandler by lazy {
        CoroutineExceptionHandler {coroutineContext, throwable ->
            Log.e(TAG, "Context: $coroutineContext\nMessage: ${throwable.localizedMessage}", throwable)
        }
    }

    private val _boosterList = MutableLiveData<UIState>()
    val boosterList: LiveData<UIState> get() = _boosterList

    fun getBoosterList() {
        viewModelSafeScope.launch (dispatcher) {
            repository.getBoosterList().collect {
                _boosterList.postValue(it)
            }
        }
    }

    fun setLoadingState() { _boosterList.value = UIState.Loading}
}