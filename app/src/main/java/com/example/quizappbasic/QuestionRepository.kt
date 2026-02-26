package com.example.quizappbasic

class QuestionRepository(private val context: Context) {

    fun getAllQuestions(): List<Question> {
        return listOf(
            Question(
                theme = Theme.PELICULAS,
                questionText = context.findViewById(R.id.),
                answers = mutableListOf(
                    Answer(context.getString(R.string.q_ciencia_1_correct), true),
                    Answer(context.getString(R.string.q_ciencia_1_wrong1), false),
                    Answer(context.getString(R.string.q_ciencia_1_wrong2), false),
                    Answer(context.getString(R.string.q_ciencia_1_wrong3), false)
                )
            ),
            // Agrega aquí las demás preguntas (5 por tema)
        )
    }
}