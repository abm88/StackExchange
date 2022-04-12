package com.stackexchange.base

interface BaseDataModel{
    fun isEmpty(data: String) = data.isEmpty()
}

fun <T: BaseDataModel> T.wrapAroundSuccessResponse() = ResultResponse.Success(this)

