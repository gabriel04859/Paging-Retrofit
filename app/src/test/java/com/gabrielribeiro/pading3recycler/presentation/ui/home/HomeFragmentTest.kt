package com.gabrielribeiro.pading3recycler.presentation.ui.home

import androidx.fragment.app.testing.launchFragmentInContainer
import com.gabrielribeiro.pading3recycler.R
import junit.framework.TestCase
import org.junit.Test

class HomeFragmentTest : TestCase() {


    @Test
    fun d() {
        val scenario = launchFragmentInContainer<HomeFragment>()
        scenario.onFragment { fragment ->
            fragment.arguments

        }
    }
}