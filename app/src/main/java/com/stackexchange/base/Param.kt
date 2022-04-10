package com.stackexchange.base


abstract class Param

class AnyParam : Param()


sealed class StackExchangeParam: Param(){
    object GetUsers: StackExchangeParam()
    data class SearchUsers(val data: String = ""): StackExchangeParam()
}

