package com.chskela.gallowsgame.data

import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.ClassLoader.getSystemClassLoader

class WordStore(pathFile: String) {

    private var listOfWords: List<String> = readFile(pathFile)

    fun getRandomWord() = listOfWords.random()

    private fun readFile(pathFile: String): List<String> {
        val inputStream = getSystemClassLoader().getResourceAsStream(pathFile)

        return inputStream?.let {
            val streamReader = InputStreamReader(it, "UTF-8")
            BufferedReader(streamReader).readLines()
        } ?: listOf("ошибка")
    }
}