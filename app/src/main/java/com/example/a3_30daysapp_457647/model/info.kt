package com.example.a3_30daysapp_457647.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class info(
    val indexResourceId:Int,
    @StringRes val stringResourceId:Int,
    @StringRes val countryResourceId:Int,
    @StringRes val infoResourceId:Int,
    @DrawableRes val imageResourceID: Int
)
