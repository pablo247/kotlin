package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), TextWatcher {

    private var editName : EditText? = null
    private var editAge : EditText? = null
    private var name : String = ""
    private var age : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editName = findViewById(R.id.editText_Name)
        editAge = findViewById(R.id.editText_Age)

        editName!!.addTextChangedListener(this)
        editAge!!.addTextChangedListener(this)
    }

    override fun afterTextChanged(p0: Editable?) {
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        name = editName?.text.toString()

        if (!editAge?.text.isNullOrEmpty())
            age= Integer.valueOf( editAge?.text.toString() )

        textView_Name.text = name
        textView_Age.text = age.toString()
        // Toast.makeText(this, p0,Toast.LENGTH_SHORT).show()
    }

}
