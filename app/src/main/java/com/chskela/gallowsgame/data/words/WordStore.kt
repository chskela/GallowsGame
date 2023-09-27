package com.chskela.gallowsgame.data.words

import android.content.Context
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

class WordStore(pathFile: String = "singular.txt", private val context: Context) {

    private var listOfWords: List<String> = readFile(pathFile) ?: emptyList()

    fun getRandomWord() = listOfWords.random()

    private fun readFile(pathFile: String): List<String>? {
        return try {
            val inputStream = context.assets.open(pathFile)
            val streamReader = InputStreamReader(inputStream, "UTF-8")
            BufferedReader(streamReader).readLines()
        } catch (e: IOException) {
            null
        }
    }
}