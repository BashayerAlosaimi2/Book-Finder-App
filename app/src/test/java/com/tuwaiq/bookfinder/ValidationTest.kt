package com.roula.kidslearning

import com.google.common.truth.Truth.assertThat
import com.roula.kidslearning.util.Validation
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test

class ValidationTest {
    private lateinit var validation : Validation

    @Before
    fun setUp() {
        validation = Validation
    }

    @Test
    fun checkEmail(){
        val result = validation.emil("test12@gmail.com")
        assertThat(result).isTrue()
    }

}