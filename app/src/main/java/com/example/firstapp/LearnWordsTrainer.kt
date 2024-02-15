package com.example.firstapp

data class Word(
    val original: String,
    val translate: String,
    var learned: Boolean = false,
)

data class Question(
    val variants: List<Word>,
    val correctAnswer: Word,
)

class LearnWordsTrainer {

    private val dictionary = listOf(
        Word("Encyclopedia","Энциклопедия"),
        Word("Phenomenon","Феномен"),
        Word("Cacophon","Какофония"),
        Word("Euphoria","Эйфория"),
        Word("Mellifluous","Мелодичный"),
        Word("Nebulous","Туманный"),
        Word("Resplendent","Великолепный"),
        Word("Labyrinth","Лабиринт"),
        Word("Exquisite","Изысканный"),
        Word("Enigmatic","Загадочный"),
        Word("Ineffable","Невыразимый"),
        Word("Pernicious","Губительный"),
        Word("Ubiquitous","Вездесущий"),
        Word("Radiance","Сияние"),
        Word("Blossom","Цветение"),
        Word("Affection","Нежность"),
        Word("Vision","Видение"),
        Word("Serenity","Спокойствие"),
        Word("Abyss","Бездна"),
        Word("Whispers","Шепот"),
        Word("Ethereal","Эфирный"),
        Word("Twilight","Сумерки"),
        Word("Harmony","Гармония"),
        Word("Tranquility","Безмятежность"),
        Word("Cascade","Водопад"),
    )

    private var currentQuestion: Question? = null

    fun getNextQuestion(): Question? {

        val notLearnedList = dictionary.filter { !it.learned }
        if (notLearnedList.isEmpty()) return null

        val questionWord =
            if (notLearnedList.size < NUMBER_OF_ANSWERS){
                val learnedList = dictionary.filter { it.learned }.shuffled()
                notLearnedList.shuffled()
                    .take(NUMBER_OF_ANSWERS) + learnedList
                    .take(NUMBER_OF_ANSWERS - notLearnedList.size)
            }else{
                notLearnedList.shuffled().take(NUMBER_OF_ANSWERS)
            }.shuffled()

        val correctAnswer = questionWord.random()

        currentQuestion = Question(
            variants = questionWord,
            correctAnswer = correctAnswer,
        )
        return currentQuestion
    }

    fun checkAnswer(userAnswerIndex: Int?): Boolean{
        return currentQuestion?.let{
            val correctAnswerId = it.variants.indexOf(it.correctAnswer)
            if (correctAnswerId == userAnswerIndex){
                it.correctAnswer.learned = true
                true
            }else{
                false
            }
        } ?: false
    }
}

const val NUMBER_OF_ANSWERS = 4
