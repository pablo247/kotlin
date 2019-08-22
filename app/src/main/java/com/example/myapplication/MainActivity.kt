package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.View
import android.widget.*
import androidx.annotation.IntegerRes

class MainActivity : AppCompatActivity(), TextWatcher, View.OnClickListener, CompoundButton.OnCheckedChangeListener,
    AdapterView.OnItemClickListener {

    private var editName : EditText? = null
    private var editAge : EditText? = null
    private var buttonExecute : Button? = null
    private var name : String = ""
    private var age : Int = 0
    private var radioButtonF : RadioButton? = null
    private var radioButtonM : RadioButton? = null
    private var lvsLista : ListView? = null

    private var num = 10
    private var count = 1
    private var nombre : Array<String>? = null
    private var edad : Array<Int>? = null
    private var sexo: Array<String>? = null

    private var genero = ""

    private var pos = 0
    private var action = "insert"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editName = findViewById<EditText>(R.id.editText_Name)
        editAge = findViewById<EditText>(R.id.editText_Age)
        radioButtonF = findViewById<RadioButton>(R.id.radioButtonF)
        radioButtonM = findViewById<RadioButton>(R.id.radioButtonM)
        lvsLista = findViewById(R.id.lista)
        buttonExecute = findViewById<Button>(R.id.button_Ejecutar)

        lvsLista!!.onItemClickListener = this
        editName!!.addTextChangedListener(this)
        editAge!!.addTextChangedListener(this)
        /*radioButtonF!!.setOnCheckedChangeListener(this)
        radioButtonM!!.setOnCheckedChangeListener(this)*/
        radioButtonF!!.setOnClickListener(this)
        radioButtonM!!.setOnClickListener(this)
        buttonExecute!!.setOnClickListener(this)

        editName!!.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS

        nombre = Array(num) {""}
        edad = Array(num) {0}
        sexo = Array(num) {""}

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
        /*when (p0!!.id) {
            R.id.radioButtonF ->
                if (p1) Toast.makeText(this,  "Ha seleccionado Femenino", Toast.LENGTH_SHORT).show()
            R.id.radioButtonM ->
                if (p1) Toast.makeText(this, "Ha seleccionado Masculino", Toast.LENGTH_SHORT).show()
        }*/
    }

    override fun onClick(p0: View?) {
        when (p0!!.id) {
            R.id.radioButtonF ->
                genero = "Femenino"
            R.id.radioButtonM ->
                genero = "Masculino"
            R.id.button_Ejecutar ->
                operacion()
        }
    }

    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        editName!!.setText(nombre?.get(p2))
        editAge!!.setText(edad!!.get(p2).toString())
        when (sexo!!.get(p2)) {
            "Masculino" -> {
                radioButtonM!!.isChecked = true
                genero = "Masculino"
            }
            "Femenino" -> {
                radioButtonF!!.isChecked = true
                genero = "Femenino"
            }

        }
        pos = p2
        action = "update"
    }

    private fun operacion() {
        name = editName?.text.toString()

        if (!editAge?.text.isNullOrEmpty()) age = Integer.valueOf(editAge?.text.toString())

        if  (name?.equals("")) editName!!.requestFocus()
        else {
            if (editAge?.text.isNullOrEmpty()) editAge?.requestFocus()
            else {
                if (radioButtonM!!.isChecked || radioButtonF!!.isChecked) {
                    when (action) {
                        "insert" -> addData()
                        "update" -> updateData()
                    }
                }
            }
        }
    }

    private fun addData() {
        val nombres : Array<String>
        for (i in 0 until num-1) {
            if (nombre?.get(i).equals("")) {
                nombre?.set(i, name)
                edad?.set(i, age)
                sexo?.set(i, genero)
                nombres = Array<String>(count) {""}
                for (j in 0..i) {
                    nombres[j] = nombre?.get(j) as String
                }

                val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nombres)

                lvsLista!!.adapter = adapter

                count++
                break
            }
        }
        editName!!.setText("")
        editAge!!.setText("")
    }

    private fun updateData() {
        count = 1
        for (i in 0 until num) {
            if (nombre?.get(i) != "") {
                if (pos === i) {
                    nombre?.set(i, name)
                    edad?.set(i, Integer.valueOf(age))
                    sexo?.set(i, genero)
                }

                val listName = Array(count){""}
                for (j in 0 until count) {
                    listName?.set(j, nombre?.get(j) as String)
                }
                count++
                val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listName)
                lvsLista!!.adapter = adapter

            }
        }
        editName!!.setText("")
        editAge!!.setText("")
    }

}
