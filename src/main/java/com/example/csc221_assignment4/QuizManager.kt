package com.example.csc221_assignment4

class QuizManager {
    private val questions: List<Question> = listOf(
        Question("What is the capital of France?", listOf("Paris", "Madrid", "Berlin", "Rome"), 0),
        Question("Who wrote 'Hamlet'?", listOf("William Shakespeare", "Charles Dickens", "Leo Tolstoy", "Mark Twain"), 0),
        Question("What is the result of 3 * 3?", listOf("6", "9", "12", "15"), 1),
        Question("Which planet is known as the Red Planet?", listOf("Earth", "Venus", "Mars", "Jupiter"), 2),
        Question("What is the largest ocean on Earth?", listOf("Atlantic", "Indian", "Arctic", "Pacific"), 3)
    )
    var score: Int = 0
    private var currentQuestionIndex: Int = 0

    fun getCurrentQuestion(): Question = questions[currentQuestionIndex]

    fun submitAnswer(answerIndex: Int): Boolean {
        val currentQuestion = getCurrentQuestion()
        val isCorrect = answerIndex == currentQuestion.correctAnswerIndex
        if (isCorrect) score++
        currentQuestionIndex = (currentQuestionIndex + 1) % questions.size
        return isCorrect
    }
}
