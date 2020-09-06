package com.dzhafar.coreCommon.view

import android.app.Activity
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController

abstract class BaseFragment(@LayoutRes contentLayoutId: Int) : Fragment(contentLayoutId) {

    override fun onStop() {
        super.onStop()
        view?.clearFocus()
    }

    fun initToolbar(toolbar: Toolbar) {
        val appBarConfiguration = AppBarConfiguration.Builder(findNavController().graph).build()
        toolbar.setupWithNavController(findNavController(), appBarConfiguration)
    }

    private fun hideKeyboard() {
        val inputMethodManager = context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view?.windowToken, 0)
    }
}