package com.gruise.data.remote

sealed class RemoteError : Exception() {
    abstract fun toStringForLog(): String
    abstract fun toStringForUser(): String

    data class ApiError(val code: Int, override val message: String? = null) : RemoteError() {
        override fun toStringForLog(): String {
            return "code: $code message: $message"
        }

        override fun toStringForUser(): String {
            return message.toString()
        }
    }

    data class NullError(override val message: String) : RemoteError() {
        override fun toStringForLog(): String {
            return message
        }

        override fun toStringForUser(): String {
            return "예기치 못한 오류가 발생하였습니다. \n잠시후 다시 시도해주세요."
        }

    }

    data class NetworkError(val fetchState: FetchState) : RemoteError() {
        override fun toStringForLog(): String {
            return "$fetchState"
        }

        override fun toStringForUser(): String {
            return when (fetchState) {
                is FetchState.BadInternet -> "인터넷 상태가 불안정합니다."
                is FetchState.WrongConnection -> "네트워크 연결이 잘못되었습니다."
                is FetchState.ParseError -> "데이터를 처리하는 중에 문제가 발생했습니다."
                is FetchState.Fail -> "네트워크 처리에 실패했습니다."
            }
        }

    }
}