package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity(), TextWatcher, View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private var editName : EditText? = null
    private var editAge : EditText? = null
    private var textName : TextView? = null
    private var textAge : TextView? = null
    private var buttonExecute : Button? = null
    private var name : String = ""
    private var age : Int = 0
    private var radioButtonF : RadioButton? = null
    private var radioButtonM : RadioButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editName = findViewById<EditText>(R.id.editText_Name)
        editAge = findViewById<EditText>(R.id.editText_Age)
        textName = findViewById<TextView>(R.id.textView_Name)
        textAge = findViewById<TextView>(R.id.textView_Age)
        radioButtonF = findViewById<RadioButton>(R.id.radioButtonF)
        radioButtonM = findViewById<RadioButton>(R.id.radioButtonM)
        buttonExecute = findViewById<Button>(R.id.button_Ejecutar)

        editName!!.addTextChangedListener(this)
        editAge!!.addTextChangedListener(this)
        radioButtonF!!.setOnCheckedChangeListener(this)
        radioButtonM!!.setOnCheckedChangeListener(this)
        buttonExecute!!.setOnClickListener(this)

        editName!!.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS

        operacion()
    }

    override fun afterTextChanged(p0: Editable?) {
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        name = editName?.text.toString()
        if  (name?.equals("")) editName!!.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS
        else editName!!.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_NORMAL
    }

    override fun onCheckedChanged(p0: CompoundButton?, p1: Boolean) {
        Toast.makeText(this,  "Ha seleccionado una opción", Toast.LENGTH_SHORT).show()
    }

    override fun onClick(p0: View?) {
        operacion()
    }

    private fun operacion() {
        name = editName?.text.toString()

        if (!editAge?.text.isNullOrEmpty()) age = Integer.valueOf(editAge?.text.toString())

        if  (name?.equals("")) editName!!.requestFocus()
        else {
            textName?.text = name
            if (editAge?.text.isNullOrEmpty()) editAge?.requestFocus()
            else textAge?.text = age.toString()
        }
    }

}
