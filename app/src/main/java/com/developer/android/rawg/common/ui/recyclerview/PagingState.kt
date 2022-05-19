package com.developer.android.rawg.common.ui.recyclerview

sealed class PagingState {
    object Idle : PagingState()
    object Loading : PagingState()
    object InitialLoading : PagingState()
    class Error(val e: Throwable): PagingState()
}