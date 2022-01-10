package com.example.triviaapp.domain.utils

 data class Resource<T,Boolean,E:Exception>(
     var data:T? = null,
     var loading:Boolean? = null,
     var exception:E? = null
 )

