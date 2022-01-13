package com.roula.kidslearning.util

object Validation {
    private val EMAIL_PATTERN = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    fun emil (emil : String):Boolean{
if (emil.matches(EMAIL_PATTERN.toRegex()))
    return true
        return false
    }
}