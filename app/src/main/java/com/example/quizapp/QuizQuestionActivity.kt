package com.example.quizapp

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_quiz_question.*

class QuizQuestionActivity : AppCompatActivity(), View.OnClickListener {

    private var mCurrentPosition: Int = 1
    lateinit private var mQuestionsList: ArrayList<Question>
    private var mSelectedOptionPosition: Int = 0
    private var total: Int = 0
    private var mUserName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_question)

        mUserName = intent.getStringExtra(Constants.USER_NAME)

        mQuestionsList = Constants.getQuestions()
        setQuestion()

        tv_option_one.setOnClickListener(this)
        tv_option_two.setOnClickListener(this)
        tv_option_three.setOnClickListener(this)
        tv_option_four.setOnClickListener(this)
    }

    private fun setQuestion(){

        val currentQuestion = mQuestionsList!![mCurrentPosition-1]
        //isto i kao mQuestionsList.get(mCurrentPosition-1)

        defaultOptionsView()

        if(mCurrentPosition == mQuestionsList!!.size){
            btn_submit.text = "FINISH"
        }
        else{
            btn_submit.text = "SUBMIT"
        }

        progressBar.progress = mCurrentPosition
        tv_progress.text = "${mCurrentPosition}" +  "/" +  "${progressBar.max}"

        tv_question.text = currentQuestion.question
        iv_image.setImageResource(currentQuestion.image)
        tv_option_one.text = currentQuestion.op1
        tv_option_two.text = currentQuestion.op2
        tv_option_three.text = currentQuestion.op3
        tv_option_four.text = currentQuestion.op4
        btn_submit.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.tv_option_one ->{
                selectedOptionView(tv_option_one,1)
            }
            R.id.tv_option_two ->{
                selectedOptionView(tv_option_two,2)
            }
            R.id.tv_option_three ->{
                selectedOptionView(tv_option_three,3)
            }
            R.id.tv_option_four ->{
                selectedOptionView(tv_option_four,4)
            }
            R.id.btn_submit ->{
                if (mSelectedOptionPosition == 0) {
                    mCurrentPosition++
                    when {
                        mCurrentPosition <= mQuestionsList!!.size -> {
                            if (mCurrentPosition > 1) {
                                setQuestion()
                            }
                        }else -> {
                            Toast.makeText(
                                this,
                                "You have successfully completed the Quiz",
                                Toast.LENGTH_SHORT).show()
                            val intent = Intent(this,ResultActivity::class.java)
                            intent.putExtra(Constants.USER_NAME, mUserName)
                            intent.putExtra(Constants.CORRECT_ANSWERS, total)
                            intent.putExtra(Constants.TOTAL_QUESTIONS, mQuestionsList!!.size)
                            startActivity(intent)
                            finish()
                        }
                    }
                }
                else{
                    val question = mQuestionsList!![mCurrentPosition-1]
                    if(question.correct != mSelectedOptionPosition){
                        //pogresan odgovor
                        answerView(mSelectedOptionPosition,R.drawable.wrong_ans)
                    }
                    else{
                        total++
                    }
                    answerView(question.correct,R.drawable.correct_ans)
                    if(mCurrentPosition == mQuestionsList!!.size){
                        //dosli do kraja
                        btn_submit.text = "FINISH"
                    }
                    else{
                        btn_submit.text = "GO TO NEXT QUESTION"
                    }
                    mSelectedOptionPosition = 0
                }
            }
            //ovo su kao sitne lambde da ne bi pisao i provjeravao rucno svaki odabrani option pa poredio sa onima u textviews
        }
    }

    private fun defaultOptionsView(){
        val options = ArrayList<TextView>()
        options.add(0,tv_option_one)
        options.add(1,tv_option_two)
        options.add(2,tv_option_three)
        options.add(3,tv_option_four)

        //setujemo svaku od opcija na defaultni izgled kako bi ona selektovana mogla dobiti specijalni
        for(option in options){
            option.setTextColor(Color.parseColor("#363A43"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this,
                R.drawable.default_option_border_bg
                //sa R pristupas svom resource odnosno app/src/main/res
            )
        }
    }

    private fun selectedOptionView(tv: TextView, selectedOptionNumber: Int){
        defaultOptionsView()
        mSelectedOptionPosition = selectedOptionNumber
        tv.setTextColor(Color.parseColor("#363A43"))
        tv.typeface = Typeface.DEFAULT_BOLD
        tv.background = ContextCompat.getDrawable(
            this,
            R.drawable.selected_option_border_bg
        )
    }

    private fun answerView(answer: Int, drawableView: Int){
        Log.i("Odgovor crtam view: ","${answer},${drawableView}")
        when(answer){
            1->{
                tv_option_one.background = ContextCompat.getDrawable(this, drawableView)
                //drawableView je onaj drawable koji smo proslijedili (wrong/correct ans view)
            }
            2->{
                tv_option_two.background = ContextCompat.getDrawable(this, drawableView)
            }
            3->{
                tv_option_three.background = ContextCompat.getDrawable(this, drawableView)
            }
            4->{
                tv_option_four.background = ContextCompat.getDrawable(this, drawableView)
            }
        }
    }
}