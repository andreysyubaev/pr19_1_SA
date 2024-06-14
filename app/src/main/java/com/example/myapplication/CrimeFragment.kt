package com.example.myapplication

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import android.util.Log

data class Crime(var title: String = "", var date: String = "", var isSolved: Boolean = false)

class CrimeFragment : Fragment() {

    private lateinit var crime: Crime
    private lateinit var titleField: EditText
    private lateinit var dateButton: Button
    private lateinit var solvedCheckBox: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        crime = Crime()
        Log.d("CrimeFragment", "onCreate called")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_crime, container, false)
        titleField = view.findViewById(R.id.crime_title) as EditText
        dateButton = view.findViewById(R.id.crime_date) as Button
        solvedCheckBox = view.findViewById(R.id.crime_solved) as CheckBox

        dateButton.apply {
            text = crime.date
            isEnabled = false
        }
        Log.d("CrimeFragment", "onCreateView called")
        return view
    }

    override fun onStart() {
        super.onStart()
        Log.d("CrimeFragment", "onStart called")
        val titleWatcher = object : TextWatcher {
            override fun beforeTextChanged(sequence: CharSequence?, start: Int, count: Int, after: Int) {
                // пусто
            }

            override fun onTextChanged(sequence: CharSequence?, start: Int, before: Int, count: Int) {
                if (!sequence.isNullOrBlank()) {
                    crime.title = sequence.toString()
                } else {
                    Toast.makeText(activity, "Title не может быть пустым", Toast.LENGTH_SHORT).show()
                }
            }

            override fun afterTextChanged(sequence: Editable?) {
                // пусто
            }
        }

        titleField.addTextChangedListener(titleWatcher)
        solvedCheckBox.apply {
            setOnCheckedChangeListener { _, isChecked -> crime.isSolved = isChecked }
        }
    }
}

