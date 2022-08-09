package com.example.ryanrileymtgfinal2.api

import com.example.ryanrileymtgfinal2.view.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface CardRepository {
    suspend fun getBoosterList(): Flow<UIState>
    suspend fun getBoosterDetails(code: String): Flow<UIState>
    suspend fun getCardList(code: String): Flow<UIState>
//    suspend fun getCardDetails(Url: String): Flow<UIState>
}

class CardRepositoryImpl @Inject constructor(private val cards: Cards): CardRepository {
    override suspend fun getBoosterList(): Flow<UIState> =
        flow {
            try {
                val response = cards.getBoosterList()
                if (response.isSuccessful) {
                    emit(response.body()?.let {
                        UIState.Success(it)
                    } ?: throw Exception("Null Response"))
                } else {
                    throw Exception("Failed Network call")
                }
            } catch (e: Exception) {
                emit(UIState.Error(e))
            }
        }

    override suspend fun getBoosterDetails(code: String): Flow<UIState> =
        flow {
            try {
                val response = cards.getBoosterDetails(code = code)
                if (response.isSuccessful) {
                    emit( response.body()?.let {
                        UIState.Success(it)
                    } ?: throw Exception("Null Response"))
                } else throw Exception("Failed network call")
            } catch (e: Exception) {
                emit(UIState.Error(e))
            }
        }

    override suspend fun getCardList(code:String): Flow<UIState> =
        flow {
            try {
                val response = cards.getCardList(code = code)
                if (response.isSuccessful) {
                    emit(response.body()?.let {
                        UIState.Success(it)
                    } ?: throw Exception("Null Response"))
                }else throw Exception("Failed network call")
            } catch (e: Exception) {
                emit(UIState.Error(e))
            }
        }

//    override suspend fun getCardDetails(Url: String): Flow<UIState> =
//        flow {
//            try {
//                val response = cards.getCardList(Url)
//                if (response.isSuccessful) {
//                    emit( response.body()?.let {
//                        UIState.Success(it)
//                    } ?: throw Exception("Null Response"))
//                }else throw Exception("Failed network call")
//            } catch (e: Exception) {
//                emit(UIState.Error(e))
//            }
//        }
}