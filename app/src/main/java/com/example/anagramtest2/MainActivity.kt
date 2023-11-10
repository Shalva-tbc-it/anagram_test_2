package com.example.anagramtest2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.anagramtest2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var words: MutableList<String> = mutableListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onClick()

    }


    private fun onClick() {
        binding.apply {
            btnSave.setOnClickListener {
                if (!edAnagram.text.isNullOrBlank()) {
                    words.addAll(listOf(edAnagram.text.toString()))
                } else {
                    Toast.makeText(this@MainActivity, "Pleas field input", Toast.LENGTH_SHORT)
                        .show()
                }
            }
            btnOutput.setOnClickListener {

                val groupedAnagrams = groupAnagrams(words)

                val resultStringBuilder = StringBuilder()

                for (groups in groupedAnagrams) {
                    resultStringBuilder.append("[")
                    resultStringBuilder.append(groups.joinToString(", "))
                    resultStringBuilder.append("], ")
                    resultStringBuilder.append("Anagram size: ${groups.size}\n")
                    resultStringBuilder.append("\n")

                }
                val resultString = resultStringBuilder.removeSuffix(", ").toString()
                tvAnagramCount.text = resultString
            }
        }
    }

    private fun groupAnagrams(words: List<String>): List<List<String>> {
        val groupedAnagrams = mutableMapOf<String, MutableList<String>>()

        for (word in words) {
            val sortedWord = word.toCharArray().sorted().joinToString("")

            if (!groupedAnagrams.containsKey(sortedWord)) {
                groupedAnagrams[sortedWord] = mutableListOf(word)
            } else {
                groupedAnagrams[sortedWord]?.add(word)
            }
        }
        return groupedAnagrams.values.toList()
    }


}