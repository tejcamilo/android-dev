package com.example.guessinggame

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
    private val words = listOf("Android", "Activity", "Fragmenr")
    private val secretWord = words.random().uppercase()

    private val _secretWordDisplay = MutableLiveData<String>()
    val secretWordDisplay: LiveData<String>
        get() = _secretWordDisplay

    private var correctGUesses = ""

    private val _incorrectGuesses = MutableLiveData<String>("")
    val incorrectGuesses: LiveData<String>
        get() = _incorrectGuesses

    private val _livesLeft = MutableLiveData<Int>(8)
    val livesLeft: LiveData<Int>
        get() = _livesLeft

    init {
        _secretWordDisplay.value = deriveSecretWordDisplay()
    }

    private fun deriveSecretWordDisplay() : String {
        var display = ""
        secretWord.forEach {
            display += checkLetter(it.toString())
        }
        return display
    }

   private fun checkLetter(str : String) =  when (correctGUesses.contains(str)) {
        true -> str
        false -> "_"
    }

    fun makeGuess(guess : String) {
        if (guess.length == 1) {
            if (secretWord.contains(guess)) {
                correctGUesses += guess
                _secretWordDisplay.value = deriveSecretWordDisplay()
            } else {
                _incorrectGuesses.value += "$guess "
                _livesLeft.value = _livesLeft.value?.minus(1) // null check
            }
        }
    }

    fun isWon() = secretWord.equals(secretWordDisplay.value, true)

    fun isLost() = (livesLeft.value ?: 0) <= 0 // if the value is null, Elvis operator returns 0.
    // if livesLeft.value is null or 0, isLost() returns true

    fun wonLostMessage() : String {
        var message = ""
        if(isWon()) message = "You won!"
        else if (isLost()) message = "You lost!"
        message += " The word was $secretWord."
        return message
    }
}