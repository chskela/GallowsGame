package com.chskela.gallowsgame.data

import android.content.Context
import java.io.BufferedReader
import java.io.InputStreamReader

class WordStore(pathFile: String, private val context: Context) {

    private var listOfWords: List<String> = readFile(pathFile)

    fun getRandomWord() = listOfWords.random()

    private fun readFile(pathFile: String): List<String> {
        val inputStream = context.assets.open(pathFile)
        val streamReader = InputStreamReader(inputStream, "UTF-8")

        return BufferedReader(streamReader).readLines()
    }
}