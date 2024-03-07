package com.example.quizztest

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.quiztestapp2.R

data class Question(val questionTextResId: Int, val answer: Boolean)

private val questionBank = listOf(
    Question(R.string.question_1, false),
    Question(R.string.question_2, true),
    Question(R.string.question_3, false),
    Question(R.string.question_4, true),

    )

private var currentIndex = 0
private var correctAnswers = 0
private var incorrectAnswers = 0

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val questionTextView: TextView = findViewById(R.id.question)
        val btnTrue: Button = findViewById(R.id.btn_true)
        val btnFalse: Button = findViewById(R.id.btn_false)

        btnTrue.setOnClickListener {
            checkAnswer(true)
        }

        btnFalse.setOnClickListener {
            checkAnswer(false)
        }

        questionTextView.setText(questionBank[currentIndex].questionTextResId)

        findViewById<Button>(R.id.btn_Next).setOnClickListener {
            currentIndex = (currentIndex + 1) % questionBank.size
            questionTextView.setText(questionBank[currentIndex].questionTextResId)
        }
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = questionBank[currentIndex].answer

        if (userAnswer == correctAnswer) {
            Toast.makeText(
                this,
                "Bien hecho...!",
                Toast.LENGTH_SHORT
            ).show()
            correctAnswers++
        } else {
            Toast.makeText(
                this, "Mal",
                Toast.LENGTH_SHORT
            ).show()
            incorrectAnswers++
        }
        if (currentIndex == questionBank.size - 1) {
            showFinalResult()
        }
    }

    private fun showFinalResult() {
        val resultMessage = "Resultados:\nRespuestas Correctas: $correctAnswers\nRespuestas Incorrectas: $incorrectAnswers"
        Toast.makeText(
            this,
            resultMessage,
            Toast.LENGTH_LONG
        ).show()


        findViewById<TextView>(R.id.finalText).setText(resultMessage)
        findViewById<Button>(R.id.btn_Next).visibility = View.INVISIBLE

    }
}