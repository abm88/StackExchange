package com.stackexchange.error

sealed class ErrorEntity(val message: String) {
    class Network( message: String) : ErrorEntity(message){
        override fun toString(): String {
            return this.javaClass.canonicalName ?: ""
        }
    }

    class NotFound(message: String) : ErrorEntity(message){
        override fun toString(): String {
            return this.javaClass.canonicalName ?: ""
        }
    }

    class AccessDenied(message: String) : ErrorEntity(message){
        override fun toString(): String {
            return this.javaClass.canonicalName ?: ""
        }
    }

    class ServiceUnavailable(message: String) : ErrorEntity(message){
        override fun toString(): String {
            return this.javaClass.canonicalName ?: ""
        }
    }

    object NoError: ErrorEntity(""){
        override fun toString(): String {
            return this.javaClass.canonicalName ?: ""
        }
    }

    class Unknown(message: String) : ErrorEntity(message){
        override fun toString(): String {
            return this.javaClass.canonicalName ?: ""
        }
    }


    fun isError(): Boolean = this != NoError
}
