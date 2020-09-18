package com.example.quizapp

import java.util.*
import kotlin.collections.ArrayList

object Constants {

    const val USER_NAME: String = "user_name"
    const val TOTAL_QUESTIONS: String = "total_question"
    const val CORRECT_ANSWERS: String = "correct_answers"

    fun getQuestions(): ArrayList<Question> {
        val list = ArrayList<Question>()
        var q = Question(1,R.drawable.ic_flag_of_argentina,"Argentina","Germany","Catalunya","Croatia",1)
        list.add(q)
        q = Question(2,R.drawable.ic_flag_of_australia,"Armenia","Bolivia","Australia","New Zealand",3)
        list.add(q)
        q = Question(3,R.drawable.ic_flag_of_belgium,"Botswana","Luxembourg","Kenya","Belgium",4)
        list.add(q)
        q = Question(4,R.drawable.ic_flag_of_brazil,"Brazil","Uruguay","Jamaica","Kuwait",1)
        list.add(q)
        q = Question(5,R.drawable.ic_flag_of_denmark,"Sweden","Denmark","Finland","Norway",2)
        list.add(q)
        q = Question(6,R.drawable.ic_flag_of_fiji,"Saint Kitts and Newis","Papua New Guinea","Fiji","Australia",3)
        list.add(q)
        q = Question(7,R.drawable.ic_flag_of_germany,"Germany","Poland","Netherlands","Russia",1)
        list.add(q)
        q = Question(8,R.drawable.ic_flag_of_india,"Pakistan","Sri Lanka","Nepal","India",4)
        list.add(q)
        q = Question(9,R.drawable.ic_flag_of_kuwait,"Jordan","Sinisha said 'Hello!'","Abu Dhabi","Syria",2)
        list.add(q)
        q = Question(10,R.drawable.ic_flag_of_new_zealand,"New Zealand","Australia","Fiji","Croatia",1)
        list.add(q)
        list.shuffle()
        return list
    }
}