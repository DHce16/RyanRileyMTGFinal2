package com.example.ryanrileymtgfinal2.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ryanrileymtgfinal2.api.CardRepository
import com.example.ryanrileymtgfinal2.model.BoosterNode
import com.example.ryanrileymtgfinal2.model.CardData
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

    private val _boosterDetails = MutableLiveData<UIState>()
    val boosterDetails: LiveData<UIState> get() = _boosterDetails

    private val _cardList = MutableLiveData<UIState>()
    val carList: LiveData<UIState> get() = _cardList

    private val _cardDetails = MutableLiveData<UIState>()
    val cardDetails: LiveData<UIState> get() = _cardDetails

    lateinit var currentBooster: BoosterNode
    lateinit var currentCard: CardData

    fun getBoosterList() {
        viewModelSafeScope.launch (dispatcher) {
            repository.getBoosterList().collect {
                _boosterList.postValue(it)
            }
        }
    }

    fun getBoosterDetails(code: String) {
        viewModelScope.launch {
            repository.getBoosterDetails(code).collect {
                _boosterDetails.postValue(it)
            }
        }
    }

    fun getCardList(code: String){
        viewModelScope.launch (dispatcher) {
            repository.getCardList(code).collect() {
                _cardList.postValue(it)
            }
        }
    }

//    fun getCardDetails(Url: String){
//        viewModelScope.launch {
//            repository.getCardList(Url).collect {
//                _cardDetails.postValue(it)
//            }
//        }
//    }

    fun setLoadingState() { _boosterList.value = UIState.Loading}

    fun setDrawnLoadingState() { _cardList.value = UIState.Loading}

    fun setBoosterDetails(node: BoosterNode) {
        currentBooster = node
        _boosterDetails.value = UIState.Loading
    }

    fun setCardDetails(data: CardData) {
        currentCard = data
        _cardDetails.value =UIState.Loading
    }
}