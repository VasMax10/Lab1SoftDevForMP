package com.example.lab1softdevformp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.anychart.AnyChart
import com.anychart.AnyChartView
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import kotlinx.android.synthetic.main.activity_histogram.*

class HistogramActivity : AppCompatActivity() {

    lateinit var anyChartView:AnyChartView

    companion object
    {
        var charList = ArrayList<String>()
        var freqList = ArrayList<String>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_histogram)

        charList = intent.getStringArrayListExtra("char") as ArrayList<String>
        freqList = intent.getStringArrayListExtra("freq") as ArrayList<String>

        anyChartView = any_chart_view

        setupChart()
    }

    fun setupChart()
    {

        val column = AnyChart.vertical()

        val dataEntries:ArrayList<DataEntry> = ArrayList()

        for (i in charList.indices)
        {
            if (charList[i] == "\n")
                dataEntries.add(ValueDataEntry("Enter", freqList[i].toInt()))
            else
                dataEntries.add(ValueDataEntry(charList[i], freqList[i].toInt()))
            println(charList[i] + " " + freqList[i].toInt())
        }
        println(dataEntries.size)
        column.data(dataEntries)
        anyChartView.setChart(column)
    }
}