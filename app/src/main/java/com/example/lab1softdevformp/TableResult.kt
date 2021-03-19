package com.example.lab1softdevformp

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class TableResult : AppCompatActivity() {

    companion object
    {
        const val LIST_KEYS = "list_keys"
        const val LIST_VALUES = "list_values"
        const val SECOND_COLUMN = "second_column"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_table_result)
        fillTable()
    }

    private fun fillTable()
    {
        val listKeys = intent.getStringArrayListExtra(LIST_KEYS)
        val listValues = intent.getStringArrayListExtra(LIST_VALUES)

        if (listKeys != null && listValues != null) {
            val list = ArrayList<Pair<String, String>>()

            for (i in 0 until listKeys.size)
            {
                list.add(Pair(listKeys[i], listValues[i]))
            }

            val sortedList = list.sortedWith(compareByDescending { it.second.length } )

            val tableLayout = findViewById<TableLayout>(R.id.resTab)
            val tableRowParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT)

            var tableRow = TableRow(this)
            tableRow.layoutParams = tableRowParams

            var tv1 = TextView(this)
            tv1.text = "Symbol"
            var tv2 = TextView(this)
            tv2.text = intent.getStringExtra(SECOND_COLUMN)

            var cellParams = TableRow.LayoutParams( 0, TableRow.LayoutParams.MATCH_PARENT)
            cellParams.weight = 1F
            tv1.gravity = Gravity.CENTER
            tv1.textSize = 22F
            tv1.setBackgroundColor(Color.DKGRAY)
            tv1.setTextColor(Color.WHITE)
            tv1.layoutParams = cellParams

            cellParams.weight = 2F
            tv2.gravity = Gravity.CENTER
            tv2.textSize = 22F
            tv2.setBackgroundColor(Color.DKGRAY)
            tv2.setTextColor(Color.WHITE)
            tv2.layoutParams = cellParams

            tableRow.addView(tv1)
            tableRow.addView(tv2)

            tableLayout.addView(tableRow)

            for (i in sortedList.indices) {

                tableRow = TableRow(this)
                tableRow.layoutParams = tableRowParams

                tv1 = TextView(this)
                tv1.text = sortedList[i].first
                tv2 = TextView(this)
                tv2.text = sortedList[i].second

                cellParams = TableRow.LayoutParams( 0, TableRow.LayoutParams.MATCH_PARENT)
                cellParams.weight = 1F
                tv1.gravity = Gravity.CENTER
                tv1.textSize = 22F
                tv1.layoutParams = cellParams

                tv2.gravity = Gravity.RIGHT
                tv2.textSize = 22F
                cellParams.weight = 2F
                tv2.layoutParams = cellParams

                if (i % 2 == 1)
                {
                    tv1.setBackgroundColor(Color.GRAY)
                    tv1.setTextColor(Color.BLACK)

                    tv2.setBackgroundColor(Color.GRAY)
                    tv2.setTextColor(Color.BLACK)
                }
                else
                {
                    tv1.setBackgroundColor(Color.LTGRAY)
                    tv1.setTextColor(Color.BLACK)

                    tv2.setBackgroundColor(Color.LTGRAY)
                    tv2.setTextColor(Color.BLACK)
                }

                tableRow.addView(tv1)
                tableRow.addView(tv2)

                tableLayout.addView(tableRow)
            }
        }
    }



}