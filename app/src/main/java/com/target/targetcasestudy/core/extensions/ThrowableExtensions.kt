package com.target.targetcasestudy.core.extensions

fun Throwable.isNetworkError() = this is java.io.IOException