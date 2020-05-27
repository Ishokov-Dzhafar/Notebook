package com.dzhafar.core_db_api.navigation

import android.os.Bundle
import androidx.navigation.NavOptions

data class NavCommand(
    val action: Int,
    var args: Bundle? = null,
    val navOptions: NavOptions? = null
)