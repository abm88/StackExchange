package com.stackexchange.base

interface BaseDataSource

open class ReadableDataSource<T> {

    open suspend fun read(): ResultResponse<T>{
        TODO("Implement reading datasource")
    }
}
open class WriteableDataSource<T> {
    open suspend fun write(t: T){
        // do nothing
    }

    open suspend fun read(query: String): ResultResponse<T>{
        TODO("Implement reading datasource")
    }
}
