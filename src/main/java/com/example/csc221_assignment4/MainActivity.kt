package com.example.csc221_assignment4

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var quizManager: QuizManager
    private lateinit var questionTextView: TextView
    private lateinit var scoreTextView: TextView
    private lateinit var answerButtons: List<Button>

    fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        quizManager = QuizManager()
        questionTextView = findViewById(R.id.question_text_view)
        scoreTextView = findViewById(R.id.score_text_view)
        answerButtons = listOf(
            findViewById(R.id.answer_button_one),
            findViewById(R.id.answer_button_two),
            findViewById(R.id.answer_button_three),
            findViewById(R.id.answer_button_four)
        )

        answerButtons.forEachIndexed { index, button ->
            button.setOnClickListener { handleAnswerButtonClick(index) }
        }

        updateQuestion()
    }

    private fun handleAnswerButtonClick(answerIndex: Int) {
        val isCorrect = quizManager.submitAnswer(answerIndex)
        if (isCorrect) {
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Incorrect!", Toast.LENGTH_SHORT).show()
        }
        updateQuestion()
    }

    private fun updateQuestion() {
        val currentQuestion = quizManager.getCurrentQuestion()
        questionTextView.text = currentQuestion.text
        scoreTextView.text = "Score: ${quizManager.score}"
        currentQuestion.options.forEachIndexed { index, option ->
            answerButtons[index].text = option
        }
    }
}
