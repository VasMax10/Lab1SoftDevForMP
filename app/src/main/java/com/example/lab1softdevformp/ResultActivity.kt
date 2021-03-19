package com.example.lab1softdevformp

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {

    var inputText = ""
    var encodedText = ""
    var decodedText = ""

    companion object
    {
        const val RESULT_TYPE = "result_type"
        const val INPUT_TEXT = "text"
        const val ENCODED_TEXT = "encoded_text"
        const val DECODED_TEXT = "decoded_text"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        showData()
    }

    fun showData()
    {
        inputText = intent.getStringExtra(INPUT_TEXT).toString()
        encodedText = intent.getStringExtra(ENCODED_TEXT).toString()
        decodedText = intent.getStringExtra(DECODED_TEXT).toString()

        viewInputText.setText(inputText)
        viewEncodedText.setText(encodedText)
        viewDecodedText.setText(decodedText)
        viewInputText.keyListener = null;
        viewEncodedText.keyListener = null;
        viewDecodedText.keyListener = null;

    }

    fun checkCorrect(view : View)
    {
        if (inputText == decodedText)
        {
            textViewCheckInfo.setText("Correct")
            checkButton.setBackgroundColor(Color.GREEN)
        }
        else
        {
            textViewCheckInfo.setText("Incorrect")
            checkButton.setBackgroundColor(Color.RED)
        }
    }
}