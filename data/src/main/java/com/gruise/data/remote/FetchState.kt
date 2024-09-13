package com.gruise.data.remote

sealed interface FetchState {
    data object BadInternet : FetchState
    data object ParseError : FetchState
    data object WrongConnection : FetchState
    data object Fail : FetchState
}