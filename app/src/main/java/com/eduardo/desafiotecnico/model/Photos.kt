package com.eduardo.desafiotecnico.model

import com.google.gson.annotations.SerializedName

data class Photos(
    @SerializedName("url")
    val photoUrl: String
)