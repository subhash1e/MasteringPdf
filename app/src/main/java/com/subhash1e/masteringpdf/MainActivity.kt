package com.subhash1e.masteringpdf

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.pdf.PdfDocument
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.File
import java.io.FileOutputStream

class MainActivity : AppCompatActivity() {
    lateinit var tvName:TextView
    lateinit var btnDownload:Button
    lateinit var rvSubjectMarks:RecyclerView
    lateinit var tvTotalMarks:TextView
    lateinit var view:LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()

        val subjectMarks = listOf<SubjectDataClass>(
            SubjectDataClass("Mathematics",60),
            SubjectDataClass("Physics",90),
            SubjectDataClass("Chemistry",65),
            SubjectDataClass("Biology",96),
            SubjectDataClass("English",70),
        )
        var totMarks = 0
        subjectMarks.forEach {
            totMarks += it.sMarks
        }
        val pdfData = PdfRowDataClass("Subhash Kumar", totMarks, subjectMarks)

        //adapter for rv
        val adapter = pdfRowAdapter(pdfData.sMarkDetails)



        //give view their values
        tvName.text = pdfData.stdName
        tvTotalMarks.text = totMarks.toString()
        rvSubjectMarks.adapter = adapter
        rvSubjectMarks.layoutManager = LinearLayoutManager(this)

        //draw on bitmpa

        //1st take screen dimensions

      /*  val displayMetrics = DisplayMetrics()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            this.display?.getRealMetrics(displayMetrics)
            displayMetrics.densityDpi
        }
        else{
            this.windowManager.defaultDisplay.getMetrics(displayMetrics)
        }
        view.measure(
            View.MeasureSpec.makeMeasureSpec(
                displayMetrics.widthPixels, View.MeasureSpec.EXACTLY
            ),
            View.MeasureSpec.makeMeasureSpec(
                displayMetrics.heightPixels, View.MeasureSpec.EXACTLY
            )
        )*/
btnDownload.setOnClickListener {
    try {
        view.layout(0,0,595,842)
        val bitMap = Bitmap.createBitmap(view.measuredWidth,view.measuredHeight,Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitMap)
        view.draw(canvas)

        // now attatch bitmpa to our df
        val pdfDocument = PdfDocument()
        val pageInfo = PdfDocument.PageInfo.Builder(595, 842, 1).create()
        // call startPage for start drawing
        val page = pdfDocument.startPage(pageInfo)
        page.canvas.drawBitmap(bitMap, 0F, 0F, null)
        pdfDocument.finishPage(page)

        // now save inside memory

        val filePath = File(this.getExternalFilesDir(null),"bitMapReport.pdf")
        println(filePath.path)
        pdfDocument.writeTo(FileOutputStream(filePath))
        pdfDocument.close()
    }catch (e:Exception){
        println(e.message)
    }

}



    }

    private fun initViews() {
        tvName = findViewById(R.id.tvName)
        tvTotalMarks = findViewById(R.id.tvTotalMarks)
        btnDownload = findViewById(R.id.btnGenerate)
        rvSubjectMarks = findViewById(R.id.rv_subject_marks)
        view =findViewById(R.id.linearLayout)
    }
}