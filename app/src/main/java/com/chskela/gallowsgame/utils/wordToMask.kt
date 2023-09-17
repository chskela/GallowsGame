package com.chskela.gallowsgame.utils

fun String.wordToMask() =  replace(Regex("[А-яЁё]"), "_")