package com.roula.kidslearning

import com.google.common.truth.Truth.assertThat
import com.tuwaiq.bookfinder.ValidationTest.Validation
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