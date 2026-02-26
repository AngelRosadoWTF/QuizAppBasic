package com.example.quizappbasic

import android.content.Context

class QuestionRepository(private val context: Context) {
    fun getAllQuestions(): List<Question> {
        return listOf(
            Question(
                theme = Theme.PELICULAS,
                questionText = context.getString(R.string.question1_t1),
                answers = mutableListOf(
                    Answer(context.getString(R.string.answer_w1_q1_t1), false),
                    Answer(context.getString(R.string.answer_w2_q1_t1), false),
                    Answer(context.getString(R.string.answer_w3_q1_t1), false),
                    Answer(context.getString(R.string.answer_c_q1_t1), true)
                )
            ),
            Question(
                theme = Theme.PELICULAS,
                questionText = context.getString(R.string.question2_t1),
                answers = mutableListOf(
                    Answer(context.getString(R.string.answer_w1_q2_t1), false),
                    Answer(context.getString(R.string.answer_w2_q2_t1), false),
                    Answer(context.getString(R.string.answer_w3_q2_t1), false),
                    Answer(context.getString(R.string.answer_c_q2_t1), true)
                )
            ),
            Question(
                theme = Theme.PELICULAS,
                questionText = context.getString(R.string.question3_t1),
                answers = mutableListOf(
                    Answer(context.getString(R.string.answer_w1_q3_t1), false),
                    Answer(context.getString(R.string.answer_w2_q3_t1), false),
                    Answer(context.getString(R.string.answer_w3_q3_t1), false),
                    Answer(context.getString(R.string.answer_c_q3_t1), true)
                )
            ),
            Question(
                theme = Theme.PELICULAS,
                questionText = context.getString(R.string.question4_t1),
                answers = mutableListOf(
                    Answer(context.getString(R.string.answer_w1_q4_t1), false),
                    Answer(context.getString(R.string.answer_w2_q4_t1), false),
                    Answer(context.getString(R.string.answer_w3_q4_t1), false),
                    Answer(context.getString(R.string.answer_c_q4_t1), true)
                )
            ),
            Question(
                theme = Theme.PELICULAS,
                questionText = context.getString(R.string.question5_t1),
                answers = mutableListOf(
                    Answer(context.getString(R.string.answer_w1_q5_t1), false),
                    Answer(context.getString(R.string.answer_w2_q5_t1), false),
                    Answer(context.getString(R.string.answer_w3_q5_t1), false),
                    Answer(context.getString(R.string.answer_c_q5_t1), true)
                )
            ),
            Question(
                theme = Theme.PELICULAS,
                questionText = context.getString(R.string.question6_t1),
                answers = mutableListOf(
                    Answer(context.getString(R.string.answer_w1_q6_t1), false),
                    Answer(context.getString(R.string.answer_w2_q6_t1), false),
                    Answer(context.getString(R.string.answer_w3_q6_t1), false),
                    Answer(context.getString(R.string.answer_c_q6_t1), true)
                )
            ),
            Question(
                theme = Theme.PELICULAS,
                questionText = context.getString(R.string.question7_t1),
                answers = mutableListOf(
                    Answer(context.getString(R.string.answer_w1_q7_t1), false),
                    Answer(context.getString(R.string.answer_w2_q7_t1), false),
                    Answer(context.getString(R.string.answer_w3_q7_t1), false),
                    Answer(context.getString(R.string.answer_c_q7_t1), true)
                )
            ),
            Question(
                theme = Theme.PELICULAS,
                questionText = context.getString(R.string.question8_t1),
                answers = mutableListOf(
                    Answer(context.getString(R.string.answer_w1_q8_t1), false),
                    Answer(context.getString(R.string.answer_w2_q8_t1), false),
                    Answer(context.getString(R.string.answer_w3_q8_t1), false),
                    Answer(context.getString(R.string.answer_c_q8_t1), true)
                )
            ),
            Question(
                theme = Theme.PELICULAS,
                questionText = context.getString(R.string.question9_t1),
                answers = mutableListOf(
                    Answer(context.getString(R.string.answer_w1_q9_t1), false),
                    Answer(context.getString(R.string.answer_w2_q9_t1), false),
                    Answer(context.getString(R.string.answer_w3_q9_t1), false),
                    Answer(context.getString(R.string.answer_c_q9_t1), true)
                )
            ),
            Question(
                theme = Theme.PELICULAS,
                questionText = context.getString(R.string.question10_t1),
                answers = mutableListOf(
                    Answer(context.getString(R.string.answer_w1_q10_t1), false),
                    Answer(context.getString(R.string.answer_w2_q10_t1), false),
                    Answer(context.getString(R.string.answer_w3_q10_t1), false),
                    Answer(context.getString(R.string.answer_c_q10_t1), true)
                )
            ),
            Question(
                theme = Theme.VIDEOJUEGOS,
                questionText = context.getString(R.string.question1_t2),
                answers = mutableListOf(
                    Answer(context.getString(R.string.answer_w1_q1_t2), false),
                    Answer(context.getString(R.string.answer_w2_q1_t2), false),
                    Answer(context.getString(R.string.answer_w3_q1_t2), false),
                    Answer(context.getString(R.string.answer_c_q1_t2), true)
                )
            ),
            Question(
                theme = Theme.VIDEOJUEGOS,
                questionText = context.getString(R.string.question2_t2),
                answers = mutableListOf(
                    Answer(context.getString(R.string.answer_w1_q2_t2), false),
                    Answer(context.getString(R.string.answer_w2_q2_t2), false),
                    Answer(context.getString(R.string.answer_w3_q2_t2), false),
                    Answer(context.getString(R.string.answer_c_q2_t2), true)
                )
            ),
            Question(
                theme = Theme.VIDEOJUEGOS,
                questionText = context.getString(R.string.question3_t2),
                answers = mutableListOf(
                    Answer(context.getString(R.string.answer_w1_q3_t2), false),
                    Answer(context.getString(R.string.answer_w2_q3_t2), false),
                    Answer(context.getString(R.string.answer_w3_q3_t2), false),
                    Answer(context.getString(R.string.answer_c_q3_t2), true)
                )
            ),
            Question(
                theme = Theme.VIDEOJUEGOS,
                questionText = context.getString(R.string.question4_t2),
                answers = mutableListOf(
                    Answer(context.getString(R.string.answer_w1_q4_t2), false),
                    Answer(context.getString(R.string.answer_w2_q4_t2), false),
                    Answer(context.getString(R.string.answer_w3_q4_t2), false),
                    Answer(context.getString(R.string.answer_c_q4_t2), true)
                )
            ),
            Question(
                theme = Theme.VIDEOJUEGOS,
                questionText = context.getString(R.string.question5_t2),
                answers = mutableListOf(
                    Answer(context.getString(R.string.answer_w1_q5_t2), false),
                    Answer(context.getString(R.string.answer_w2_q5_t2), false),
                    Answer(context.getString(R.string.answer_w3_q5_t2), false),
                    Answer(context.getString(R.string.answer_c_q5_t2), true)
                )
            ),
            Question(
                theme = Theme.VIDEOJUEGOS,
                questionText = context.getString(R.string.question6_t2),
                answers = mutableListOf(
                    Answer(context.getString(R.string.answer_w1_q6_t2), false),
                    Answer(context.getString(R.string.answer_w2_q6_t2), false),
                    Answer(context.getString(R.string.answer_w3_q6_t2), false),
                    Answer(context.getString(R.string.answer_c_q6_t2), true)
                )
            ),
            Question(
                theme = Theme.VIDEOJUEGOS,
                questionText = context.getString(R.string.question7_t2),
                answers = mutableListOf(
                    Answer(context.getString(R.string.answer_w1_q7_t2), false),
                    Answer(context.getString(R.string.answer_w2_q7_t2), false),
                    Answer(context.getString(R.string.answer_w3_q7_t2), false),
                    Answer(context.getString(R.string.answer_c_q7_t2), true)
                )
            ),
            Question(
                theme = Theme.VIDEOJUEGOS,
                questionText = context.getString(R.string.question8_t2),
                answers = mutableListOf(
                    Answer(context.getString(R.string.answer_w1_q8_t2), false),
                    Answer(context.getString(R.string.answer_w2_q8_t2), false),
                    Answer(context.getString(R.string.answer_w3_q8_t2), false),
                    Answer(context.getString(R.string.answer_c_q8_t2), true)
                )
            ),
            Question(
                theme = Theme.VIDEOJUEGOS,
                questionText = context.getString(R.string.question9_t2),
                answers = mutableListOf(
                    Answer(context.getString(R.string.answer_w1_q9_t2), false),
                    Answer(context.getString(R.string.answer_w2_q9_t2), false),
                    Answer(context.getString(R.string.answer_w3_q9_t2), false),
                    Answer(context.getString(R.string.answer_c_q9_t2), true)
                )
            ),
            Question(
                theme = Theme.VIDEOJUEGOS,
                questionText = context.getString(R.string.question10_t2),
                answers = mutableListOf(
                    Answer(context.getString(R.string.answer_w1_q10_t2), false),
                    Answer(context.getString(R.string.answer_w2_q10_t2), false),
                    Answer(context.getString(R.string.answer_w3_q10_t2), false),
                    Answer(context.getString(R.string.answer_c_q10_t2), true)
                )
            ),
            Question(
                theme = Theme.GEOGRAFIA,
                questionText = context.getString(R.string.question1_t3),
                answers = mutableListOf(
                    Answer(context.getString(R.string.answer_w1_q1_t3), false),
                    Answer(context.getString(R.string.answer_w2_q1_t3), false),
                    Answer(context.getString(R.string.answer_w3_q1_t3), false),
                    Answer(context.getString(R.string.answer_c_q1_t3), true)
                )
            ),
            Question(
                theme = Theme.GEOGRAFIA,
                questionText = context.getString(R.string.question2_t3),
                answers = mutableListOf(
                    Answer(context.getString(R.string.answer_w1_q2_t3), false),
                    Answer(context.getString(R.string.answer_w2_q2_t3), false),
                    Answer(context.getString(R.string.answer_w3_q2_t3), false),
                    Answer(context.getString(R.string.answer_c_q2_t3), true)
                )
            ),
            Question(
                theme = Theme.GEOGRAFIA,
                questionText = context.getString(R.string.question3_t3),
                answers = mutableListOf(
                    Answer(context.getString(R.string.answer_w1_q3_t3), false),
                    Answer(context.getString(R.string.answer_w2_q3_t3), false),
                    Answer(context.getString(R.string.answer_w3_q3_t3), false),
                    Answer(context.getString(R.string.answer_c_q3_t3), true)
                )
            ),
            Question(
                theme = Theme.GEOGRAFIA,
                questionText = context.getString(R.string.question4_t3),
                answers = mutableListOf(
                    Answer(context.getString(R.string.answer_w1_q4_t3), false),
                    Answer(context.getString(R.string.answer_w2_q4_t3), false),
                    Answer(context.getString(R.string.answer_w3_q4_t3), false),
                    Answer(context.getString(R.string.answer_c_q4_t3), true)
                )
            ),
            Question(
                theme = Theme.GEOGRAFIA,
                questionText = context.getString(R.string.question5_t3),
                answers = mutableListOf(
                    Answer(context.getString(R.string.answer_w1_q5_t3), false),
                    Answer(context.getString(R.string.answer_w2_q5_t3), false),
                    Answer(context.getString(R.string.answer_w3_q5_t3), false),
                    Answer(context.getString(R.string.answer_c_q5_t3), true)
                )
            ),
            Question(
                theme = Theme.GEOGRAFIA,
                questionText = context.getString(R.string.question6_t3),
                answers = mutableListOf(
                    Answer(context.getString(R.string.answer_w1_q6_t3), false),
                    Answer(context.getString(R.string.answer_w2_q6_t3), false),
                    Answer(context.getString(R.string.answer_w3_q6_t3), false),
                    Answer(context.getString(R.string.answer_c_q6_t3), true)
                )
            ),
            Question(
                theme = Theme.GEOGRAFIA,
                questionText = context.getString(R.string.question7_t3),
                answers = mutableListOf(
                    Answer(context.getString(R.string.answer_w1_q7_t3), false),
                    Answer(context.getString(R.string.answer_w2_q7_t3), false),
                    Answer(context.getString(R.string.answer_w3_q7_t3), false),
                    Answer(context.getString(R.string.answer_c_q7_t3), true)
                )
            ),
            Question(
                theme = Theme.GEOGRAFIA,
                questionText = context.getString(R.string.question8_t3),
                answers = mutableListOf(
                    Answer(context.getString(R.string.answer_w1_q8_t3), false),
                    Answer(context.getString(R.string.answer_w2_q8_t3), false),
                    Answer(context.getString(R.string.answer_w3_q8_t3), false),
                    Answer(context.getString(R.string.answer_c_q8_t3), true)
                )
            ),
            Question(
                theme = Theme.GEOGRAFIA,
                questionText = context.getString(R.string.question9_t3),
                answers = mutableListOf(
                    Answer(context.getString(R.string.answer_w1_q9_t3), false),
                    Answer(context.getString(R.string.answer_w2_q9_t3), false),
                    Answer(context.getString(R.string.answer_w3_q9_t3), false),
                    Answer(context.getString(R.string.answer_c_q9_t3), true)
                )
            ),
            Question(
                theme = Theme.GEOGRAFIA,
                questionText = context.getString(R.string.question10_t3),
                answers = mutableListOf(
                    Answer(context.getString(R.string.answer_w1_q10_t3), false),
                    Answer(context.getString(R.string.answer_w2_q10_t3), false),
                    Answer(context.getString(R.string.answer_w3_q10_t3), false),
                    Answer(context.getString(R.string.answer_c_q10_t3), true)
                )
            ),
            Question(
                theme = Theme.PROGRAMACION,
                questionText = context.getString(R.string.question1_t4),
                answers = mutableListOf(
                    Answer(context.getString(R.string.answer_w1_q1_t4), false),
                    Answer(context.getString(R.string.answer_w2_q1_t4), false),
                    Answer(context.getString(R.string.answer_w3_q1_t4), false),
                    Answer(context.getString(R.string.answer_c_q1_t4), true)
                )
            ),
            Question(
                theme = Theme.PROGRAMACION,
                questionText = context.getString(R.string.question2_t4),
                answers = mutableListOf(
                    Answer(context.getString(R.string.answer_w1_q2_t4), false),
                    Answer(context.getString(R.string.answer_w2_q2_t4), false),
                    Answer(context.getString(R.string.answer_w3_q2_t4), false),
                    Answer(context.getString(R.string.answer_c_q2_t4), true)
                )
            ),
            Question(
                theme = Theme.PROGRAMACION,
                questionText = context.getString(R.string.question3_t4),
                answers = mutableListOf(
                    Answer(context.getString(R.string.answer_w1_q3_t4), false),
                    Answer(context.getString(R.string.answer_w2_q3_t4), false),
                    Answer(context.getString(R.string.answer_w3_q3_t4), false),
                    Answer(context.getString(R.string.answer_c_q3_t4), true)
                )
            ),
            Question(
                theme = Theme.PROGRAMACION,
                questionText = context.getString(R.string.question4_t4),
                answers = mutableListOf(
                    Answer(context.getString(R.string.answer_w1_q4_t4), false),
                    Answer(context.getString(R.string.answer_w2_q4_t4), false),
                    Answer(context.getString(R.string.answer_w3_q4_t4), false),
                    Answer(context.getString(R.string.answer_c_q4_t4), true)
                )
            ),
            Question(
                theme = Theme.PROGRAMACION,
                questionText = context.getString(R.string.question5_t4),
                answers = mutableListOf(
                    Answer(context.getString(R.string.answer_w1_q5_t4), false),
                    Answer(context.getString(R.string.answer_w2_q5_t4), false),
                    Answer(context.getString(R.string.answer_w3_q5_t4), false),
                    Answer(context.getString(R.string.answer_c_q5_t4), true)
                )
            ),
            Question(
                theme = Theme.PROGRAMACION,
                questionText = context.getString(R.string.question6_t4),
                answers = mutableListOf(
                    Answer(context.getString(R.string.answer_w1_q6_t4), false),
                    Answer(context.getString(R.string.answer_w2_q6_t4), false),
                    Answer(context.getString(R.string.answer_w3_q6_t4), false),
                    Answer(context.getString(R.string.answer_c_q6_t4), true)
                )
            ),
            Question(
                theme = Theme.PROGRAMACION,
                questionText = context.getString(R.string.question7_t4),
                answers = mutableListOf(
                    Answer(context.getString(R.string.answer_w1_q7_t4), false),
                    Answer(context.getString(R.string.answer_w2_q7_t4), false),
                    Answer(context.getString(R.string.answer_w3_q7_t4), false),
                    Answer(context.getString(R.string.answer_c_q7_t4), true)
                )
            ),
            Question(
                theme = Theme.PROGRAMACION,
                questionText = context.getString(R.string.question8_t4),
                answers = mutableListOf(
                    Answer(context.getString(R.string.answer_w1_q8_t4), false),
                    Answer(context.getString(R.string.answer_w2_q8_t4), false),
                    Answer(context.getString(R.string.answer_w3_q8_t4), false),
                    Answer(context.getString(R.string.answer_c_q8_t4), true)
                )
            ),
            Question(
                theme = Theme.PROGRAMACION,
                questionText = context.getString(R.string.question9_t4),
                answers = mutableListOf(
                    Answer(context.getString(R.string.answer_w1_q9_t4), false),
                    Answer(context.getString(R.string.answer_w2_q9_t4), false),
                    Answer(context.getString(R.string.answer_w3_q9_t4), false),
                    Answer(context.getString(R.string.answer_c_q9_t4), true)
                )
            ),
            Question(
                theme = Theme.PROGRAMACION,
                questionText = context.getString(R.string.question10_t4),
                answers = mutableListOf(
                    Answer(context.getString(R.string.answer_w1_q10_t4), false),
                    Answer(context.getString(R.string.answer_w2_q10_t4), false),
                    Answer(context.getString(R.string.answer_w3_q10_t4), false),
                    Answer(context.getString(R.string.answer_c_q10_t4), true)
                )
            ),
            Question(
                theme = Theme.ALGEBRA,
                questionText = context.getString(R.string.question1_t5),
                answers = mutableListOf(
                    Answer(context.getString(R.string.answer_w1_q1_t5), false),
                    Answer(context.getString(R.string.answer_w2_q1_t5), false),
                    Answer(context.getString(R.string.answer_w3_q1_t5), false),
                    Answer(context.getString(R.string.answer_c_q1_t5), true)
                )
            ),
            Question(
                theme = Theme.ALGEBRA,
                questionText = context.getString(R.string.question2_t5),
                answers = mutableListOf(
                    Answer(context.getString(R.string.answer_w1_q2_t5), false),
                    Answer(context.getString(R.string.answer_w2_q2_t5), false),
                    Answer(context.getString(R.string.answer_w3_q2_t5), false),
                    Answer(context.getString(R.string.answer_c_q2_t5), true)
                )
            ),
            Question(
                theme = Theme.ALGEBRA,
                questionText = context.getString(R.string.question3_t5),
                answers = mutableListOf(
                    Answer(context.getString(R.string.answer_w1_q3_t5), false),
                    Answer(context.getString(R.string.answer_w2_q3_t5), false),
                    Answer(context.getString(R.string.answer_w3_q3_t5), false),
                    Answer(context.getString(R.string.answer_c_q3_t5), true)
                )
            ),
            Question(
                theme = Theme.ALGEBRA,
                questionText = context.getString(R.string.question4_t5),
                answers = mutableListOf(
                    Answer(context.getString(R.string.answer_w1_q4_t5), false),
                    Answer(context.getString(R.string.answer_w2_q4_t5), false),
                    Answer(context.getString(R.string.answer_w3_q4_t5), false),
                    Answer(context.getString(R.string.answer_c_q4_t5), true)
                )
            ),
            Question(
                theme = Theme.ALGEBRA,
                questionText = context.getString(R.string.question5_t5),
                answers = mutableListOf(
                    Answer(context.getString(R.string.answer_w1_q5_t5), false),
                    Answer(context.getString(R.string.answer_w2_q5_t5), false),
                    Answer(context.getString(R.string.answer_w3_q5_t5), false),
                    Answer(context.getString(R.string.answer_c_q5_t5), true)
                )
            ),
            Question(
                theme = Theme.ALGEBRA,
                questionText = context.getString(R.string.question6_t5),
                answers = mutableListOf(
                    Answer(context.getString(R.string.answer_w1_q6_t5), false),
                    Answer(context.getString(R.string.answer_w2_q6_t5), false),
                    Answer(context.getString(R.string.answer_w3_q6_t5), false),
                    Answer(context.getString(R.string.answer_c_q6_t5), true)
                )
            ),
            Question(
                theme = Theme.ALGEBRA,
                questionText = context.getString(R.string.question7_t5),
                answers = mutableListOf(
                    Answer(context.getString(R.string.answer_w1_q7_t5), false),
                    Answer(context.getString(R.string.answer_w2_q7_t5), false),
                    Answer(context.getString(R.string.answer_w3_q7_t5), false),
                    Answer(context.getString(R.string.answer_c_q7_t5), true)
                )
            ),
            Question(
                theme = Theme.ALGEBRA,
                questionText = context.getString(R.string.question8_t5),
                answers = mutableListOf(
                    Answer(context.getString(R.string.answer_w1_q8_t5), false),
                    Answer(context.getString(R.string.answer_w2_q8_t5), false),
                    Answer(context.getString(R.string.answer_w3_q8_t5), false),
                    Answer(context.getString(R.string.answer_c_q8_t5), true)
                )
            ),
            Question(
                theme = Theme.ALGEBRA,
                questionText = context.getString(R.string.question9_t5),
                answers = mutableListOf(
                    Answer(context.getString(R.string.answer_w1_q9_t5), false),
                    Answer(context.getString(R.string.answer_w2_q9_t5), false),
                    Answer(context.getString(R.string.answer_w3_q9_t5), false),
                    Answer(context.getString(R.string.answer_c_q9_t5), true)
                )
            ),
            Question(
                theme = Theme.ALGEBRA,
                questionText = context.getString(R.string.question10_t5),
                answers = mutableListOf(
                    Answer(context.getString(R.string.answer_w1_q10_t5), false),
                    Answer(context.getString(R.string.answer_w2_q10_t5), false),
                    Answer(context.getString(R.string.answer_w3_q10_t5), false),
                    Answer(context.getString(R.string.answer_c_q10_t5), true)
                )
            )
        )
    }
}