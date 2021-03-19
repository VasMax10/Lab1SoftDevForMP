package com.example.lab1softdevformp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.io.*


class MainActivity : AppCompatActivity() {

    var encodedText = ""
    var decodedText = ""
    var freq = ArrayList<Pair<String, String>>();
    var codes = ArrayList<Pair<String, String>>();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun classicHuffman(view : View)
    {
        val text = textToEncode.text.toString()

        try {
            val huf = HuffmanClassic(text)
            freq = huf.getUniqueChars()
            codes = huf.createCodes(freq)
            encodedText = huf.encode()
            decodedText = huf.decode()
            val toast = Toast.makeText(this, "Text was encoded", Toast.LENGTH_SHORT)
            toast.show()
        } catch (e: Exception) {
            val toast = Toast.makeText(this, "Text was NOT encoded", Toast.LENGTH_SHORT)
            toast.show()
        }
    }

    fun modHuffman(view : View)
    {
        try {
            val text = textToEncode.text.toString()
            val huf = HuffmanMod(text)
            freq = huf.getUniqueChars()
            codes = huf.createCodes(freq)
            encodedText = huf.encode()
            decodedText = huf.decode()
            val toast = Toast.makeText(this, "Text was encoded", Toast.LENGTH_SHORT)
            toast.show()
        } catch (e: Exception) {
            val toast = Toast.makeText(this, "Text was NOT encoded", Toast.LENGTH_SHORT)
            toast.show()
        }
    }

    fun openFile (view : View)
    {
        val intent = Intent()
            .setType("*/*")
            .setAction(Intent.ACTION_GET_CONTENT)

        startActivityForResult(Intent.createChooser(intent, "Select a file"), 111)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 111 && resultCode == RESULT_OK) {
            val selectedFile = data?.data //The uri with the location of the file

            if (selectedFile != null) {
                val input = readTextFromUri(selectedFile)
                textToEncode.setText(input)
            }
        }
    }

    @Throws(IOException::class)
    private fun readTextFromUri(uri: Uri): String {
        val stringBuilder = StringBuilder()
        contentResolver.openInputStream(uri)?.use { inputStream ->
            BufferedReader(InputStreamReader(inputStream)).use { reader ->
                var line: String? = reader.readLine()
                while (line != null) {
                    stringBuilder.append(line + "\n")
                    line = reader.readLine()
                }
            }
        }
        return stringBuilder.toString()
    }

    fun showTableFreq (view : View)
    {
        val tableIntent = Intent(this,TableResult::class.java)

        val listKeys = ArrayList<String>();
        val listValues = ArrayList<String>();

        for (x in freq)
        {
            listKeys.add(x.first)
            listValues.add(x.second)
        }

        tableIntent.putExtra(TableResult.LIST_KEYS, listKeys)
        tableIntent.putExtra(TableResult.LIST_VALUES, listValues)
        tableIntent.putExtra(TableResult.SECOND_COLUMN, "Frequencies")

        startActivity(tableIntent)
    }

    fun showTableCodes (view : View)
    {
        val tableIntent = Intent(this,TableResult::class.java)

        val listKeys = ArrayList<String>();
        val listValues = ArrayList<String>();

        for (x in codes)
        {
            listKeys.add(x.first)
            listValues.add(x.second)
        }

        tableIntent.putExtra(TableResult.LIST_KEYS, listKeys)
        tableIntent.putExtra(TableResult.LIST_VALUES, listValues)
        tableIntent.putExtra(TableResult.SECOND_COLUMN, "Codes")

        startActivity(tableIntent)
    }

    fun showEncodedText (view : View)
    {
        val resIntent = Intent(this,ResultActivity::class.java)
        resIntent.putExtra(ResultActivity.INPUT_TEXT, textToEncode.text.toString())
        resIntent.putExtra(ResultActivity.ENCODED_TEXT, encodedText)
        resIntent.putExtra(ResultActivity.DECODED_TEXT, decodedText)

        startActivity(resIntent)
    }

    fun saveToFile( view : View)
    {
        try
        {
            val file = File("/storage/emulated/0/Download/", "EncodedText.txt")
            val writer = PrintWriter(file)
            writer.print(encodedText)
            writer.close()
            val toast = Toast.makeText(this, "File 'EncodedText.txt' successfully saved to Download", Toast.LENGTH_SHORT)
            toast.show()
        }
        catch (e : Exception)
        {
            val toast = Toast.makeText(this, "Error in saving 'Encoded Text'", Toast.LENGTH_SHORT)
            toast.show()
        }

        try
        {
            val file = File("/storage/emulated/0/Download/", "Huffman's table.txt")
            val writer = PrintWriter(file)
            for (x in codes)
            {
                writer.print(x.first + " " + x.second + "\n")
            }
            writer.close()
            val toast = Toast.makeText(this, "File 'Huffman's table.txt' successfully saved to Download", Toast.LENGTH_SHORT)
            toast.show()
        }
        catch (e : Exception)
        {
            val toast = Toast.makeText(this, "Error in saving 'Huffman's table'", Toast.LENGTH_SHORT)
            toast.show()
        }

    }

    fun showChart(view : View)
    {
        val intent = Intent(this,HistogramActivity::class.java)

        val charList = ArrayList<String>()
        val freqList = ArrayList<String>()

        for (i in freq.indices)
        {
            charList.add(freq[i].first)
            freqList.add(freq[i].second)
        }
        intent.putExtra("char", charList)
        intent.putExtra("freq", freqList)

        startActivity(intent)
    }

    fun clearTextView(view: View)
    {
        textToEncode.setText("")
    }

    fun openAbout(view: View)
    {
        val intent = Intent(this,AboutActivity::class.java)
        startActivity(intent)
    }
}