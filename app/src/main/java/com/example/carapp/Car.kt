package com.example.carapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Car(
    val name: String,
    val description: String,
    val photo: Int
) : Parcelable

